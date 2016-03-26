package br.com.depro.mugetsu.carga.service.ann;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
import br.com.depro.fw.typezero.infrastructure.utils.ExtracaoUtils;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.carga.handler.ann.FormatoANN;
import br.com.depro.mugetsu.carga.handler.ann.GeneroANN;
import br.com.depro.mugetsu.carga.handler.ann.IdiomaANNEnum;
import br.com.depro.mugetsu.carga.utils.ImportacaoUtils;
import br.com.depro.mugetsu.core.media.service.AlternativeNameService;
import br.com.depro.mugetsu.core.media.service.GeneroService;
import br.com.depro.mugetsu.core.media.service.MediaService;
import br.com.depro.mugetsu.core.media.service.TagService;
import br.com.depro.mugetsu.model.Anexo;
import br.com.depro.mugetsu.model.Anexo.TipoAnexo;
import br.com.depro.mugetsu.model.LocaleEnum;
import br.com.depro.mugetsu.model.media.AlternativeName;
import br.com.depro.mugetsu.model.media.Genero;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.Tag;
import br.com.depro.mugetsu.model.media.util.FormatoMedia;
import br.com.depro.mugetsu.model.media.util.OrigemEnum;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 05.08.2012
 */
@Service
public class ImportarANNServiceImpl implements ImportarANNService {

    private static final String PATH_OUTPUT_TXT = "dir.path.output.carga.anime";
    private static final String PATH_OUTPUT_FORMATO = "dir.path.output.carga";
    private static final String PATH_OUTPUT_IMG = "dir.path.output.img.exibicao";
    private static final String PREFIXO_ANN = "-animenewsnetwork";
    private static final String URL_BASE_ANN = "http://www.animenewsnetwork.com";
    private static final String URL_ENCYCLOPEDIA = URL_BASE_ANN + "/encyclopedia/anime.php?id=";
    private static final int TIPO_EPI = 0;
    private static final int TIPO_VOL = 1;
    private static final int TIPO_CAP = 1;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private AlternativeNameService alternativeNameService;
    @Autowired
    private TagService tagService;
    @Autowired
    private PropConfig propConfig;
    private Map<String, Genero> mapGeneros;
    private Map<String, Tag> mapTags;
    
