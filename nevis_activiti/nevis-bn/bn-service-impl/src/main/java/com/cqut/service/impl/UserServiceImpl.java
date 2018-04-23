package com.cqut.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.organization.AddUserOrgDTO;
import com.cqut.entity.dto.role.AddUserRoleDTO;
import com.cqut.entity.dto.role.ElTreeDTO;
import com.cqut.entity.dto.user.UserDTO;
import com.cqut.entity.dto.user.UserLockDTO;
import com.cqut.entity.dto.user.UserPasswordDTO;
import com.cqut.entity.dto.user.UserSearchDTO;
import com.cqut.entity.entity.Organization;
import com.cqut.entity.entity.OrganizationUser;
import com.cqut.entity.entity.User;
import com.cqut.entity.entity.UserRole;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.mapper.OrganizationMapper;
import com.cqut.mapper.OrganizationUserMapper;
import com.cqut.mapper.UserMapper;
import com.cqut.mapper.UserRoleMapper;
import com.cqut.service.DataScopeService;
import com.cqut.service.UserService;
import com.cqut.util.code.GetElTree;
import com.cqut.util.constants.SystemConstants;
import com.cqut.util.exception.BusinessException;
import com.cqut.util.exception.NonDataScopeException;
import com.cqut.util.mapper.BeanMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private OrganizationUserMapper organizationUserMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private DataScopeService dataScopeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 作者：谭勇
     *
     * @param authUser      当前登录用户的信息
     * @param paginationDTO 分页信息
     * @param userSearchDTO 用户搜索信息
     */
    @Transactional(readOnly = true)
    @Override
    public Page<User> findUsers(AuthUser authUser, PaginationDTO paginationDTO, UserSearchDTO userSearchDTO) throws NonDataScopeException {
        PageHelper.startPage(paginationDTO.getPageNum(), paginationDTO.getPageSize());
        String dataScope = dataScopeService.dataScopeFilterByCreateId(authUser, "sys_user");
        Page<User> users = userMapper.selectUsers(userSearchDTO, dataScope);
        return users;
    }

    /**
     * 作者：谭勇
     * 验证账号是否重复
     *
     * @param account 账号
     */
    @Override
    public boolean existAccount(String account) {
        return userMapper.existAccount(account);
    }

    /**
     * 作者：谭勇
     * 新增用户基本信息
     * 修改人：罗杏函
     * 修改时间：2018.4.13
     * 用户进行增加时判断是否选择角色和组织机构
     * 如果选择了角色和组织机构就进行关联表添加
     * @param userId  当前登录人的id
     * @param userDTO 用户信息
     */
    @Transactional
    @Override
    public boolean addUser(String userId, UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO) throws BusinessException {
        if (userMapper.existAccount(userDTO.getAccount())) {
            throw new BusinessException("该账号已经被注册了");
        }

        User user = BeanMapper.map(userDTO, User.class);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateId(userId);
        user.setCreateTime(new Date());
        //密码需加密之后存入数据库，密码加密器必需和登录验证时的密码加密器一致
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean addValue = userMapper.insertSelective(user);
        if(addValue){
            UserRole userRole = new UserRole(user.getUserId(),addUserRoleDTO.getRoleId());
            OrganizationUser organizationUser = new OrganizationUser(user.getUserId(),addUserOrgDTO.getOrgId());
            updateRoleAndOrganization(userRole,organizationUser);
        }
        return addValue;
    }

    /**
     * 作者：谭勇
     * 删除用户，软删除
     *
     * @param userId
     */
    @Transactional
    @Override
    public boolean softDeleteUser(String userId) {
        return userMapper.softDeleteUser(userId, SystemConstants.TRUE_FLAG);
    }

    /**
     * 作者：谭勇
     * 删除用户，硬删除
     *
     * @param userId 硬删除
     */
    @Transactional
    @Override
    public boolean hardDeleteUser(String userId) {
        //删除用户关联的角色
        userRoleMapper.deleteUserRole(userId);
        //删除用户关联的组织机构
        organizationUserMapper.deleteUserOrganization(userId);

        //目前user_id作为外键关联的表只有这两个，后面若有扩展，需删除其数据再进行用户的删除
        userMapper.deleteByPrimaryKey(userId);

        return true;
    }

    /**
     * 作者：谭勇
     * 根据用户id查找用户基本信息
     *
     * @param userId 用户id
     */
    @Transactional(readOnly = true)
    @Override
    public User findUser(String userId) {
        return userMapper.findUser(userId);
    }

    /**
     * 作者：谭勇
     * 修改用户的基本信息
     * 修改人：罗杏函
     * 修改时间：2018.4.13
     * 修改目的：用户修改时，对用户进行判断，如果用户已经添加一个角色或是组织机构
     * 则对其进行修改，若没有则进行添加。
     * @param userId  当前登录人的用户id
     * @param userDTO 用户信息
     */
    @Transactional
    @Override
    public boolean updateUser(String userId, UserDTO userDTO, AddUserRoleDTO addUserRoleDTO, AddUserOrgDTO addUserOrgDTO) {
        User user = BeanMapper.map(userDTO, User.class);
        user.setUpdateId(userId);
        user.setUpdateTime(new Date());

        boolean updateValue = userMapper.updateByPrimaryKeySelective(user);
        if(updateValue){
            UserRole userRole = new UserRole(user.getUserId(),addUserRoleDTO.getRoleId());
            OrganizationUser organizationUser = new OrganizationUser(user.getUserId(),addUserOrgDTO.getOrgId());
            updateRoleAndOrganization(userRole,organizationUser);
        }
        return updateValue;
    }

    /**
     * 作者：谭勇
     * 验证密码是否正确
     *
     * @param userPasswordDTO 用户密码信息
     */
    @Override
    public boolean validatePassword(UserPasswordDTO userPasswordDTO) {
        String actualPassword = passwordEncoder.encode(userPasswordDTO.getPassword());
        userPasswordDTO.setPassword(actualPassword);
        return userMapper.validatePassword(userPasswordDTO);
    }

    /**
     * 作者：谭勇
     * 修改密码
     *
     * @param userPasswordDTO 用户密码信息
     */
    @Override
    public boolean updatePassword(UserPasswordDTO userPasswordDTO) {
        User user = BeanMapper.map(userPasswordDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 作者：罗杏函
     * @param userId 当前登录人的用户id
     * @param userLockDTO 用户信息
     * @return
     */
    @Override
    public boolean changeUserLockState(String userId, UserLockDTO userLockDTO){

        return userMapper.changeUserLockState(userLockDTO);
    }

    /**
     * 得到所有的组织机构
     * 树形
     * 作者：罗杏函
     * 时间：2018.4.15
     * @return
     */
    @Override
    public List<ElTreeDTO> findAllOrg(){
        List<ElTreeDTO> result = GetElTree.getTree(organizationUserMapper.selectAllOrg());
        return result;
    }

    /**
     * 得到所有的组织机构
     * 列表
     * 作者：罗杏函
     * 时间：2018.4.15
     * @return
     */
    @Override
    public List<Organization> findUserAllOrgs(){
        return organizationMapper.selectAllOrg();
    }

    /**
     *
     * @param userRole
     * @param organizationUser
     * @return
     */
    public boolean updateRoleAndOrganization(UserRole userRole, OrganizationUser organizationUser){
        int rv = userRoleMapper.findUserRole(userRole.getUserId());
        if (rv == 0 && userRole.getRoleId() != null){
            userRoleMapper.insert(userRole);
        } else if (rv == 1 && userRole.getRoleId() != null){
            userRoleMapper.update(userRole);
        }
        int ov = organizationUserMapper.findUserOrganization(organizationUser.getUserId());
        if (ov == 0 && organizationUser.getOrganizationId() != null) {
            organizationUserMapper.insert(organizationUser);
        } else if(ov == 1 && organizationUser.getOrganizationId() != null) {
            organizationUserMapper.update(organizationUser);
        }
        return true;
    }

}
