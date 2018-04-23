package com.cqut.mapper;

import com.cqut.entity.entity.CompanyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface CompanyInfoMapper {
    boolean deleteByPrimaryKey(@Param("companyInfoId") String companyInfoId);

    int insert(@Param("CompanyInfo") CompanyInfo record);

    CompanyInfo selectByPrimaryKey(@Param("companyInfoId") String companyInfoId);

    List<CompanyInfo> selectAll();

    boolean updateByPrimaryKey(@Param("CompanyInfo") CompanyInfo record);

    /**
     * orgID查询公司信息
     * @param orgId
     * @return
     */
    CompanyInfo selectByOrgId(@Param("orgId") String orgId);

    int deleteByOrgId(@Param("orgId") String orgId);
}