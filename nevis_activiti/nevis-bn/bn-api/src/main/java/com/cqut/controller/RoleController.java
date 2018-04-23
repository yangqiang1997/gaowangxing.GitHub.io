package com.cqut.controller;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.role.AddRoleDTO;
import com.cqut.entity.dto.role.GetRoleDTO;
import com.cqut.entity.dto.role.ResultRoleDTO;
import com.cqut.entity.dto.role.RoleIdDTO;
import com.cqut.entity.global.JSONResult;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.service.RoleManagementService;
import com.cqut.util.constants.ResponseCodeConstants;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * roleManagement: 角色管理
 * 作者：杨强
 * 日期：2018/04/5
 */
@RestController
@CrossOrigin
@Api("roleManagement: 角色管理")
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleManagementService roleManagementService;


    /**
     * GetRoleData: 角色信息列表获取
     * 作者：罗杏函
     * 时间：2018.1.13
     * 为了用户管理时查询所有的角色
     */
    @RequestMapping(value = "/allRoles", method = RequestMethod.GET)
    public JSONResult getAllRoles(GetRoleDTO role, @AuthenticationPrincipal AuthUser authUser) {
        return new JSONResult(roleManagementService.getAllRoles(role, authUser));
    }

    /**
     * GetRoleData: 角色信息列表获取
     */
    //@PreAuthorize("hasPermission('ROLE', 'LIST')")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public JSONResult getRoleData(GetRoleDTO role, @Validated PaginationDTO paginationDTO, @AuthenticationPrincipal AuthUser authUser) {
        PageInfo roles = new PageInfo(roleManagementService.getRoleData(role, paginationDTO, authUser));
        return new JSONResult(roles);
    }

    /**
     * GetRoleData: 获取一个角色信息
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public JSONResult getARole(@Validated RoleIdDTO id) {
        return new JSONResult(roleManagementService.getARole(id.getId()));
    }

    /**
     * AddRole: 新增角色
     */
    //@PreAuthorize("hasPermission('ROLE', 'ADD')")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public JSONResult AddRole(@Validated AddRoleDTO role, @AuthenticationPrincipal AuthUser authUser) {
        if (roleManagementService.addRole(role, authUser) == true) {
            return new JSONResult(ResponseCodeConstants.SUCCESS);
        } else {
            return new JSONResult(ResponseCodeConstants.FAILURE);
        }
    }

    /**
     * UpdateRole: 更新角色
     */
    //@PreAuthorize("hasPermission('ROLE', 'UPDATE')")
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public JSONResult updateRole(@Validated AddRoleDTO role, @Validated RoleIdDTO id, @AuthenticationPrincipal AuthUser authUser) {
        if (roleManagementService.updateRole(role, id.getId(), authUser) == true) {
            return new JSONResult(ResponseCodeConstants.SUCCESS);
        } else {
            return new JSONResult(ResponseCodeConstants.FAILURE);
        }
    }

    /**
     * DeleteRole: 删除角色
     */
    //@PreAuthorize("hasPermission('ROLE', 'DELETE')")
    @RequestMapping(value = "/role/delete", method = RequestMethod.DELETE)
    public JSONResult deleteRole(@Validated RoleIdDTO id) {
        if (roleManagementService.deleteRole(id.getId()) == true) {
            return new JSONResult(ResponseCodeConstants.SUCCESS);
        } else {
            return new JSONResult(ResponseCodeConstants.FAILURE);
        }
    }

    /**
     * DeleteRole: 删除选定角色
     */
    //@PreAuthorize("hasPermission('ROLE', 'DELETE')")
    @RequestMapping(value = "/roles/delete", method = RequestMethod.DELETE)
    public JSONResult deleteSelectRole(String[] ids) {
        if (ids != null && roleManagementService.deleteSelectRole(ids) == true) {
            return new JSONResult(ResponseCodeConstants.SUCCESS);
        } else {
            return new JSONResult(ResponseCodeConstants.FAILURE);
        }
    }

    /**
     * GetJurisdictionList: 获取权限列表
     */
    @RequestMapping(value = "/sourceList", method = RequestMethod.GET)
    public JSONResult getSourceList() {
        return new JSONResult(ResponseCodeConstants.SUCCESS, roleManagementService.getSource());
    }

    /**
     * GetARoleSource: 角色信息列表获取
     */
    @RequestMapping(value = "/roleSource", method = RequestMethod.GET)
    public JSONResult GetARoleSource(@Validated RoleIdDTO id) {
        return new JSONResult(ResponseCodeConstants.SUCCESS, roleManagementService.getRoleSource(id.getId()));
    }

    /**
     * SetRoleSource: 设置角色资源
     */
    @RequestMapping(value = "/roleSource/dstribution", method = RequestMethod.GET)
    public JSONResult SetRoleSource(String[] checked, @Validated RoleIdDTO id) {
        roleManagementService.setRoleResource(checked, id.getId());
        return new JSONResult(ResponseCodeConstants.SUCCESS);
    }

    /**
     * roleDataScope: 设置角色数据范围
     */
    @RequestMapping(value = "/roleDataScope", method = RequestMethod.GET)
    public JSONResult SetRoleDataScope(String[] checked, @Validated RoleIdDTO id) {
        roleManagementService.setDataScope(checked, id.getId());
        return new JSONResult(ResponseCodeConstants.SUCCESS);
    }

    /**
     * SetRoleSource: 设置角色数据范围
     */
    @RequestMapping(value = "/roleDataScopeList", method = RequestMethod.GET)
    public JSONResult GetRoleDataScope(@Validated RoleIdDTO id) {
        return new JSONResult(roleManagementService.getDataScope(id.getId()));
    }
}
