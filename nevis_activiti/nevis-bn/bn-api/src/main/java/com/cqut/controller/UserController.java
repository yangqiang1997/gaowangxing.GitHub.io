package com.cqut.controller;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.group.Add;
import com.cqut.entity.dto.organization.AddUserOrgDTO;
import com.cqut.entity.dto.role.AddUserRoleDTO;
import com.cqut.entity.dto.user.*;
import com.cqut.entity.entity.User;
import com.cqut.entity.global.JSONResult;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.service.OrganizationService;
import com.cqut.service.UserService;
import com.cqut.util.exception.BusinessException;
import com.cqut.util.exception.NonDataScopeException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理
 * 作者：谭勇
 * 日期：2018-04-05
 * */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    /**
     * 获取用户列表
     * */
    //@PreAuthorize("hasPermission('USER', 'LIST')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public JSONResult findUsers(@AuthenticationPrincipal AuthUser authUser,
                                @Validated PaginationDTO paginationDTO,
                                UserSearchDTO userSearchDTO) throws NonDataScopeException {
        Page<User> users = userService.findUsers(authUser, paginationDTO, userSearchDTO);
        PageInfo pageInfo = new PageInfo(users);
        return new JSONResult(pageInfo);
    }

    /**
     * 判断账号是否重复
     * */
    @RequestMapping(value = "/account/validate", method = RequestMethod.GET)
    public JSONResult validateAccount(@Validated UserAccountDTO userAccountDTO) {

        return new JSONResult(userService.existAccount(userAccountDTO.getAccount()));
    }

    /**
     * 新增用户
     * */
    //@PreAuthorize("hasPermission('USER', 'ADD')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public JSONResult addUser(@AuthenticationPrincipal AuthUser authUser,
                              @Validated(Add.class) UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO) throws BusinessException {

        return new JSONResult(userService.addUser(authUser.getUserId(), userDTO, addUserRoleDTO, addUserOrgDTO));
    }

    /**
     * 删除用户
     * */
    //@PreAuthorize("hasPermission('USER', 'DELETE')")
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public JSONResult deleteUser(@Validated UserIdDTO userIdDTO) {

        //当前执行的是硬删除，正式上线之前需要改过来
        return new JSONResult(userService.softDeleteUser(userIdDTO.getUserId()));
    }

    /**
     * 获取用户详情
     * */
    //@PreAuthorize("hasPermission('USER', 'DETAIL')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JSONResult findUser(@Validated UserIdDTO userIdDTO) {

        return new JSONResult(userService.findUser(userIdDTO.getUserId()));
    }

    /**
     * 修改用户信息
     * */
    //@PreAuthorize("hasPermission('USER', 'UPDATE')")
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public JSONResult updateUser(@AuthenticationPrincipal AuthUser authUser,
                                 @Validated UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO) {

        return new JSONResult(userService.updateUser(authUser.getUserId(), userDTO, addUserRoleDTO, addUserOrgDTO));
    }

    /**
     * 检验当前登录人的密码是否正确
     * */
    @RequestMapping(value = "/password/validate", method = RequestMethod.GET)
    public JSONResult validatePassword(@AuthenticationPrincipal AuthUser authUser,
            @Validated UserPasswordDTO userPasswordDTO) {

        userPasswordDTO.setUserId(authUser.getUserId());
        return new JSONResult(userService.validatePassword(userPasswordDTO));
    }

    /**
     * 修改密码
     * */
    @RequestMapping(value = "/userPassword", method = RequestMethod.PUT)
    public JSONResult updatePassword(@AuthenticationPrincipal AuthUser authUser,
                                     @Validated UserPasswordDTO userPasswordDTO) {

        userPasswordDTO.setUserId(authUser.getUserId());
        return new JSONResult(userService.updatePassword(userPasswordDTO));
    }

    /**
     * 修改用户锁定状态
     * */
    //@PreAuthorize("hasPermission('USER', 'UPDATE')")
    @RequestMapping(value = "/lockState", method = RequestMethod.PUT)
    public JSONResult changeUserLockState(@AuthenticationPrincipal AuthUser authUser,
                                 @Validated UserLockDTO userLockDTO) {

        return new JSONResult(userService.changeUserLockState(authUser.getUserId(), userLockDTO));
    }

    /**
     * 查询所有组织机构 以树形返回
     * @return
     */
    @RequestMapping(value = "/userOrgs", method = RequestMethod.GET)
    public JSONResult getAllOrg() {
        return new JSONResult<List>(userService.findAllOrg());
    }

    /**
     * 查询所有组织机构 以列表形返回
     * @return
     */
    @RequestMapping(value = "/userAllOrgs", method = RequestMethod.GET)
    public JSONResult getUserAllOrgs() {
        return new JSONResult<List>(userService.findUserAllOrgs());
    }
}