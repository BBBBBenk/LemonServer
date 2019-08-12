package com.mimumi.lemonserver.job;

import com.mimumi.lemonserver.exception.SystemExceptionHandler;
import com.mimumi.lemonserver.service.inter.IGoodService;
import com.mimumi.lemonserver.service.inter.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *用户信息
 */
@Component
public class UserTask {


    Logger logger= LoggerFactory.getLogger(UserTask.class);


    @Autowired
    IUserService userService;

    /**
     * 检查vip是否过期
     * 每天执行一次
     * @throws Exception
     */
    @Async
    @Scheduled(cron = "0 0 1 * * * ")
    public   void checkIsVip() throws  Exception{

        try{
            userService.updateVipInactive();
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("检查vip是否过期出现错误，错误信息："+ex.getMessage());
        }
        finally{

        }

    }



}
