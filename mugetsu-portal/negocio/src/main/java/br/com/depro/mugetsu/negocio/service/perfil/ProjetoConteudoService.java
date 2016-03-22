package br.com.depro.mugetsu.negocio.service.perfil;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 02.09.2012
 */
public interface ProjetoConteudoService extends TypezeroGenericService<ProjetoConteudo> {

    /**
     * Lista todas as entidades que o usuairo nao assitiu que tenha feito
     * download
     *
     * @param login
     * @return Lista de Entidade
     */
    Integer buscarQuantidadeNaoAssistidos(Login login);
}
