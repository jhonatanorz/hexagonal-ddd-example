package com.jhonatanorz.learning.departments.infraestructure.web.api;

import com.jhonatanorz.learning.config.RoutesConfig;
import com.jhonatanorz.learning.departments.application.DepartmentEditor;
import com.jhonatanorz.learning.departments.domain.Department;
import com.jhonatanorz.learning.departments.domain.DepartmentForm;
import com.jhonatanorz.learning.departments.domain.DepartmentId;
import com.jhonatanorz.learning.departments.infraestructure.web.dto.DepartmentDTO;
import com.jhonatanorz.learning.shared.infraestructure.web.ApiController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentApiPatchController extends ApiController {

    private final DepartmentEditor editor;

    public DepartmentApiPatchController(ModelMapper mapper, DepartmentEditor editor) {
        super(mapper);
        this.editor = editor;
    }

    @PatchMapping(RoutesConfig.API.DEPARTMENT)
    public ResponseEntity<DepartmentDTO> patch(@PathVariable String departmentId, @RequestBody DepartmentForm form) {

        DepartmentId id = new DepartmentId(departmentId);

        Department department = editor.edit(id, form);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.map(department, DepartmentDTO.class));
    }
}
