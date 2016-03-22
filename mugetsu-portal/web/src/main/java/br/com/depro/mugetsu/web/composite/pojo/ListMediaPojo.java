package br.com.depro.mugetsu.web.composite.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.depro.mugetsu.model.Localidade;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.media.Genero;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.MediaTitulo;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.model.projeto.Projeto;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 28.07.2012
 */
public class ListMediaPojo implements Comparable<ListMediaPojo> {

    private Long id;
    private List<MediaTitulo> titulos = new ArrayList<MediaTitulo>();
    private Status status;
    private FormatoMedia formatoMedia;
    private FormatoAnime formatoAnime;
    private FormatoDorama formatoDorama;
    private Localidade idiomaDefault;
    private Set<Genero> generos = new HashSet<Genero>();
    private BigDecimal ranking;
    private Date dataInicioProjeto;
    private Date dataFimProjeto;
    private Date dataCadastroMedia;
    private int quantidadeEpisodios;
    private int quantidadeVolumes;
    private int quantidadeEpiProjeto;
    private int quantidadeVolProejto;
    private int ano;
    private String nomeImagem;
    private String nomePrincipal;

    /**
     * Construtor de Projeto
     */
    public ListMediaPojo(Projeto data) {
        Media media = data.getMedia();
        this.id = media.getId();
        this.nomePrincipal = media.getNomePrincipal();
        this.formatoAnime = media.getFormatoAnime();
        this.formatoDorama = media.getFormatoDorama();
        this.formatoMedia = media.getFormatoMedia();
        this.quantidadeEpisodios = media.getEpisodios();
        this.quantidadeVolumes = media.getVolumes();
        if (media.getGeneros() != null && !media.getGeneros().isEmpty()) {
            this.generos.addAll(media.getGeneros());
        }
        this.ranking = data.getRanking();
        this.dataInicioProjeto = data.getDataInicial();
        this.dataFimProjeto = data.getDataFinal();
        this.quantidadeEpiProjeto = data.getConteudosProjeto().size();
        this.quantidadeVolProejto = data.getConteudosProjeto().size();
        this.status = data.getStatus();
        this.ano = media.getAno();
    }

    /**
     * Construtor de Media
     */
    public ListMediaPojo(Media data) {
        this.id = data.getId();
        this.nomePrincipal = "" + data.getNomePrincipal();
        this.formatoAnime = data.getFormatoAnime();
        this.formatoDorama = data.getFormatoDorama();
        this.formatoMedia = data.getFormatoMedia();
        this.quantidadeEpisodios = data.getEpisodios();
        this.quantidadeVolumes = data.getVolumes();
        if (data.getGeneros() != null && !data.getGeneros().isEmpty()) {
            this.generos.addAll(data.getGeneros());
        }
        this.ano = data.getAno();
        this.nomeImagem = data.getPathImagem();
    }

    /**
     * Realiza parse de projetos para pojo de lista media
     *
     * @param dados
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<ListMediaPojo> parse(List dados) {
        List<ListMediaPojo> retorno = new ArrayList<ListMediaPojo>();
        for (Object item : dados) {
            if (item instanceof Projeto) {
                Projeto dado = (Projeto) item;
                retorno.add(new ListMediaPojo(dado));
            } else if (item instanceof Media) {
                Media dado = (Media) item;
                retorno.add(new ListMediaPojo(dado));
            }
        }
        Collections.reverse(retorno);
        Collections.sort(retorno);
        return retorno;
    }

    /**
     * Retorna o formato real da media
     *
     * @return {@link String}
     */
    public String getTipoMedia() {
        String formato = this.getFormatoMedia().getPrefixo();
        if (this.getFormatoMedia().equals(FormatoMedia.ANIME)) {
            formato += "-" + this.getFormatoAnime().getPrefixo();
        } else if (this.getFormatoMedia().equals(FormatoMedia.DORAMA)) {
            formato = this.getFormatoDorama().getPrefixo();
        }
        return formato;
    }

