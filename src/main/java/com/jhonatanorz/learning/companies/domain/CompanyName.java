package com.jhonatanorz.learning.companies.domain;

import com.jhonatanorz.learning.shared.domain.NotBlankString;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CompanyName extends NotBlankString {

    public CompanyName(String value) {
        super(value);
    }
}
