package com.cqut.mapper;

import com.cqut.entity.entity.Organization;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface OrganizationMapper {
    /**
     * 直接删除
     * @param organizationId
     * @return
     */
    int deleteByPrimaryKey(String organizationId);

    /**
     * 添加组织机构
     * @param record
     * @return
     */
    int insert(Organization record);

    /**
     * 查询组织机构
     * @param organizationId
     * @return
     */
    Organization selectByPrimaryKey(@Param("organizationId") String organizationId);

    /**
     * 查询所有的组织机构
     * @return
     */
    List<Organization> selectAllOrg();

    /**
     * 通过UserId获取用户的组织机构
     * @return
     */
    List<Organization> selectOrgByUserId(String userId);

    /**
     * 修改组织机构
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(Organization record);

    /**
     * 软删除
     * @param trueFlag
     * @return
     */
    boolean deleteOrg(@Param("orgId") String orgId,
            @Param("trueFlag") byte trueFlag);

    /**
     * 得到最大的id
     * @param parentId
     * @return
     */
    String findChildrenMaxCode(@Param("parentId") String parentId);

    /**
     * 根据Id得到组织机构
     * @param orgId
     * @return
     */
    Organization selectOrg(@Param("orgId") String orgId);

    /**
     * 公司或者部门名称判重
     * @param state
     * @param organizationName
     *  @param organizationId
     * @return
     */
    boolean existOrgName(@Param("organizationName") String organizationName,
                         @Param("organizationId") String organizationId,
                        @Param("parentId") String parentId,
                        @Param("state") byte state);

    boolean existOrgNameForCompany(@Param("organizationName") String organizationName,
                                   @Param("organizationId") String organizationId,
                                   @Param("state") byte state);
}