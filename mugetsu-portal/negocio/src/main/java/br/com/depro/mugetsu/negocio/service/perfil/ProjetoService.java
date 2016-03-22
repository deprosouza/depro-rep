package br.com.depro.mugetsu.negocio.service.perfil;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 23.07.2012
 */
public interface ProjetoService extends TypezeroGenericService<Projeto> {

    /**
     * Inseri o projeto
     *
     * @param login
     * @param media
     * @throws ApplicationException Caso algum erro ocorra.
     */
	void inserir(Login login, Media media) throws ApplicationException;

    /**
     * Inseri um novo projeto
     *
     * @param login
     * @param media
     * @param projeto
     * @throws ApplicationException
     */
	void inserir(Login login, Media media, Projeto projeto) throws ApplicationException;

    /**
     * Lista projetos do usuario especificado.
     *
     * @param login
     * @return Lista de entidades
     * @throws ApplicationException Caso algum erro ocorra
     */
    List<Projeto> listarProjetosPorUsuario(Login login) throws ApplicationException;

    /**
     * Lista projetos do usuario especificado.
     *
     * @param login
     * @return Lista de entidades
     * @throws ApplicationException Caso algum erro ocorra
     */
    List<Projeto> listarProjetosPorUsuarioEPrimeiraLetra(Login login, String letra) throws ApplicationException;

    /**
     * Obtem um projeto especido do usuario requisitado
     *
     * @param login
     * @param media
     * @return Entidade
     * @throws ApplicationException Caso algum erro ocorra
     */
    Projeto buscarPorCredencialEMidia(Login login, Media media) throws ApplicationException;

    /**
     * Obtem um projeto especido do usuario requisitado
     *
     * @param login
     * @param id
     * @return Entidade
     * @throws ApplicationException Caso algum erro ocorra
     */
    Projeto buscarPorCredencialEIdMedia(Login login, Long id) throws ApplicationException;

    /**
     * Cria ou atualiza projeto conteudo
     *
     * @param login
     * @param projetoConteudo
     * @param conteudo
     * @throws ApplicationException Caso algum erro ocorra.
     */
    void atualizarProjetoConteudo(Login login, ProjetoConteudo projetoConteudo) throws ApplicationException;

    /**
     * Busca lista de projetos incompletos
     *
     * @param size
     * @param login
     * @param isNaoAssistidos
     * @return Lista de entidade {@link Projeto}
     */
    List<Projeto> buscarProjetosIncompletos(int size, Login login, boolean isNaoAssistidos);
}
