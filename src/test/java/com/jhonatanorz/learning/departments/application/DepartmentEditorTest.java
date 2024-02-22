package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.departments.domain.*;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentIdMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentEditorTest {

    private DepartmentRepository repository;
    private DepartmentEditor editor;

    @BeforeEach
    void setUp() {

        repository = mock(DepartmentRepository.class);
        editor = new DepartmentEditor(repository);
    }

    @Test
    void should_throw_error_when_trying_to_edit_with_null_id() {

        DepartmentForm template = DepartmentFormMother.random();

        assertThrows(IllegalArgumentException.class, () -> editor.edit(null, template));
    }

    @Test
    void should_throw_error_when_trying_to_edit_with_null_edit_template() {

        DepartmentId departmentId = DepartmentIdMother.random();

        assertThrows(IllegalArgumentException.class, () -> editor.edit(departmentId, null));
    }

    @Test
    void should_throw_error_when_trying_to_edit_with_a_form_without_name() {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm template = DepartmentFormMother.withName(null);

        assertThrows(IllegalArgumentException.class, () -> editor.edit(departmentId, template));
    }

    @Test
    void should_not_throw_error_when_trying_to_edit_with_a_form_without_an_id() {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm template = DepartmentFormMother.withId(null);

        when(repository.find(departmentId)).thenReturn(Optional.of(DepartmentMother.withId(departmentId)));

        assertDoesNotThrow(() -> editor.edit(departmentId, template));
    }

    @Test
    void should_throw_error_when_trying_to_edit_a_department_that_does_not_exist() {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm template = DepartmentFormMother.random();

        when(repository.find(departmentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> editor.edit(departmentId, template));
    }

    @Test
    void should_edit_a_department_and_save_changes() {

        DepartmentId departmentId = DepartmentIdMother.random();
        Department existentDepartment = DepartmentMother.withId(departmentId);

        DepartmentForm template = DepartmentFormMother.random();

        when(repository.find(departmentId)).thenReturn(Optional.of(existentDepartment));

        Department expected = new Department(
                departmentId,
                existentDepartment.getCompanyId(),
                new DepartmentName(template.getName())
        );

        assertEquals(expected, editor.edit(departmentId, template));
        verify(repository).save(expected);
    }

    @Test
    void should_not_change_department_id_even_if_form_contains_one() {

        DepartmentId departmentId = DepartmentIdMother.random();
        Department existentDepartment = DepartmentMother.withId(departmentId);

        DepartmentForm template = DepartmentFormMother.random();

        when(repository.find(departmentId)).thenReturn(Optional.of(existentDepartment));

        Department expected = new Department(
                departmentId,
                existentDepartment.getCompanyId(),
                new DepartmentName(template.getName())
        );

        assertEquals(expected, editor.edit(departmentId, template));
        verify(repository).save(expected);
    }
}