package com.jhonatanorz.learning.departments.infraestructure.web.ui;

import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.config.ViewConfig;
import com.jhonatanorz.learning.shared.infraestructure.persistence.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jhonatanorz.learning.shared.infraestructure.web.ViewController.APP_VERSION_MODEL_ATTRIBUTE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepartmentViewControllerTest extends ControllerTest<DepartmentViewController> {

    @Override
    protected DepartmentViewController instantiateController() {
        return new DepartmentViewController(mapper);
    }

    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    @Test
    void should_return_view_with_model() throws Exception {

        mockMvc.perform(get(RoutesConfig.UI.DEPARTMENTS))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewConfig.COMPANY_DEPARTMENTS_VIEW))
                .andExpect(model().attributeExists(APP_VERSION_MODEL_ATTRIBUTE));
    }

}