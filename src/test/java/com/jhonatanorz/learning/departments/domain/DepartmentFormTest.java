package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentIdMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentNameMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentFormTest {

    @Nested
    @DisplayName("Getter tests")
    class GetterTests {

        @Test
        void should_return_its_id_correctly() {

            String id = DepartmentIdMother.random().value();

            DepartmentForm form = DepartmentFormMother.withId(id);

            assertEquals(id, form.getId());
        }

        @Test
        void should_return_its_name_correctly() {

            String name = DepartmentNameMother.random().value();

            DepartmentForm form = DepartmentFormMother.withName(name);

            assertEquals(name, form.getName());
        }
    }

    @Nested
    @DisplayName("Is valid tests")
    class IsValidTests {

        @Test
        void should_be_invalid_when_id_is_blank() {

            assertAll(
                    () -> assertFalse(DepartmentFormMother.withId(null).isValid()),
                    () -> assertFalse(DepartmentFormMother.withId("").isValid()),
                    () -> assertFalse(DepartmentFormMother.withId(" ").isValid())
            );
        }

        @Test
        void should_be_invalid_when_id_is_not_valid_uuid() {

            String invalidId = "some-invalid-id";
            assertFalse(DepartmentFormMother.withId(invalidId).isValid());
        }

        @Test
        void should_be_invalid_when_name_is_blank() {

            assertAll(
                    () -> assertFalse(DepartmentFormMother.withName(null).isValid()),
                    () -> assertFalse(DepartmentFormMother.withName("").isValid()),
                    () -> assertFalse(DepartmentFormMother.withName(" ").isValid())
            );
        }
    }
}