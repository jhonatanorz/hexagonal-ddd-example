package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyAsset;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.shared.domain.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Department implements CompanyAsset, Identifiable<DepartmentId> {

    private final DepartmentId id;
    private final CompanyId companyId;

    private DepartmentName name;

    public Department(DepartmentId id, CompanyId companyId, DepartmentName name) {

        ensureValid(id);
        this.id = id;

        ensureValid(companyId);
        this.companyId = companyId;

        changeName(name);
    }

    public Department(DepartmentForm form, CompanyId companyId) {

        ensureValid(form);
        ensureValid(companyId);

        id = new DepartmentId(form.getId());
        name = new DepartmentName(form.getName());
        this.companyId = companyId;
    }

    public void changeName(DepartmentName newName) {

        ensureValid(newName);
        this.name = newName;
    }

    @Override
    public boolean isOwnedBy(Company company) {
        return companyId.equals(company.getId());
    }

    private void ensureValid(DepartmentId id) {

        if (id == null) {
            throw new IllegalArgumentException("Department id can't be null");
        }
    }

    private void ensureValid(CompanyId companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("Company id can't be null");
        }
    }

    private void ensureValid(DepartmentName name) {

        if (name == null) {
            throw new IllegalArgumentException("Department name can't be null");
        }
    }

    private void ensureValid(DepartmentForm form) {

        if (form == null) {
            throw new IllegalArgumentException("Department form can't be null");
        }
    }
}
