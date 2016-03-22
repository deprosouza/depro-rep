package br.com.depro.mugetsu.core.service.security.impl;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.security.Criptografia;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.security.LoginDAO;
import br.com.depro.mugetsu.core.service.security.AtivacaoService;
import br.com.depro.mugetsu.core.service.security.LoginService;
import br.com.depro.mugetsu.core.service.security.RoleService;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.security.Login;

/**
 * Classe de servicos de usuario <br><br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 *
 * @since 1.0
 *
 */
@Service
public class LoginServiceImpl extends TypezeroGenericServiceImpl<Login, LoginDAO> implements LoginService {

    @Autowired
    private AtivacaoService ativacaoService;
    @Autowired
    private RoleService roleService;

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.dsphere.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(LoginDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see TypezeroGenericService#salvar(Object)
     */
    public Login salvar(Login login) throws ApplicationException {
        try {
            Criptografia criptografia = new Criptografia();
            login.setSenha(criptografia.encriptar(login.getSenha()));
            login.setStatus(Status.PENDENTE);
            login.getRoles().add(this.roleService.buscarRolePadrao());
            this.ativacaoService.gerarAtivacao(login);
        } catch (NoSuchAlgorithmException nsaexp) {
            throw new ApplicationException(nsaexp, "MG0759");
        }
        return null;
    }

    /**
     * @see LoginService#distinctEmail(String)
     */
    public boolean distinctEmail(String email) {
        return super.getDAO().obterPorUsername(email) != null ? false : true;
    }

    /**
     * @see LoginService#distinctUsername(String)
     */
    public boolean distinctUsername(String username) {
        return super.getDAO().obterPorUsername(username) != null ? false : true;
    }
}
