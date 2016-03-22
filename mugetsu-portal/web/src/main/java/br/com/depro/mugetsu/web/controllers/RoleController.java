package br.com.depro.mugetsu.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.mugetsu.model.security.Permissao;
import br.com.depro.mugetsu.model.security.Role;
import br.com.depro.mugetsu.negocio.service.security.PermissaoService;
import br.com.depro.mugetsu.negocio.service.security.RoleService;
import br.com.depro.mugetsu.web.composite.DualListboPermissao;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 21.07.2012
 */
public class RoleController extends BaseController {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 2299850915349519296L;

	@Wire
	private DualListboPermissao dualBox;

	@TypezeroBean
	private PermissaoService permissaoService;
	
	@TypezeroBean
	private RoleService roleService;
	
	private List<Permissao> permissoes;
	
	private List<Role> roles;
	
	private Role roleSelecionado;
	
	@AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view){
    	super.init(view);
        this.dualBox.setVisible(false);
        if (this.roles == null) {
    		this.roles = roleService.listarTodas();
    	}
        
        if (this.permissoes == null) {
    		this.permissoes = permissaoService.listarTodas();
    	}
    }
    
    /**
     * Retorna todos os roles
     * 
     * @return
     * @throws ApplicationException Caso algum erro ocorra.
     * @throws CoreException Caso algim erro ocorra.
     */
    public List<Role> getRoles() throws ApplicationException, CoreException {
    	return roles;
    }

	/**
	 * @return the roleSelecionado
	 */
	public Role getRoleSelecionado() {
		return roleSelecionado;
	}

	/**
	 * @param roleSelecionado the roleSelecionado to set
	 */
	public void setRoleSelecionado(Role roleSelecionado) {
		try {
			this.roleSelecionado = roleSelecionado;
			this.dualBox.setVisible(true);
			List<Permissao> permissoesRole = this.permissaoService.obterPermissoesPorIdRole(this.roleSelecionado.getId());
			List<Permissao> newList = new ArrayList<Permissao>();
			for (Permissao permNot : permissoes) {
				boolean isNotEquals = true;
				for (Permissao permRole : permissoesRole) {
					if (permRole.getId().equals(permNot.getId())) {
						isNotEquals = false;
					}
				}
				if (isNotEquals) {
					newList.add(permNot);
				}
			}
			this.dualBox.setModel(newList, permissoesRole, roleSelecionado);
		} catch (CoreException nexp) {
			ViewHandler.showMessageErro(nexp);
		}
	}
}
