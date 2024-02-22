package com.jhonatanorz.learning.companies.domain;


import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {

    public CompanyNotFoundException(CompanyId id) {
        super("Company with id " + id + " does not exist");
    }
}
