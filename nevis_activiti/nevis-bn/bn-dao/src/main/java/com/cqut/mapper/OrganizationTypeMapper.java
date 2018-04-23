package com.cqut.mapper;

import com.cqut.entity.entity.OrganizationType;
import java.util.List;

public interface OrganizationTypeMapper {
    int deleteByPrimaryKey(String organizationTypeId);

    int insert(OrganizationType record);

    OrganizationType selectByPrimaryKey(String organizationTypeId);

    List<OrganizationType> selectAll();

    int updateByPrimaryKey(OrganizationType record);
}