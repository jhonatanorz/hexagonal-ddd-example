package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyRepository;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.companies.factories.CompanyMother;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DepartmentRegisterTest {

    private DepartmentRepository repository;
    private CompanyRepository companyRepository;

    private DepartmentRegister register;

    @BeforeEach
    void setUp() {

        repository = mock(DepartmentRepository.class);
        companyRepository = mock(CompanyRepository.class);
        register = new DepartmentRegister(repository, companyRepository);
    }

    @Test
    void should_throw_error_when_trying_to_register_a_department_without_specifying_the_company_id() {

        DepartmentForm form = DepartmentFormMother.random();

        assertThrows(IllegalArgumentException.class, () -> register.register(null, form));
    }

    @Test
    void should_throw_error_when_trying_to_register_a_department_without_a_form() {

        CompanyId companyId = CompanyIdMother.random();

        assertThrows(IllegalArgumentException.class, () -> register.register(companyId, null));
    }

    @Test
    void should_throw_error_when_trying_to_register_a_department_with_an_invalid_form() {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm invalidForm = DepartmentFormMother.withName(null);

        assertThrows(IllegalArgumentException.class, () -> register.register(companyId, invalidForm));
    }

    @Test
    void should_throw_error_when_trying_to_register_a_department_of_a_company_that_does_not_exist() {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        when(companyRepository.find(companyId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> register.register(companyId, form));
    }

    @Test
    void should_register_a_department() {

        CompanyId companyId = CompanyIdMother.random();
        Company company = CompanyMother.withId(companyId);

        DepartmentForm form = DepartmentFormMother.random();

        when(companyRepository.find(companyId)).thenReturn(Optional.of(company));

        Department expected = new Department(form, companyId);

        assertEquals(expected, register.register(companyId, form));
        verify(repository).save(expected);
    }
}