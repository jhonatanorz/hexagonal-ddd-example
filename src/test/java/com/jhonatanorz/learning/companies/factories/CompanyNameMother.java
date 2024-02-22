package com.jhonatanorz.learning.companies.factories;

import com.jhonatanorz.learning.companies.domain.CompanyName;
import net.andreinc.mockneat.MockNeat;

public class CompanyNameMother {

    public static CompanyName random() {
        return create(MockNeat.threadLocal().companies().get());
    }

    public static CompanyName create(String value) {
        return new CompanyName(value);
    }
}
