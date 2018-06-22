package com.hey.handler;

import com.hey.enums.CodeStatus;
import com.hey.result.BaseResult;
import com.hey.result.SingleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by heer on 2018/3/29.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    BaseResult handleException(Exception e){
        return exception(e);
    }

    public static BaseResult exception(Exception e){

        LOGGER.error(e.getMessage(), e);
        SingleResult response = new SingleResult();
        response.setMsg(CodeStatus.ERROR.getMsg());
        response.setCode(CodeStatus.ERROR.getCode());
        response.setData(e.getMessage()==null?"java.lang.NullPointerException":e.getMessage());
        return response;
    }

}
