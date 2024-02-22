package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.companies.factories.CompanyIdMother;
import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentSearcher;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import com.jhonatanorz.learning.departments.infraestructure.web.dto.DepartmentDTO;
import com.jhonatanorz.learning.shared.domain.exceptions.UnauthorizedException;
import com.jhonatanorz.learning.shared.infraestructure.persistence.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentApiGetControllerTest extends ControllerTest<DepartmentApiGetController> {

    private DepartmentSearcher searcher;

    @Override
    protected DepartmentApiGetController instantiateController() {
        return new DepartmentApiGetController(mapper, searcher);
    }

    @BeforeEach
    protected void setUp() {
        searcher = mock(DepartmentSearcher.class);
        super.setUp();
    }

    @Test
    void should_return_bad_request_when_id_is_invalid() throws Exception {

        String invalidId = CompanyIdMother.random().value().replace("-", "_");

        mockMvc.perform(get(RoutesConfig.API.COMPANY_DEPARTMENTS, invalidId))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_return_internal_server_error_when_an_exception_occurs() throws Exception {

        CompanyId companyId = CompanyIdMother.random();

        when(searcher.search(companyId)).thenAnswer((t) -> new Exception());

        mockMvc.perform(get(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void should_return_ok_with_departments_as_payload() throws Exception {

        CompanyId companyId = CompanyIdMother.random();

        List<Department> departments = List.of(
                DepartmentMother.withCompanyId(companyId),
                DepartmentMother.withCompanyId(companyId)
        );

        when(searcher.search(companyId)).thenReturn(departments);

        String expectedResponse = serializeAsJson(map(departments, DepartmentDTO.class));

        mockMvc.perform(get(RoutesConfig.API.COMPANY_DEPARTMENTS, companyId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}