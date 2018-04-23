package com.cqut.service;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.resource.ListMenu;
import com.cqut.entity.dto.resource.ResourceDTO;
import com.cqut.entity.dto.resource.SearchResourceDTO;
import com.cqut.entity.entity.menu.SubMenu;
import com.cqut.entity.entity.menu.TreeMenu;
import com.cqut.util.exception.NonDataScopeException;

import java.util.List;


public interface ResourceService {

    /**
     * 根据id获取资源(菜单)
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param id 资源id
     */
    ResourceDTO findResource(String id);

    /**
     * 新增资源(菜单)
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceDTO 资源信息
     */
    boolean addResource(ResourceDTO resourceDTO);

    /**
     * 删除模块
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceId 资源id
     * @return
     */
    boolean deleteByIdsAndChild(String resourceId);

    /**
     * 更新模块
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceDTO 资源信息
     * @return
     */
    boolean updateSysResource(ResourceDTO resourceDTO);

    /**
     * 获取资源列表(查询)
     * 作者：黎鹏
     * 日期：2018-04-05
     * @param searchResourceDTO 资源信息
     */
    List<TreeMenu> searchResource(AuthUser authUser, SearchResourceDTO searchResourceDTO) throws NonDataScopeException;

    /**
     * 找到菜单
     * @param userId 用户id
     * @return
     */
    List<SubMenu> findMenus(String userId);

    /**
     * 获取资源列表(根据资源名称查询)
     * 作者：黎鹏
     * 日期：2018-04-16
     * @param searchResourceDTO 资源信息
     */
    List<ListMenu> searchResourceByName(AuthUser authUser, SearchResourceDTO searchResourceDTO) throws NonDataScopeException;

    /**
     * 更新模块
     * 作者：黎鹏
     * 日期：2018-04-22
     * @param resourceDTO 资源信息
     * @return
     */
    boolean updateSysResourceAll(ResourceDTO resourceDTO);

    /**
     * 字符串比较
     * 作者：黎鹏
     * 日期：2018-04-22
     */
    boolean equalsStr(String strA,String strB);
}
