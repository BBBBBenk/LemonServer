package com.mimumi.lemonserver.config;

import com.mimumi.lemonserver.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/8/5.
 */

@Configuration
public class SmsConfig {

    @Value("${aliyun.sms.accessKeyId}")
    private  String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private  String accessKeySecret;

    @Value("${aliyun.sms.signName}")
    private  String signName;

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    @Bean(name = "smsUtil")
    public SMSUtil smsUtil() {
        SMSUtil redisUtil = new SMSUtil();
        return redisUtil;
    }
}
