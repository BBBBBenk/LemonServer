package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.AccountLog;
import com.mimumi.lemonserver.entity.Config;
import com.mimumi.lemonserver.entity.User;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface IAccountLogService {
    boolean insert(AccountLog record);
    boolean insertLog(User user, Config vipmode);
}
