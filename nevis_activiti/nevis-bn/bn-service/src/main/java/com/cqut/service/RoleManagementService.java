package com.cqut.service;


import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.role.AddRoleDTO;
import com.cqut.entity.dto.role.GetRoleDTO;
import com.cqut.entity.dto.role.ResultRoleDTO;
import com.cqut.entity.dto.role.ElTreeDTO;
import com.cqut.entity.global.PaginationDTO;

import java.util.List;

public interface RoleManagementService{
    List<ResultRoleDTO> getRoleData(GetRoleDTO role, PaginationDTO paginationDTO, AuthUser authUser);
    List<ResultRoleDTO> getAllRoles(GetRoleDTO role, AuthUser authUser);
    ResultRoleDTO getARole(String id);
    boolean deleteRole(String id);
    boolean deleteSelectRole(String[] ids);
    boolean updateRole(AddRoleDTO role, String id, AuthUser authUser);
    boolean addRole(AddRoleDTO role, AuthUser authUser);
    List<ElTreeDTO> getSource();
    List<String> getRoleSource(String id);
    boolean setRoleResource(String[] resource, String id);
    boolean setDataScope(String[] dataScope, String id);
    List<String> getDataScope(String id);
}