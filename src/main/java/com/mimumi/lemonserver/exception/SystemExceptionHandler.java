package com.mimumi.lemonserver.exception;


import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.enums.Constants;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类描述：错误捕获异常处理类，当出现报错的时候将错误数据用json的形式返回给页面。
 */
@ControllerAdvice
public class SystemExceptionHandler {

    private  final Logger logger= LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ExceptionHandler(value = ShiroException.class)
    public ResponseResult handleGlobalException(HttpServletRequest request, HttpServletResponse response, ShiroException e){
        ResponseResult result=new ResponseResult();
        result.setStatus(Constants.FAIL);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseResult handleBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException e){
        ResponseResult result=new ResponseResult();
        result.setStatus(Constants.FAIL);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult handleGlobalException(HttpServletRequest request, HttpServletResponse response, Exception e){
        logger.error(e.getMessage());
        ResponseResult result=new ResponseResult();
        result.setStatus(Constants.FAIL);
        result.setMessage(e.getMessage());
        return result;
    }
}
