package com.jhonatanorz.learning.departments.domain.factories;


import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.DepartmentName;

public class DepartmentMother {

    public static Department random() {
        return create(
                DepartmentIdMother.random(),
                CompanyIdMother.random(),
                DepartmentNameMother.random()
        );
    }

    public static Department withId(DepartmentId value) {
        return create(
                value,
                CompanyIdMother.random(),
                DepartmentNameMother.random()
        );
    }

    public static Department withCompanyId(CompanyId value) {
        return create(
                DepartmentIdMother.random(),
                value,
                DepartmentNameMother.random()
        );
    }

    public static Department withName(DepartmentName value) {
        return create(
                DepartmentIdMother.random(),
                CompanyIdMother.random(),
                value
        );
    }

    public static Department withIdAndCompanyId(DepartmentId id, CompanyId companyId) {
        return create(
                id,
                companyId,
                DepartmentNameMother.random()
        );
    }

    public static Department create(DepartmentId id, CompanyId companyId, DepartmentName name) {
        return new Department(id, companyId, name);
    }

}
