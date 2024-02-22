package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.companies.domain.CompanyId;
import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentSearcher;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.infraestructure.web.dto.DepartmentDTO;
import com.jhonatanorz.learning.shared.infraestructure.web.ApiController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentApiGetController extends ApiController {

    private final DepartmentSearcher searcher;

    public DepartmentApiGetController(ModelMapper mapper, DepartmentSearcher searcher) {
        super(mapper);
        this.searcher = searcher;
    }

    @GetMapping(RoutesConfig.API.COMPANY_DEPARTMENTS)
    public ResponseEntity<List<DepartmentDTO>> companyDepartments(@PathVariable String companyId) {

        CompanyId id = new CompanyId(companyId);

        List<Department> companyDepartments = searcher.search(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(map(companyDepartments, DepartmentDTO.class));
    }
}
