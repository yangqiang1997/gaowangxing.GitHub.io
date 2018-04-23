package com.cqut.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.resource.ListMenu;
import com.cqut.entity.dto.resource.ResourceDTO;
import com.cqut.entity.dto.resource.SearchResourceDTO;
import com.cqut.entity.entity.menu.SubMenu;
import com.cqut.entity.entity.menu.TreeMenu;
import com.cqut.mapper.ResourceMapper;
import com.cqut.service.DataScopeService;
import com.cqut.service.ResourceService;
import com.cqut.util.constants.BeanUtil;
import com.cqut.util.exception.NonDataScopeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private DataScopeService dataScopeService;


    /**数字1*/
    public static final int NUMBER_ONE = 1;

    /**初始下标*/
    public static final int FIRST_INDEX = 0;

    /**一级菜单的长度*/
    public static final int FIRST_LEVEL_ID_LENGTH = 8;

    /**二级菜单的长度*/
    public static final int SECOND_LEVEL_ID_LENGTH = 16;

    /**三级菜单的长度*/
    public static final int THIRD_LEVEL_ID_LENGTH = 24;

    /**
     * 根据id获取资源(菜单)
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param id 资源id
     */
    @Override
    public ResourceDTO findResource(String id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取资源列表(查询)
     * 作者：黎鹏
     * 日期：2018-04-12
     * @param searchResourceDTO 资源信息
     */
    public List<TreeMenu> searchResource(AuthUser authUser, SearchResourceDTO searchResourceDTO) throws NonDataScopeException {
        String dataScope = dataScopeService.dataScopeFilterByCreateId(authUser, "sys_user");
        return resourceMapper.findResourceMenus(searchResourceDTO.getResourceName(), dataScope);

    }

    /**
     * 获取资源列表(根据资源名称查询)
     * 作者：黎鹏
     * 日期：2018-04-16
     * @param searchResourceDTO 资源信息
     */
    public List<ListMenu> searchResourceByName(AuthUser authUser, SearchResourceDTO searchResourceDTO) throws NonDataScopeException{
        String dataScope = dataScopeService.dataScopeFilterByCreateId(authUser, "sys_user");
        List<ResourceDTO> allList = new ArrayList<>();
        List<ResourceDTO> secondLevelList = resourceMapper.searchResource(searchResourceDTO.getResourceName(), dataScope);
        for (int i = 0;i < secondLevelList.size();i++) {
            List<ResourceDTO> childList  = resourceMapper.findChild(secondLevelList.get(i).getResourceId());
            List<ResourceDTO> parentList = resourceMapper.findParent(secondLevelList.get(i).getParentResourceId());

            allList.addAll(secondLevelList);
            allList.addAll(childList);
            allList.addAll(parentList);
        }

        List<ResourceDTO> firstLevelList = resourceMapper.firstLevelMenu(searchResourceDTO.getResourceName());

        for (int i = 0;i < firstLevelList.size();i++) {
            List<ResourceDTO>  childListAll = resourceMapper.findChild(firstLevelList.get(i).getResourceId());
            allList.addAll(firstLevelList);
            allList.addAll(childListAll);
        }

        return treeResource(removeDuplicate(allList));
    }

    /**
     * 去除重复记录
     * 作者：黎鹏
     * 日期：2018-04-16
     * @param list
     */
    public List<ResourceDTO> removeDuplicate(List<ResourceDTO> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if ((list.get(j).getResourceId()).equals(list.get(i).getResourceId())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 获取资源树形结构
     * 作者：黎鹏
     * 日期：2018-04-16
     * @param list
     */
    private List<ListMenu> treeResource(List<ResourceDTO> list){
        List<ListMenu> result = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            ResourceDTO resourceDTO =  list.get(i);
            if(resourceDTO.getParentResourceId() == null){
                ListMenu listMenu = new ListMenu(resourceDTO);
                listMenu.setChildren(putChildren(listMenu,list));
                result.add(listMenu);
            }
        }
        return result;
    }

    /**
     * 树形结构孩子结点
     * 作者：黎鹏
     * 日期：2018-04-16
     * @param list
     */
    private List<ListMenu> putChildren(ListMenu result,List<ResourceDTO> list){
        List<ListMenu> children = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ResourceDTO resourceDTO =  list.get(i);

            if(resourceDTO.getParentResourceId()!= null &&
                    resourceDTO.getParentResourceId().equals(result.getResourceId())){
                ListMenu listMenu = new ListMenu(resourceDTO);
                listMenu.setChildren(putChildren(listMenu,list));
                children.add(listMenu);
            }
        }
        return children;
    }

    /**
     * 获取资源新增树形结构
     * 作者：黎鹏
     * 日期：2018-04-22
     * @param list
     */
    private List<ListMenu> treeResourceAdd(List<ResourceDTO> list, String newParentId, String oldId){
        List<ListMenu> result = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            ResourceDTO resourceDTO =  list.get(i);
            if(resourceDTO.getParentResourceId().equals(oldId)){
                treeAdd(resourceDTO, list, newParentId, result);
                //resourceMapper.setSource((resourceMapper.selectRoleId(oldId)).getRoleId(), resourceDTO.getResourceId());
            }
        }
        return result;
    }

    /**
     * 资源新增树形结构孩子结点
     * 作者：黎鹏
     * 日期：2018-04-22
     * @param list
     */
    private List<ListMenu> putChildrenAdd(ListMenu result,List<ResourceDTO> list, String newParentId){
        List<ListMenu> children = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ResourceDTO resourceDTO =  list.get(i);

            if(resourceDTO.getParentResourceId()!= null &&
                    resourceDTO.getParentResourceId().equals(result.getResourceId())){
                treeAdd(resourceDTO, list, newParentId, children);
                //resourceMapper.setSource(resourceMapper.selectRoleId(result.getResourceId()).getRoleId(), resourceDTO.getResourceId());
            }
        }
        return children;
    }

    /**
     * 树形结构资源新增
     * 作者：黎鹏
     * 日期：2018-04-22
     * @param list
     */
    private List<ListMenu> treeAdd(ResourceDTO resourceDTO,List<ResourceDTO> list, String newParentId, List<ListMenu> children){
        resourceDTO.setParentResourceId(newParentId);
        addResource(resourceDTO);
        ListMenu listMenu = new ListMenu(resourceDTO);
        listMenu.setChildren(putChildrenAdd(listMenu, list, resourceDTO.getResourceId()));
        children.add(listMenu);

        return children;
    }

    /**
     * 新增资源(菜单)
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceDTO 资源信息
     */
    @Override
    public boolean addResource(ResourceDTO resourceDTO) {
        Map<String, Object> maxCode = resourceMapper.findMaxIdByParentId(resourceDTO.getParentResourceId());

        if(maxCode != null && !maxCode.isEmpty()) {
            resourceDTO.setResourceId(BeanUtil.getTreeCode(maxCode.get("maxId").toString()));
        } else {
            resourceDTO.setResourceId(BeanUtil.getTreeCodeForInit(resourceDTO.getParentResourceId()));
        }

        //生成层级
        String level = replace(resourceDTO.getResourceId());
        resourceDTO.setLevel0(level);

        return resourceMapper.insertSelective(resourceDTO);
    }

    /**
     * 删除模块
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceId 资源id
     * @return
     */
    @Override
    public boolean deleteByIdsAndChild(String resourceId) {
        resourceMapper.deleteRelationResourceRole(resourceId);

        return resourceMapper.deleteByParent(resourceId);
    }

    /**
     * 更新模块
     * 作者：黎鹏
     * 日期：2018-04-06
     * @param resourceDTO 资源信息
     * @return
     */
    @Override
    public boolean updateSysResource(ResourceDTO resourceDTO) {

        return resourceMapper.updateByPrimaryKey(resourceDTO);
    }

    /**
     * 更换父级后更新模块
     * 作者：黎鹏
     * 日期：2018-04-22
     * @param resourceDTO 资源信息
     * @return
     */
    @Override
    public boolean updateSysResourceAll(ResourceDTO resourceDTO) {
        String oldId = resourceDTO.getResourceId();

        addResource(resourceDTO);
        //resourceMapper.setSource(resourceMapper.selectRoleId(oldId).getRoleId(), resourceDTO.getResourceId());
        List<ResourceDTO> childList  = resourceMapper.findChild(oldId);
        deleteByIdsAndChild(oldId);

        return treeResourceAdd(childList, resourceDTO.getResourceId(), oldId).size() > 0;
    }

    /**
     * 根据parentResourceId生成level
     * 作者：黎鹏
     * 日期：2018-04-07
     * @param str 字符串
     */
    public String replace(String str) {
        if(str.length() == FIRST_LEVEL_ID_LENGTH) {
            return (Integer.parseInt(str) - NUMBER_ONE) + "";
        } else if(str.length() == SECOND_LEVEL_ID_LENGTH) {
            return (Integer.parseInt(str.substring(FIRST_INDEX,FIRST_LEVEL_ID_LENGTH)) - NUMBER_ONE) + "-" +
                    (Integer.parseInt(str.substring(FIRST_LEVEL_ID_LENGTH,SECOND_LEVEL_ID_LENGTH)) - NUMBER_ONE) ;
        } else {
            return (Integer.parseInt(str.substring(FIRST_INDEX,FIRST_LEVEL_ID_LENGTH)) - NUMBER_ONE)+ "-" + (Integer.
                    parseInt(str.substring(FIRST_LEVEL_ID_LENGTH,SECOND_LEVEL_ID_LENGTH)) - NUMBER_ONE) + "-" +
                    (Integer.parseInt(str.substring(SECOND_LEVEL_ID_LENGTH,THIRD_LEVEL_ID_LENGTH)) - NUMBER_ONE) ;
        }
    }

    /**
     * 字符串比较
     * 作者：黎鹏
     * 日期：2018-04-22
     */
    @Override
    public boolean equalsStr(String strA,String strB) {
        if(strA.length() > strB.length()) {
            return strB.equals(strA.substring(0, strB.length()));
        } else {
            return strA.equals(strB.substring(0, strA.length()));
        }
    }


    /**
     * 找到菜单
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SubMenu> findMenus(String userId) {
        return resourceMapper.findMenus(userId);
    }
}
