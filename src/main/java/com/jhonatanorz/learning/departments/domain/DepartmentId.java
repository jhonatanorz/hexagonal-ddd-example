package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.shared.domain.Identifier;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DepartmentId extends Identifier {

    public DepartmentId(String value) {
        super(value);
    }
}
