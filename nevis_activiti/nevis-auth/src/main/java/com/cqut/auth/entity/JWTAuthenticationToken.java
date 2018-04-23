package com.cqut.auth.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private AuthUser authUser;

    public JWTAuthenticationToken(AuthUser authUser) {
        super(authUser.getAuthorities());
        this.authUser = authUser;
    }

    @Override
    public Object getCredentials() {
        return authUser.getToken();
    }

    @Override
    public Object getPrincipal() {
        return authUser;
    }
}
