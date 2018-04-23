package com.cqut.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.organization.FindOrg;
import com.cqut.entity.dto.organization.FindOrgList;
import com.cqut.entity.entity.*;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.mapper.*;
import com.cqut.service.OrganizationService;
import com.cqut.util.code.TreeCode;
import com.cqut.util.constants.SystemConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Date;

/**
 * 组织机构管理
 * 作者：程若兰
 * 日期：2018-04-05
 * */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private OrganizationTypeMapper organizationTypeMapper;

    @Autowired
    private OrganizationUserMapper organizationUserMapper;

    /**
     * 得到所有的组织机构
     * @return
     */
    @Override
    public List<FindOrgList> findAllOrg(AuthUser authUser){
        List<Organization> list;
        if(authUser.getAccount().equals("admin")){
            list = organizationMapper.selectAllOrg();
        }else{
            list = organizationMapper.selectOrgByUserId(authUser.getUserId());
        }

        List<FindOrgList> result = treeOrg(list);
        return result;
    }

    @Override
    public Organization findOrg(FindOrg findOrg) {
        return organizationMapper.selectOrg(findOrg.getId());
    }

    /**
     * 得到组织机构类型
     * @return
     */
    @Override
    public List<OrganizationType> selectOrgType(){
        return organizationTypeMapper.selectAll();
    }

    /**
     *删除组织机构
     * @param orgId
     * @return
     */
    @Override
    public boolean deleteOrg(String orgId) {
        if(orgId.isEmpty())
            return false;
        if(organizationUserMapper.selectUserExesit(orgId)){
            return organizationUserMapper.deleteByOrgId(orgId) &&  organizationMapper.deleteOrg(orgId, SystemConstants.TRUE_FLAG);
        }else{
            return organizationMapper.deleteOrg(orgId, SystemConstants.TRUE_FLAG);
        }
    }

    /**
     * 添加组织机构
     * @param authUser
     * @param organization
     * @return
     */
    @Override
    public boolean addOrg(AuthUser authUser, Organization organization){
        organization.setCreateId(authUser.getUserId());

        //判断名字是否重复
        if(existOrgName(organization)){
            return false;
        }

        if (organization.getParentOrganizationId() == null) {
            organization.setOrganizationId(TreeCode.getTreeCode(null,organizationMapper.findChildrenMaxCode(null)));
        }else{
            organization.setOrganizationId(TreeCode.getTreeCode(organization.getParentOrganizationId(),organizationMapper.findChildrenMaxCode(organization.getParentOrganizationId())));
        }
        organizationMapper.insert(organization);
        return true;
    }

    /**
     * 公司或者部门名判重
     * @param organization
     * @return
     */
    @Override
    public boolean existOrgName(Organization organization){
        String orgId = organization.getParentOrganizationId();
        if(orgId == null){
           return organizationMapper.existOrgNameForCompany(organization.getOrganizationName(),organization.getOrganizationId(),SystemConstants.FALSE_FLAG);
        }

        return organizationMapper.existOrgName(organization.getOrganizationName(),organization.getOrganizationId(),orgId.substring(0,8),SystemConstants.FALSE_FLAG);
    }

    /**
     * 修改组织机构
     * @param authUser
     * @param organization
     * @return
     */
    @Override
    public boolean updateOrg(AuthUser authUser, Organization organization){
        organization.setUpdateId(authUser.getUserId());
        organization.setUpdateTime(new Date());

        if(existOrgName(organization)){
            return false;
        }
        return organizationMapper.updateByPrimaryKey(organization);
    }

    /**
     * 查询组织机构下的用户
     * @param orgId
     * @return
     */
    @Override
    public Page<User> findUser(PaginationDTO paginationDTO, String orgId){
        PageHelper.startPage(paginationDTO.getPageNum(), paginationDTO.getPageSize());
        Page<User> users = organizationUserMapper.selectOrgUsers(orgId);
        return users;
    }

    /**
     * 得到公司信息
     * @param orgId
     * @return
     */
    @Override
    public CompanyInfo findOrgExtendedInfo(String orgId){
        return companyInfoMapper.selectByOrgId(orgId);
    }

    /**
     * 修改公司信息
     * @param companyInfo
     * @return
     */
    @Override
    public boolean updateCompanyInfo(CompanyInfo companyInfo) {
        return companyInfoMapper.updateByPrimaryKey(companyInfo);
    }

    /**
     * 删除公司信息
     * @param comInfoId
     * @return
     */
    @Override
    public boolean deleteCompanyInfo(String comInfoId) {
        if(comInfoId.isEmpty())
            return false;
        else return companyInfoMapper.deleteByPrimaryKey(comInfoId);
    }

    /**
     * 利用循将组织机构转换成树形结构
     * @param list
     * @return
     */
    private List<FindOrgList> treeOrg(List<Organization> list){
        List<FindOrgList> result = new ArrayList();
        if(list.size() <= 1 && list.size() > 0){
            result.add(new FindOrgList(list.get(0)));
        }else{
            for(int i = 0; i < list.size(); i++){
                Organization organization =  list.get(i);
                if(organization.getParentOrganizationId() == null){
                    FindOrgList findOrgList = new FindOrgList(organization);
                    findOrgList.setChildren(putChildren(findOrgList,list));
                    result.add(findOrgList);
                }
            }
        }
        return result;
    }

    /**
     * 利用递归找孩子
     * @param result
     * @param list
     * @return
     */
    private List<FindOrgList> putChildren(FindOrgList result,List<Organization> list){
        List<FindOrgList> children = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            Organization organization =  list.get(i);

            if(organization.getParentOrganizationId()!= null &&
                    organization.getParentOrganizationId().equals(result.getOrganizationId())){
                FindOrgList findOrgList = new FindOrgList(organization);
                findOrgList.setChildren(putChildren(findOrgList,list));
                children.add(findOrgList);
            }
        }
        return children;
    }
}
