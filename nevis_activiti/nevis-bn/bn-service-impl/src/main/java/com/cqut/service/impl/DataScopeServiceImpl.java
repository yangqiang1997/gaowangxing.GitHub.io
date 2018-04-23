package com.cqut.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.entity.Role;
import com.cqut.entity.global.DataScope;
import com.cqut.mapper.DataScopeRoleMapper;
import com.cqut.service.DataScopeService;
import com.cqut.util.code.TreeCode;
import com.cqut.util.constants.SystemConstants;
import com.cqut.util.exception.NonDataScopeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据范围筛选
 * 作者：谭勇
 * 时间：2018-04-05
 * 如需扩展，自行扩展，需加上扩展人信息
 * */
@Service
public class DataScopeServiceImpl implements DataScopeService {

    private String getLikeStatements(String value) {
        return " LIKE '" + value + "%'";
    }

    private String getEqualStatements(String value) {
        return " = '" + value + "'";
    }

    private static String ORGANIZATION_FILTER = " OR organization_id ";

    private void appendLikeTypeSql(List<String> codes, StringBuilder sqlFilter) {
        for (String code : codes) {
            sqlFilter.append(ORGANIZATION_FILTER + getLikeStatements(code));
        }
    }

    private void appendEqualTypeSql(List<String> codes, StringBuilder sqlFilter) {
        for (String code : codes) {
            sqlFilter.append(ORGANIZATION_FILTER + getEqualStatements(code));
        }
    }

    private String getCompanyOrganizationCode(String code) {

        /*
        * 公司是顶级结构，所以只需要考虑前几位code
        * */
        return code.substring(0, TreeCode.INIT_LENGTH);
    }

    private List<String> getCompanyOrganizationCode(List<String> codes) {
        List<String> companyCodes = new ArrayList<>(codes.size());
        for (String code : codes) {
            companyCodes.add(getCompanyOrganizationCode(code));
        }

        return companyCodes;
    }

    private String createIdSqlPrefix = ".create_id IN ( SELECT user_id FROM sys_organization_user WHERE ";
    private String createIdSqlSuffix = ")";

    @Value("${admin.account}")
    private String adminAccount;

    @Autowired
    private DataScopeRoleMapper dataScopeRoleMapper;

    /**
     * 根据创建人来筛选
     * 作者：谭勇
     * 时间：2018-04-06
     * */
    @Override
    public String dataScopeFilterByCreateId(AuthUser authUser) throws NonDataScopeException {
        throw new NonDataScopeException("该方法废弃");
    }

    /**
     * @param authUser 当前登录人信息
     * @param alias    要查询的表的别名
     */
    @Override
    public String dataScopeFilterByCreateId(AuthUser authUser, String alias) throws NonDataScopeException {
        if (authUser.getAccount().equals(adminAccount)) {
            return null;
        }

        StringBuilder orgFilterSql = new StringBuilder();
        StringBuilder createIdFilterSql = new StringBuilder();

        for (Role role : authUser.getRoles()) {
            DataScope dataScope = Enum.valueOf(DataScope.class, role.getDataScope());
            List<String> companyCodes = getCompanyOrganizationCode(authUser.getOrganizations());

            switch (dataScope) {
                case ALL: {
                    return null;
                }
                case COMPANY_AND_CHILD: {
                    appendLikeTypeSql(companyCodes, orgFilterSql);
                } break;
                case COMPANY: {
                    appendEqualTypeSql(companyCodes, orgFilterSql);
                } break;
                case DEPARTMENT_AND_CHILD: {
                    appendLikeTypeSql(authUser.getOrganizations(), orgFilterSql);
                } break;
                case DEPARTMENT: {
                    appendEqualTypeSql(authUser.getOrganizations(), orgFilterSql);
                } break;
                case SELF: {
                    createIdFilterSql.append(" OR " + alias + ".create_id " + getEqualStatements(authUser.getUserId()));
                } break;
                case CUSTOM: {
                    appendEqualTypeSql(dataScopeRoleMapper.findRoleDataScopes(role.getRoleId()), orgFilterSql);
                } break;
                default: {

                } break;
            }
        }

        String result = SystemConstants.EMPTY_STR;
        if (orgFilterSql.length() != 0) {
            // 去掉第一个OR
            orgFilterSql.delete(0, 4);
            result += alias + createIdSqlPrefix + orgFilterSql.toString() + createIdSqlSuffix;
        }

        if (createIdFilterSql.length() != 0) {
            if (orgFilterSql.length() == 0) {
                createIdFilterSql.delete(0, 4);
            }
            result += createIdFilterSql.toString();
        }

        if (result.length() == 0) {
            throw new NonDataScopeException("暂无角色");
        }

        return result;
    }
}
