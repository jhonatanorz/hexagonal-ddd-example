package com.jhonatanorz.learning.shared.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Email extends ValueObject<String> {

    private static final String RFC_5322_EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public Email(String value) {
        super(value);
        ensureValid(value);
    }


    private void ensureValid(String value) {
        if (value == null || value.isBlank() || !value.matches(RFC_5322_EMAIL_REGEX)) {
            throw new IllegalArgumentException("A valid email is required");
        }
    }
}
