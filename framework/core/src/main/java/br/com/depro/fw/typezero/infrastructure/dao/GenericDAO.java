package br.com.depro.fw.typezero.infrastructure.dao;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 24/06/2012
 *
 * @param <T>
 */
public interface GenericDAO<T extends EntidadeBase> {

    T findById(Long id) throws ApplicationException;

    List<T> findAll();

    List<T> findByAttrs(TypezeroCriteria criteriaRaw);
    
    T findOneByAttrs(TypezeroCriteria criteriaRaw);

    T save(T entidade);

    T update(T entidade) throws ApplicationException;

    void delete(T entidade) throws ApplicationException;

    void deleteById(Long id) throws ApplicationException;

    T refresh(T entidade);

}
