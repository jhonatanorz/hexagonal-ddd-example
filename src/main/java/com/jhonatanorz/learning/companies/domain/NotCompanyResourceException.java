package com.jhonatanorz.learning.companies.domain;

public class NotCompanyResourceException extends IllegalArgumentException {

    public NotCompanyResourceException() {
    }

    public NotCompanyResourceException(String s) {
        super(s);
    }
}
