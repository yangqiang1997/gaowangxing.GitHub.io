package com.cqut.auth.auth;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;

/**
 * 自定义hasPermission表达式的处理类
 * */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || targetDomainObject == null || !(permission instanceof String)) {
            return false;
        }

        String targetType = targetDomainObject instanceof String ?
                ((String) targetDomainObject).toUpperCase() :
                targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasAuthority(authentication, targetType, ((String) permission).toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || targetId == null || targetType == null || !(permission instanceof String)) {
            return false;
        }

        System.out.println(targetType);

        return true;
    }

    private boolean hasAuthority(Authentication authentication, String targetType, String authority) {

        return authentication.getAuthorities().contains(new SimpleGrantedAuthority(targetType + "_" + authority));
    }
}
