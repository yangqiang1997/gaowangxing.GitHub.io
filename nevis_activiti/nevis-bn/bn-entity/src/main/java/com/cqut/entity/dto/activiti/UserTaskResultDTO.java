package com.cqut.entity.dto.activiti;

import java.util.Date;

/**
 * 杨强
 * 2018.4.16
 */
public class UserTaskResultDTO {

    /**
     * 任务所属流程实例类型
     */
    private ProcessKey processKey;

    /**
     * 任务所属的流程实例id
     */
    private String processInstanceId;

    /**
     * 所属流程实例的第几结点
     */
    private Integer nodeOrder;

    /**
     * 流程发起人id
     */
    private String startUserId;

    /**
     * 上一节点执行人id
     */
    private String previousUserId;

    /**
     * 上一节点处理时间
     */
    private Date previousDate;

    /**
     * 上一节点数据id
     */
    private String preDataId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public ProcessKey getProcessKey() {
        return processKey;
    }

    public void setProcessKey(ProcessKey processKey) {
        this.processKey = processKey;
    }

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getPreviousUserId() {
        return previousUserId;
    }

    public void setPreviousUserId(String previousUserId) {
        this.previousUserId = previousUserId;
    }

    public Date getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(Date previousDate) {
        this.previousDate = previousDate;
    }

    public String getPreDataId() {
        return preDataId;
    }

    public void setPreDataId(String preDataId) {
        this.preDataId = preDataId;
    }
}
