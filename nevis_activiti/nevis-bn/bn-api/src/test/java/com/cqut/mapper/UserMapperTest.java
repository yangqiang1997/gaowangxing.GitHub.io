package com.cqut.mapper;

import com.cqut.auth.entity.AuthUser;
import com.cqut.util.constants.SystemConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        AuthUser authUser = userMapper.loadUserByUsername("admin", SystemConstants.FALSE_FLAG);
        System.out.println(authUser.toString());
    }
}
