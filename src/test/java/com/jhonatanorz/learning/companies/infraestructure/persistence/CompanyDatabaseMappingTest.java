package com.jhonatanorz.learning.companies.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.factories.CompanyMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDatabaseMappingTest {

    @Test
    void should_throw_error_when_creating_it_with_null_company() {

        Company company = null;
        assertThrows(IllegalArgumentException.class, () -> new CompanyDatabaseMapping(company));
    }

    @Test
    void should_create_with_company_values() {

        Company company = CompanyMother.random();
        CompanyDatabaseMapping databaseMapping = new CompanyDatabaseMapping(company);

        assertAll(
                () -> assertEquals(company.getId().value(), databaseMapping.getId()),
                () -> assertEquals(company.getName().value(), databaseMapping.getName()),
                () -> assertEquals(company.getSubdomain().value(), databaseMapping.getSubdomain())
        );
    }

    @Test
    void should_map_to_a_company_correctly() {

        Company company = CompanyMother.random();
        CompanyDatabaseMapping databaseMapping = new CompanyDatabaseMapping(company);

        assertEquals(company, databaseMapping.domainMap());
    }
}