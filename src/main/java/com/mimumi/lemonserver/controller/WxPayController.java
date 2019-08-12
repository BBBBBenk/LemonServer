package com.mimumi.lemonserver.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mimumi.lemonserver.config.WxProperties;
import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Invitecontact;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.entity.UserPermitLog;
import com.mimumi.lemonserver.entity.Viporder;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.ApiOperation;

import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {
    @Autowired
    WxProperties properties;

    @Autowired
    WxMpService wxMpService;

    @Autowired
    WxPayService wxPayService;

    private  final Logger logger= LoggerFactory.getLogger(WxPayController.class);

    @ApiOperation(value = "查看信息下单")
    @RequestMapping(value="/chargevip",method = RequestMethod.POST)
    public ResponseResult chargeIcon(Integer goodid) {
        ResponseResult result = new ResponseResult();
        User charger = UserUtil.getCurrentUser();
        Integer viewCoast = Integer.parseInt(configService.getByKey("view_consume_integration").getConfigvalue())*100;
        try{
            WxPayUnifiedOrderRequest order = new WxPayUnifiedOrderRequest();
            order.setSignType(WxPayConstants.SignType.MD5);
            long timeStart = Calendar.getInstance().getTimeInMillis();
            String out_trade_no = 1 + "" + timeStart;

            //随机字符串
            String nonce_str = UUID.randomUUID().toString().substring(0, 15);
            order.setAppid(properties.getAppId());
            order.setMchId(properties.getMchId());
            order.setOutTradeNo(out_trade_no);
            order.setNonceStr(nonce_str);
            order.setBody("购买Vip");
            order.setAttach("东莞市广裕科技有限公司");
            // 获取发起电脑 ip
            String spbill_create_ip = this.getIpAddress(request);
            order.setSpbillCreateIp(spbill_create_ip);

            order.setTotalFee(viewCoast);
            order.setTradeType(WxPayConstants.TradeType.JSAPI);
            order.setOpenid(charger.getOpenid());
            //回调地址
            order.setNotifyUrl("https://api.cxzylm.cn/api/wxpay/viewpay");


            WxPayMpOrderResult orderResult = wxPayService.createOrder(order);
            result.setData(orderResult);
            //插入订单到数据库
            Viporder orders = new Viporder();
            orders.setAmount((long)viewCoast);
            orders.setOuttradeno(out_trade_no);
            orders.setStauts(0);
            orders.setUserid(charger.getUserid());
            orders.setGoodid(goodid);
            vipOrderService.insert(orders);

            logger.info("=========>" + goodid + "<==========");
        }catch (WxPayException e){
            result.setMessage(e.getReturnMsg());
        }

        return result;
    }
    @ApiOperation(value = "支付成功的回调通知")
    @RequestMapping(value="/viewpay",method = RequestMethod.POST)
    public Object Test(HttpServletRequest request, HttpServletResponse response){

        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");
            WxPayOrderNotifyResult wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(result);
            if(wxPayOrderNotifyResult.getResultCode().equals("SUCCESS")){
                // 处理业务 -修改订单支付状态
                Date gmt_payment=null;
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss"); //加上时间
                try {
                    gmt_payment=simpleDateFormat.parse(wxPayOrderNotifyResult.getTimeEnd());
                } catch(ParseException px) {
                    px.printStackTrace();
                }

                String outTradeNo = wxPayOrderNotifyResult.getOutTradeNo(); //从返回信息中心获取订单号
                Viporder pay = vipOrderService.getByOutTradeNo(outTradeNo);
                pay.setStauts(1);
                pay.setPaytime(gmt_payment);
                vipOrderService.paySuccessUpdate(pay);

                User currentUser = userService.getByUserId(pay.getUserid()); //查找购买者

                Invitecontact record = new Invitecontact();
                Invitecontact finded = new Invitecontact();
                User secProxy = null;
                User firstProxy = null;
                record.setInvitees(currentUser.getUserid());
            /* 查找二级代理 */
                finded = inviteContactService.getUpProxy(record);
                if(finded != null){
                    secProxy = finded.getInviterUser();   //二级代理
                }
            /* 查找一级代理 */
                record.setInvitees(secProxy.getUserid());
                finded = inviteContactService.getUpProxy(record);
                if(finded != null){
                    firstProxy = finded.getInviterUser(); //一级代理
                }
                BigDecimal amount = BigDecimal.valueOf(pay.getAmount()/100); //本次消费   分为单位 所以要除于100
                int divipercenter = Integer.parseInt(configService.getByKey("sec_proxy").getConfigvalue()); //获取二级代理分成百分比
                int topdiviPercent = Integer.parseInt(configService.getByKey("first_proxy").getConfigvalue());//获取一级代理分成百分比
            /* 分成 */
                userService.dividenAmountCoast(secProxy, firstProxy, currentUser, amount, divipercenter, topdiviPercent);
            /* 记录分成 */
                dividenLogService.insertLog(secProxy, firstProxy, currentUser, amount,  divipercenter  , topdiviPercent);

                return WxPayNotifyResponse.success("处理成功!");
            }else{
                return WxPayNotifyResponse.fail("返回结果为FAIL");
            }
        } catch (Exception e) {
            logger.error("微信支付回调异常：" + e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }


    public  String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
