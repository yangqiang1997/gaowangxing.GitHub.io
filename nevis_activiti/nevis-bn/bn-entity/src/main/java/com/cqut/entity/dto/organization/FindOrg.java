package com.cqut.entity.dto.organization;

import javax.validation.constraints.NotNull;

public class FindOrg {
    @NotNull
    private String  id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
