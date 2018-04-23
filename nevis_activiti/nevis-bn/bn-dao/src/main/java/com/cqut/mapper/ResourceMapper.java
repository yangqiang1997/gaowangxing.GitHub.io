package com.cqut.mapper;

import com.cqut.entity.dto.resource.ResourceDTO;
import com.cqut.entity.dto.role.ResultRoleDTO;
import com.cqut.entity.entity.Resource;
import com.cqut.entity.entity.menu.SubMenu;
import com.cqut.entity.entity.menu.TreeMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourceMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(Resource resource);

    /**
     * 新增资源
     * @param resourceDTO
     * @return
     */
    boolean insertSelective(ResourceDTO resourceDTO);

    /**
     * 根据id查询资源详细信息
     * @param resourceId
     * @return
     */
    ResourceDTO selectByPrimaryKey(@Param("resourceId") String resourceId);

    /**
     * 更新资源信息
     * @param resourceDTO
     * @return
     */
    boolean updateByPrimaryKey(ResourceDTO resourceDTO);

    /**
     * 找到菜单
     * @param userId 用户id
     * @return
     */
    List<SubMenu> findMenus(@Param("userId") String userId);

    /**
     * 找到资源树形菜单
     * @param resourceName 用户id
     * @return
     */
    List<TreeMenu> findResourceMenus(@Param("resourceName") String resourceName,
                                     @Param("dataScope") String dataScope);

    /**
     * 根据父亲id找到最大的id
     * @param parentResourceId 父亲资源id
     * @return
     */
    Map<String, Object> findMaxIdByParentId(@Param("parentResourceId") String parentResourceId);

    /**
     * 根据父亲id删除他和他的孩子
     * @param resourceId 资源id
     * @return
     */
    boolean deleteByParent(@Param("resourceId") String resourceId);

    /**
     * 删除资源角色关系
     * @param resourceId 资源id
     * @return
     */
    boolean deleteRelationResourceRole(@Param("resourceId") String resourceId);

    /**
     * 根据资源名称搜索
     * @param resourceName 资源名称
     * @return
     */
    List<ResourceDTO> searchResource(@Param("resourceName") String resourceName,
                                     @Param("dataScope") String dataScope);

    /**
     * 找到父节点下所有孩子
     * @param resourceId 资源id
     * @return
     */
    List<ResourceDTO> findChild(@Param("resourceId") String resourceId);

    /**
     * 找到子节点的父节点
     * @param parentResourceId 资源id
     * @return
     */
    List<ResourceDTO> findParent(@Param("parentResourceId") String parentResourceId);

    /**
     * 找到一级菜单根据名称
     * @param resourceName 资源id
     * @return
     */
    List<ResourceDTO> firstLevelMenu(@Param("resourceName") String resourceName);

    /**
     * 找到资源对应角色id
     * @param resourceId 资源id
     * @return
     */
    ResultRoleDTO selectRoleId(@Param("resourceId") String resourceId);

    /**
     * 找到资源对应角色id
     * @param resourceId 资源id
     * @return
     */
    boolean setSource(@Param("roleId") String roleId, @Param("resourceId") String resourceId);
}