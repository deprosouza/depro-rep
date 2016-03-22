package br.com.depro.mugetsu.carga.dorama.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.fw.typezero.infrastructure.utils.ExtracaoUtils;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.carga.dorama.model.DatasDW;
import br.com.depro.mugetsu.carga.dorama.model.FormatoDW;
import br.com.depro.mugetsu.carga.dorama.model.GeneroDW;
import br.com.depro.mugetsu.carga.dorama.model.IdiomaDW;
import br.com.depro.mugetsu.carga.dorama.service.ImportarDWService;
import br.com.depro.mugetsu.carga.principal.utils.ImportacaoUtils;
import br.com.depro.mugetsu.core.dao.media.MediaDAO;
import br.com.depro.mugetsu.core.service.LocalidadeService;
import br.com.depro.mugetsu.core.service.media.GeneroService;
import br.com.depro.mugetsu.core.service.media.MediaService;
import br.com.depro.mugetsu.core.service.media.MediaTituloService;
import br.com.depro.mugetsu.core.service.media.TemaService;
import br.com.depro.mugetsu.model.Localidade;
import br.com.depro.mugetsu.model.cenum.Origem;
import br.com.depro.mugetsu.model.media.Galeria;
import br.com.depro.mugetsu.model.media.Genero;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.Tag;
import br.com.depro.mugetsu.model.media.broadcast.BroadcastBase;
import br.com.depro.mugetsu.model.media.nome.MediaNome;
import br.com.depro.mugetsu.model.media.vo.FormatoImagem;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10/02/2013
 */
