package br.com.depro.fw.typezero.infrastructure.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.depro.fw.typezero.infrastructure.annotation.Audit;
import br.com.depro.fw.typezero.infrastructure.dao.GenericDAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.fw.typezero.infrastructure.model.audit.Auditoria.EventAudit;

/**
 * @author rsouza
 * @version 1.0 - Versao Incial - 15.07.2012
 *
 * @param <T>
 * @param <DAO>
 */
public abstract class TypezeroGenericServiceImpl<T extends EntidadeBase, DAO extends GenericDAO<T>> implements TypezeroGenericService<T> {

    private DAO dao;

	@Autowired
	public void initDAO(DAO dao) {
		setDAO(dao);
	}

    /**
     * Construtor padrao da classe
     */
    @SuppressWarnings("unchecked")
    public TypezeroGenericServiceImpl() {
        super();
        this.dao = (DAO) ((Class<DAO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).cast(dao);
    }

    /**
     * @see TypezeroGenericService#salvar(Object)
     */
    @Audit(eventAudit = EventAudit.SALVAR)
    public T salvar(T entidade) throws ApplicationException {
        return this.getDAO().save(entidade);
    }

    /**
     * @see TypezeroGenericService#atualizar(Object)
     */
    @Audit(eventAudit = EventAudit.EDITAR)
    public T atualizar(T entidade) throws ApplicationException {
        return this.getDAO().update(entidade);
    }

    /**
     * @see TypezeroGenericService#excluir(Object)
     */
    @Audit(eventAudit = EventAudit.DELETAR)
    public void excluir(T entidade) throws ApplicationException {
        try {
            this.getDAO().delete(entidade);
        } catch (ApplicationException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see TypezeroGenericService#excluirPorId(Long)
     */
    @Audit(eventAudit = EventAudit.DELETAR)
    public void excluirPorId(Long id) throws ApplicationException {
        try {
            this.getDAO().deleteById(id);
        } catch (ApplicationException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see TypezeroGenericService#refresh(Object)
     */
    public T refresh(T entidade) throws ApplicationException {
        try {
            return this.getDAO().refresh(entidade);
        } catch (ApplicationException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see TypezeroGenericService#findById(Serializable)
     */
    @Audit(eventAudit = EventAudit.BUSCAR)
    public T buscarPorId(Long id) throws ApplicationException {
        return getDAO().findById(id);
    }

    /**
     * @see TypezeroGenericService#buscarTodos()
     */
    @Audit(eventAudit = EventAudit.BUSCAR)
    public List<T> buscarTodos() {
        return getDAO().findAll();
    }

    /**
     * @see TypezeroGenericService#buscarPorAtributos(TypezeroCriteria)
     */
    @Audit(eventAudit = EventAudit.BUSCAR)
    public List<T> buscarPorAtributos(TypezeroCriteria criteriaRaw) {
        return getDAO().findByAttrs(criteriaRaw);
    }

    public DAO getDAO() {
        return dao;
    }

    protected void setDAO(DAO dao) {
        this.dao = dao;
    }
}
