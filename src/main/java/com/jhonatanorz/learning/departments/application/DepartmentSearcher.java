package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentSearcher {

    private final DepartmentRepository repository;

    public DepartmentSearcher(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department search(DepartmentId id) {

        ensureValid(id);

        return repository.find(id)
                .orElseThrow(() -> new NotFoundException("Department with id " + id.value() + " does not exist"));
    }

    public List<Department> search(CompanyId companyId) {

        ensureValid(companyId);
        return repository.find(companyId);
    }


    private void ensureValid(DepartmentId id) {
        if (id == null) {
            throw new IllegalArgumentException("Department id is required for this search");
        }
    }

    private void ensureValid(CompanyId companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("Company id is required for this search");
        }
    }
}
