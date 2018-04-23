package com.cqut.entity.dto.organization;

import javax.validation.constraints.NotNull;

public class DeleteOrgsDTO {
    @NotNull
    private String[] ids;


    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
