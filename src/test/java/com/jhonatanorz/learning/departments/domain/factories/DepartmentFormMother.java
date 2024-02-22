package com.jhonatanorz.learning.departments.domain.factories;


import com.jhonatanorz.learning.departments.domain.DepartmentForm;

public class DepartmentFormMother {

    public static DepartmentForm random() {
        return create(
                DepartmentIdMother.random().value(),
                DepartmentNameMother.random().value()
        );
    }

    public static DepartmentForm withId(String id) {
        return create(
                id,
                DepartmentNameMother.random().value()
        );
    }

    public static DepartmentForm withName(String name) {
        return create(
                DepartmentIdMother.random().value(),
                name
        );
    }

    public static DepartmentForm create(String id, String name) {
        return new DepartmentForm(id, name);
    }
}
