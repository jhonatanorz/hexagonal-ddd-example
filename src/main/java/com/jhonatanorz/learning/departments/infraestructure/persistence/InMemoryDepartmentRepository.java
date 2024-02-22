package com.jhonatanorz.learning.departments.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentName;
import com.jhonatanorz.learning.departments.domain.DepartmentRepository;
import com.jhonatanorz.learning.shared.infraestructure.persistence.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("memory")
public class InMemoryDepartmentRepository extends InMemoryRepository<DepartmentId, Department> implements DepartmentRepository {

    public InMemoryDepartmentRepository() {
        loadData();
    }

    @Override
    public List<Department> find(CompanyId companyId) {

        List<Department> companyDepartments = new ArrayList<>();

        for (Department department : memory.values()) {
            if (department.getCompanyId().equals(companyId)) {
                companyDepartments.add(department);
            }
        }
        return companyDepartments;
    }


    private void loadData() {

        Department development = new Department(
                new DepartmentId(UUID.randomUUID().toString()),
                new CompanyId("06847e37-82ec-4c57-8c43-add66415d2c6"),
                new DepartmentName("Development")
        );

        memory.put(development.getId(), development);

        Department sales = new Department(
                new DepartmentId(UUID.randomUUID().toString()),
                new CompanyId("06847e37-82ec-4c57-8c43-add66415d2c6"),
                new DepartmentName("Sales")
        );

        memory.put(sales.getId(), sales);

        Department hr = new Department(
                new DepartmentId(UUID.randomUUID().toString()),
                new CompanyId(UUID.randomUUID().toString()),
                new DepartmentName("Human Resources")
        );

        memory.put(hr.getId(), hr);
    }
}
