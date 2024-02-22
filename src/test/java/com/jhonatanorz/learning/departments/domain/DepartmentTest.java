package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.companies.domain.Company;
import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.companies.factories.CompanyMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentNameMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Nested
    @DisplayName("When creating a department")
    class ConstructorTests {

        @Test
        void should_throw_error_when_creating_it_without_id() {

            assertThrows(IllegalArgumentException.class, () -> DepartmentMother.withId(null));
        }

        @Test
        void should_throw_error_when_creating_it_without_company_id() {

            assertThrows(IllegalArgumentException.class, () -> DepartmentMother.withCompanyId(null));
        }

        @Test
        void should_throw_error_when_creating_it_without_name() {

            assertThrows(IllegalArgumentException.class, () -> DepartmentMother.withName(null));
        }

        @Test
        void should_throw_error_when_creating_it_with_null_form() {

            CompanyId companyId = CompanyIdMother.random();

            assertThrows(IllegalArgumentException.class, () -> new Department(null, companyId));
        }

        @Test
        void should_throw_error_when_creating_it_with_invalid_form() {

            CompanyId companyId = CompanyIdMother.random();
            DepartmentForm invalidForm = DepartmentFormMother.withName(null);

            assertThrows(IllegalArgumentException.class, () -> new Department(invalidForm, companyId));
        }

        @Test
        void should_throw_error_when_creating_it_by_form_with_null_company_id() {

            DepartmentForm form = DepartmentFormMother.random();

            assertThrows(IllegalArgumentException.class, () -> new Department(form, null));
        }

        @Test
        void should_create_with_form_values() {

            DepartmentForm form = DepartmentFormMother.random();
            CompanyId companyId = CompanyIdMother.random();

            Department department = new Department(form, companyId);

            assertAll(
                    () -> assertEquals(form.getId(), department.getId().value()),
                    () -> assertEquals(form.getName(), department.getName().value()),
                    () -> assertEquals(companyId, department.getCompanyId())
            );
        }
    }

    @Nested
    @DisplayName("When changing a department name")
    class ChangeNameTests {

        @Test
        void should_throw_error_when_changing_it_to_null() {

            Department department = DepartmentMother.random();
            assertThrows(IllegalArgumentException.class, () -> department.changeName(null));
        }

        @Test
        void should_change_its_name_correctly() {

            Department department = DepartmentMother.random();
            DepartmentName newName = DepartmentNameMother.random();

            department.changeName(newName);

            assertEquals(newName, department.getName());
        }
    }

    @Nested
    @DisplayName("When checking if it owned by a company")
    class IsOwnedByTests{

        @Test
        void should_return_false_when_is_not_owned_by_a_company(){

            Company company = CompanyMother.random();
            Department department = DepartmentMother.random();

            assertFalse(department.isOwnedBy(company));
        }

        @Test
        void should_return_true_when_is_owned_by_a_company() {

            Company company = CompanyMother.random();
            Department department = DepartmentMother.withCompanyId(company.getId());

            assertTrue(department.isOwnedBy(company));
        }
    }
}