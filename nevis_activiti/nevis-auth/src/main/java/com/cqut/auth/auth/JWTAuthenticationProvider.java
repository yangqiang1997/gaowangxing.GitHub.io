package com.cqut.auth.auth;

import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.entity.JWTAuthenticationToken;
import com.cqut.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return authentication;
        }

        String token = authentication.getCredentials().toString();
        AuthUser authUser = authenticationService.findUserByToken(token);
        if (authUser != null) {
            Authentication auth = new JWTAuthenticationToken(authUser);
            auth.setAuthenticated(true);
            return auth;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
