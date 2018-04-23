package com.cqut.entity.dto.role;


import com.cqut.entity.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * RoleManagementController 获取角色信息DTO
 * 设计者:   杨强
 * 更新日期: 2018/4/5
 */
public class AddRoleDTO{

    /**
     * 角色名
     */
    @NotNull(message = "角色名不能为空")
    private String roleName;

    /**
     * 是否允许编辑
     */
    @NotNull(message = "是否允许编辑不能为空")
    private Integer isAllowEdit;

    /**
     * 是否允许删除
     */
    @NotNull(message = "是否允许删除不能为空")
    private Integer isAllowDelete;

    /**
     * 角色类型
     */
    @NotNull(message = "角色类型不能为空")
    private String roleType;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsAllowEdit() {
        return isAllowEdit;
    }

    public void setIsAllowEdit(Integer isAllowEdit) {
        this.isAllowEdit = isAllowEdit;
    }

    public Integer getIsAllowDelete() {
        return isAllowDelete;
    }

    public void setIsAllowDelete(Integer isAllowDelete) {
        this.isAllowDelete = isAllowDelete;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Role getRole(){

        Role role = new Role();
        role.setRoleId(UUID.randomUUID().toString());
        role.setIsAllowDelete((isAllowDelete == 0)?false:true);
        role.setIsAllowEdit((isAllowEdit == 0)?false:true);
        role.setRoleName(roleName);
        role.setRoleType(roleType);

        return role;
    }
}
