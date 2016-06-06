package br.com.depro.fw.typezero.infrastructure.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.ResultTransformer;

import br.com.depro.fw.typezero.infrastructure.dao.helper.QueryHelper;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriterionUtils;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.fw.typezero.infrastructure.utils.TypezeroPagedList;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 24.06.2012
 * @version 1.1 - Implementando delete por id - 22.10.2015
 * @param <T>
 */
public abstract class TypezeroGenericJPADAO<T extends EntidadeBase> implements GenericDAO<T> {

    protected static final int MODE_COUNT = 1;
    protected static final int MODE_SELECT = 2;
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    private Class<T> persistentClass;

    public List<T> findByAttrs(TypezeroCriteria criteriaRaw) {
    	QueryHelper helper = QueryHelper.newInstance(criteriaRaw);
    	helper.transformer = generateQuery(helper);
        return executeCriteria(helper);
    }
    
	public T findOneByAttrs(TypezeroCriteria criteriaRaw) {
		QueryHelper helper = QueryHelper.newInstance(criteriaRaw);
        generateQuery(helper);
		return executeCriteriaUnique(helper);
	}
	
	public ResultTransformer generateQuery(QueryHelper helper) {
		helper.setPersistentClass(getPersistentClass());
    	return generateQuery(helper.criteriaRaw, helper.criterions, helper.projections, helper.alias, helper.disjunction);
    }
    
    public ResultTransformer generateQuery(TypezeroCriteria criteriaRaw, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction ) {
    	TypezeroCriterionUtils.createLongBlock(criterions, criteriaRaw, "id");
    	return Criteria.DISTINCT_ROOT_ENTITY;
    }
    
    /**
     * Construtor padrao da classes
     */
    @SuppressWarnings("unchecked")
    public TypezeroGenericJPADAO() {
        super();
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @see GenericDAO#save(Object)
     */
    public T save(T entidade) {
    	T entidadeSalva = null;
    	if (entidade.getId() == null) {
    		Long id = (Long) getSession().save(entidade);
    		entidade.setId(id);
    		entidadeSalva = entidade;
    	} else {
    		entidadeSalva = getEntityManager().merge(entidade);
    	}
        return entidadeSalva;
    }

    /**
     * @see GenericDAO#update(Object)
     */
    public T update(T entidade) {
        return getEntityManager().merge(entidade);
    }

    /**
     * @see GenericDAO#delete(Object)
     */
    public void delete(T entidade) throws ApplicationException {
        try {
            getEntityManager().remove(entidade);
        } catch (Exception hexp) {
            if (hexp instanceof ConstraintViolationException) {
                throw new ApplicationException(hexp, "ER0001", hexp.getMessage());
            } else {
                throw new ApplicationException(hexp);
            }
        }
    }

    /**
     * @see GenericDAO#deleteById(Long)
     */
    public void deleteById(Long id) throws ApplicationException {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaDelete<T> delete = cb.createCriteriaDelete(getPersistentClass());
            Root<T> root = delete.from(getPersistentClass());
            delete.where(cb.equal(root.get("id"), id));

            getEntityManager().createQuery(delete).executeUpdate();
        } catch (Exception hexp) {
            if (hexp.getCause() instanceof ConstraintViolationException) {
                throw new ApplicationException(hexp, "ER0001", hexp.getMessage());
            } else {
                throw new ApplicationException(hexp);
            }
        }
    }

    /**
     * @see GenericDAO#refresh(Object)
     */
    public T refresh(T entidade) {
        return findById(entidade.getId());
    }

    /**
     * @see GenericDAO#findById(Serializable)
     */
    public T findById(Long id) {
        return getEntityManager().find(this.getPersistentClass(), id);
    }

