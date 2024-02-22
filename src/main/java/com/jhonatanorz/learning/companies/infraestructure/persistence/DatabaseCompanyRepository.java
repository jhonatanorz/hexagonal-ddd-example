package com.jhonatanorz.learning.companies.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyRepository;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DatabaseCompanyRepository implements CompanyRepository {

    private final SpringDataJpaCompanyRepository springRepository;

    public DatabaseCompanyRepository(SpringDataJpaCompanyRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<Company> find(Subdomain subdomain) {

        ensureValid(subdomain);

        return springRepository.findBySubdomainEqualsIgnoreCase(subdomain.value())
                .map(CompanyDatabaseMapping::domainMap);
    }

    @Override
    public Optional<Company> find(CompanyId companyId) {

        ensureValid(companyId);

        return springRepository.findById(companyId.value())
                .map(CompanyDatabaseMapping::domainMap);
    }

    @Override
    public void save(Company company) {

        ensureValid(company);
        springRepository.save(new CompanyDatabaseMapping(company));
    }

    @Override
    public void delete(CompanyId companyId) {

        ensureValid(companyId);
        springRepository.deleteById(companyId.value());
    }


    private void ensureValid(Subdomain subdomain) {

        if (subdomain == null) {
            throw new IllegalArgumentException("Subdomain is required to perform this search");
        }
    }

    private void ensureValid(CompanyId companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("Company id is required to perform this search");
        }
    }

    private void ensureValid(Company company) {

        if (company == null) {
            throw new IllegalArgumentException("Company is required");
        }
    }
}
