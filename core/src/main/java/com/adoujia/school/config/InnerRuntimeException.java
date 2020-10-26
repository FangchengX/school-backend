package com.adoujia.school.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目定义的运行时异常.
 *
 * @author fangcheng
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class InnerRuntimeException extends RuntimeException {

    public InnerRuntimeException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public InnerRuntimeException(String msg) {
        super(msg);
    }

    private int errorCode = 500;

    /**
     * 构造函数
     *
     * @param message       异常信息
     * @param errorCodeEnum 异常码，是个枚举类型
     */
    public InnerRuntimeException(String message, MyErrorCodeEnum errorCodeEnum) {
        super(message);
        int code = errorCodeEnum.getValue();
        //防错
        if (code == 0) {
            code = 500;
        }
        this.errorCode = code;
    }


    @Override
    public String toString() {
        return this.errorCode + " : " + this.getMessage();
    }
}
