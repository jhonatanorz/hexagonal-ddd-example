package com.jhonatanorz.learning.shared.infraestructure.persistence;


import com.jhonatanorz.learning.shared.domain.Identifiable;
import com.jhonatanorz.learning.shared.domain.Identifier;
import com.jhonatanorz.learning.shared.domain.repositories.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<ID extends Identifier, T extends Identifiable<ID>> implements CrudRepository<ID, T> {

    protected final Map<ID, T> memory;

    public InMemoryRepository() {
        this.memory = new HashMap<>();
    }

    @Override
    public Optional<T> find(ID id) {
        return Optional.ofNullable(memory.get(id));
    }

    @Override
    public void save(T entity) {
        memory.put(entity.getId(), entity);
    }

    @Override
    public void delete(ID id) {
        memory.remove(id);
    }

}

