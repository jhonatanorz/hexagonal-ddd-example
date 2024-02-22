package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentRegister;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.shared.infraestructure.web.ApiController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentApiPutController extends ApiController {

    private final DepartmentRegister register;

    public DepartmentApiPutController(ModelMapper mapper, DepartmentRegister register) {
        super(mapper);
        this.register = register;
    }

    @PutMapping(RoutesConfig.API.COMPANY_DEPARTMENTS)
    public ResponseEntity put(@PathVariable String companyId, @RequestBody DepartmentForm form) {

        CompanyId id = new CompanyId(companyId);

        register.register(id, form);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


}
