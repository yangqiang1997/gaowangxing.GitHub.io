package com.cqut.service;

import com.cqut.entity.dto.activiti.*;

import java.util.List;

/**
 * 杨强
 * 2018.4.16
 */
public interface ActivitiService {

    StartResultDTO startProcess(StartProcessDTO startProcessDTO);

    ResultDTO pushProcess(PushProcessDTO pushProcessDTO);

    List<ProcessRecordResultDTO> getProcessRecord(ProcessRecordQueryDTO processRecordQueryDTO);

    List<UserTaskResultDTO> getUserTask(UserTaskQueryDTO userTaskQueryDTO);

    ResultDTO cancelProcessInstance(ProcessRecordQueryDTO processRecordQueryDTO);
}
