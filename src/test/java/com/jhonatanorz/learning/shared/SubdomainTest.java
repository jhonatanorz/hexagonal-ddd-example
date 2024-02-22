package com.jhonatanorz.learning.shared;

import com.jhonatanorz.learning.shared.domain.Subdomain;
import com.jhonatanorz.learning.shared.factories.SubdomainMother;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubdomainTest {

    @Test
    void should_throw_error_when_creating_it_with_a_value_that_does_not_start_with_an_alphanumeric_character() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("-abc")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("_abc")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create(".abc"))
        );
    }

    @Test
    void should_throw_error_when_creating_it_with_a_value_that_does_not_end_with_an_alphanumeric_character() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("abc-")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("abc_")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("abc."))
        );
    }

    @Test
    void should_throw_error_when_creating_it_with_a_value_that_contains_non_alphanumeric_characters() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("a@c")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("a#b")),
                () -> assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create("a!b"))
        );
    }

    @Test
    void should_throw_error_when_creating_it_with_a_value_with_more_than_63_characters() {

        String invalidValue = RandomStringUtils.randomAlphanumeric(64);

        assertThrows(IllegalArgumentException.class, () -> SubdomainMother.create(invalidValue));
    }

    @Test
    void should_not_throw_error_when_creating_it_with_a_value_with_of_63_characters() {

        String validValue = RandomStringUtils.randomAlphanumeric(63);

        assertDoesNotThrow(() -> SubdomainMother.create(validValue));
    }

    @Test
    void should_return_its_value() {

        String value = "app";
        Subdomain subdomain = SubdomainMother.create(value);

        assertEquals(value, subdomain.value());
    }

}