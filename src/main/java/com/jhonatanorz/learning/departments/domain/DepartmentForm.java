package com.jhonatanorz.learning.departments.domain;

import com.jhonatanorz.learning.shared.domain.Form;
import com.jhonatanorz.learning.shared.domain.Identifier;
import lombok.Data;

@Data
public class DepartmentForm implements Form {

    private String id;
    private String name;

    public DepartmentForm() {
    }

    public DepartmentForm(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean isValid() {

        try{
            new Identifier(id);
            new DepartmentName(name);

        }catch (IllegalArgumentException ex){
            return false;
        }

        return true;
    }
}
