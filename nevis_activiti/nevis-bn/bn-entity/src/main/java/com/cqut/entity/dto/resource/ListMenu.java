package com.cqut.entity.dto.resource;

import com.cqut.entity.entity.Organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListMenu implements Serializable {

	private List children = new ArrayList();

	private String resourceId;

	private String resourceName;

	private String resourcePath;

	private String level0;

	private String resourceIconClass;

	private Byte resourceShowOrder;

	private String parentResourceId;

	private Boolean isMenu;

	private String authorizationToken;

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId == null ? null : resourceId.trim();
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName == null ? null : resourceName.trim();
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath == null ? null : resourcePath.trim();
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
		this.resourceIconClass = resourceIconClass == null ? null : resourceIconClass.trim();
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
		this.parentResourceId = parentResourceId == null ? null : parentResourceId.trim();
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
		this.authorizationToken = authorizationToken == null ? null : authorizationToken.trim();
	}

	public ListMenu(ResourceDTO resourceDTO){
		this.resourceId = resourceDTO.getResourceId();
		this.resourceName = resourceDTO.getResourceName();
		this.resourcePath = resourceDTO.getResourcePath();
		this.level0 = resourceDTO.getLevel0();
		this.resourceIconClass = resourceDTO.getResourceIconClass();
		this.resourceShowOrder = resourceDTO.getResourceShowOrder();
		this.parentResourceId = resourceDTO.getParentResourceId();
		this.isMenu = resourceDTO.getIsMenu();
		this.authorizationToken = resourceDTO.getAuthorizationToken();
	}

}