    /**
     * @see GenericDAO#buscarTodos()
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query query = getEntityManager().createQuery("FROM " + this.getEntity());
        return query.getResultList();
    }
    
    /**
     * @param criteriaRaw
     * @param criterions
     * @param projections
     * @param mapAlias
     * @param disjunction
     * @return
     */
    public List<T> executeCriteria(TypezeroCriteria criteriaRaw, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction) {
        return executeCriteria(criteriaRaw, criterions, projections, mapAlias, disjunction, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * @param criteriaRaw
     * @param criterions
     * @param projections
     * @param mapAlias
     * @param disjunction
     * @param resultTransformer
     * @return
     */
    public List<T> executeCriteria(TypezeroCriteria criteriaRaw, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction, ResultTransformer resultTransformer) {
    	return executeCriteria(criteriaRaw, criterions, projections, mapAlias, disjunction, resultTransformer, getPersistentClass());
    }
    
    /**
     * @param criteriaRaw
     * @param criterions
     * @param projections
     * @param mapAlias
     * @param disjunction
     * @param resultTransformer
     * @return
     */
    public <E extends Object> List<E> executeCriteria(TypezeroCriteria criteriaRaw, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction, ResultTransformer resultTransformer, Class<E> type) {
    	QueryHelper helper = QueryHelper.newInstance();
    	helper.alias = mapAlias;
    	helper.criteriaRaw = criteriaRaw;
    	helper.criterions = criterions;
    	helper.projections = projections;
    	helper.disjunction = disjunction;
    	helper.transformer = resultTransformer;
    	
    	return executeCriteria(helper, type);
    }
    
    public List<T> executeCriteria(QueryHelper helper) {
    	return executeCriteria(helper, getPersistentClass());
    }
    
    public <E extends Object> List<E> executeCriteria(QueryHelper helper, Class<E> type) {
    	if (helper.criteriaRaw == null) {
    		helper.criteriaRaw = new TypezeroCriteria();
    	}
    	
    	int count = 0;
        int mode = helper.criteriaRaw.isPaged() ? MODE_COUNT : MODE_SELECT;
        TypezeroPagedList<E> typeList = null;

        Criteria criteria = null;
        switch (mode) {
        case MODE_COUNT:
        	criteria = createCriteria(helper.criterions, helper.alias, helper.disjunction, helper.criteriaRaw);
            criteria.setProjection(Projections.rowCount());
            count = ((Number) criteria.uniqueResult()).intValue();
            if (count == 0) {
                typeList = new TypezeroPagedList<E>();
                break;
            }
        case MODE_SELECT:
        	criteria = createCriteria(helper.criterions, helper.alias, helper.disjunction, helper.criteriaRaw);
            if (helper.projections != null && helper.projections.getLength() > 0) {
                criteria.setProjection(helper.projections);
            }
            
        	criteria.setResultTransformer(helper.transformer);
            configureCriteria(criteria, helper.criteriaRaw);
            typeList = new TypezeroPagedList<E>(count, criteria.list());
            break;
        }
        return typeList;
    }

    public T executeCriteriaUnique(List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction) {
        return executeCriteriaUnique(criterions, projections, mapAlias, disjunction, Criteria.DISTINCT_ROOT_ENTITY);
    }

    public T executeCriteriaUnique(List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction, ResultTransformer resultTransformer) {
    	QueryHelper helper = QueryHelper.newInstance();
    	helper.alias = mapAlias;
    	helper.criterions = criterions;
    	helper.projections = projections;
    	helper.disjunction = disjunction;
    	helper.transformer = resultTransformer;
        return (T) executeCriteriaUnique(helper, getPersistentClass());
    }
    
    public T executeCriteriaUnique(QueryHelper helper) {
    	return executeCriteriaUnique(helper, getPersistentClass());
    }
    
    public <E extends Object> E executeCriteriaUnique(QueryHelper helper, Class<E> type) {
        Criteria criteria = null;
        criteria = createCriteria(helper.criterions, helper.alias, helper.disjunction, helper.criteriaRaw);
        if (helper.projections != null && helper.projections.getLength() > 0) {
            criteria.setProjection(helper.projections);
        }
        configureCriteria(criteria, helper.criteriaRaw);
        criteria.setResultTransformer(helper.transformer);
        return (E) criteria.uniqueResult();
    }

    private Criteria createCriteria(List<Criterion> criterions, Map<String, String> mapAlias, Disjunction disjunction, TypezeroCriteria criteriaRaw) {
        Criteria criteria = getSession().createCriteria(criteriaRaw.getPersistenceClass() != null ? criteriaRaw.getPersistenceClass() : this.getPersistentClass(), 
        		StringUtils.isNotBlank(criteriaRaw.getAlias()) ? criteriaRaw.getAlias() : getPersistentClassAlias());
        if (mapAlias != null) {
            for (Map.Entry<String, String> entry : mapAlias.entrySet()) {
                criteria.createAlias(entry.getKey(), entry.getValue());
            }
        }
        addCriterions(criteria, criterions);
        if (disjunction != null && disjunction.conditions().iterator().hasNext()) {
            criteria.add(disjunction);
        }
        return criteria;
    }

    private void configureCriteria(Criteria criteria, TypezeroCriteria criteriaRaw) {
        for (Map.Entry<String, String> entry : criteriaRaw.getMapOrdenacao().entrySet()) {
            if ("DESC".equalsIgnoreCase(entry.getValue())) {
            	criteria.addOrder(Order.desc(entry.getKey()));
            } else {
            	criteria.addOrder(Order.asc(entry.getKey()));
            }
        }
        if (criteriaRaw.isPaged()) {
            if (criteriaRaw.getOffset() >= 0) {
            	criteria.setFirstResult(criteriaRaw.getOffset() * criteriaRaw.getLimit());
            }
        }
        
        if (criteriaRaw.getLimit() > 0) {
        	criteria.setMaxResults(criteriaRaw.getLimit());
        }
    }

    private void addCriterions(Criteria criteria, List<Criterion> criterions) {
        if (CollectionUtils.isNotEmpty(criterions)) {
            for (Criterion crit : criterions) {
                criteria.add(crit);
            }
        }
    }

    /**
     * @param sort
     * @return
     */
    private boolean constainsField(String sort) {
        if (StringUtils.isNotBlank(sort)) {
            for (Field field : getPersistentClass().getDeclaredFields()) {
                if (field.getName().contains(sort)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna a entidade em questao
     *
     * @return
     */
    public String getEntity() {
        return getPersistentClass().getSimpleName();
    }

    /**
     * @return the persistentClass
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    
    /**
     * @return the alias
     */
    public String getPersistentClassAlias() {
        return getPersistentClass().getSimpleName().toLowerCase();
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    /**
     * Retorna a sessao do Hibernate
     *
     * @return
     */
    protected Session getSession() {
        return (Session) this.getEntityManager().getDelegate();
    }
    
}
