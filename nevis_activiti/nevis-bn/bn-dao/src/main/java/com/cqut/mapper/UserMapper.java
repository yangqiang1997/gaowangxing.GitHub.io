package com.cqut.mapper;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.user.UserLockDTO;
import com.cqut.entity.dto.user.UserPasswordDTO;
import com.cqut.entity.dto.user.UserSearchDTO;
import com.cqut.entity.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * 作者：谭勇
 * 时间：2018-04-07
 */
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    boolean insertSelective(User record);

    User selectByPrimaryKey(String userId);

    boolean updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(@Param("record") User record);

    /**
     * 根据账号去查找用户
     * @param username 用户登录的账号
     * @param falseFlag 值是0（false）
     * */
    AuthUser loadUserByUsername(@Param("username") String username,
                                @Param("falseFlag") byte falseFlag);

    /**
     * 利用pagehelper框架进行选择
     * @param userSearchDTO 搜索条件
     * @param dataScope 数据范围
     * */
    Page<User> selectUsers(@Param("us") UserSearchDTO userSearchDTO,
                           @Param("dataScope") String dataScope);

    /**
     * 软删除，将is_delete的值改为true
     * @param userId 用户id
     * @param trueFlag 值是1（true）
     * */
    boolean softDeleteUser(@Param("userId") String userId,
                           @Param("trueFlag") byte trueFlag);

    /**
     * 查找用户的基本信息，不包括密码
     * @param userId 用户id
     * */
    User findUser(@Param("userId") String userId);

    /**
     * 验证用户密码是否正确
     * @param userPasswordDTO 用户密码信息
     * */
    boolean validatePassword(UserPasswordDTO userPasswordDTO);

    /**
     * 判断账号是否已经存在
     * @param account 账号
     * */
    boolean existAccount(@Param("account") String account);

    /**
     * 根据组织机构查询用户
     * @param orgId 账号
     * */
    ArrayList<User> selectUserByOrgId(@Param("orgId") String orgId);

    /**
     * 修改用户当前禁用状态
     * @param userLockDTO
     * @return
     */
    boolean changeUserLockState(UserLockDTO userLockDTO);
}