@Service
public class ImportarDWServiceImpl extends TypezeroGenericServiceImpl<Media, MediaDAO> implements
        ImportarDWService {

    private static final String SUFIXO_DRAMAWIKI = "dramawiki";
    private static final String KEY_PATH_OUTPUT = "dir.path.output.carga.dorama";
    private static final String KEY_PATH_OUTPUT_IMAGEM = "dir.path.output.img.exibicao";
    private static final String URL_EXTRAIR_NOMES = "http://wiki.d-addicts.com/index.php?title=Category:%s&from=%s";
    private static final String URL_BASE_DRAMA_WIKI = "http://wiki.d-addicts.com";
    private static final String DESCRICAO_TEMA = "Importado via carga Drama Wiki";
    @Autowired
    private PropConfig propConfig;
    @Autowired
    private MediaTituloService mediaTituloService;
    @Autowired
    private LocalidadeService localidadeService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private TemaService tagService;
    @Autowired
    private MediaService mediaService;
    private ExtracaoUtils extracao;
    private Map<String, Localidade> mapIdiomas;
    private Map<String, Genero> mapGeneros;
    private Map<String, Tag> mapTags;

    public Media atualizarDadosMedia(Media media) throws ApplicationException {
		try {
			this.extracao = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, media.getIdOrigem() + "-" + SUFIXO_DRAMAWIKI);
			List<String> conteudo = extracao.obterLinhas();
            Media mediaNova = extrarDadosMedia(conteudo, FormatoDW.getEnum(media.getFormatoDorama()));
            if (mediaNova != null && mediaTituloService.validarMediaTitulos(mediaNova)) {
                extrairDadosAdicionais(conteudo, mediaNova);
                extrairDadosSinopse(conteudo, mediaNova);
//                extrairDadosImagem(conteudo, mediaNova, false);
                
                media = ImportacaoUtils.mergeMedia(media, mediaNova);
                this.mediaService.salvar(media);
            }
		} catch (ApplicationException e) {
            this.logger.error(e.getMessage());
        }
		return media;
	}
    
    /**
     * @see ImportarDramaWikiServiceImpl#extrairArquivoDeNomes()
     */
    public void extrairArquivoDeNomes() throws ApplicationException {
//        for (FormatoDW tipo : FormatoDW.values()) {
//            this.extracao = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, "00-" + tipo + "-FileOfNames", false);
//            if (!extracao.exists()) {
//	            for (Alfabeto alfabeto : Alfabeto.values()) {
//	                List<String> nomes = new ArrayList<String>();
//	                String letra = "";
//	                if (Alfabeto.NUM.equals(alfabeto)) {
//	                    letra = "0";
//	                } else {
//	                    letra = alfabeto.getLabel();
//	                }
//	                nomes = extrairNomes(extracao.doRequest(String.format(URL_EXTRAIR_NOMES, tipo.name(), letra)));
//	                this.extracao.criarArquivo(nomes);
//	            }
//            }
//        }
    }

    /**
     * @see ImportarDramaWikiServiceImpl#extrairHTML()
     */
    public void extrairHTML() throws ApplicationException {
        for (FormatoDW tipo : FormatoDW.values()) {
            try {
                this.extracao = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, "00-" + tipo + "-FileOfNames");
                ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, SUFIXO_DRAMAWIKI, false);
                for (String linha : new HashSet<String>(extracao.obterLinhas())) {
                    String nome = linha.split(" ")[0];
                    extracaoUtils.doRequest(URL_BASE_DRAMA_WIKI + linha.replace("\"", ""), nome.replace("\\", ""), false);
                }
            } catch (ApplicationException aexp) {
                super.logger.error(aexp.getMessage());
            }
        }
    }

    /**
     * @see ImportarDramaWikiServiceImpl#importarFromCarga()
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = ApplicationException.class)
    public void importarFromCarga() throws ApplicationException {
        carregarDadosBasicos();
        for (FormatoDW tipo : FormatoDW.values()) {
            try {
                this.extracao = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, "00-" + tipo + "-FileOfNames");
                for (String linhaNome : new HashSet<String>(extracao.obterLinhas())) {
                    String nome = linhaNome.split(" ")[0].replace("\"", "").replace("/", "");
                    ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT, nome + "-" + SUFIXO_DRAMAWIKI);
                    List<String> conteudo = extracaoUtils.obterLinhas();
                    Media media = extrarDadosMedia(conteudo, tipo);
                    if (media != null && mediaTituloService.validarMediaTitulos(media)) {
                    	media.setIdOrigem(nome);
                        extrairDadosAdicionais(conteudo, media);
                        extrairDadosSinopse(conteudo, media);
                        extrairDadosImagem(conteudo, media, false);
                        this.mediaService.salvar(media);
                    }
                }
            } catch (ApplicationException aexp) {
                super.logger.error(aexp.getMessage());
            } catch (Exception exp) {
                super.logger.error(exp.getMessage());
            }
        }
    }

    /**
     * Extrai nome de media e nomes alternativos
     * 
     * @param conteudo
     * @param tipo TODO
     * @return Entidade Media
     */
    private Media extrarDadosMedia(final List<String> conteudo, FormatoDW tipo) {
        Map<String, MediaNome> mapTitulos = new HashMap<String, MediaNome>();
        boolean extraiTitulosAlternativos = false;
        boolean possivelFinalizar = false;
        for (String linha : conteudo) {
            if (!extraiTitulosAlternativos) {
                Matcher matcher = executeRegex("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"(.*.)\"><span dir=\"auto\">(.*.)</span></h1>", linha);
                if (matcher.matches()) {
                    extraiTitulosAlternativos = true;
                    MediaNome titulo = new MediaNome();
                    titulo.setNome(ImportacaoUtils.toProperCase(matcher.group(2)));
                    titulo.setPrincipal(true);
                    titulo.setLocalidade(obterLocalidade(matcher.group(1)));
                    mapTitulos.put(titulo.getNome(), titulo);
                }
            } else if (extraiTitulosAlternativos) {
                if (executeRegex("<h2><span class=\"mw-headline\" id=\"Details\">Details</span></h2>", linha).matches() || possivelFinalizar) {
                    possivelFinalizar = true;
                    Matcher matcher = executeRegex("<li><b>Title:</b>(.*.)</li>|<li><b>Also known as:</b>(.*.)</li>|<li><b>(.*.) title:</b>(.*.)</li>", linha);
                    if (matcher.matches()) {
                        String nomes = ImportacaoUtils.removerHtml(matcher.group(1) != null ? matcher.group(1) : matcher.group(2));
                        Localidade localidade = null;
                        if (StringUtils.isNotBlank(matcher.group(3)) && StringUtils.isBlank(nomes)) {
                            String idioma = matcher.group(3).replace("/", "_").replace(" ", "_");
                            nomes = matcher.group(4);
                            localidade = obterLocalidade(idioma);
                        }
                        for (String nome : nomes.split("/")) {
                            if (!mapTitulos.containsKey(ImportacaoUtils.toProperCase(nome))) {
                                MediaNome titulo = new MediaNome();
                                titulo.setNome(ImportacaoUtils.toProperCase(nome.trim()));
                                titulo.setLocalidade(localidade);
                                mapTitulos.put(titulo.getNome(), titulo);
                            }
                        }
                    } else if (executeRegex("</ul>", linha).matches()) {
                        break;
                    }
                }
            }
        }
        Media media = null;
        if (!mapTitulos.isEmpty()) {
            media = new Media();
            media.setNomes(new ArrayList<MediaNome>(mapTitulos.values()));
            media.setFormatoMedia(FormatoMedia.DORAMA);
            media.setFormatoDorama(tipo.getForamto());
            media.setOrigem(Origem.DW);
        }
        return media;
    }

    /**
     * Extrai dos htmls baixados dados adicionais como genero, numero de episodios, data lacamento etx
     * 
     * @param conteudo
     * @throws ApplicationException 
     */
    private void extrairDadosAdicionais(final List<String> conteudo, Media media) throws ApplicationException {
        boolean possivelFinalizar = false;
        BroadcastBase broadcast = new BroadcastBase();
        for (String linha : conteudo) {
            if (executeRegex("<h2><span class=\"mw-headline\" id=\"Details\">Details</span></h2>", linha).matches() || possivelFinalizar) {
                possivelFinalizar = true;
                extrairGeneroETema(media, linha);
                extrairEpisodios(media, linha);
                extrairBroadcast(media, linha, broadcast);
                if (executeRegex("</ul>", linha).matches()) {
                    break;
                }
            }
        }
    }

    /**
     * Extrai data de lancamento e encerramento
     * 
     * @param media
     * @param linha
     * @param broadcast TODO
     */
    @SuppressWarnings("deprecation")
    private void extrairBroadcast(Media media, String linha, BroadcastBase broadcast) {
        try {
            Matcher matcher = executeRegex("<li><b>Broadcast network:</b>(.*.)</li>|<li><b>Broadcast period:</b>(.*.)</li>", linha);
            if (matcher.matches()) {
                if (StringUtils.isNotBlank(matcher.group(1))) {
                    broadcast.setEmissora(matcher.group(1).trim());
                } else if (StringUtils.isNotBlank(matcher.group(2))) {
                    String datas = matcher.group(2).trim().replace("-", "/");
                    for (DatasDW dataReplace : DatasDW.values()) {
                        if (datas.contains(dataReplace.name())) {
                            datas = datas.replace(dataReplace.name(), dataReplace.getNunero());
                        }
                    }
                    broadcast.setLacamento(new Date(datas.split("to")[0].trim()));
                    broadcast.setEncerramento(new Date(datas.split("to")[1].trim()));
//                    media.getBroadcasts().add(broadcast);
                }
            }
        } catch (Exception exp) {
        	broadcast = null;
        }
    }

    /**
     * Extrai quantidade de episodios
     * 
     * @param media
     * @param linha
     */
    private void extrairEpisodios(Media media, String linha) {
        Matcher matcher = executeRegex("<li><b>Episodes:</b>(.*.)</li>", linha);
        try {
            if (matcher.matches()) {
                String qtde = matcher.group(1).trim().split(" ")[0].split("\\.")[0].split("/")[0].replace("+", "").trim();
                int numeroEpis = new Integer(qtde).intValue();
                media.setQuantidedaEpisodios(numeroEpis);
            }
        } catch (Exception exp) {
        	super.logger.error("NumberFormat : " + matcher.group(1).trim() + " Formatado : " + matcher.group(1).trim().split(" ")[0].split("\\\\.")[0].replace("+", "").trim());
        }
    }

    /**
     * Obtem geneor e tema do dorama
     * 
     * @param media
     * @param linha
     * @throws ApplicationException
     */
    private void extrairGeneroETema(Media media, String linha) throws ApplicationException {
        Matcher matcher = executeRegex("<li><b>Genre:</b>(.*.)</li>|<li><b>Genere:</b>(.*.)</li>", linha);
        if (matcher.matches()) {
            for (String itemVirgula : matcher.group(1) != null ? matcher.group(1).split(",| ") : matcher.group(2).split(",| ")) {
                for (String item : itemVirgula.split("/")) {
                	if (StringUtils.isNotBlank(item)) {
	                    String genero = item.trim().replace(" ", "_");
	                    try {
	                        GeneroDW generoDW = GeneroDW.valueOf(genero);
                            if (mapGeneros.containsKey(generoDW.getGenero().getPrefixo())) {
                                media.getGeneros().add(mapGeneros.get(generoDW.getGenero().getPrefixo()));
                                break;
                            }
	                    } catch (Exception exp) {
	                        Tag tagSelecionado = mapTags.get(genero.replace("_", " ").toLowerCase());
	                        if (tagSelecionado == null) {
	                            Tag tagNova = new Tag();
	                            tagNova.setTag(genero.replace("_", " "));
	                            tagService.salvar(tagNova);
	                            mapTags.put(tagNova.getTag().toLowerCase(), tagNova);
	                        } else {
	                            media.getTemas().add(tagSelecionado);
	                        }
	                    }
                	}
                }
            }
        }
    }

    /**
     * Extrai a sinopse da media
     * 
     * @param conteudo
     * @param media
     */
    private void extrairDadosSinopse(List<String> conteudo, Media media) {
        String sinopse = "";
        boolean possivelFinalizar = false;
        for (String linha : conteudo) {
            if (executeRegex("<h2><span class=\"mw-headline\" id=\"Synopsis\">Synopsis</span></h2>", linha).matches() && !possivelFinalizar) {
                possivelFinalizar = true;
            } else if (possivelFinalizar) {
                Matcher matcher = executeRegex("<p>(.*.)</p>", linha);
                if (matcher.matches()) {
                    sinopse += ImportacaoUtils.removerHtml(matcher.group(1));
                } else if (executeRegex("<h2>(.*.)</h2>", linha).matches()) {
                    break;
                }
            }
        }
        media.setSinopse(sinopse);
    }

    /**
     * Extrair imagem de exibicao
     * 
     * @param conteudo
     * @param media
     * @param cancelaRecursividade TODO
     * @throws ApplicationException Caso algum erro ocorra
     */
    private void extrairDadosImagem(List<String> conteudo, Media media, boolean cancelaRecursividade) throws ApplicationException {
        ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig, KEY_PATH_OUTPUT_IMAGEM, null);
        for (String linha : conteudo) {
            Matcher matcher = executeRegex("(.*.)<a href=\"/File:(.*.)\" class=\"image\"><img alt=\"\" src=\"(.*.)\" width=(.*.)"
                    + "|<li><a href=\"#metadata\">Metadata</a></li></ul><div class=\"fullImageLink\" id=\"file\"><a href=\"(.*.)\"><img alt=\"(.*.)", linha);
            if (matcher.matches()) {
                String imagemAmpliada =  matcher.group(2);
                String imagemDownload = cancelaRecursividade ? matcher.group(5) : matcher.group(3);
                
                if (imagemAmpliada == null) {
	                extracaoUtils.doRequest(URL_BASE_DRAMA_WIKI + imagemDownload, null, true);
	                Galeria galeria = new Galeria();
	                galeria.setPathImagem(extracaoUtils.getNomeArquivoGerado());
	                galeria.setImagemAtual(media.getGaleriaImagens().isEmpty());
	                galeria.setFormatoImagem(FormatoImagem.IMG_MEDIA);
	                media.getGaleriaImagens().add(galeria);
                }
                if (imagemAmpliada != null) {
                    extrairDadosImagem(extracaoUtils.doRequest(URL_BASE_DRAMA_WIKI + "/File:" + imagemAmpliada), media, true);
                }
            }
        }
    }

    /**
     * Extrai nome das medias
     * 
     * @param bufferedReader
     * @return Liusta de nomes 
     * @throws ApplicationException 
     */
    private List<String> extrairNomes(List<String> conteudo) {
        List<String> nomes = new ArrayList<String>();
        boolean iniciarCaptura = false;
        for (String linha : conteudo) {
            if (executeRegex("(.*.)\"mw-content-ltr\"(.*.)", linha).matches() && !iniciarCaptura) {
                iniciarCaptura = true;
            } else if (iniciarCaptura) {
                Matcher matcher = executeRegex("(.*.)<li><a href=\"(.*.)\" title(.*.)|<li><a href=\"(.*.)\" title(.*.)", linha);
                if (matcher.matches()) {
                    String nome = matcher.group(2);
                    if (StringUtils.isBlank(nome)) {
                        nome = matcher.group(4);
                    }
                    nomes.add(nome);
                } else if (executeRegex("(.*.)</table>(.*.)", linha).matches()) {
                    break;
                }
            }
        }
        return nomes;
    }
    
    /**
     * Obtem localidade do titulo
     * @param language
     * @return
     */
    private Localidade obterLocalidade(String language) {
    	Localidade localidade = null;
    	 if (StringUtils.isNotBlank(language)) {
             try {
                 IdiomaDW idiomaDW = IdiomaDW.valueOf(language);
                 localidade = mapIdiomas.get(idiomaDW.getPrefixoIdoma());
             } catch (Exception exp) {
                 super.logger.error("Não foi possivel recuperar localidade para : " + language);
             }
         }
    	 return localidade;
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
     * Carrega nas variaveis globais informacoes basicas de importacao
     */
    private void carregarDadosBasicos() {
    	mapIdiomas = new HashMap<String, Localidade>();
    	for (Localidade localidade : localidadeService.listarTodosIdiomas()) {
    		mapIdiomas.put(localidade.getPrefixo(), localidade);
    	}
    	
    	mapGeneros = new HashMap<String, Genero>();
    	for (Genero genero : generoService.buscarTodas()) {
    		mapGeneros.put(genero.getPrefixo(), genero);
    	}
    	
    	mapTags = new HashMap<String, Tag>();
    	for (Tag tag : tagService.buscarTodas()) {
    		mapTags.put(tag.getTag().toLowerCase(), tag);
    	}
    }

    @Autowired
    public void initDAO(MediaDAO dao) {
        super.setDAO(dao);
    }
}
