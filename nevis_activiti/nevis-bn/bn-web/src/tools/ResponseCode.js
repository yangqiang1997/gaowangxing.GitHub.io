/**
 *请求响应值
 */
const ResponseCode = {
  SUCCESS : "200", //请求成功

  ERROR :"500", //请求失败

  NON_ACCOUNT : "1002", //账号不存在

  ERROR_PASSWORD : "1003", //密码错误

  LOGIN_USER_EXPIRED :"1004", //账号过期

  LOGIN_USER_LOCKED : "1005", //账号被冻结

  LOGIN_USER_DISABLED : "1006", //账号失效

  INVALID_TOKEN : "1001",  //权限验证失败

  DATA_VALIDATION_ERROR:"1007",

  TIME_OUT :408,

  TRUE : true,

  FALSE : false,
}

export {ResponseCode};
