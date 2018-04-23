package com.cqut.entity.dto.organization;


/**
 * userManagementDTO 存入用户组织机构信息
 * 设计者:   罗杏函
 * 更新日期: 2018-04-10
 */
public class AddUserOrgDTO{

    /**
     * 角色名
     */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        if (orgId == ""){
            this.orgId = null;
        }else {
            this.orgId = orgId;
        }
    }
}