    /**
     * @return Titulo principal da media
     */
    public MediaTitulo getTituloPrincipal() {
        MediaTitulo titulo = new MediaTitulo();
        for (MediaTitulo item : this.titulos) {
            if (item.isPrincipal() && idiomaDefault == null) {
                titulo = item;
                break;
            } else if (idiomaDefault != null && item.getLocalidade().equals(idiomaDefault)) {
                titulo = item;
                break;
            }
        }
        return titulo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the titulos
     */
    public List<MediaTitulo> getTitulos() {
        return titulos;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(List<MediaTitulo> titulos) {
        this.titulos = titulos;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the formatoMedia
     */
    public FormatoMedia getFormatoMedia() {
        return formatoMedia;
    }

    /**
     * @param formatoMedia the formatoMedia to set
     */
    public void setFormatoMedia(FormatoMedia formatoMedia) {
        this.formatoMedia = formatoMedia;
    }

    /**
     * @return the formatoAnime
     */
    public FormatoAnime getFormatoAnime() {
        return formatoAnime;
    }

    /**
     * @param formatoAnime the formatoAnime to set
     */
    public void setFormatoAnime(FormatoAnime formatoAnime) {
        this.formatoAnime = formatoAnime;
    }

    /**
     * @return the formatoDorama
     */
    public FormatoDorama getFormatoDorama() {
        return formatoDorama;
    }

    /**
     * @param formatoDorama the formatoDorama to set
     */
    public void setFormatoDorama(FormatoDorama formatoDorama) {
        this.formatoDorama = formatoDorama;
    }

    /**
     * @return the idiomaDefault
     */
    public Localidade getIdiomaDefault() {
        return idiomaDefault;
    }

    /**
     * @param idiomaDefault the idiomaDefault to set
     */
    public void setIdiomaDefault(Localidade idiomaDefault) {
        this.idiomaDefault = idiomaDefault;
    }

    /**
     * @return the generos
     */
    public Set<Genero> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }

    /**
     * @return the quantidadeEpiProjeto
     */
    public int getQuantidadeEpiProjeto() {
        return quantidadeEpiProjeto;
    }

    /**
     * @param quantidadeEpiProjeto the quantidadeEpiProjeto to set
     */
    public void setQuantidadeEpiProjeto(int quantidadeEpiProjeto) {
        this.quantidadeEpiProjeto = quantidadeEpiProjeto;
    }

    /**
     * @return the ranking
     */
    public BigDecimal getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(BigDecimal ranking) {
        this.ranking = ranking;
    }

    /**
     * @return the dataInicioProjeto
     */
    public Date getDataInicioProjeto() {
        return dataInicioProjeto;
    }

    /**
     * @param dataInicioProjeto the dataInicioProjeto to set
     */
    public void setDataInicioProjeto(Date dataInicioProjeto) {
        this.dataInicioProjeto = dataInicioProjeto;
    }

    /**
     * @return the dataFimProjeto
     */
    public Date getDataFimProjeto() {
        return dataFimProjeto;
    }

    /**
     * @param dataFimProjeto the dataFimProjeto to set
     */
    public void setDataFimProjeto(Date dataFimProjeto) {
        this.dataFimProjeto = dataFimProjeto;
    }

    /**
     * @return the dataCadastroMedia
     */
    public Date getDataCadastroMedia() {
        return dataCadastroMedia;
    }

    /**
     * @param dataCadastroMedia the dataCadastroMedia to set
     */
    public void setDataCadastroMedia(Date dataCadastroMedia) {
        this.dataCadastroMedia = dataCadastroMedia;
    }

    /**
     * @return the quantidadeEpisodios
     */
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    /**
     * @param quantidadeEpisodios the quantidadeEpisodios to set
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * @return the quantidadeVolumes
     */
    public int getQuantidadeVolumes() {
        return quantidadeVolumes;
    }

    /**
     * @param quantidadeVolumes the quantidadeVolumes to set
     */
    public void setQuantidadeVolumes(int quantidadeVolumes) {
        this.quantidadeVolumes = quantidadeVolumes;
    }

    /**
     * @return the quantidadeVolProejto
     */
    public int getQuantidadeVolProejto() {
        return quantidadeVolProejto;
    }

    /**
     * @param quantidadeVolProejto the quantidadeVolProejto to set
     */
    public void setQuantidadeVolProejto(int quantidadeVolProejto) {
        this.quantidadeVolProejto = quantidadeVolProejto;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(ListMediaPojo o) {
        return this.getNomePrincipal().compareTo(o.getNomePrincipal());
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the nomeImagem
     */
    public String getNomeImagem() {
        return nomeImagem;
    }

    /**
     * @param nomeImagem the nomeImagem to set
     */
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    /**
     * @return the nomePrincipal
     */
    public String getNomePrincipal() {
        return nomePrincipal;
    }

    /**
     * @param nomePrincipal the nomePrincipal to set
     */
    public void setNomePrincipal(String nomePrincipal) {
        this.nomePrincipal = nomePrincipal;
    }
}
