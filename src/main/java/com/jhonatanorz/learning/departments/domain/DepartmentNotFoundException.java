package com.jhonatanorz.learning.departments.domain;


import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;

public class DepartmentNotFoundException extends NotFoundException {

    public DepartmentNotFoundException(DepartmentId id) {
        super("Department with id " + id + " does not exist");
    }
}
