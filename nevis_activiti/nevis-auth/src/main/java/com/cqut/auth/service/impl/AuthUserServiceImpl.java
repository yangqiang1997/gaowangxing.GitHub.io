package com.cqut.auth.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.exception.UserNotFoundException;
import com.cqut.auth.service.AuthUserService;
import com.cqut.entity.entity.Role;
import com.cqut.mapper.OrganizationUserMapper;
import com.cqut.mapper.RoleMapper;
import com.cqut.mapper.UserMapper;
import com.cqut.util.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private OrganizationUserMapper organizationUserMapper;

    @Override
    public AuthUser loadAuthUserByUserName(String userName) {
        AuthUser authUser = userMapper.loadUserByUsername(userName, SystemConstants.FALSE_FLAG);
        if (authUser == null) {
            throw new UserNotFoundException();
        }

        List<Role> roles = authUser.getRoleIds() == null ? null : roleMapper.selectUserRoles(authUser.getRoleIds());
        List<String> organizations = organizationUserMapper.selectUserOrganizations(authUser.getUserId());

        authUser.setRoles(roles);
        authUser.setOrganizations(organizations);

        return authUser;
    }
}
