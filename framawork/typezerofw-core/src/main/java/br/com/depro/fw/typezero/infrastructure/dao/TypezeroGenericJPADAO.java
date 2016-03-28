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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.ResultTransformer;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
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
    private Class<T> perssitentClass;

    public abstract List<T> findByAttrs(TypezeroCriteria criteriaRaw);

    /**
     * Construtor padrao da classes
     */
    @SuppressWarnings("unchecked")
    public TypezeroGenericJPADAO() {
        super();
        this.perssitentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @see GenericDAO#save(Object)
     */
    public T save(T entidade) {
        T entidadeSalva = getEntityManager().merge(entidade);
        getEntityManager().flush();
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
            CriteriaDelete<T> delete = cb.createCriteriaDelete(getPerssitentClass());
            Root<T> root = delete.from(getPerssitentClass());
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
    public T refresh(T entidade) throws ApplicationException {
        try {
            return findById(entidade.getId());
        } catch (HibernateException hexp) {
            throw new ApplicationException(hexp);
        }
    }

    /**
     * @see GenericDAO#findById(Serializable)
     */
    public T findById(Long id) {
        return getEntityManager().find(this.getPerssitentClass(), id);
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
     * @param dcriteria
     * @param criterions
     * @param projections
     * @param mapAlias
     * @param disjunction
     * @return
     */
    public List<T> executeCriteria(TypezeroCriteria dcriteria, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction) {
        return executeCriteria(dcriteria, criterions, projections, mapAlias, disjunction, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * @param dcriteria
     * @param criterions
     * @param projections
     * @param mapAlias
     * @param disjunction
     * @param resultTransformer
     * @return
     */
    public List<T> executeCriteria(TypezeroCriteria dcriteria, List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction, ResultTransformer resultTransformer) {
        int count = 0;
        int mode = dcriteria.isPaged() ? MODE_COUNT : MODE_SELECT;
        TypezeroPagedList<T> typeList = null;

        Criteria criteria = null;
        switch (mode) {
        case MODE_COUNT:
            criteria = createCriteria(criterions, mapAlias, disjunction);
            criteria.setProjection(Projections.rowCount());
            count = ((Number) criteria.uniqueResult()).intValue();
            if (count == 0) {
                typeList = new TypezeroPagedList<T>();
                break;
            }
        case MODE_SELECT:
            criteria = createCriteria(criterions, mapAlias, disjunction);
            if (projections != null) {
                criteria.setProjection(projections);
            }
            criteria.setResultTransformer(resultTransformer);
            configureCriteria(criteria, dcriteria);
            typeList = new TypezeroPagedList<T>(count, criteria.list());
            break;
        }
        return typeList;
    }

    public T executeCriteriaUnique(List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction) {
        return executeCriteriaUnique(criterions, projections, mapAlias, disjunction, Criteria.DISTINCT_ROOT_ENTITY);
    }

    public T executeCriteriaUnique(List<Criterion> criterions, ProjectionList projections, Map<String, String> mapAlias, Disjunction disjunction, ResultTransformer resultTransformer) {
        Criteria criteria = null;
        criteria = createCriteria(criterions, mapAlias, disjunction);
        if (projections != null) {
            criteria.setProjection(projections);
        }
        criteria.setResultTransformer(resultTransformer);
        return (T) criteria.uniqueResult();
    }

    private Criteria createCriteria(List<Criterion> criterions, Map<String, String> mapAlias, Disjunction disjunction) {
        Criteria criteria = getSession().createCriteria(this.getPerssitentClass(), getPerssitentClass().getSimpleName().toLowerCase());
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

    private void configureCriteria(Criteria criteria, TypezeroCriteria dcriteria) {
        for (Map.Entry<String, String> entry : dcriteria.getMapOrdenacao().entrySet()) {
            if (constainsField(entry.getKey())) {
                if ("DESC".equalsIgnoreCase(entry.getValue())) {
                    criteria.addOrder(Order.desc(entry.getKey()));
                } else {
                    criteria.addOrder(Order.asc(entry.getKey()));
                }
            }
        }
        if (dcriteria.isPaged()) {
            if (dcriteria.getLimit() > 0) {
                criteria.setMaxResults(dcriteria.getLimit());
            }
            if (dcriteria.getOffset() >= 0) {
                criteria.setFirstResult(dcriteria.getOffset() * dcriteria.getLimit());
            }
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
            for (Field field : getPerssitentClass().getDeclaredFields()) {
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
        return getPerssitentClass().getSimpleName();
    }

    /**
     * @return the perssitentClass
     */
    public Class<T> getPerssitentClass() {
        return perssitentClass;
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
