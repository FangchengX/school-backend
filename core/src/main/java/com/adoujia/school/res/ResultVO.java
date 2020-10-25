package com.adoujia.school.res;

import lombok.Data;

/**
 * 前端返回结果.
 *
 * @author fangcheng
 * @since 20/12/19 15:00
 */
@Data
public class ResultVO<T> {
    private T data;
    private Integer errCode;
    private String errMsg;

    public ResultVO() {
        this.errCode = 0;
    }

    /**
     * 返回给前端的数据结构.
     *
     * @param data 返回数据
     * @param <T>  泛型参数
     * @return 返回结果
     */
    public static <T> ResultVO<T> ok(T data) {
        ResultVO<T> r = new ResultVO<>();
        r.data = data;
        return r;
    }

    public static ResultVO<Void> ok() {
        return ok(null);
    }


    /**
     * 后台异常时返回给前端的数据.
     *
     * @param errCode 错误码
     * @param errMsg  错误信息
     * @return 返回结果
     */
    public static <T> ResultVO<T> error(Integer errCode, String errMsg) {
        ResultVO<T> r = new ResultVO<>();
        r.errCode = errCode;
        r.errMsg = errMsg;
        return r;
    }


    /**
     * 在仅返回errMsg和errCode信息不足的情况下，返回额外数据.
     *
     * @param errCode 错误码
     * @param errMsg  错误信息
     * @param <T>     泛型参数
     * @return 返回结果
     */
    public static <T> ResultVO<T> error(Integer errCode, String errMsg, T data) {
        ResultVO<T> r = new ResultVO<>();
        r.errCode = errCode;
        r.errMsg = errMsg;
        r.data = data;
        return r;
    }
}
