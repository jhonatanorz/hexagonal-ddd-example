package com.jhonatanorz.learning.companies.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyName;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.infraestructure.persistence.DatabaseMapping;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class CompanyDatabaseMapping implements DatabaseMapping<Company> {

    @Id
    private String id;

    private String name;
    private String subdomain;

    public CompanyDatabaseMapping(Company company) {

        ensureValid(company);
        mapValues(company);
    }

    @Override
    public Company domainMap() {

        return new Company(
                new CompanyId(id),
                new CompanyName(name),
                new Subdomain(subdomain)
        );
    }


    private void ensureValid(Company company) {

        if (company == null) {
            throw new IllegalArgumentException("Company is required");
        }
    }

    private void mapValues(Company company) {

        id = company.getId().value();
        name = company.getName().value();
        subdomain = company.getSubdomain().value();
    }

}
