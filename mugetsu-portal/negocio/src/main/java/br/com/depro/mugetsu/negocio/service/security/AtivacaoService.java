package br.com.depro.mugetsu.negocio.service.security;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.security.Ativacao;
import br.com.depro.mugetsu.model.security.Login;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.09.2012
 */
public interface AtivacaoService extends TypezeroGenericService<Ativacao> {

    /**
     * Gera codigo de ativacao e envia para o email do usuario
     * 
     * @param login
     * @return TODO
     * @throws ApplicationException Caso algum erro ocorra
     */
    Login gerarAtivacao(Login login) throws ApplicationException;

    /**
     * Ativa conta do usuario pelo codigo de ativacao enviado por email
     * 
     * @param codigoAtivacao
     * @throws ApplicationException Caso algum erro ocorra
     */
    void ativarContaUsuario(String codigoAtivacao) throws ApplicationException;
}
