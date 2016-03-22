package br.com.depro.mugetsu.web.composite.pojo;

import java.util.ArrayList;
import java.util.List;

import br.com.depro.mugetsu.model.Localidade;
import br.com.depro.mugetsu.model.media.Conteudo;
import br.com.depro.mugetsu.model.media.ConteudoTitulo;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.07.2012
 */
public class ListConteudoPojo implements Comparable<ListConteudoPojo> {

    private List<ConteudoTitulo> titulos = new ArrayList<ConteudoTitulo>();
    private Localidade idiomaDefault;
    private ProjetoConteudo projetoConteudo;
    private Conteudo conteudo;

    /**
     * Construtor de {@link Conteudo}
     *
     * @param dado
     */
    public ListConteudoPojo(Conteudo dado, ProjetoConteudo projetoConteudo) {
        this.titulos = dado.getTitulos();
        this.conteudo = dado;
        this.projetoConteudo = projetoConteudo;

    }

    /**
     * Identifica e realiza o parse da lista de entidade passada como parametro
     *
     * @param dados
     * @return Lista de pojo.
     */
    public static List<ListConteudoPojo> parse(List<Conteudo> dados, Projeto projeto) {
        List<ListConteudoPojo> retorno = new ArrayList<ListConteudoPojo>();
        List<ProjetoConteudo> dadosProjeto = new ArrayList<ProjetoConteudo>();
        ProjetoConteudo dadoProjetoConteudo = null;
        if (projeto != null) {
            dadosProjeto = projeto.getConteudosProjeto();
        }
        for (Conteudo item : dados) {
            if (!dadosProjeto.isEmpty()) {
                for (ProjetoConteudo projetoConteudo : dadosProjeto) {
                    if (item.getId().equals(projetoConteudo.getConteudo().getId())) {
                        dadoProjetoConteudo = projetoConteudo;
                        break;
                    }
                }
            } 
            
            if (projeto != null && dadoProjetoConteudo == null) {
                dadoProjetoConteudo = new ProjetoConteudo();
                dadoProjetoConteudo.setConteudo(item);
                projeto.getConteudosProjeto().add(dadoProjetoConteudo);
            }
            retorno.add(new ListConteudoPojo(item, dadoProjetoConteudo));
            dadoProjetoConteudo = null;
        }
        return retorno;
    }

    /**
     * Retorna o titulo principal do conteudo
     *
     * @return
     */
    public ConteudoTitulo getTituloPrincipal() {
        ConteudoTitulo retorno = new ConteudoTitulo();
        for (ConteudoTitulo item : titulos) {
            if (this.idiomaDefault == null && item.isPrincipal()) {
                retorno = item;
                break;
            } else if (this.idiomaDefault != null
                    && item.getLocalidade().getPrefixo().equals(this.idiomaDefault.getPrefixo())) {
                retorno = item;
                break;
            }
        }
        return retorno;
    }

    /**
     * @return the titulos
     */
    public List<ConteudoTitulo> getTitulos() {
        return titulos;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(List<ConteudoTitulo> titulos) {
        this.titulos = titulos;
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
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(ListConteudoPojo o) {
        return this.getConteudo().getNumeroEpisodio().compareTo(o.getConteudo().getNumeroEpisodio());
    }

    /**
     * @return the projetoConteudo
     */
    public ProjetoConteudo getProjetoConteudo() {
        return projetoConteudo;
    }

    /**
     * @param projetoConteudo the projetoConteudo to set
     */
    public void setProjetoConteudo(ProjetoConteudo projetoConteudo) {
        this.projetoConteudo = projetoConteudo;
    }

    /**
     * @return the conteudo
     */
    public Conteudo getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }
}
