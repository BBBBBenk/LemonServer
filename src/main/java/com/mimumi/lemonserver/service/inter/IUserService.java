package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.condition.UserCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Config;
import com.mimumi.lemonserver.entity.User;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface IUserService {

    User getByUserId(Integer userid);

    boolean checkMobileIsExists(User user);

    boolean insert(User user);

    boolean update(User user);

    boolean updateUserInfo(User user);

    boolean delete(Integer userid);

    User getByMobile(String mobile);

    boolean updatePassWord(User user);

    PageVo<User> selectByCond(UserCondition condition, int page, int rows);

    boolean slienceUser(String mobile, Integer slienceMode);

    boolean chargePoints(String mobile, Integer points, Integer managerId);

    int updateVipInactive();

    boolean openVip(User user, Config vipmode);

    User checkOpenidIsExists(String openid);

    String initInviteCode(User currentuser);

    User getByCode(User record);

    void dividenAmountCoast(User secProxy, User firstProxy, User consumer, BigDecimal amount, int diviPercent, int topdiviPercent);

    boolean updatemview(User record);
}
