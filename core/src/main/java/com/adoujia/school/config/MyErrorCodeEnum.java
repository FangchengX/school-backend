package com.adoujia.school.config;

/**
 * 项目的异常码.
 *
 * @author fangcheng
 */

public enum MyErrorCodeEnum {
    /**
     * 接口未实现
     */
    NOT_IMPLEMENTED(30),
    /**
     * 不合理的参数
     */
    ILLEGAL_ARGUMENT(31),
    GAT(-1),
    /**
     * 无权限
     */
    NOT_ALLOWED(99),
    /**
     * IO异常
     */
    IO(40),
    /**
     * 数据不存在
     */
    NOT_EXIST(100),
    /**
     * excel解析出错
     */
    EXCEL_PARSE(230),
    /**
     * 企业微信API异常
     */
    WORK_WECHAT_API_ERROR(1000),
    /**
     * jackson数据解析异常
     */
    JACKSON_PARSE_ERROR(1001),
    /**
     * 特殊异常，未定义
     */
    END_ERROR(1);
    private final int value;


    MyErrorCodeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}