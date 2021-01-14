package com.smart.library.exception;

/**
 * @Author zrh
 * @Created 4/22/19
 * @Editor zrh
 * @Edited 4/22/19
 * @Type
 * @Layout
 * @Api
 */
public class ExceptionCode {
    // 本地异常
    public static final String INTERNAL_ERROR = "E1000";
    // json解析异常
    public static final String JSON_PARSE_ERROR = "E1001";
    // 无网络异常
    public static final String NO_NERWORK_ERROR = "E1002";
    // 无数据
    public static final String NO_DATA_ERROR = "E1003";
    // 网络超时
    public static final String TIMEOUT_ERROR = "E1004";
    //为入职或者没有权限
    public static  final  String NO_PERMITION_ERROR = "B10026";

    // token失效需重新登录
    public static final String INVALID_TOKEN_ERROR = "4010011002";
    // 无权限访问
    public static final String NO_ACCESS_PERMISSION = "4030011003";
    // 非法访问
    public static final String INVALID_ACCESS1 = "4000011003";
    public static final String INVALID_ACCESS2 = "4000011005";
    public static final String INVALID_ACCESS3 = "4002071000";

    /**
     * 是否为合法的token
     *
     * @param code
     * @return
     */
    public static boolean isInvalidToken(String code) {

        return INVALID_TOKEN_ERROR.equals(code);
    }
}
