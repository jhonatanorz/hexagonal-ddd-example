package com.jhonatanorz.learning.shared.infraestructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhonatanorz.learning.config.SerializationConfig;
import org.modelmapper.ModelMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ControllerTest<C> {

    protected final ModelMapper mapper;
    protected final ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    public ControllerTest() {
        SerializationConfig serializationConfig = new SerializationConfig();
        mapper = serializationConfig.modelMapper();
        objectMapper = serializationConfig.objectMapper();
    }

    protected void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(instantiateController())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }


    protected abstract C instantiateController();

    protected <S, T> List<T> map(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    protected String serializeAsJson(Object payload) throws JsonProcessingException {
        return objectMapper.writeValueAsString(payload);
    }
}