	public Media atualizarDadosMedia(Media media) throws ApplicationException {
		try {
			ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, PATH_OUTPUT_TXT, "animenewsnetwork", true);
			extracaoUtils.doRequest(URL_ENCYCLOPEDIA + media.getIdOrigem(), media.getIdOrigem(), false);
			
			List<String> linhas = extracaoUtils.obterLinhas();
			Media mediaNova = extrairDadosMedia(linhas);
			
			if (mediaNova != null) {
				mediaNova.getNomes().addAll(extrairTitulosAlternativos(linhas));
				mediaNova.setSinopse(extrairSinopse(linhas));
	
	            extrairGeneroETag(linhas, mediaNova);
	
	            if (FormatoMedia.MANGA.equals(mediaNova.getFormatoMedia())) {
	            	mediaNova.setQuantidadeVolumes(extrairQuantidades(linhas, media.getFormatoMedia()));
	            } else if (!FormatoMedia.FILME.equals(mediaNova.getFormatoMedia())) {
	            	mediaNova.setQuantidadeEpisodios(extrairQuantidades(linhas, media.getFormatoMedia()));
	            }
	            
	            if (media.getPathImagem() == null) {
	            	extrairImagem(linhas, mediaNova);
	            }
	            media = ImportacaoUtils.mergeMedia(media, mediaNova);
	            compararImagens(media, mediaNova);
	            
	            //mediaService.salvar(media);
			}
			
		} catch (ApplicationException e) {
        } catch (IOException e) {
		}
		return media;
	}

    public void extrairHTML(int quantidadeInteracao) throws ApplicationException {
        propConfig = (PropConfig) TypezeroSpringUtils.getBean(PropConfig.class);
        ExtracaoUtils importacao = new ExtracaoUtils(propConfig, PATH_OUTPUT_TXT, "animenewsnetwork", false);
        for (long i = 1; i <= quantidadeInteracao; i++) {
            try {
                importacao.doRequest(URL_ENCYCLOPEDIA + i, i, false);
            } catch (ApplicationException e) {
                importacao.getResumo().addQuantidadeErro();
            }
        }
        importacao.getResumo().exibirResumo();
    }

    /**
     *
     * @param quantidadeInteracao
     * @throws ApplicationException
     */
    public void importarFromCarga(int quantidadeInteracao) throws ApplicationException {
        for (long i = 0; i <= quantidadeInteracao; i++) {
            Media media = null;
            try {
            	ExtracaoUtils importacao = new ExtracaoUtils(propConfig, PATH_OUTPUT_TXT, + i + PREFIXO_ANN, false);
                List<String> linhas = importacao.obterLinhas();
                media = extrairDadosMedia(linhas);
                if (media != null /*&& !mediaTituloService.validarMediaTitulos(media)*/) {
                	media.setIdOrigem(i + "");
                    media.getNomes().addAll(extrairTitulosAlternativos(linhas));
                    media.setSinopse(extrairSinopse(linhas));

                    extrairGeneroETag(linhas, media);

                    switch (media.getFormatoMedia()) {
                    	case MANGA:
                    		break;
                    	case ANIME:
                    		media.setEpisodios(extrairEpisodios(i));
                    		break;
                    	case FILME:
                    		break;
                    	case DORAMA:
                    		break;
                    	case TOKUSATSU:
                    		break;
                    	case SERIE:
                    		break;
                    		
                    }
                    
                    if (FormatoMedia.MANGA.equals(media.getFormatoMedia())) {
                        media.setQuantidadeVolumes(extrairQuantidades(linhas, media.getFormatoMedia()));
                    } else if (!FormatoMedia.FILME.equals(media.getFormatoMedia())) {
                        media.setQuantidedaEpisodios(extrairQuantidades(linhas, media.getFormatoMedia()));
                    }
                    
//                    media.setBroadcasts(extrairBroadcast(linhas));
                    //extrairImagem(linhas, media);
                    //this.mediaService.salvar(media);
                    
                    for (Tag tag : media.getTags()) {
                    	mapTags.put(tag.getTag().toLowerCase(), tag);
                    }
                }
            } catch (Exception e) {
                if (media != null) {
//                    super.logger.error(media.getNomePrincipal() + " - " + media.getIdOrigem() + " = " + e.getMessage());
                } else {
//                    super.logger.error(e.getMessage());
                }
            }
        }
    }
    
    private List<Episodio> extrairEpisodios(long id) throws ApplicationException, ParseException {
		List<Episodio> episodios = new ArrayList<Episodio>();
		ExtracaoUtils importacao = new ExtracaoUtils(propConfig);
        List<String> linhas = importacao.doRequest(URL_ENCYCLOPEDIA + id + "&page=25");
        
        final int NADA = 0;
        final int BROADCAST = 1;
        final int SEQUENCIA = 2;
        final int NOMES = 4;
        
        int tipoConteudo = NADA;
        boolean inTable = false;
        Episodio episodio = new Episodio();
        for (String linha : linhas) {
        	if (linha.contains("class=\"episode-list\"") || inTable) {
        		inTable = true;
        		if (linha.contains("<td class=\"d\">")) {
        			tipoConteudo = BROADCAST;
        		} 
        		
        		if (linha.contains("class=\"n\"")) {
        			tipoConteudo = SEQUENCIA;
        		}
        		
        		if (linha.contains("valign=\"top\"")) {
        			tipoConteudo = NOMES;
        		}
        		
        		Matcher matcher = null;
        		switch (tipoConteudo) {
        			case BROADCAST:
        				matcher = Pattern.compile("<div>(.*.)</div>").matcher(linha);
        				if (matcher.find()) {
        					EpisodiosBroadcast broadcast = new EpisodiosBroadcast();
        					broadcast.setLacamento(new SimpleDateFormat("yyyy-MM-dd").parse(matcher.group(1)));
        					broadcast.setLocalidade(obterLocalidadeTitulo(IdiomaANNEnum.Japanese.name()));
        					episodio.getBroadcasts().add(broadcast);
        					tipoConteudo = NADA;
        				}
        				break;
        			case SEQUENCIA:
        				matcher = Pattern.compile("\\d+").matcher(linha);
        				if (matcher.find()) {
        					episodio.setNumero(Integer.valueOf(matcher.group()));
        					tipoConteudo = NADA;
        				}
        				break;
        			case NOMES:
        				matcher = Pattern.compile("<div>(.*.)</div>").matcher(linha);
        				if (matcher.find()) {
        					EpisodioNome nome = new EpisodioNome();
        					nome.setPrincipal(episodio.getNomes().isEmpty());
        					nome.setNome(matcher.group(1));
        					episodio.getNomes().add(nome);
        				}
        			case NADA:
        				matcher = Pattern.compile("</tr>|</table>").matcher(linha);
        				if (matcher.find()) {
        					episodios.add(episodio);
        					if (linha.contains("tr")) {
	        					episodio = new Episodio();
        					} else {
        						break;
        					}
        				}
        		}
        	}
        }
		return episodios;
	}

	/**
     * @see ImportarANNService#extrairFormatosMediasANN(int)
     */
    public void extrairFormatosMediasANN(int quantidadeInterecoes) {
        Set<String> tipos = new HashSet<String>();
        for (long i = 0; i <= quantidadeInterecoes; i++) {
            try {
                List<String> linhas = new ArrayList<String>();
                BufferedReader bf = new BufferedReader(new FileReader(propConfig.get(PATH_OUTPUT_TXT) + i + PREFIXO_ANN + ".txt"));
                while (bf.ready()) {
                    linhas.add(bf.readLine());
                }
                bf.close();

                for (String linha : linhas) {
                    if (linha.indexOf("page_header") != -1) {
                        Matcher matcher = executeRegex("<h1 id=\"page_header\">(.*.)\\((.*.)\\)</h1>", linha);
                        if (matcher.find()) {
                            String formato = matcher.group(2).trim().replaceAll("\\.", "_").replaceAll(" ", "").replaceAll("/", "").replaceAll("-", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("&amp;", "").replaceAll("&", "").replaceAll("'", "").replaceAll(",", "").replaceAll("\\?", "").replaceAll("\\+", "_");
                            formato = ImportacaoUtils.normalizer(formato);
                            try {
                                FormatoANN.valueOf(formato);
                            } catch (Exception exp) {
                                tipos.add(formato);
                            }
                            break;
                        }
                    }
                }

            } catch (Exception e) {
            }
        }

        try {
            ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, PATH_OUTPUT_FORMATO, "formatosANN", true);
            extracaoUtils.criarArquivo(new ArrayList<String>(tipos));
        } catch (ApplicationException e) {
        }
    }

    /**
     * Executa REGEX para extrarir titulo principal
     *
     * @param linhas
     * @return Titulo principal
     * @throws IOException
     */
    private Media extrairDadosMedia(List<String> linhas) {
        Media media = null;
        for (String linha : linhas) {
            Matcher matcher = executeRegex("<h1 id=\"page_header\">(.*.)\\((.*.)\\)</h1>", linha);
            if (matcher.find()) {
                media = new Media();
                AlternativeName mediaNome = new AlternativeName();
                mediaNome.setPrincipal(true);
                mediaNome.setNome(matcher.group(1).trim());
                media.getNomes().add(mediaNome);
                String formato = matcher.group(2).trim().replaceAll("\\.", "_").replaceAll(" ", "").replaceAll("/", "").replaceAll("-", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("&", "").replaceAll("'", "").replaceAll(",", "").replaceAll("\\?", "").replaceAll("\\+", "_");
                formato = ImportacaoUtils.normalizer(formato);
                FormatoANN formatoANN = FormatoANN.valueOf(formato);
                media.setFormatoMedia(formatoANN.getFormatoMedia());
                media.setFormatoAnime(formatoANN.getFormatoAnime());
                media.setFormatoDorama(formatoANN.getFormatoDorama());
                media.setOrigem(OrigemEnum.ANN);
                break;
            }
        }
        return media;
    }

    /**
     * Extrai sinopse
     *
     * @param bf
     * @return Sinopse
     * @throws IOException
     */
    private String extrairSinopse(List<String> linhas) {
        String retorno = null;
        boolean check = false;
        for (String linha : linhas) {
            if (!check) {
                if (linha.indexOf("infotype-12") != -1) {
                    check = true;
                }
            } else {
                Matcher matcher = executeRegex("<span>(.*.)</span>", linha);
                if (matcher.find()) {
                    retorno = matcher.group(1);
                    break;
                }
            }
        }
        return ImportacaoUtils.removerHtml(retorno);
    }

    /**
     * Executa REGEX para extrarir titulos alternativos.
     *
     * @param bf
     * @return titulos alternativos
     * @throws IOException
     * @throws ApplicationException 
     */
    private List<AlternativeName> extrairTitulosAlternativos(List<String> linhas) throws ApplicationException {
        List<AlternativeName> titulos = new ArrayList<AlternativeName>();
        int status = 0;
        for (String linha : linhas) {
            if (status == 0) {
                if (linha.indexOf("infotype-2\"") != -1) {
                    status = 1;
                }
            } else if (status == 2) {
                Matcher matcher = executeRegex("(^<div.*>(([\\p{L}\\d!,:\"'$%#\\.*\\-\\+@\\?\\\\/;=\\| ]+)(\\((.*)\\))*)</div>$)", linha);
                if (matcher.find()) {
                	AlternativeName titulo = new AlternativeName();
                    titulo.setNome(matcher.group(3).trim());
                    titulo.setLocale(obterLocalidadeTitulo(matcher.group(5)));
                    titulos.add(titulo);
                } else {
                    break;
                }
            } else {
                status = 2;
            }
        }
        return titulos;
    }
    
    /**
     * Extrai localidade do titulo
     * @param bruteValue
     * @return Entidade {@link Localidade} do titulo em questao
     * @throws ApplicationException 
     */
    private LocaleEnum obterLocalidadeTitulo(String bruteValue) throws ApplicationException {
        LocaleEnum localidadeRetorno = null;
        if (StringUtils.isNotBlank(bruteValue)) {
            String strEnum = bruteValue.trim().replaceAll(" |\\(|\\)", "");
            try {
                IdiomaANNEnum annEnum = IdiomaANNEnum.valueOf(strEnum);
                localidadeRetorno = annEnum.getLocale();
            } catch (Exception exp) {
            }
        }
        return localidadeRetorno;
    }

    /**
     *
     * @param media
     * @param bf
     * @return
     * @throws IOException
     * @throws ApplicationException
     */
    private void extrairImagem(List<String> linhas, Media media) {
    	try {
	        String retorno = null;
	        String regex = "";
	        boolean isBigImage = false;
	        boolean isKeepFinding = true;
	        for (String linha : linhas) {
	            if (isKeepFinding) {
	                if (linha.indexOf("infotype-19") != -1) {
	                    regex = "(.*.)src=\"(.*.)\" (.*.)";
	                    isKeepFinding = false;
	                } else if (linha.indexOf("id=\"big-video\"") != -1) {
	                    regex = "(.*.)src=\"(.*.)\">";
	                    isKeepFinding = false;
	                    isBigImage = true;
	                }
	            } else {
	                Matcher matcher = executeRegex(regex, linha);
	                if (matcher.matches()) {
	                    String url = URL_BASE_ANN + (isBigImage ? matcher.group(2) : matcher.group(2).replaceAll("/thumbnails/(.*.)/", "/images/encyc/"));
						retorno = downloadFromURL(url);
	                    Anexo anexo = new Anexo();
	                    anexo.setNome(retorno);
	                    anexo.setDataCriacao(new Date());
	                    anexo.setTipoAnexo(TipoAnexo.IMAGEM);
	                    anexo.setPrincipal(true);
	                    media.getAnexos().add(anexo);
	                    break;
	                }
	            }
	        }
    	} catch (ApplicationException e) {
    	}
    }

    /**
     *
     * @param linhas
     * @return
     */
    private int extrairQuantidades(List<String> linhas, FormatoMedia fomrato) {
        int numero = 0;
        boolean extract = false;
        for (String linha : linhas) {
            if (!extract) {
            	switch (fomrato) {
            		case ANIME:
            			if (linha.contains("infotype-3\"")) extract = true;
            			break;
            		case MANGA:
            			if (linha.contains("infotype-5\"")) extract = true;
            			break;
            		
            	}
            } else {
                Matcher matcher = executeRegex("<span>(\\d+)</span>", linha);
                if (matcher.find()) {
                    numero = Integer.valueOf(matcher.group(1));
                    break;
                }
            }
        }
        return numero;
    }
    
    public List<BroadcastBase> extrairBroadcast(List<String> linhas) {
    	List<BroadcastBase> broadcasts = new ArrayList<BroadcastBase>();
    	boolean find = false;
    	for (String linha : linhas) {
    		if (linha.contains("infotype-7")) {
    			find = true;
    		} else if (find) {
    			Matcher matcher = executeRegex("<(.*.)>(.*|to.)<(.*.)>", linha);
    			if (matcher.find()) {
    				
    			}
    		}
    	}
    	return broadcasts;
    }

    /**
     * Extrai generos e tags da lista de linhas
     *
     * @throws ApplicationException
     *
     */
    private void extrairGeneroETag(List<String> linhas, Media media) throws ApplicationException {
        List<String> conteudos = new ArrayList<String>();
        boolean check = true;
        for (String linha : linhas) {
            if (check) {
                if (linha.indexOf("id=\"infotype-30\"") != -1) {
                    check = false;
                } else if (linha.indexOf("id=\"infotype-31\"") != -1) {
                    check = false;
                }
            } else {
                Matcher matcher = executeRegex("<span><(.*.)>(.*.)</(.*.)></span>", linha);
                if (matcher.find()) {
                    conteudos.add(ImportacaoUtils.normalizer(matcher.group(2).replaceAll(" ", "_").replaceAll("-", "_")));
                } else {
                    matcher = executeRegex("</div>", linha);
                    if (matcher.find()) {
                        check = true;
                    }
                }
            }
        }

        for (String item : conteudos) {
            try {
                GeneroANN gen = GeneroANN.valueOf(item);
                if (getMapGeneros().containsKey(gen.getGenero().getPrefixo())) {
                    media.getGeneros().add(getMapGeneros().get(gen.getGenero().getPrefixo()));
                }
            } catch (IllegalArgumentException epx) {
                item = item.replaceAll("_", " ");
                if (getMapTags().containsKey(item.toLowerCase())) {
                    media.getTags().add(getMapTags().get(item.toLowerCase()));
                } else {
                    Tag tag = new Tag();
                    tag.setKey(item);
                    media.getTags().add(tag);
                }
            }
        }
    }

    /**
     *
     * @param regex
     * @param linha
     * @return
     */
    private Matcher executeRegex(String regex, String linha) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(linha);
        return matcher;
    }

    /**
     *
     * @param url
     * @param evento
     * @return
     * @throws ApplicationException
     * @throws IOException
     */
    private String downloadFromURL(String enderecoImagem) throws ApplicationException {
        ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, PATH_OUTPUT_IMG, null);
        extracaoUtils.doRequest(enderecoImagem, null, true);
        return extracaoUtils.getNomeArquivoGerado();
    }
    
    /**
     * @param media
     * @param mediaNova
     * @throws IOException
     */
	private void compararImagens(Media media, Media mediaNova) throws IOException {
		Anexo iamgemPrincipal = mediaNova.getImagemPrincipal();
		if (iamgemPrincipal != null) {
			ExtracaoUtils imgOriginalExt = new ExtracaoUtils(propConfig, PATH_OUTPUT_IMG, null, media.getImagemPrincipal().getNome());
			ExtracaoUtils imgNovaExt = new ExtracaoUtils(propConfig, PATH_OUTPUT_IMG, null, mediaNova.getImagemPrincipal().getNome());
			
			if (imgNovaExt.exists()) {
		    	boolean isImagensIguais = imgOriginalExt.exists() ? FileUtils.contentEquals(imgOriginalExt.getFile(), imgNovaExt.getFile()) : false;
		    	if (!isImagensIguais) {
		    		media.getImagemPrincipal().setPrincipal(false);
	        		media.getAnexos().add( mediaNova.getImagemPrincipal());
		    	} else {
		    		imgNovaExt.removeFile();
		    	}
			}
		}
	}
    
    /**
	 * @return the mapGeneros
	 */
	private Map<String, Genero> getMapGeneros() {
		if (mapGeneros == null) {
			mapGeneros = new HashMap<String, Genero>();
			for (Genero genero : generoService.buscarTodos()) {
				mapGeneros.put(genero.getKey(), genero);
			}
		}
		return mapGeneros;
	}

	/**
	 * @return the mapTags
	 */
	private Map<String, Tag> getMapTags() {
		if (mapTags == null) {
			mapTags = new HashMap<String, Tag>();
			for (Tag tag : tagService.buscarTodos()) {
				mapTags.put(tag.getNome().toLowerCase(), tag);
			}
		}
		return mapTags;
	}
}
