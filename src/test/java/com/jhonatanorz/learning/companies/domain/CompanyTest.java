package com.jhonatanorz.learning.companies.domain;

import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.companies.factories.CompanyMother;
import com.jhonatanorz.learning.companies.factories.CompanyNameMother;
import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.factories.SubdomainMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyTest {

    @Nested
    @DisplayName("constructor tests")
    static class ConstructorTests {

        @Test
        void should_throw_error_when_id_is_null() {

            assertThrows(IllegalArgumentException.class, ()-> CompanyMother.withId(null));
        }

        @Test
        void should_throw_error_when_name_is_null() {

            assertThrows(IllegalArgumentException.class, ()-> CompanyMother.withName(null));
        }

        @Test
        void should_throw_error_when_subdomain_is_null() {

            assertThrows(IllegalArgumentException.class, ()-> CompanyMother.withSubdomain(null));
        }

    }

    @Nested
    @DisplayName("getters tests")
    static class GettersTests {

        @Test
        void should_return_its_id_correctly() {

            CompanyId id = CompanyIdMother.random();

            Company company = CompanyMother.withId(id);

            assertEquals(id, company.getId());
        }

        @Test
        void should_return_its_name_correctly() {

            CompanyName name = CompanyNameMother.random();

            Company company = CompanyMother.withName(name);

            assertEquals(name, company.getName());
        }

        @Test
        void should_return_its_subdomain_correctly() {

            Subdomain subdomain = SubdomainMother.random();

            Company company = CompanyMother.withSubdomain(subdomain);

            assertEquals(subdomain, company.getSubdomain());
        }

    }

    @Nested
    @DisplayName("change name tests")
    static class ChangeNameTests{

        @Test
        void should_throw_error_when_trying_to_change_its_name_with_null() {

            Company company = CompanyMother.random();

            assertThrows(IllegalArgumentException.class, ()-> company.changeName(null));
        }

        @Test
        void should_change_its_name_correctly() {

            Company company = CompanyMother.random();
            CompanyName newName = CompanyNameMother.random();

            company.changeName(newName);

            assertEquals(newName, company.getName());
        }
    }

    @Nested
    @DisplayName("change subdomain tests")
    static class ChangeSubdomainTests{

        @Test
        void should_throw_error_when_trying_to_change_its_subdomain_with_null() {

            Company company = CompanyMother.random();

            assertThrows(IllegalArgumentException.class, ()-> company.changeSubdomain(null));
        }

        @Test
        void should_change_its_subdomain_correctly() {

            Company company = CompanyMother.random();
            Subdomain newSubdomain = SubdomainMother.random();

            company.changeSubdomain(newSubdomain);

            assertEquals(newSubdomain, company.getSubdomain());
        }
    }

}