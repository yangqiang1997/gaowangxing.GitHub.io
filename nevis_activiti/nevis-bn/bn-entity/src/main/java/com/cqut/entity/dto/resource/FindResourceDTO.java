package com.cqut.entity.dto.resource;

import com.cqut.entity.entity.Resource;

import static com.cqut.util.string.StringUtil.emptyStr2NullStr;

/**
 * 资源管理(菜单管理DTO）根据id查询
 * 作者：黎鹏
 * 日期：2018-04-05
 * */
public class FindResourceDTO {

    private String resourceId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}