package com.cqut.mapper;

import com.cqut.entity.entity.DataScopeRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataScopeRoleMapper {
    int insert(DataScopeRole record);

    List<DataScopeRole> selectAll();

    List<String> findRoleDataScopes(@Param("roleId") String roleId);
}