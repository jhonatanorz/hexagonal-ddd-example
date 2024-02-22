package com.jhonatanorz.learning.departments.infraestructure.web.ui;

import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.config.ViewConfig;
import com.jhonatanorz.learning.shared.infraestructure.web.ViewController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentViewController extends ViewController {

    public DepartmentViewController(ModelMapper mapper) {
        super(mapper);
    }

    @GetMapping(RoutesConfig.UI.DEPARTMENTS)
    public String companyDepartments(Model model) {

        addMetadata(model);
        return ViewConfig.COMPANY_DEPARTMENTS_VIEW;
    }

}
