package com.cqut.service;

import com.cqut.auth.entity.AuthUser;
import com.cqut.util.exception.NonDataScopeException;

public interface DataScopeService {

    @Deprecated
    String dataScopeFilterByCreateId(AuthUser authUser) throws NonDataScopeException;

    /**
     * @param authUser 当前登录人信息
     * @param alias 要查询的表的别名
     * */
    String dataScopeFilterByCreateId(AuthUser authUser, String alias) throws NonDataScopeException;
}
