package com.cqut.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void test() {
        userDetailsService.loadUserByUsername("admin");
    }
}
