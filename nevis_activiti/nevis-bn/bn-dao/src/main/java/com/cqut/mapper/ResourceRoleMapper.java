package com.cqut.mapper;

import com.cqut.entity.entity.ResourceRole;
import java.util.List;

public interface ResourceRoleMapper {
    int insert(ResourceRole record);

    List<ResourceRole> selectAll();
}