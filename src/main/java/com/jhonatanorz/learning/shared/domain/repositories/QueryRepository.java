package com.jhonatanorz.learning.shared.domain.repositories;


import com.jhonatanorz.learning.shared.domain.Identifier;

import java.util.Optional;

public interface QueryRepository<ID extends Identifier, T> extends Repository<ID, T> {

    Optional<T> find(ID id);
}
