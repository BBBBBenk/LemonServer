package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Dividenlog;
import com.mimumi.lemonserver.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface IDividenLogService {
    void insertLog(User secProxy, User firstProxy, User consumer, BigDecimal amount, int diviPercent, int topdiviPercent);

    List<Dividenlog> getMyTotalDivi(Integer dividener);

    List<Dividenlog> getMyTodayDivi(Integer dividener);

    void publishLog(User publish, User consumer, BigDecimal amount, int topdiviPercent);
}
