package com.jhonatanorz.learning.departments.domain;


import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.shared.domain.repositories.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<DepartmentId, Department> {

    List<Department> find(CompanyId companyId);
}
