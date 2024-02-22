package com.jhonatanorz.learning.companies.domain;

import com.jhonatanorz.learning.shared.domain.Identifier;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CompanyId extends Identifier {

    public CompanyId(String value) {
        super(value);
    }
}
