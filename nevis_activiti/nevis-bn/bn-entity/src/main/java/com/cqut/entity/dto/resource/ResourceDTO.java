package com.cqut.entity.dto.resource;

import com.cqut.entity.entity.Resource;
import javax.validation.constraints.NotNull;
import static com.cqut.util.string.StringUtil.emptyStr2NullStr;

/**
 * 资源管理(菜单管理DTO）DTO
 * 作者：黎鹏
 * 日期：2018-04-05
 * */
public class ResourceDTO {

    private String resourceId;

    @NotNull(message = "资源名称不能为空")
    private String resourceName;

    private String resourcePath;

    private String level0;

    private String resourceIconClass;

    private Byte resourceShowOrder;

    private String parentResourceId;

    @NotNull(message = "是否是菜单不能为空")
    private Boolean isMenu;

    @NotNull(message = "授权令牌不能为空")
    private String authorizationToken;

    private String parentName;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = emptyStr2NullStr(resourceName);
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = emptyStr2NullStr(resourcePath);
    }

    public String getLevel0() {
        return level0;
    }

    public void setLevel0(String level0) {
        this.level0 = level0;
    }

    public String getResourceIconClass() {
        return resourceIconClass;
    }

    public void setResourceIconClass(String resourceIconClass) {
        this.resourceIconClass = emptyStr2NullStr(resourceIconClass);
    }

    public Byte getResourceShowOrder() {
        return resourceShowOrder;
    }

    public void setResourceShowOrder(Byte resourceShowOrder) {
        this.resourceShowOrder = resourceShowOrder;
    }

    public String getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(String parentResourceId) {
        this.parentResourceId = emptyStr2NullStr(parentResourceId);
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Resource toSysResource() {
        Resource sysResource = new Resource();

        sysResource.setResourceId(resourceId);
        sysResource.setResourceName(resourceName);
        sysResource.setResourcePath(resourcePath);
        sysResource.setLevel0(level0);
        sysResource.setResourceIconClass(resourceIconClass);
        sysResource.setResourceShowOrder(resourceShowOrder);
        sysResource.setParentResourceId(parentResourceId);
        sysResource.setIsMenu(isMenu);
        sysResource.setAuthorizationToken(authorizationToken);

        return sysResource;
    }
}