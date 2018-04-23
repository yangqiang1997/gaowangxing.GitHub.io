package com.cqut.mapper;

import com.cqut.entity.dto.activiti.ProcessRecordResultDTO;
import com.cqut.entity.dto.activiti.TaskRecordDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivitiRecordMapper {
    List<ProcessRecordResultDTO> getTaskRecord(@Param("processInstanceId") String processInstanceId);
    ProcessRecordResultDTO getLastRecord(@Param("processInstanceId") String processInstanceId);
    boolean insert(TaskRecordDTO taskRecordDTO);
}
