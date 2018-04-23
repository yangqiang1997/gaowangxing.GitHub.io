package com.cqut.auth.service.impl;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.cqut.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    @Autowired
    private JWTCreator.Builder builder;

    @Autowired
    private Algorithm algorithm;

    @Override
    public String generateToken(String subject, String claimKey, String claimValue) {
        return builder.withSubject(subject)
                .withExpiresAt(getExpireDate())
                .withClaim(claimKey, claimValue)
                .sign(algorithm);
    }

    private Date getExpireDate() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTimeInMillis() + expirationTime * 60 * 1000);
    }
}
