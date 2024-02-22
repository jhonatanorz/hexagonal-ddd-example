package com.jhonatanorz.learning.companies.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.companies.factories.CompanyMother;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.factories.SubdomainMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DatabaseCompanyRepositoryTest {

    private SpringDataJpaCompanyRepository springRepository;
    private DatabaseCompanyRepository databaseRepository;


    @BeforeEach
    void setUp() {
        springRepository = mock(SpringDataJpaCompanyRepository.class);
        databaseRepository = new DatabaseCompanyRepository(springRepository);
    }

    @Test
    void should_throw_error_when_searching_a_company_by_subdomain_with_null_value() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.find((Subdomain) null));
    }

    @Test
    void should_find_a_company_by_subdomain() {

        Subdomain subdomain = SubdomainMother.random();
        Company company = CompanyMother.withSubdomain(subdomain);

        when(springRepository.findBySubdomainEqualsIgnoreCase(subdomain.value()))
                .thenReturn(Optional.of(new CompanyDatabaseMapping(company)));

        assertEquals(Optional.of(company), databaseRepository.find(subdomain));
    }

    @Test
    void should_throw_error_when_searching_a_company_by_id_with_null_value() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.find((CompanyId) null));
    }

    @Test
    void should_find_a_company_by_id() {

        CompanyId id = CompanyIdMother.random();
        Company company = CompanyMother.withId(id);

        when(springRepository.findById(id.value()))
                .thenReturn(Optional.of(new CompanyDatabaseMapping(company)));

        assertEquals(Optional.of(company), databaseRepository.find(id));
    }

    @Test
    void should_throw_error_when_trying_to_save_a_company_with_null_value() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.save(null));
    }

    @Test
    void should_save_a_company() {

        Company company = CompanyMother.random();

        databaseRepository.save(company);

        verify(springRepository).save(new CompanyDatabaseMapping(company));
    }

    @Test
    void should_throw_error_when_trying_to_delete_a_company_with_null_id() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.delete(null));
    }

    @Test
    void should_delete_a_company() {

        CompanyId id = CompanyIdMother.random();

        databaseRepository.delete(id);

        verify(springRepository).deleteById(id.value());
    }
}