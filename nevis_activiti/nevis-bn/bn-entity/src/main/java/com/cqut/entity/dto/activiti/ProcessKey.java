package com.cqut.entity.dto.activiti;

/**
 * 杨强
 * 2018.4.19
 * 相关说明：在Activiti发布流程后，需要在枚举中新增枚举与流程的KEY值的映射。
 *          新增的流程，所有节点设置为单角色执行，并选定相应角色。
 */
public enum ProcessKey {
    REGISTER_PROCESS;

    public static String getDefineProcessKey(ProcessKey processKey) {
        switch (processKey) {
            case REGISTER_PROCESS:
                return "registerProcess";
            default:
                return null;
        }
    }

    public static ProcessKey getProcessKey(String processKey){
        switch (processKey) {
            case "registerProcess":
                return REGISTER_PROCESS;
            default:
                return null;
        }
    }
}
