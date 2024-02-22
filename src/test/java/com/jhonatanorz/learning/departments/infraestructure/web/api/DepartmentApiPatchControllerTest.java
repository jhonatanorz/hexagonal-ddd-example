package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentEditor;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentFormMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentIdMother;
import com.jhonatanorz.learning.departments.domain.factories.DepartmentMother;
import com.jhonatanorz.learning.departments.infraestructure.web.dto.DepartmentDTO;
import com.jhonatanorz.learning.shared.domain.exceptions.NotFoundException;
import com.jhonatanorz.learning.shared.infraestructure.persistence.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentApiPatchControllerTest extends ControllerTest<DepartmentApiPatchController> {

    private DepartmentEditor editor;

    @Override
    protected DepartmentApiPatchController instantiateController() {
        return new DepartmentApiPatchController(mapper, editor);
    }

    @BeforeEach
    protected void setUp() {
        editor = mock(DepartmentEditor.class);
        super.setUp();
    }

    @Test
    void should_return_bad_request_when_department_id_is_not_valid() throws Exception {

        String invalidId = DepartmentIdMother.random().value().replace("-", "_");

        String requestPayload = serializeAsJson(DepartmentFormMother.random());

        mockMvc.perform(
                        patch(RoutesConfig.API.DEPARTMENT, invalidId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestPayload)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_return_internal_server_error_when_an_exception_occurs() throws Exception {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        when(editor.edit(departmentId, form)).thenAnswer((t) -> new Exception());

        mockMvc.perform(
                        patch(RoutesConfig.API.DEPARTMENT, departmentId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_return_not_found_when_department_id_does_not_exist() throws Exception {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        when(editor.edit(departmentId, form)).thenThrow(new NotFoundException());

        mockMvc.perform(
                        patch(RoutesConfig.API.DEPARTMENT, departmentId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_ok_with_edited_department_as_payload() throws Exception {

        DepartmentId departmentId = DepartmentIdMother.random();
        DepartmentForm form = DepartmentFormMother.random();

        Department department = DepartmentMother.withId(departmentId);

        when(editor.edit(departmentId, form)).thenReturn(department);

        mockMvc.perform(
                        patch(RoutesConfig.API.DEPARTMENT, departmentId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(serializeAsJson(form))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(serializeAsJson(mapper.map(department, DepartmentDTO.class))));
    }
}