package com.cqut.controller;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.resource.DeleteResourceDTO;
import com.cqut.entity.dto.resource.FindResourceDTO;
import com.cqut.entity.dto.resource.ResourceDTO;
import com.cqut.entity.dto.resource.SearchResourceDTO;
import com.cqut.entity.global.JSONResult;
import com.cqut.service.ResourceService;
import com.cqut.util.exception.NonDataScopeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static com.cqut.util.string.StringUtil.isNullOrEmpty;

/**
 * 资源管理(菜单管理）
 * 作者：黎鹏
 * 日期：2018-04-05
 * */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 新增模块
     * 作者：黎鹏
     * 日期：2018-04-06
     */
    //@PreAuthorize("hasPermission('RESOURCE', 'ADD')")
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public JSONResult addSysResource(@Validated ResourceDTO resourceDTO) {

        return new JSONResult(resourceService.addResource(resourceDTO));
    }

    /**
     * 更新模块
     * 作者：黎鹏
     * 日期：2018-04-0
     */
    //@PreAuthorize("hasPermission('RESOURCE', 'UPDATE')")
    @RequestMapping(value = "/resource", method = RequestMethod.PUT)
    public JSONResult updateSysResource(@Validated ResourceDTO resourceDTO) {
        boolean firstCondition = resourceDTO.getParentResourceId() == null && resourceDTO.getResourceId().length() == 8;
        boolean secondCondition = resourceDTO.getParentResourceId() != null && resourceService.equalsStr(resourceDTO.getParentResourceId(),resourceDTO.getResourceId());
        if(firstCondition || secondCondition) {
            return new JSONResult(resourceService.updateSysResource(resourceDTO));
        } else {
            return new JSONResult(resourceService.updateSysResourceAll(resourceDTO));
        }
    }

    /**
     * 删除模块（根据id删除该ID以及所有孩子）
     * 作者：黎鹏
     * 日期：2018-04-06
     */
    //@PreAuthorize("hasPermission('RESOURCE', 'DELETE')")
    @RequestMapping(value = "/resource", method = RequestMethod.DELETE)
    public JSONResult deleteSysResource(@Validated DeleteResourceDTO deleteResourceDTO) {

        return new JSONResult(resourceService.deleteByIdsAndChild(deleteResourceDTO.getResourceId()));
    }

    /**
     * 根据id获取资源
     * 作者：黎鹏
     * 日期：2018-04-05
     */
    //@PreAuthorize("hasPermission('RESOURCE', 'DETAIL')")
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public JSONResult findSysResource(@Validated FindResourceDTO findResourceDTO) {

        return new JSONResult(resourceService.findResource(findResourceDTO.getResourceId()));
    }

    /**
     * 获取资源列表(以及查询）
     * 作者：黎鹏
     * 日期：2018-04-05
     */
    //@PreAuthorize("hasPermission('RESOURCE', 'LIST')")
    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public JSONResult searchSysResource(@AuthenticationPrincipal AuthUser authUser, @Validated SearchResourceDTO searchResourceDTO) throws NonDataScopeException {
        if(isNullOrEmpty(searchResourceDTO.getResourceName())) {
            return new JSONResult(resourceService.searchResource(authUser, searchResourceDTO));
        } else {
            return new JSONResult(resourceService.searchResourceByName(authUser, searchResourceDTO));
        }
    }

    /**
     * 获取菜单
     * 作者：谭勇
     * 日期：2018-04-11
     * */
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public JSONResult findMenus(@AuthenticationPrincipal AuthUser authUser) {

        return new JSONResult(resourceService.findMenus(authUser.getUserId()));
    }
}