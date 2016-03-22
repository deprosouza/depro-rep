package br.com.depro.mugetsu.web.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.mugetsu.model.security.Permissao;
import br.com.depro.mugetsu.model.security.Role;
import br.com.depro.mugetsu.negocio.service.security.RoleService;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Incial - 21.07.2012
 */
public class DualListboPermissao extends Div implements IdSpace {
	
	 /** Numero de serie da classe */
    private static final long serialVersionUID = 5183321186606483396L;
     
    @Wire
    private Listbox notGrantedLb;
    @Wire
    private Listbox grantedLb;
 
    private ListModelList<Permissao> notGrantedModel;
    private ListModelList<Permissao> grantedModel;
    
    private Role roleselecionado;
 
    /**
     * Construtor padrao da classe
     */
    public DualListboPermissao() {
        Executions.createComponents(NavigateEnum.COMPISITE_DUAL_LIST_ROLE.getUri(), this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this, this);
        grantedLb.setModel(grantedModel = new ListModelList<Permissao>());
    }
 
    
    
    @Listen("onClick = #grantBtn")
    public void chooseItem() {
        Events.postEvent(new ChooseEvent(this, chooseOne()));
    }
 
    @Listen("onClick = #removeBtn")
    public void unchooseItem() {
        Events.postEvent(new ChooseEvent(this, unchooseOne()));
    }
 
    @Listen("onClick = #grantAllBtn")
    public void chooseAllItem() {
        for (int i = 0, j = notGrantedModel.getSize(); i < j; i++) {
            grantedModel.add(notGrantedModel.getElementAt(i));
        }
        notGrantedModel.clear();
    }
 
    @Listen("onClick = #removeAllBtn")
    public void unchooseAll() {
        for (int i = 0, j = grantedModel.getSize(); i < j; i++) {
            notGrantedModel.add(grantedModel.getElementAt(i));
        }
        grantedModel.clear();
    }
    
    /**
     * Salvar as alterações feitas
     */
    @Listen("onClick = #salveBtn")
    public void saveChanges() {
    	try {
    		RoleService service = (RoleService) MugetsuSpringUtils.getBean(RoleService.class);
    		service.atualizarRolePermissoes(this.roleselecionado, grantedModel.getInnerList());
    		grantedModel.clear();
    		for (Permissao permissao : this.roleselecionado.getPermissoes()) {
    			grantedModel.add(permissao);
    			notGrantedModel.remove(permissao);
    		}
    	} catch (CoreException nexp) {
    		ViewHandler.showMessageErro(nexp);
    	}
    }
 
    /**
     * Set as permissacoes que  nao estao autorizadas e autorizadas
     * 
     * @param permissoesNotGranted
     * @param permissoesGranted
     */
    public void setModel(List<Permissao> permissoesNotGranted, List<Permissao> permissoesGranted, Role role) {
    	this.notGrantedModel = new ListModelList<Permissao>(permissoesNotGranted);
        notGrantedLb.setModel(this.notGrantedModel);
        
        this.grantedModel = new ListModelList<Permissao>(permissoesGranted);
    	this.grantedLb.setModel(this.grantedModel);
    	
    	this.roleselecionado = role;
    }
 
    /**
     * @return current chosen data list
     */
    public List<Permissao> getChosenDataList() {
        return new ArrayList<Permissao>(grantedModel);
    }
 
    /**
     * @return
     */
    private Set<Permissao> chooseOne() {
        Set<Permissao> set = notGrantedModel.getSelection();
        for (Permissao selectedItem : set) {
			grantedModel.add(selectedItem);
			notGrantedModel.remove(selectedItem);
    	}
        return set;
    }
 
    private Set<Permissao> unchooseOne() {
        Set<Permissao> set = grantedModel.getSelection();
        for (Permissao selectedItem : set) {
            notGrantedModel.add(selectedItem);
            grantedModel.remove(selectedItem);
        }
        return set;
    }
 
    // Customized Event
    public class ChooseEvent extends Event {
        /**
         * 
         */
        private static final long serialVersionUID = -7334906383953342976L;
 
        public ChooseEvent(Component target, Set<Permissao> data) {
            super("onChoose", target, data);
        }
    }
}
