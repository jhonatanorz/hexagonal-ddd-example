package com.jhonatanorz.learning.companies.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyName;
import com.jhonatanorz.learning.companies.domain.CompanyRepository;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.infraestructure.persistence.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("memory")
public class InMemoryCompanyRepository extends InMemoryRepository<CompanyId, Company> implements CompanyRepository {

    public InMemoryCompanyRepository() {

        loadData();
    }

    @Override
    public Optional<Company> find(Subdomain subdomain) {

        return memory.values().stream()
                .filter(company -> company.getSubdomain().equals(subdomain))
                .findFirst();
    }


    private void loadData() {

        Company mindup = new Company(
                new CompanyId("06847e37-82ec-4c57-8c43-add66415d2c6"),
                new CompanyName("Mindup"),
                new Subdomain("mindup")
        );

        memory.put(mindup.getId(), mindup);

        Company mabe = new Company(
                new CompanyId("58175a04-079d-4105-b53b-3995b65a7b9c"),
                new CompanyName("Mabe"),
                new Subdomain("mabe")
        );

        memory.put(mabe.getId(), mabe);
    }
}
