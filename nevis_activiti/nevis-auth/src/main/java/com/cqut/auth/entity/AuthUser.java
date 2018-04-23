package com.cqut.auth.entity;

import com.cqut.entity.entity.Role;
import com.cqut.util.constants.SystemConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.*;

/**
 * 权限验证
 * 作者：谭勇
 * 时间：2018-04-05
 * */
public class AuthUser implements Serializable {

    private String userId;

    private String account;

    private String password;

    private String userName;

    private List<String> roleIds;

    private List<Role> roles;

    private List<String> organizations;

    private boolean isExpired;

    private boolean isLock;

    private boolean isDisabled;

    private Set<GrantedAuthority> authorities;

    private String token;

    public String getUserId() {
        return userId;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = new ArrayList<>();
        if (roleIds != null) {
            for (String roleId : roleIds.split(SystemConstants.COMMA_REGEX)) {
                this.roleIds.add(roleId);
            }
        }
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setOrganizations(List<String> organizations) {
        this.organizations = organizations;
    }

    public List<String> getOrganizations() {
        return organizations;
    }

    public void setAuthorities(String authorities) {
        this.authorities = new HashSet<>();
        if (authorities != null) {
            for (String authority : authorities.split(SystemConstants.COMMA_REGEX)) {
                this.authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public boolean isLock() {
        return isLock;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
