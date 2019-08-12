package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Dividenlog;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.mapper.DividenlogMapper;
import com.mimumi.lemonserver.service.inter.IDividenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DividenLogService implements IDividenLogService {
    @Autowired
    DividenlogMapper dividenlogMapper;
    public void insertLog(User secProxy, User firstProxy, User consumer, BigDecimal amount, int diviPercent, int topdiviPercent) {
        Dividenlog record = new Dividenlog();
        BigDecimal sec = amount;
        BigDecimal first = amount;
        if(secProxy != null) {
            record.setConsumer(consumer.getUserid());
            record.setDividener(secProxy.getUserid());
            sec = sec.multiply(BigDecimal.valueOf((double)(topdiviPercent)/100));
            record.setAmount(sec);
            dividenlogMapper.insert(record);
            if(firstProxy != null) {
                record.setConsumer(consumer.getUserid());
                record.setDividener(firstProxy.getUserid());
                first = first.multiply(BigDecimal.valueOf((double)(diviPercent)/100));
                record.setAmount(first);
                dividenlogMapper.insert(record);
            }
        }
    }

    public List<Dividenlog> getMyTotalDivi(Integer dividener){ return dividenlogMapper.getdividen(dividener); }

    public List<Dividenlog> getMyTodayDivi(Integer dividener) { return dividenlogMapper.getdividentoday(dividener); }

}
