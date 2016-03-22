package br.com.depro.mugetsu.negocio.service.security;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.security.Login;

/**
 * Interface para classe de servicos de usuario <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
public interface LoginService extends TypezeroGenericService<Login> {

    /**
     * Valida se email ja existe
     * 
     * @param email
     * @return Retornar true caso email nao exista na base
     */
    boolean distinctEmail(String email);

    /**
     * Valida se username ja existe
     * 
     * @param username
     * @return Retornar true caso username nao exista na base
     */
    boolean distinctUsername(String username);
}
