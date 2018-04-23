package com.cqut.util.string;

import com.cqut.util.constants.SystemConstants;

public class StringUtil {

    /**
     * 将空的字符串转成空字符串，不是空字符串就返回原来值
     * 作者：谭勇
     * 时间：2018-04-07
     * */
    public static String emptyStr2NullStr(String str) {
        if (isNullOrEmpty(str.trim())) {
            return null;
        }

        return str;
    }

    /**
     * 判断一个字符串是否为空
     * 作者：谭勇
     * 时间：2018-04-07
     * */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals(SystemConstants.EMPTY_STR);
    }
}
