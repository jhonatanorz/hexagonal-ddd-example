package com.jhonatanorz.learning.shared.domain.repositories;


import com.jhonatanorz.learning.shared.domain.Identifier;

public interface CrudRepository<ID extends Identifier, T> extends QueryRepository<ID, T>{

    void save(T entity);

    void delete(ID id);

}
