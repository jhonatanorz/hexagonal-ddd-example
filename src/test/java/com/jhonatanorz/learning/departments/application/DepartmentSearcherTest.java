package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentIdMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentSearcherTest {

    private DepartmentRepository repository;
    private DepartmentSearcher searcher;

    @BeforeEach
    void setUp() {

        repository = mock(DepartmentRepository.class);
        searcher = new DepartmentSearcher(repository);
    }

    @Nested
    @DisplayName("When searching a department by id")
    class SearchByIdTests {

        @Test
        void should_throw_error_when_searching_with_null_value() {

            assertThrows(IllegalArgumentException.class, () -> searcher.search((DepartmentId) null));
        }

        @Test
        void should_throw_error_when_searching_a_department_that_does_not_exist() {

            DepartmentId id = DepartmentIdMother.random();

            assertThrows(NotFoundException.class, () -> searcher.search(id));
        }

        @Test
        void should_find_an_existent_department_by_id() {

            DepartmentId id = DepartmentIdMother.random();
            Department department = DepartmentMother.withId(id);

            when(repository.find(id)).thenReturn(Optional.of(department));

            assertEquals(department, searcher.search(id));
        }
    }

    @Nested
    @DisplayName("When searching departments by company")
    class SearchByCompanyIdTests {

        @Test
        void should_throw_error_when_searching_with_null_company_id() {

            assertThrows(IllegalArgumentException.class, () -> searcher.search((CompanyId) null));
        }

        @Test
        void should_find_existent_departments_of_a_given_company() {

            CompanyId companyId = CompanyIdMother.random();

            List<Department> departments = List.of(
                    DepartmentMother.withCompanyId(companyId),
                    DepartmentMother.withCompanyId(companyId)
            );

            when(repository.find(companyId)).thenReturn(departments);

            assertEquals(departments, searcher.search(companyId));
        }
    }
}