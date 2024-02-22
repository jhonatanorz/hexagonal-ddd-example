package com.jhonatanorz.learning.departments.domain.factories;


import com.jhonatanorz.learning.departments.domain.DepartmentId;
import net.andreinc.mockneat.MockNeat;

public class DepartmentIdMother {

    public static DepartmentId random() {
        return create(MockNeat.threadLocal().uuids().get());
    }

    public static DepartmentId create(String value) {
        return new DepartmentId(value);
    }
}
