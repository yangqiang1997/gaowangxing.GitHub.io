package com.cqut.mapper;

import com.cqut.entity.dto.role.ElTreeDTO;
import com.cqut.entity.entity.OrganizationUser;
import com.cqut.entity.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者：谭勇
 * 时间：2018-04-07
 * */
public interface OrganizationUserMapper {
    int insert(@Param("record") OrganizationUser record);

    int update(OrganizationUser organizationUser);

    List<OrganizationUser> selectAll();

    /**
     * 找到用户对应的组织机构id
     * @param userId 用户id
     * */
    List<String> selectUserOrganizations(@Param("userId") String userId);

    boolean selectUserExesit(@Param("orgId") String orgId);

    /**
     * 删除用户关联的组织机构
     * 在执行硬删除操作删除用户时，需要先删除用户关联的组织机构
     * @param userId 用户id
     * */
    int deleteUserOrganization(@Param("userId") String userId);

    /**
     * 删除组织机构关联的用户
     * 在执行硬删除操作删除组织机构是，需要先删除组织机构关联的用户
     * @param
     * */
    int deleteOrganizationUser(@Param("organizationId") String organizationId);

    /**
     * 删除关联表中的相关信息　删除用户
     * @param userId
     * @param organizationId
     * @return
     */
    int deleteByUserIdAndOrgID(@Param("userId") String userId,
                               @Param("organizationId") String organizationId);


    boolean deleteByOrgId(@Param("organizationId") String organizationId);

    /**
     * 找到该用户对应的组织机构条数
     * 作者：罗杏函
     * 时间：2018.4.13
     * @param userId
     * @return
     */
    int findUserOrganization(@Param("userId") String userId);

    /**
     * 查找到对应组织机构下的用户所有信息
     * 作者：罗杏函
     * 时间：2018.4.13
     * @param organizationId
     * @return 用户信息列表 可直接在用户管理界面使用作为tableDate
     */
    Page<User> selectOrgUsers(@Param("orgId") String organizationId);

    /**
     * 查询所有的组织机构
     * 树形
     * 作者：罗杏函
     * 时间：2018.4.15
     * @return
     */
    List<ElTreeDTO> selectAllOrg();

}