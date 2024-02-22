package com.jhonatanorz.learning.departments.application;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.domain.CompanyRepository;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentRegister {

    private final DepartmentRepository repository;
    private final CompanyRepository companyRepository;

    public DepartmentRegister(DepartmentRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public Department register(CompanyId companyId, DepartmentForm form) {

        Department department = new Department(form, companyId);

        ensureExists(companyId);

        repository.save(department);
        return department;
    }

    private void ensureExists(CompanyId companyId) {
        if (!companyRepository.find(companyId).isPresent()) {
            throw new NotFoundException("Company with id " + companyId + " does not exist");
        }
    }
}
