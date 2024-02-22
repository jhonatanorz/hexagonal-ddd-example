package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.departments.domain.*;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentEditor {

    private final DepartmentRepository repository;

    public DepartmentEditor(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department edit(DepartmentId id, DepartmentForm template) {

        ensureValid(id);
        ensureValid(template);

        Department department = findDepartment(id);

        department.changeName(new DepartmentName(template.getName()));

        repository.save(department);

        return department;
    }

    private void ensureValid(DepartmentId id) {
        if (id == null) {
            throw new IllegalArgumentException("Department id is required");
        }
    }

    private void ensureValid(DepartmentForm template) {
        if (template == null) {
            throw new IllegalArgumentException("Edit form is required");
        }
        if (template.getName() == null || template.getName().isEmpty()) {
            throw new IllegalArgumentException("Department name is required");
        }
    }

    private Department findDepartment(DepartmentId id) {
        return repository.find(id)
                .orElseThrow(() -> new NotFoundException("Department with id " + id + " does not exist"));
    }
}
