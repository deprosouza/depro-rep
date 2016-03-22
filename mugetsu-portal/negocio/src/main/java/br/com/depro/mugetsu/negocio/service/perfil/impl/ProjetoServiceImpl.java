package br.com.depro.mugetsu.negocio.service.perfil.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.model.cenum.Alfabeto;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.dao.perfil.ProjetoDAO;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 23.07.2012
 */
@Service
public class ProjetoServiceImpl extends TypezeroGenericServiceImpl<Projeto, ProjetoDAO>
        implements ProjetoService {

    @Autowired
    private MediaService mediaService;

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.typezero.infrastructure.dao.DSphereGenericDAO)
     */
    @Autowired
    public void initDAO(ProjetoDAO dao) {
        super.setDAO(dao);
    }

    @Override
    public void inserir(Login login, Media media) throws ApplicationException {
        Projeto projeto = new Projeto();
        this.inserir(login, media, projeto);
    }

    @Override
    public void inserir(Login login, Media media, Projeto projeto) throws ApplicationException {
        projeto.setUsuario(login);
        projeto.setMedia(media);
        projeto.setDataInicial(new Date());
        projeto.setStatus(Status.INCOMPLETO);
        super.getDAO().salvar(projeto);
    }

    @Override
    public List<Projeto> listarProjetosPorUsuario(Login login) throws ApplicationException {
        try {
            return super.getDAO().listarPorUsuario(login);
        } catch (TransactionBaseException tbexp) {
            throw new ApplicationException(tbexp);
        }
    }

    @Override
    public List<Projeto> listarProjetosPorUsuarioEPrimeiraLetra(Login login, String letra) throws ApplicationException {
        try {
            List<String> letras = new ArrayList<String>();
            for (Alfabeto alfabeto : Alfabeto.values()) {
                if (alfabeto.getLabel().equalsIgnoreCase(letra)) {
                    for (String s : alfabeto.getValores()) {
                        letras.add(s);
                    }
                    break;
                }
            }
            return super.getDAO().listarProUsuarioELetra(login, letras);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    @Override
    public Projeto buscarPorCredencialEMidia(Login login, Media media) throws ApplicationException {
        try {
            return super.getDAO().findByCredencialEMedia(login, media);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    @Override
    public Projeto buscarPorCredencialEIdMedia(Login login, Long id)
            throws ApplicationException {
        return buscarPorCredencialEMidia(login, mediaService.buscarPorId(id));
    }

    @Override
    public void atualizarProjetoConteudo(Login login, ProjetoConteudo projetoConteudo)
            throws ApplicationException {
        Media media = projetoConteudo.getConteudo().getMedia();
        Projeto projeto = this.buscarPorCredencialEMidia(login, media);
        if (projeto == null) {
            projeto = new Projeto();
            this.inserir(login, media, projeto);
            projeto.getConteudosProjeto().add(projetoConteudo);
        }
        boolean isCompleto = true;
        for (ProjetoConteudo item : projeto.getConteudosProjeto()) {
            isCompleto = isCompleto && item.isAssistido() && item.isBaixado();
        }
        projeto.setStatus((isCompleto ? Status.COMPLETO : Status.INCOMPLETO));
        this.atualizar(projeto);
    }

    public List<Projeto> buscarProjetosIncompletos(int size, Login login, boolean isNaoAssistidos) {
        return getDAO().findProjetosIncompletos(size, login, isNaoAssistidos);
    }
}
