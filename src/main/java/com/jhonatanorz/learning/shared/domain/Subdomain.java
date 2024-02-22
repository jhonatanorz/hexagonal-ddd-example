package com.jhonatanorz.learning.shared.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Subdomain extends NotBlankString {

    private String REGEX = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.(?!-)[A-Za-z0-9-]{1,63}(?<!-))*$";

    public Subdomain(String value) {
        super(value);
        ensureValidSubdomain(value);
    }

    private void ensureValidSubdomain(String value) {
        if (!value.matches(REGEX)) {
            throw new IllegalArgumentException("Invalid subdomain: " + value);
        }
    }


}
