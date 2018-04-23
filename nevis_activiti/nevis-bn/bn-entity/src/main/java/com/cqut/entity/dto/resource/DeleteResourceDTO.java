package com.cqut.entity.dto.resource;

import javax.validation.constraints.NotNull;

/**
 * 资源管理(菜单管理DTO）删除
 * 作者：黎鹏
 * 日期：2018-04-05
 * */
public class DeleteResourceDTO {

    @NotNull(message = "资源id不能为空")
    private String resourceId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}