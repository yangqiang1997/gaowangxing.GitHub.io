package com.cqut.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.role.AddRoleDTO;
import com.cqut.entity.dto.role.GetRoleDTO;
import com.cqut.entity.dto.role.ResultRoleDTO;
import com.cqut.entity.dto.role.ElTreeDTO;
import com.cqut.entity.entity.Role;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.mapper.RoleManagementMapper;
import com.cqut.service.DataScopeService;
import com.cqut.service.RoleManagementService;
import com.cqut.util.code.GetElTree;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    @Autowired
    private DataScopeService dataScopeService;

    @Autowired
    private RoleManagementMapper roleManagementMapper;

    private static String TABLE_NAME = "sys_role";

    /**
     * 获取所有角色列表
     * 作者：罗杏函
     * 时间：2018.4.13
     * @param role
     * @param authUser
     * @return
     */
    public List<ResultRoleDTO> getAllRoles(GetRoleDTO role, AuthUser authUser) {
        Page<ResultRoleDTO> result = roleManagementMapper.selectRole(role, null, authUser.getUserId());
        return result;
    }

    /**
     * 获取角色列表
     * @param role
     * @param paginationDTO
     * @return
     */
    @Override
    public Page<ResultRoleDTO> getRoleData(GetRoleDTO role, PaginationDTO paginationDTO, AuthUser authUser) {
        Page<ResultRoleDTO> result = null;
        try {
            String dataScope = dataScopeService.dataScopeFilterByCreateId(authUser, TABLE_NAME);
            PageHelper.startPage(paginationDTO.getPageNum(), paginationDTO.getPageSize());
            result = roleManagementMapper.selectRole(role, dataScope, authUser.getUserId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询一个角色
     * @param id
     * @return
     */
    @Override
    public ResultRoleDTO getARole(String id) {
        return roleManagementMapper.getARole(id);
    }

    /**
     * 删除一个角色
     * @param id
     * @return
     */
    @Override
    public boolean deleteRole(String id) {
        return roleManagementMapper.delete(id);
    }

    /**
     * 删除选定角色
     * @param ids
     * @return
     */
    @Override
    public boolean deleteSelectRole(String[] ids) {
        return roleManagementMapper.deleteRoles(ids);
    }

    /**
     * 更新角色
     * @param role
     * @param roleId
     * @param authUser
     * @return
     */
    @Override
    public boolean updateRole(AddRoleDTO role, String roleId, AuthUser authUser) {
        Role newRole = role.getRole();
        newRole.setRoleId(roleId);
        newRole.setUpdateId(authUser.getUserId());
        return roleManagementMapper.update(newRole);
    }

    /**
     * 新增角色
     * @param role
     * @param authUser
     * @return
     */
    @Override
    public boolean addRole(AddRoleDTO role, AuthUser authUser) {
        Role newRole = role.getRole();
        newRole.setCreateId(authUser.getUserId());
        return roleManagementMapper.insert(newRole);
    }

    /**
     * 获取资源的树形数据
     * @return
     */
    @Override
    public List<ElTreeDTO> getSource() {
        return GetElTree.getTree(roleManagementMapper.selectSource());
    }

    /**
     * 获取角色资源
     * @param id
     * @return
     */
    @Override
    public List<String> getRoleSource(String id) {
        return roleManagementMapper.selectRoleSource(id);
    }

    /**
     * 设置角色资源
     * @param resource
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean setRoleResource(String[] resource, String id) {
        roleManagementMapper.deleteSource(id);
        if(resource.length != 0){
            roleManagementMapper.setSource(resource, id);
        }
        return true;
    }

    /**
     * 设置角色数据范围
     * @param dataScope
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean setDataScope(String[] dataScope, String id) {
        try {
            System.err.println(Arrays.toString(dataScope));
            roleManagementMapper.setDataScope(dataScope[0], id);
            roleManagementMapper.deleteCustomDataScope(id);
            if(dataScope.length > 1){
                roleManagementMapper.setCustomDataScope(Arrays.copyOfRange(dataScope, 1 ,dataScope.length),id);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取角色数据范围
     * @param id
     * @return
     */
    @Override
    public List<String> getDataScope(String id) {
        List<String> dataScope = roleManagementMapper.selectCustomDataScope(id);
        if(dataScope.size() != 0){
            return dataScope;
        }else {
            return roleManagementMapper.selectDataScope(id);
        }
    }
}
