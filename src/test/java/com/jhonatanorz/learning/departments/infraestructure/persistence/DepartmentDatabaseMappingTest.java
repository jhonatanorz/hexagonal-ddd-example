package com.jhonatanorz.learning.departments.infraestructure.persistence;

import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDatabaseMappingTest {

    @Test
    void should_throw_error_when_creating_it_with_null_department() {

        assertThrows(IllegalArgumentException.class, () -> new DepartmentDatabaseMapping(null));
    }

    @Test
    void should_create_with_department_values() {

        Department department = DepartmentMother.random();
        DepartmentDatabaseMapping databaseMapping = new DepartmentDatabaseMapping(department);

        assertAll(
                () -> assertEquals(department.getId().value(), databaseMapping.getId()),
                () -> assertEquals(department.getCompanyId().value(), databaseMapping.getCompanyId()),
                () -> assertEquals(department.getName().value(), databaseMapping.getName())
        );
    }

    @Test
    void should_map_to_a_department_correctly() {

        Department department = DepartmentMother.random();
        DepartmentDatabaseMapping databaseMapping = new DepartmentDatabaseMapping(department);

        assertEquals(department, databaseMapping.domainMap());
    }
}