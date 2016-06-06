package br.com.depro.fw.typezero.infrastructure.dao.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

/**
 * @author rsouza
 * @version 2.0 - Versao Inicial - 31.05.2016
 */
public class QueryHelper {
	
	public TypezeroCriteria criteriaRaw;
	public List<Criterion> criterions = new ArrayList<Criterion>();
	public Map<String, String> alias = new HashMap<String, String>();
	public Disjunction disjunction = Restrictions.disjunction();
	public ProjectionList projections = Projections.projectionList();
	public ResultTransformer transformer = Criteria.DISTINCT_ROOT_ENTITY;
	public Class<?> persistentClass = null;
	public DetachedCriteria dc;;

	public QueryHelper(TypezeroCriteria criteriaRaw) {
		this.criteriaRaw = criteriaRaw;
	}
	
	public void fillCriteriaHelper(TypezeroCriteria criteria) {
		this.criteriaRaw = new TypezeroCriteria(criteria);
	}
	
	public void setPersistentClass(Class<?> type) {
		this.persistentClass = type;
		this.criteriaRaw.setPersistenceClass(type);
		this.criteriaRaw.setAlias(type.getSimpleName().toLowerCase());
	}
	
	public <E extends TypezeroCriteria> E newInstanceCriteriaHelper(Class<E> clazz) {
		try {
			Constructor<E> constructor = (Constructor<E>) clazz.getConstructor(TypezeroCriteria.class);
			return constructor.newInstance(criteriaRaw);
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		return null;
	}
	
	public DetachedCriteria newInstanceDC() {
		DetachedCriteria dc = DetachedCriteria.forClass(persistentClass, criteriaRaw.getAlias());
		
		for (Map.Entry<String, String> entry : alias.entrySet()) {
			dc.createAlias(entry.getKey(), entry.getValue());
		}
		
		for (Criterion criterion : criterions) {
			dc.add(criterion);
		}
		
		if (projections.getLength() > 0) {
			for (int i = 0 ; i < projections.getLength() ; i++) {
				dc.setProjection(projections.getProjection(i));
			}
			transformer = Criteria.PROJECTION;
		}
		
		dc.setResultTransformer(transformer);
		return dc;
	}

    public static QueryHelper newInstance() {
    	return new QueryHelper(new TypezeroCriteria());
    }
    
    public static QueryHelper newInstance(TypezeroCriteria criteriaRaw) {
    	return new QueryHelper(criteriaRaw != null ? criteriaRaw : new TypezeroCriteria());
    }
}
