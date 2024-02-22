package com.jhonatanorz.learning.shared.factories;

import com.jhonatanorz.learning.shared.domain.Subdomain;
import net.andreinc.mockneat.MockNeat;

public class SubdomainMother {

    public static Subdomain random() {
        return create(MockNeat.threadLocal().domains().get());
    }

    public static Subdomain create(String value) {
        return new Subdomain(value);
    }
}
