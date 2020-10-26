package com.adoujia.school.config;

import com.adoujia.school.res.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获.
 *
 * @author fangcheng
 */

@Slf4j
@ControllerAdvice
@ConditionalOnWebApplication
public class GlobalErrorHandler {

    public static final String DEFAULT_PARAM_ERROR_MSG = "参数不正确";

    private String getMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * 运行时异常，一般由于数据不一致或者用于集合stream
     */
    @ResponseBody
    @ExceptionHandler(InnerRuntimeException.class)
    public ResultVO<Void> handleActivityRuntimeException(InnerRuntimeException e) {
        log.error("", e);
        return ResultVO.error(e.getErrorCode(), getMessage(e));
    }

    /**
     * 捕捉API参数传入异常
     *
     * @param e 方法参数异常
     * @return 返回给客户端500
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<Void> handleBindException(MethodArgumentNotValidException e) {

        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            return ResultVO.error(500, fieldError.getDefaultMessage());
        } else {
            return ResultVO.error(500, DEFAULT_PARAM_ERROR_MSG);
        }
    }

    /**
     * 未捕获的内部异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVO<Void> handleSeriousException(Exception e) throws Exception {
        if (e instanceof AccessDeniedException) {
            log.error("访问被拒绝");
            throw e;
        }
        log.error("应用未捕获的异常", e);
        // security
        String errMsg = getMessage(e);
        return ResultVO.error(500, errMsg);
    }

}
