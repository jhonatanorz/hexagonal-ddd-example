package com.jhonatanorz.learning.shared.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NotBlankString extends ValueObject<String> {

    public NotBlankString(String value) {
        super(value);
        ensureValid(value);
    }

    private void ensureValid(String value) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("String can't be blank");
        }
    }
}
