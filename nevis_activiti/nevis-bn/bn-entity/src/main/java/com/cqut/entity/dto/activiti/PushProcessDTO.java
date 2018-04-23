package com.cqut.entity.dto.activiti;

/**
 * 杨强
 * 2018.4.16
 */
public class PushProcessDTO {
    /**
     * 推动流程的id
     */
    private String processId;

    /**
     * 当前用户的id
     */
    private String pussUserId;

    /**
     * 当前的角色类型
     */
    private String pussRoleType;

    /**
     * 下一节点执行人id，不指定为null
     */
    private String nextNodeUserId;

    /**
     * 最上级组织机构
     */
    private String organization;

    /**
     * 节点相关数据id，没有为null
     */
    private String nodeDataId;


    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getPussUserId() {
        return pussUserId;
    }

    public void setPussUserId(String pussUserId) {
        this.pussUserId = pussUserId;
    }

    public String getPussRoleType() {
        return pussRoleType;
    }

    public void setPussRoleType(String pussRoleType) {
        this.pussRoleType = pussRoleType;
    }

    public String getNextNodeUserId() {
        return nextNodeUserId;
    }

    public void setNextNodeUserId(String nextNodeUserId) {
        this.nextNodeUserId = nextNodeUserId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNodeDataId() {
        return nodeDataId;
    }

    public void setNodeDataId(String nodeDataId) {
        this.nodeDataId = nodeDataId;
    }
}
