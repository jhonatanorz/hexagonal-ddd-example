package com.jhonatanorz.learning.departments.domain.factories;

import com.jhonatanorz.learning.departments.domain.DepartmentName;
import net.andreinc.mockneat.MockNeat;

public class DepartmentNameMother {

    public static DepartmentName random() {
        return create(MockNeat.threadLocal().departments().get());
    }

    public static DepartmentName create(String value) {
        return new DepartmentName(value);
    }
}
