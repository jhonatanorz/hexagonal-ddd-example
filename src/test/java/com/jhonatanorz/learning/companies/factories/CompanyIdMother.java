package com.jhonatanorz.learning.companies.factories;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import net.andreinc.mockneat.MockNeat;

public class CompanyIdMother {

    public static CompanyId random() {
        return create(MockNeat.threadLocal().uuids().get());
    }

    public static CompanyId create(String value) {
        return new CompanyId(value);
    }
}
