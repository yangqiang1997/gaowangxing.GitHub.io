package com.cqut.entity.dto.activiti;

/**
 * 杨强
 * 2018.4.16
 */
public class StartResultDTO {
    /**
     * 启动是否成功
     */
    private boolean result;

    /**
     * 返回的流程实例id
     */
    private String processInstanceId;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }


    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
