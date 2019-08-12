package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.AccountLog;
import com.mimumi.lemonserver.entity.Config;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.mapper.AccountLogMapper;
import com.mimumi.lemonserver.service.inter.IAccountLogService;
import com.mimumi.lemonserver.service.inter.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class AccountLogService implements IAccountLogService{

    @Autowired
    AccountLogMapper accountLogMapper;

    public boolean insert(AccountLog record){
        return accountLogMapper.insert(record)>0;
    }

    public boolean insertLog(User user, Config vipmode){
        AccountLog record = new AccountLog();
        record.setPaypoints(BigDecimal.valueOf(Double.parseDouble(vipmode.getConfigvalue())));
        record.setChangetime(new Date());
        record.setUserid(user.getUserid());
        return insert(record);
    }

}
