package com.cqut.entity.dto.resource;

import com.cqut.entity.entity.Resource;

import javax.validation.constraints.NotNull;

import static com.cqut.util.string.StringUtil.emptyStr2NullStr;

/**
 * 资源管理(菜单管理DTO）根据资源名称搜索
 * 作者：黎鹏
 * 日期：2018-04-05
 * */
public class SearchResourceDTO {

    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = emptyStr2NullStr(resourceName);
    }
}