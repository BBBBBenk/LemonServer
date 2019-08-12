package com.mimumi.lemonserver.filter;

import com.alibaba.fastjson.JSON;
import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.dto.JWTToken;
import com.mimumi.lemonserver.enums.Constants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2018/9/6.
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(Constants.USER_TOKEN_KEY);
/*        boolean isLogin=true;
        if(StringUtils.isBlank(authorization) || StringUtils.isEmpty(authorization) || authorization==null){
            isLogin=false;
        }*/
        return authorization != null ;
    }

    /**
     *验证token
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(Constants.USER_TOKEN_KEY);

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


   @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
       boolean loggedIn = false;
       String errorMessage="";
       try {
           if(this.isLoginAttempt(request, response)) {
               loggedIn = this.executeLogin(request, response);
           }
       }catch ( AuthenticationException ex){
           errorMessage=ex.getMessage();
       }
       // return super.isAccessAllowed(request,response,mappedValue);
       return loggedIn;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ResponseResult result=new ResponseResult();
        result.setStatus(Constants.NOPERM);
        result.setMessage("Token失效或已过期");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter=httpServletResponse.getWriter();
        printWriter.write(JSON.toJSONString(result));
        printWriter.flush();
        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp,String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            ResponseResult result=new ResponseResult();
            result.setStatus(Constants.NOPERM);
            result.setMessage(message);

            httpServletResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter=httpServletResponse.getWriter();
            printWriter.write(JSON.toJSONString(result));
            printWriter.flush();
            // httpServletResponse.sendError(401,message);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
