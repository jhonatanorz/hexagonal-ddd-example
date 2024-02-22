package com.jhonatanorz.learning.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jhonatanorz.learning.shared.domain.Identifier;

import java.io.IOException;

public class IdentifierJsonSerializer extends JsonSerializer<Identifier> {

    @Override
    public void serialize(Identifier value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeString(value.value());
    }
}