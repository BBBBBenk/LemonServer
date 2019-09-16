package com.mimumi.lemonserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.utils.JWTUtil;
import com.mimumi.lemonserver.utils.RedisUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.net.www.http.HttpClient;

import javax.xml.ws.Response;
import java.net.URI;

/**
 * 微信接口
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController extends BaseController {

    @Autowired
    WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    WxMpService wxMpService;

    @Autowired
    WxMpConfigStorage wxOpenConfigStorage;

    @Autowired
    WxMpService wxOpenService;

    @Autowired
    RedisUtil redisUtil;


    @RequestMapping(value="/authorize",method = RequestMethod.POST)
    public ResponseResult authorize(String returnUrl) {
        ResponseResult result=new ResponseResult();
        //将参数带入
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(returnUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        result.setStatus(Constants.SUCCESS);
        result.setData(redirectURL);
        return result;
    }

    @RequestMapping(value="/qrlogin")
    public ResponseResult qrLogin(String redirectUrl){
        ResponseResult result=new ResponseResult();
        String url =wxOpenService.buildQrConnectUrl(redirectUrl, WxConsts.QrConnectScope.SNSAPI_LOGIN, null);
        result.setStatus(Constants.SUCCESS);
        result.setData(url);
        return result;
    }

   /* @RequestMapping(value="/getconfig")
    public ResponseResult getWxConfig(String redirectUrl){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData();
        return result;
    }*/

    @RequestMapping(value="/getconfig")
    public ResponseResult getConfigWechat(String shareUrl) throws WxErrorException {
        ResponseResult result = new ResponseResult();
        WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(shareUrl);
        result.setData(wxJsapiSignature);
        return result;
    }

    /**
     * 获取微信账号信息 redirect_URI是值使用第三方登录页面登录成功后的跳转地址
     * @return
     */
    @RequestMapping(value = "/getuserinfo")
    public ResponseResult getUserInfo(String code, String redirectUrl) throws Exception {
        ResponseResult result=new ResponseResult();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        User wxUser = userService.checkOpenidIsExists(openId);

        if(wxUser != null) {
            wxUser.setOpenid(openId);
            userService.update(wxUser);
            result.setData(JWTUtil.sign(wxUser.getMobile(), wxUser.getOpenid()));
            result.setStatus(Constants.SUCCESS);
        }else{
            WxMpUser wxMpUser = wxOpenService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            result.setData(wxMpUser);
            result.setStatus(Constants.FAIL);
        }
        return result;
    }

    /**
     * 获取微信账号信息 redirect_URI是值使用第三方登录页面登录成功后的跳转地址
     * @return
     */
    @RequestMapping(value = "/getsappuserinfo", method = RequestMethod.POST)
    public ResponseResult getUserSaInfo(String code) throws Exception {
        ResponseResult result=new ResponseResult();
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx5f780c4ed8150590&secret=1979c19b0427d1a67709870b51f2c5e6&js_code="+code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String openid = jsonObject.get("openid")+"";
        result.setData(openid);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/wxlogin")
    public ResponseResult wxLogin(String redirectUrl){
        ResponseResult result=new ResponseResult();
        String url =wxMpService.oauth2buildAuthorizationUrl(redirectUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        result.setStatus(Constants.SUCCESS);
        result.setData(url);
        return result;
    }

    /**
     * 获取微信账号信息 redirect_URI是值使用第三方登录页面登录成功后的跳转地址
     * @return
     */
    @RequestMapping(value = "/getaccess", method = RequestMethod.POST)
    public ResponseResult getAccess_Token(String code) throws Exception {
        ResponseResult result=new ResponseResult();
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        String url=String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", "wx5f780c4ed8150590", "1979c19b0427d1a67709870b51f2c5e6");
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String access_token = jsonObject.get("access_token")+"";
        result.setData(access_token);
        result.setStatus(Constants.SUCCESS);
        return result;
    }



}
