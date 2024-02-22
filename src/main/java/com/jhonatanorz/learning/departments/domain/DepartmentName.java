package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.shared.domain.NotBlankString;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DepartmentName extends NotBlankString {

    public DepartmentName(String value) {
        super(value);
    }
}
