package com.jhonatanorz.learning.companies.domain;


import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.domain.repositories.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<CompanyId, Company> {

    Optional<Company> find(Subdomain subdomain);
}
