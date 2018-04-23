package com.cqut.service;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.organization.AddUserOrgDTO;
import com.cqut.entity.dto.role.AddUserRoleDTO;
import com.cqut.entity.dto.role.ElTreeDTO;
import com.cqut.entity.dto.user.UserLockDTO;
import com.cqut.entity.dto.user.UserPasswordDTO;
import com.cqut.entity.dto.user.UserDTO;
import com.cqut.entity.dto.user.UserSearchDTO;
import com.cqut.entity.entity.Organization;
import com.cqut.entity.entity.User;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.util.exception.BusinessException;
import com.cqut.util.exception.NonDataScopeException;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 用户服务类
 * 作者：谭勇
 * 时间：2018-04-06
 * */
public interface UserService {

    /**
     * 作者：谭勇
     * @param authUser 当前登录用户的信息
     * @param paginationDTO 分页信息
     * @param userSearchDTO 用户搜索信息
     * */
    Page<User> findUsers(AuthUser authUser, PaginationDTO paginationDTO, UserSearchDTO userSearchDTO) throws NonDataScopeException;

    /**
     * 作者：谭勇
     * 验证账号是否重复
     * @param account 账号
     * */
    boolean existAccount(String account);

    /**
     * 作者：谭勇
     * 新增用户基本信息
     * @param userId 当前登录人的id
     * @param userDTO 用户信息
     * */
    boolean addUser(String userId, UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO) throws BusinessException;

    /**
     * 作者：谭勇
     * 删除用户，软删除
     * @param userId 用户id
     * */
    boolean softDeleteUser(String userId);

    /**
     * 作者：谭勇
     * 删除用户，硬删除
     * @param userId 硬删除
     * */
    boolean hardDeleteUser(String userId);

    /**
     * 作者：谭勇
     * 根据用户id查找用户基本信息
     * @param userId 用户id
     * */
    User findUser(String userId);

    /**
     * 作者：谭勇
     * 修改用户的基本信息
     * @param userId 当前登录人的用户id
     * @param userDTO 用户信息
     * */
    boolean updateUser(String userId, UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO);

    /**
     * 作者：罗杏函
     * 修改用户的禁止状态
     * @param userId 当前登录人的用户id
     * @param userLockDTO 用户信息
     * @return
     */
    boolean changeUserLockState(String userId, UserLockDTO userLockDTO);

    /**
     * 作者：谭勇
     * 验证密码是否正确
     *
     * @param userPasswordDTO 用户密码信息
     * */
    boolean validatePassword(UserPasswordDTO userPasswordDTO);

    /**
     * 作者：谭勇
     * 修改密码
     *
     * @param userPasswordDTO 用户密码信息
     * */
    boolean updatePassword(UserPasswordDTO userPasswordDTO);

    /**
     * 得到所有的组织机构
     * 树形
     * @return
     */
    List<ElTreeDTO> findAllOrg();

    /**
     * 找到所有的组织机构
     * 非树形
     * @return
     */
    List<Organization> findUserAllOrgs();
}
