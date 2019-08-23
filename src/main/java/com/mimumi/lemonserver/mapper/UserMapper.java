package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.condition.UserCondition;
import com.mimumi.lemonserver.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int insert(User record);

    User getByUserId(Integer userid);

    User getByMobile(String mobile);

    int update(User record);

    int updateUserInfo(User record);

    int delete(Integer userid);

    int checkMobileIsExists(User record);

    int updatePassWord(User user);

    List<User> selectByCond(UserCondition condition);

    int updateVipInactive();

    User checkOpenidIsExists(User openid);

    int updateInviteCode(User record);

    int checkExistCode(String code);

    User getByCode(User record);

    int updatemview(User record);

    int registPassToReset(User record);
}