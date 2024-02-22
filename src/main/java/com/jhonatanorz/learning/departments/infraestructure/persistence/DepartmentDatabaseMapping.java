package com.jhonatanorz.learning.departments.infraestructure.persistence;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentName;
import com.jhonatanorz.learning.shared.infraestructure.persistence.DatabaseMapping;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentDatabaseMapping implements DatabaseMapping<Department> {

    @Id
    private String id;

    private String companyId;
    private String name;

    public DepartmentDatabaseMapping(Department department) {

        ensureValid(department);
        mapValues(department);
    }

    @Override
    public Department domainMap() {

        return new Department(
                new DepartmentId(id),
                new CompanyId(companyId),
                new DepartmentName(name)
        );
    }

    private void ensureValid(Department department) {

        if (department == null) {
            throw new IllegalArgumentException("Department is required");
        }
    }

    private void mapValues(Department department) {

        id = department.getId().value();
        companyId = department.getCompanyId().value();
        name = department.getName().value();
    }
}
