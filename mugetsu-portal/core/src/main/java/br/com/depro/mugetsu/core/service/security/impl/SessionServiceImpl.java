package br.com.depro.mugetsu.core.service.security.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.security.Criptografia;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.security.LoginDAO;
import br.com.depro.mugetsu.core.dao.security.SessionDAO;
import br.com.depro.mugetsu.core.exception.SenhaInvalidaException;
import br.com.depro.mugetsu.core.exception.UsuarioInexistenteExsception;
import br.com.depro.mugetsu.core.service.security.LoginService;
import br.com.depro.mugetsu.core.service.security.SessionService;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.model.security.Session;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.07.2012
 */
@Service
public class SessionServiceImpl extends TypezeroGenericServiceImpl<Session, SessionDAO> implements SessionService {

    @Autowired
    private LoginDAO loginDAO;

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.dsphere.mugetsu.web.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(SessionDAO sessionDAO) {
        super.setDAO(sessionDAO);
    }

    /**
     * @see LoginService#efetuarLogin(String, String, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Session efetuarLogin(String username, String senha, String enderecoIP)
            throws ApplicationException {
        try {

            Login login = this.loginDAO.obterPorUsername(username);
            if (login == null) {
                throw new UsuarioInexistenteExsception("MG0751");
            } else if (!login.getStatus().equals(Status.ATIVO)) {
                throw new UsuarioInexistenteExsception("MG0753");
            } else {
                String senhaEncriptada = Criptografia.getInstance().encriptar(senha);
                if (!login.getSenha().equals(senhaEncriptada)) {
                    throw new SenhaInvalidaException("MG0752");
                }
            }

            if (login.getStatus().equals(Status.BLOQUEADO) || login.getStatus().equals(Status.PENDENTE)) {
                throw new ApplicationException("MG0753");
            }

            Session session = new Session();
            session.setLogin(login);
            session.setDataInicio(new Date());
            session.setRemoteAddress(enderecoIP);
            session.setStatus(Status.ATIVO);
            session.setNumeroControle(enderecoIP.replaceAll("\\.", "") + String.valueOf(Math.abs(session.hashCode())));

            super.salvar(session);
            return session;
        } catch (NoSuchAlgorithmException nsaexp) {
            throw new ApplicationException(nsaexp, "MG0759");
        }
    }

    /**
     * @see SessionService#efetuarLoggout(Session)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void efetuarLoggout(Session session) throws ApplicationException {
        session.setDatatFim(new Date());
        session.setStatus(Status.INATIVO);
        super.getDAO().atualizar(session);
    }
}
