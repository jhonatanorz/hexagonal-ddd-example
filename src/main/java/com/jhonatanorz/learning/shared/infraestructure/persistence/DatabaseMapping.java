package com.jhonatanorz.learning.shared.infraestructure.persistence;

public interface DatabaseMapping<T> {

    T domainMap();
}
