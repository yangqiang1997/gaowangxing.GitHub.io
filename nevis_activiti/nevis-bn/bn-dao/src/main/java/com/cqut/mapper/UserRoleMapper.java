package com.cqut.mapper;

import com.cqut.entity.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者：谭勇
 * 时间：2018-04-07
 * */
public interface UserRoleMapper {
    int insert(UserRole record);

    int update(UserRole userRole);

    List<UserRole> selectAll();

    /**
     * 删除用户与角色关联
     * 在执行硬删除时，需要先删除用户关联的角色
     * @param userId 用户id
     * */
    int deleteUserRole(@Param("userId") String userId);

    /**
     * 删除角色与用户管理
     * 在执行硬删除时，需要先删除角色关联的用户
     * @param roleId 角色id
     * */
    int deleteRoleUser(@Param("roleId") String roleId);


    /**
     * 根据用户查询角色 传入用户id 返回是否当前用户已经分配角色
     * 修改人：罗杏函
     * 时间：2018-04-11
     * @param userId
     * @return
     */
    int findUserRole(@Param("userId") String userId);
}