package com.cqut.service.impl;

import com.cqut.entity.dto.activiti.*;
import com.cqut.mapper.ActivitiRecordMapper;
import com.cqut.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 杨强
 * 2018.4.17
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

    private static final String PROCESS_DATA_ID = "ProcessDataId";
    private static final String START_USER_ID = "StartUserId";
    private static final String START_ROLE_TYPE = "StartRoleType";
    private static final String ORGANIZATION = "Organization";
    private static final String NEXT_NODE_USER_ID = "NextNodeUserId";
    private static final String CANCEL_PROCESS_INSTANCE = "取消流程实例";
    private static final String USER_TASK = "userTask";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActivitiRecordMapper activitiRecordMapper;

    @Autowired
    RepositoryService repositoryService;


    /**
     * 启动流程
     *
     * @param startProcessDTO
     * @return
     */
    @Override
    public StartResultDTO startProcess(StartProcessDTO startProcessDTO) {
        StartResultDTO startResultDTO = new StartResultDTO();
        try {
            if (!validatedStartDTO(startProcessDTO)) throw new Exception(ActivitiTip.START_INCOMPLETE_DATA);
            ProcessInstance pi = runtimeService.startProcessInstanceByKey(ProcessKey.getDefineProcessKey(startProcessDTO.getProcessKey())
                    , transformVariables(startProcessDTO));
            startResultDTO.setResult(true);
            startResultDTO.setProcessInstanceId(pi.getProcessInstanceId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            startResultDTO.setResult(false);
        }

        return startResultDTO;
    }

    /**
     * 推动流程节点
     *
     * @param pushProcessDTO
     * @return
     */
    @Override
    public ResultDTO pushProcess(PushProcessDTO pushProcessDTO) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            if (!validatedPussDTO(pushProcessDTO)) throw new Exception(ActivitiTip.PUSH_INCOMPLETE_DATA);
            if (!userCanPush(pushProcessDTO)) throw new Exception(ActivitiTip.CANNOT_PUSH_PROCESS);
            if(!actualPushProcess(pushProcessDTO)) throw new Exception(ActivitiTip.COMPLETED_TASK_DEFAULT);

            resultDTO.setResult(true);
        }catch (ActivitiObjectNotFoundException e){
            System.err.println(ActivitiTip.TASK_HAS_BEEN_COMPLETED);
            resultDTO.setMessage(ActivitiTip.TASK_HAS_BEEN_COMPLETED);
            resultDTO.setResult(false);
        }catch (Exception e) {
            System.err.println(e.getMessage());
            resultDTO.setMessage(e.getMessage());
            resultDTO.setResult(false);
        }

        return resultDTO;
    }

    /**
     * 查询流程执行记录
     * @param processRecordQueryDTO
     * @return
     */
    @Override
    public List<ProcessRecordResultDTO> getProcessRecord(ProcessRecordQueryDTO processRecordQueryDTO) {
        return activitiRecordMapper.getTaskRecord(processRecordQueryDTO.getProcessInstanceId());
    }

    /**
     * 获取用户任务
     * @param userTaskQueryDTO
     * @return
     */
    @Override
    public List<UserTaskResultDTO> getUserTask(UserTaskQueryDTO userTaskQueryDTO) {
        if (!validatedTaskQueryDTO(userTaskQueryDTO)) return null;
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userTaskQueryDTO.getRoleType()).list();

        return getActualUserTask(userTaskQueryDTO, tasks);
    }

    /**
     * 取消一个流程实例
     * @param processRecordQueryDTO
     * @return
     */
    @Override
    public ResultDTO cancelProcessInstance(ProcessRecordQueryDTO processRecordQueryDTO) {
        ResultDTO resultDTO = new ResultDTO();
        try{
            runtimeService.deleteProcessInstance(processRecordQueryDTO.getProcessInstanceId(), CANCEL_PROCESS_INSTANCE);//删除流程实例
            resultDTO.setResult(true);
        }catch (Exception e){
            resultDTO.setResult(false);
            resultDTO.setMessage(ActivitiTip.PROCESS_NON_EXIST);
        }
        return resultDTO;
    }

    /**
     * 获取用户任务列表
     */
    private List<UserTaskResultDTO> getActualUserTask(UserTaskQueryDTO userTaskQueryDTO, List<Task> tasks){
        List<UserTaskResultDTO> resultList = new LinkedList<>();
        for(int i = 0 ;  i < tasks.size() ; i++){
            if(validatedTask(tasks.get(i).getProcessInstanceId(), userTaskQueryDTO)){
                resultList.add(getUserTaskResultDTO(userTaskQueryDTO, tasks.get(i)));
            }
        }

        return resultList;
    }

    /**
     * 验证任务是否属于该用户
     */
    private boolean validatedTask(String processId, UserTaskQueryDTO userTaskQueryDTO){
        boolean validateOrganization, validateNextNodeUser=false;
        //验证组织结构权限
        validateOrganization = userTaskQueryDTO.getOrganization().equals(runtimeService.getVariable(processId, ORGANIZATION));
        //验证当前节点是否特别指定
        String nextNodeUser = (String) runtimeService.getVariable(processId, NEXT_NODE_USER_ID);
        if (nextNodeUser == null || nextNodeUser.equals(userTaskQueryDTO.getUserId())) { validateNextNodeUser = true; }

        return validateOrganization && validateNextNodeUser;
    }

    /**
     * 获取一个用户任务详情
     */
    private UserTaskResultDTO getUserTaskResultDTO(UserTaskQueryDTO userTaskQueryDTO, Task task){
        UserTaskResultDTO userTaskResultDTO = new UserTaskResultDTO();

        ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionId(task.getProcessDefinitionId()) // 根据流程定义id查询
                .singleResult();

        int nodeOrder = getNodeOrder(task.getProcessInstanceId());
        userTaskResultDTO.setProcessKey(ProcessKey.getProcessKey(processDefinition.getKey()));
        userTaskResultDTO.setProcessInstanceId(task.getProcessInstanceId());
        userTaskResultDTO.setNodeOrder(nodeOrder + 1);
        userTaskResultDTO.setStartUserId((String )runtimeService.getVariable(task.getProcessInstanceId(), START_USER_ID));

        if(nodeOrder > 0){ setPreNodeData(userTaskResultDTO);}

        return userTaskResultDTO;
    }

    /**
     * 设置上一节点的相关信息
     */
    private void setPreNodeData(UserTaskResultDTO userTaskResultDTO){
        ProcessRecordResultDTO processRecordResultDTO = activitiRecordMapper.getLastRecord(userTaskResultDTO.getProcessInstanceId());
        userTaskResultDTO.setPreviousUserId(processRecordResultDTO.getCompleteUserId());
        userTaskResultDTO.setPreviousDate(processRecordResultDTO.getCompleteDate());
        userTaskResultDTO.setPreDataId(processRecordResultDTO.getNodeDataId());
    }

    /**
     * 验证用户任务查询信息是否完整
     */
    private boolean validatedTaskQueryDTO(UserTaskQueryDTO userTaskQueryDTO) {
        return userTaskQueryDTO.getOrganization() != null && userTaskQueryDTO.getRoleType() != null &&
                    userTaskQueryDTO.getUserId() != null;
    }

    /**
     * 转换StartProcessDTO为启动流程的流程变量
     */
    private Map<String, Object> transformVariables(StartProcessDTO startProcessDTO) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(PROCESS_DATA_ID, startProcessDTO.getProcessDataId());
        variables.put(START_USER_ID, startProcessDTO.getStartUserId());
        variables.put(START_ROLE_TYPE, startProcessDTO.getStartRoleType());
        variables.put(ORGANIZATION, startProcessDTO.getOrganization());

        return variables;
    }

    /**
     * 验证启动流程信息是否完整
     */
    private boolean validatedStartDTO(StartProcessDTO startProcessDTO) {
        return startProcessDTO.getOrganization() != null && startProcessDTO.getProcessDataId() != null
                && startProcessDTO.getProcessKey() != null && startProcessDTO.getStartRoleType() != null
                && startProcessDTO.getStartUserId() != null;
    }

    /**
     * 验证推动流程信息是否完整
     */
    private boolean validatedPussDTO(PushProcessDTO pushProcessDTO) {
        return pushProcessDTO.getOrganization() != null && pushProcessDTO.getProcessId() != null
                && pushProcessDTO.getPussRoleType() != null && pushProcessDTO.getPussUserId() != null;
    }

    /**
     * 验证用户是否有权推动此流程
     */
    private boolean userCanPush(PushProcessDTO pushProcessDTO) throws ActivitiObjectNotFoundException{

        boolean validateRoleType, validateOrganization, validateNextNodeUser=false;

        //验证角色权限
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pushProcessDTO.getProcessId()).list();
        validateRoleType = pushProcessDTO.getPussRoleType().equals(tasks.get(0).getAssignee());

        //验证组织结构权限
        validateOrganization = pushProcessDTO.getOrganization().equals(runtimeService.getVariable(pushProcessDTO.getProcessId(), ORGANIZATION));

        //验证当前节点是否特别指定
        String nextNodeUser = (String) runtimeService.getVariable(pushProcessDTO.getProcessId(), NEXT_NODE_USER_ID);
        if (nextNodeUser == null || nextNodeUser.equals(pushProcessDTO.getPussUserId())) { validateNextNodeUser = true; }

        return validateOrganization && validateNextNodeUser && validateRoleType;
    }

    /**
     * 推动节点并保存记录
     */
    private boolean actualPushProcess(PushProcessDTO pushProcessDTO){

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pushProcessDTO.getProcessId()).list();
        taskService.complete(tasks.get(0).getId());
        saveNextNodeUser(pushProcessDTO);

        return activitiRecordMapper.insert(getTaskRecordDTO(tasks.get(0), pushProcessDTO));
    }

    /**
     * 封装TaskRecordDTO
     */
    private TaskRecordDTO getTaskRecordDTO(Task task, PushProcessDTO pushProcessDTO){
        TaskRecordDTO taskRecordDTO = new TaskRecordDTO();
        taskRecordDTO.setProcessId(pushProcessDTO.getProcessId());
        taskRecordDTO.setCompleteUserId(pushProcessDTO.getPussUserId());
        taskRecordDTO.setNodeDataId(pushProcessDTO.getNodeDataId());
        taskRecordDTO.setNodeOrder(getNodeOrder(pushProcessDTO.getProcessId()));
        taskRecordDTO.setTaskId(task.getId());

        return taskRecordDTO;
    }

    /**
     * 获取已经完成的节点数目
     */
    private int getNodeOrder(String processId){
        return (historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processId).activityType(USER_TASK)
                .finished().orderByHistoricActivityInstanceEndTime().desc())
                .list().size();
    }

    /**
     * 保存下一节点执行人
     */
    private void saveNextNodeUser(PushProcessDTO pushProcessDTO){
        if(runtimeService.createProcessInstanceQuery().processInstanceId(pushProcessDTO.getProcessId()).singleResult() != null){
            runtimeService.setVariable(pushProcessDTO.getProcessId(), NEXT_NODE_USER_ID, pushProcessDTO.getNextNodeUserId());
        }
    }
}
