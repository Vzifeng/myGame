package com.example.game.controller;

import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:36 2019/2/19
 * @Version ： $version$
 */
public class BaseController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handerException(HttpServletRequest request,Exception ex){
        Map<String,Object> errorMap = new HashMap <String, Object>();
        if (ex instanceof BusnessException){
            BusnessException busnessException = (BusnessException)ex;
            errorMap.put("errorCode",busnessException.getErrorCode());
            errorMap.put("errorMsg",busnessException.getErrorMsg());
        }else {
            errorMap.put("errorCode",BusinessErrorEnum.UNKNOWN.getErrorCode());
            errorMap.put("errorMsg",BusinessErrorEnum.UNKNOWN.getErrorMsg());
        }
        //test
        return CommonResponse.create(errorMap,"fail");
    }

}
