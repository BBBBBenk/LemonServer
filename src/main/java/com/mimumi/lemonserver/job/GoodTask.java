package com.mimumi.lemonserver.job;

import com.mimumi.lemonserver.service.inter.IGoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *商品信息
 */
@Component
public class GoodTask {

    Logger logger= LoggerFactory.getLogger(GoodTask.class);

    @Autowired
    IGoodService goodService;

    /**
     * 检查是否满足要求，从Vip区移到免费区
     * 每小时执行一次
     * @throws Exception
     */
    @Async
    @Scheduled(cron = "0 0 0/1 * * * ")
    public   void checkIsVip() throws  Exception{

        try{
            goodService.updateIsVip();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("检查商品信息是否满足要求，从Vip区移到免费区出现错误，错误信息："+ex.getMessage());
        }
        finally{

        }

    }


    /**
     * 检查商品是否回收
     * 每天执行一次
     * @throws Exception
     */
    @Async
    @Scheduled(cron = "0 0 1 * * * ")
    public   void checkIsRecovery() throws  Exception{

        try{
            goodService.updateIsRecovery();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("检查商品是否回收出现错误，错误信息："+ex.getMessage());
        }
        finally{

        }

    }
}
