package com.jhonatanorz.learning.departments.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaDepartmentRepository extends JpaRepository<DepartmentDatabaseMapping, String> {

    List<DepartmentDatabaseMapping> findByCompanyIdOrderByName(String value);
}
