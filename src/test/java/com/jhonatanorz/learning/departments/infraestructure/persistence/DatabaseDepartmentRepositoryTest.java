package com.jhonatanorz.learning.departments.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentIdMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DatabaseDepartmentRepositoryTest {

    private SpringDataJpaDepartmentRepository springRepository;
    private DatabaseDepartmentRepository databaseRepository;

    @BeforeEach
    void setUp() {
        springRepository = mock(SpringDataJpaDepartmentRepository.class);
        databaseRepository = new DatabaseDepartmentRepository(springRepository);
    }

    @Test
    void should_throw_error_when_searching_departments_by_company_id_with_null_value() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.find((CompanyId) null));
    }


    @Test
    void should_find_departments_by_company_id() {

        CompanyId companyId = CompanyIdMother.random();

        List<Department> departments = List.of(
                DepartmentMother.withCompanyId(companyId),
                DepartmentMother.withCompanyId(companyId)
        );

        when(springRepository.findByCompanyIdOrderByName(companyId.value()))
                .thenReturn(databaseMap(departments));

        assertEquals(departments, databaseRepository.find(companyId));
    }

    @Test
    void should_throw_error_when_searching_a_department_by_id_with_null_value() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.find((DepartmentId) null));
    }

    @Test
    void should_find_a_department_by_id() {

        DepartmentId id = DepartmentIdMother.random();
        Department department = DepartmentMother.withId(id);

        when(springRepository.findById(id.value()))
                .thenReturn(Optional.of(new DepartmentDatabaseMapping(department)));

        assertEquals(Optional.of(department), databaseRepository.find(id));
    }

    @Test
    void should_throw_error_when_trying_to_save_a_null_department() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.save(null));
    }

    @Test
    void should_save_a_department() {

        Department department = DepartmentMother.random();

        databaseRepository.save(department);

        verify(springRepository).save(new DepartmentDatabaseMapping(department));
    }

    @Test
    void should_throw_error_when_trying_to_delete_a_department_with_null_id() {

        assertThrows(IllegalArgumentException.class, () -> databaseRepository.delete( null));
    }

    @Test
    void should_delete_a_department_by_id() {

        DepartmentId id = DepartmentIdMother.random();

        databaseRepository.delete(id);

        verify(springRepository).deleteById(id.value());
    }

    private List<DepartmentDatabaseMapping> databaseMap(List<Department> departments) {

        return departments.stream()
                .map(DepartmentDatabaseMapping::new)
                .toList();
    }
}