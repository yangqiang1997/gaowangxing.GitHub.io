package com.cqut.entity.dto.activiti;

import javax.validation.constraints.NotNull;

/**
 * 杨强
 * 2018.4.16
 */
public class StartProcessDTO {
    /**
     * 启动流程的类型，为枚举类型
     */
    @NotNull
    private ProcessKey processKey;

    /**
     * 关联表单数据id
     */
    @NotNull
    private String processDataId;

    /**
     * 启动用户的id
     */
    @NotNull
    private String startUserId;

    /**
     * 启动用户的角色类型
     */
    @NotNull
    private String startRoleType;

    /**
     * 最上级组织机构
     */
    @NotNull
    private String organization;

    public ProcessKey getProcessKey() {
        return processKey;
    }

    public void setProcessKey(ProcessKey processKey) {
        this.processKey = processKey;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartRoleType() {
        return startRoleType;
    }

    public void setStartRoleType(String startRoleType) {
        this.startRoleType = startRoleType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProcessDataId() {
        return processDataId;
    }

    public void setProcessDataId(String processDataId) {
        this.processDataId = processDataId;
    }
}
