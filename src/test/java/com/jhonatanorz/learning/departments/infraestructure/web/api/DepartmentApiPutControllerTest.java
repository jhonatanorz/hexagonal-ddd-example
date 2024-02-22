package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentRegister;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import com.jhonatanorz.learning.shared.infraestructure.persistence.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentApiPutControllerTest extends ControllerTest<DepartmentApiPutController> {

    private DepartmentRegister register;

    @Override
    protected DepartmentApiPutController instantiateController() {
        return new DepartmentApiPutController(mapper, register);
    }

    @BeforeEach
    protected void setUp() {
        register = mock(DepartmentRegister.class);
        super.setUp();
    }

    @Test
    void should_return_bad_request_when_company_id_is_not_valid() throws Exception {

        String invalidId = CompanyIdMother.random().value().replace("-", "_");
        DepartmentForm form = DepartmentFormMother.random();

        String requestPayload = serializeAsJson(form);

        mockMvc.perform(
                        put(RoutesConfig.API.COMPANY_DEPARTMENTS, invalidId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestPayload)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_bad_request_when_illegal_argument_exception_occurs() throws Exception {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        String requestPayload = serializeAsJson(form);

        when(register.register(companyId, form)).thenThrow(new IllegalArgumentException());

        mockMvc.perform(
                        put(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestPayload)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_internal_server_error_when_an_exception_occurs() throws Exception {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        when(register.register(companyId, form)).thenAnswer((t) -> new Exception());

        mockMvc.perform(
                        put(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_return_not_found_when_company_does_not_exist() throws Exception {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        when(register.register(companyId, form)).thenThrow(new NotFoundException());

        mockMvc.perform(
                        put(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_created_when_input_is_valid() throws Exception {

        CompanyId companyId = CompanyIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        mockMvc.perform(
                        put(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isCreated());

        verify(register).register(companyId, form);
    }
}