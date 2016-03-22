package br.com.depro.mugetsu.web.renderer;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.mugetsu.model.media.Conteudo;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.negocio.service.media.ConteudoService;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
import br.com.depro.mugetsu.web.composite.pojo.ListConteudoPojo;
import br.com.depro.mugetsu.web.security.Rights;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;
import br.com.depro.mugetsu.web.utils.SessionUtils;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 02.09.2012
 */
public class RendererListboxConteudoCompleto implements ListitemRenderer<ListConteudoPojo> {

    /**
     * @see ListitemRenderer#render(Listitem, Object, int)
     */
    public void render(Listitem item, ListConteudoPojo data, int index) throws Exception {
        item.appendChild(new Listcell(String.valueOf(data.getConteudo().getNumeroEpisodio())));
        item.appendChild(new Listcell(data.getTituloPrincipal().getNome()));

        item.setValue(data);

        Image image = new Image(RendererUtils.renderImagemCheckOrNotCheck(data, 2));
        if (SessionUtils.hasPermissao(Rights.ALTER_FILLER)) {
            image.addEventListener(Events.ON_CLICK, createEventeAlterarFiller(image, data));
            image.setSclass("alterarStatusFiller");
        }
        Listcell listcell = new Listcell();
        listcell.appendChild(image);
        item.appendChild(listcell);

        image = new Image(RendererUtils.renderImagemCheckOrNotCheck(data, 0));
        image.addEventListener(Events.ON_CLICK, this.createEvent(image, data, 0));
        listcell = new Listcell();
        listcell.appendChild(image);
        item.appendChild(listcell);

        image = new Image(RendererUtils.renderImagemCheckOrNotCheck(data, 1));
        image.addEventListener(Events.ON_CLICK, this.createEvent(image, data, 1));
        listcell = new Listcell();
        listcell.appendChild(image);
        item.appendChild(listcell);

        item.setValue(data);
    }

    /**
     * Cria evento para alterar de status de filler
     *
     * @param image
     * @param pojo
     * @return Evento
     */
    private EventListener<Event> createEventeAlterarFiller(final Image image, final ListConteudoPojo pojo) {
        return new EventListener<Event>() {
            public void onEvent(Event arg0) throws Exception {
                ConteudoService conteudoService = (ConteudoService) MugetsuSpringUtils.getBean(ConteudoService.class);
                Conteudo conteudo = pojo.getConteudo();
                conteudo.setFiller(!conteudo.isFiller());
                conteudoService.atualizar(conteudo);
                image.setSrc(RendererUtils.renderImagemCheckOrNotCheck(pojo, 2));
            }
        };
    }

    /**
     *
     * @param image
     * @param pojo
     * @return Evento
     */
    private EventListener<Event> createEvent(final Image image, final ListConteudoPojo pojo, final int opcao) {
        return new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                ProjetoService projetoService = (ProjetoService) MugetsuSpringUtils.getBean(ProjetoService.class);
                if (pojo.getProjetoConteudo() == null) {
                    pojo.setProjetoConteudo(new ProjetoConteudo());
                }
                if (opcao == 0) {
                    pojo.getProjetoConteudo().setBaixado(!pojo.getProjetoConteudo().isBaixado());
                    pojo.getProjetoConteudo().setConteudo(pojo.getConteudo());
                    image.setSrc(RendererUtils.renderImagemCheckOrNotCheck(pojo, 0));
                } else {
                    pojo.getProjetoConteudo().setAssistido(!pojo.getProjetoConteudo().isAssistido());
                    image.setSrc(RendererUtils.renderImagemCheckOrNotCheck(pojo, 1));
                }

                projetoService.atualizarProjetoConteudo(SessionUtils.getCredenciais(),
                        pojo.getProjetoConteudo());
            }
        };
    }
}
