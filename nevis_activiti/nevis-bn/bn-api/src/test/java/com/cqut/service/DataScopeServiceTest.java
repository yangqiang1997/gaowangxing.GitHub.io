package com.cqut.service;

import com.cqut.auth.entity.AuthUser;
import com.cqut.util.exception.NonDataScopeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataScopeServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataScopeService dataScopeService;

    @Test
    public void test() {
        UserDetails authUser = userDetailsService.loadUserByUsername("test");
        try {
            System.out.println(dataScopeService.dataScopeFilterByCreateId((AuthUser) authUser) + "test");
        } catch (NonDataScopeException e) {
            e.printStackTrace();
        }
    }
}
