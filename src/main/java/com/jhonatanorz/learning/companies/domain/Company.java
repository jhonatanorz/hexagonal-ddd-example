package com.jhonatanorz.learning.companies.domain;

import com.jhonatanorz.learning.shared.domain.Identifiable;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Company implements Identifiable<CompanyId> {

    private final CompanyId id;
    private CompanyName name;
    private Subdomain subdomain;

    public Company(CompanyId id, CompanyName name, Subdomain subdomain) {

        ensureValid(id);
        this.id = id;

        changeName(name);
        changeSubdomain(subdomain);
    }

    public void changeName(CompanyName name) {
        ensureValid(name);
        this.name = name;
    }

    public void changeSubdomain(Subdomain newSubdomain) {

        ensureValid(newSubdomain);
        this.subdomain = newSubdomain;
    }


    private void ensureValid(CompanyId id) {
        if (id == null) {
            throw new IllegalArgumentException("Company id can't be null");
        }
    }

    private void ensureValid(CompanyName name) {
        if (name == null) {
            throw new IllegalArgumentException("Company name can't be null");
        }
    }

    private void ensureValid(Subdomain subdomain) {
        if (subdomain == null) {
            throw new IllegalArgumentException("Company subdomain can't be null");
        }
    }

}
