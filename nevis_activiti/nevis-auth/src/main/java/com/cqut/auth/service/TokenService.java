package com.cqut.auth.service;

public interface TokenService {

    String generateToken(String subject, String claimKey, String claimValue);
}
