package com.jhonatanorz.learning.companies.factories;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyName;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.factories.SubdomainMother;

public class CompanyMother {

    public static Company random() {
        return create(
                CompanyIdMother.random(),
                CompanyNameMother.random(),
                SubdomainMother.random()
        );
    }

    public static Company withId(CompanyId value) {
        return create(
                value,
                CompanyNameMother.random(),
                SubdomainMother.random()
        );
    }

    public static Company withName(CompanyName value) {
        return create(
                CompanyIdMother.random(),
                value,
                SubdomainMother.random()
        );
    }

    public static Company withSubdomain(Subdomain value) {
        return create(
                CompanyIdMother.random(),
                CompanyNameMother.random(),
                value
        );
    }

    public static Company create(CompanyId id, CompanyName name, Subdomain subdomain) {
        return new Company(id, name, subdomain);
    }
}
