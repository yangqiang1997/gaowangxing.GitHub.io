package com.cqut.service;


import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.organization.*;
import com.cqut.entity.entity.CompanyInfo;
import com.cqut.entity.entity.OrganizationType;
import com.cqut.entity.global.PaginationDTO;
import com.github.pagehelper.Page;

import java.util.List;

import com.cqut.entity.entity.Organization;
import com.cqut.entity.entity.User;

/**
 * 组织机构管理Service
 * 作者 ：程若兰
 * 时间 ：2018-04-05
 */
public interface OrganizationService {
    /**
     * 得到所有的组织机构
     * @return
     */
    List<FindOrgList> findAllOrg(AuthUser authUser);

    /**
     * 根据Id得到组织机构
     * @return
     */
    Organization findOrg(FindOrg findOrg);

    /**
     * 得到组织机构类型
     * @return
     */
    List<OrganizationType> selectOrgType();

    /**
     * 删除组织机构
     * @param orgId
     * @return
     */
    boolean deleteOrg(String orgId);

    /**
     * 添加组织机构
     * @param authUser
     * @param organization
     * @return
     */
    boolean addOrg(AuthUser authUser, Organization organization);

    /**
     * 公司或者部门名判重
     * @param organization
     * @return
     */
    boolean existOrgName(Organization organization);

    /**
     * 修改组织机构
     * @param authUser
     * @param organization
     * @return
     */
    boolean updateOrg(AuthUser authUser, Organization organization);

    /**
     * 通过组织机构获取用户信息
     * @param paginationDTO
     *   @param orgId
     * @return
     */
    Page<User> findUser(PaginationDTO paginationDTO,String orgId);

    /**
     * 得到公司信息
     * @param orgId
     * @return
     */
    CompanyInfo findOrgExtendedInfo(String orgId);

    /**
     * 修改公司信息
     * @param companyInfo
     * @return
     */
    boolean updateCompanyInfo(CompanyInfo companyInfo);

    /**
     * 删除公司信息
     * @param comInfoId
     * @return
     */
    boolean deleteCompanyInfo(String comInfoId);
}
