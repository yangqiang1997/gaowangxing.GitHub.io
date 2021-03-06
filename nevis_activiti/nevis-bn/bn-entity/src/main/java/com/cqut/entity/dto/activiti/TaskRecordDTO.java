package com.cqut.entity.dto.activiti;

import java.util.Date;

/**
 * 杨强
 * 2018.4.16
 */
public class TaskRecordDTO {

    /**
     * 流程id
     */
    private String processId;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 所属流程实例的第几结点
     */
    private Integer nodeOrder;

    /**
     * 节点执行时间
     */
    private Date completeDate;

    /**
     * 节点执行人
     */
    private String completeUserId;

    /**
     * 节点相关数据id
     */
    private String nodeDataId;

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getCompleteUserId() {
        return completeUserId;
    }

    public void setCompleteUserId(String completeUserId) {
        this.completeUserId = completeUserId;
    }

    public String getNodeDataId() {
        return nodeDataId;
    }

    public void setNodeDataId(String nodeDataId) {
        this.nodeDataId = nodeDataId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
