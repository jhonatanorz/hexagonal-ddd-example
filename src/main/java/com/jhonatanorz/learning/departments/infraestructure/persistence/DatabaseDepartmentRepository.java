package com.jhonatanorz.learning.departments.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseDepartmentRepository implements DepartmentRepository {

    private final SpringDataJpaDepartmentRepository springRepository;

    public DatabaseDepartmentRepository(SpringDataJpaDepartmentRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public List<Department> find(CompanyId companyId) {

        ensureValid(companyId);
        return domainMap(springRepository.findByCompanyIdOrderByName(companyId.value()));
    }

    @Override
    public Optional<Department> find(DepartmentId id) {

        ensureValid(id);
        return springRepository.findById(id.value())
                .map(DepartmentDatabaseMapping::domainMap);
    }

    @Override
    public void save(Department department) {

        ensureValid(department);
        springRepository.save(new DepartmentDatabaseMapping(department));
    }

    @Override
    public void delete(DepartmentId id) {

        ensureValid(id);
        springRepository.deleteById(id.value());
    }


    private void ensureValid(CompanyId companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("Company id is required to perform this search");
        }
    }

    private void ensureValid(DepartmentId id) {

        if (id == null) {
            throw new IllegalArgumentException("Department id is required to perform this search");
        }
    }

    private void ensureValid(Department department) {

        if (department == null) {
            throw new IllegalArgumentException("Department is required to perform this operation");
        }
    }

    private List<Department> domainMap(List<DepartmentDatabaseMapping> departments) {

        return departments.stream()
                .map(DepartmentDatabaseMapping::domainMap)
                .toList();
    }
}
