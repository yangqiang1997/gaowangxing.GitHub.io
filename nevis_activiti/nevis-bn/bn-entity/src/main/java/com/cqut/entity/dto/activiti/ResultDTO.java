package com.cqut.entity.dto.activiti;

/**
 * 杨强
 * 2018.4.16
 */
public class ResultDTO {
    /**
     * 处理是否成功
     */
    private boolean result;

    /**
     * 处理信息
     */
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
