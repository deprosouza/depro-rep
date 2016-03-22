package br.com.depro.mugetsu.core.service.security.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.mail.MailConfiguration;
import br.com.depro.fw.typezero.infrastructure.mail.MailSender;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.core.dao.security.AtivacaoDAO;
import br.com.depro.mugetsu.core.service.security.AtivacaoService;
import br.com.depro.mugetsu.core.service.security.RoleService;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;
import br.com.depro.mugetsu.model.security.Ativacao;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.09.2012
 */
@Service
public class AtivacaoServiceImpl extends TypezeroGenericServiceImpl<Ativacao, AtivacaoDAO> implements AtivacaoService {

    /**
     * Chave para o caminho do temaplete de ativacao de email
     */
    private static String KEY_PATH_TEMPLATE_EMAIL_ATIVACAO = "dir.temaplate.xslt.ativacao.conta";
    @Autowired
    private PropConfig propConfig;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private RoleService roleService;

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericDAO)
     */
    @Autowired
    public void initDAO(AtivacaoDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see AtivacaoService#gerarAtivacao(Login)
     */
    public void gerarAtivacao(Login login) throws ApplicationException {
        Ativacao ativacao = new Ativacao(login);
        super.getDAO().salvar(ativacao);
        File file = new File(this.propConfig.get(KEY_PATH_TEMPLATE_EMAIL_ATIVACAO));
        MailConfiguration configuration = new MailConfiguration(ativacao, "Confirmacao de ativacao da conta", login.getEmail(), file);
        this.mailSender.enviarEmail(configuration);
    }

    /**
     * @see AtivacaoService#ativarContaUsuario(String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void ativarContaUsuario(String codigoAtivacao) throws ApplicationException {
        Ativacao ativacao = super.getDAO().obterPorCondigoAtivacao(codigoAtivacao);
        if (ativacao == null) {
            throw new ApplicationException("MG1244");
        } else if (ativacao.getStatus().equals(Status.ATIVO)) {
            throw new ApplicationException("MG1245");
        } else if (ativacao.getStatus().equals(Status.EXPIRADO)) {
            throw new ApplicationException("MG1246");
        } else if (ativacao.getStatus().equals(Status.PENDENTE)) {
            ativacao.setStatus(Status.ATIVO);
            ativacao.getLogin().setStatus(Status.ATIVO);
            ativacao.getLogin().setRoles(roleService.buscaRolesPorTipoAcesso(TipoAcessoEnum.USER, TipoAcessoEnum.COMMON));
            super.getDAO().atualizar(ativacao);
        } else {
            throw new ApplicationException("MG1247");
        }
    }
}
