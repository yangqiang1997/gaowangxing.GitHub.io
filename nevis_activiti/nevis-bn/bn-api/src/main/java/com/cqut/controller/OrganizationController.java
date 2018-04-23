package com.cqut.controller;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.entity.User;
import com.cqut.entity.global.JSONResult;
import com.cqut.entity.global.PaginationDTO;
import com.cqut.service.OrganizationService;
import com.cqut.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cqut.entity.dto.organization.*;
import com.cqut.entity.entity.Organization;
import java.util.List;

/**
 * 组织机构管理
 * 作者：程若兰
 * 日期：2018-04-05
 * */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    /**
     * 获取所有的组织机构类型
     * @return 返回　组织机构类型
     */
    //@PreAuthorize("hasPermission('ORG', 'LIST')")
    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public JSONResult getAllOrg(@AuthenticationPrincipal AuthUser authUser) {
        return new JSONResult<List>(organizationService.findAllOrg(authUser));
    }

    /**
     * 根据ID得到组织机构
     * @return
     */
    @RequestMapping(value = "/org", method = RequestMethod.GET)
    public JSONResult getOrg(@Validated FindOrg findOrg) {
        return new JSONResult(organizationService.findOrg(findOrg));
    }

    /**
     * 获取扩展信息
     * 暂时只有公司的扩展信息
     */
    //@PreAuthorize("hasPermission('ORG', 'LIST')")
    @RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
    public JSONResult getOrgExtendedInfo(@Validated Organization organization) {
        return new JSONResult<>(organizationService.findOrgExtendedInfo(organization.getOrganizationId()));
    }

    /**
     * 获取组织机构类型
     */
    //@PreAuthorize("hasPermission('ORG', 'LIST')")
    @RequestMapping(value = "/orgType", method = RequestMethod.GET)
    public JSONResult getOrgType() {
        return new JSONResult<List>(organizationService.selectOrgType());
    }


    /**
     * 新增组织机构
     */
    //@PreAuthorize("hasPermission('ORG', 'ADD')")
    @RequestMapping(value = "/org", method = RequestMethod.POST)
    public JSONResult addOrg(@AuthenticationPrincipal AuthUser authUser,
                             @Validated AddOrg addOrg) {
        return new JSONResult<>(organizationService.addOrg(authUser,addOrg.toOrganization()));
    }

    /**
     * 修改组织机构
     */
    //@PreAuthorize("hasPermission('ORG', 'UPDATE')")
    @RequestMapping(value = "/org", method = RequestMethod.PUT)
    public JSONResult updateOrg(@AuthenticationPrincipal AuthUser authUser,
                                @Validated Organization organization) {
        return new JSONResult(organizationService.updateOrg(authUser,organization));
    }

    /**
     * 删除组织机构
     */
    //@PreAuthorize("hasPermission('ORG', 'DELETE')")
    @RequestMapping(value = "/org", method = RequestMethod.DELETE)
    public JSONResult deleteOrg(@Validated Organization organization) {
        return new JSONResult<>(organizationService.deleteOrg(organization.getOrganizationId()));
    }

    /**
     * 获取用户
     */
    //@PreAuthorize("hasPermission('USER', 'LIST')")
    @RequestMapping(value = "/userOrg",method = RequestMethod.GET)
    public JSONResult selectUser(@Validated Organization organization,
                                 @Validated PaginationDTO paginationDTO){
        Page<User> users = organizationService.findUser(paginationDTO, organization.getOrganizationId());
        PageInfo pageInfo = new PageInfo(users);
        return  new JSONResult(pageInfo);
    }

    /**
     * 公司和部名字判重
     * @param organization
     * @return
     */
    public JSONResult existOrgName(@Validated Organization organization){
        return new JSONResult(organizationService.existOrgName(organization));
    }
}
