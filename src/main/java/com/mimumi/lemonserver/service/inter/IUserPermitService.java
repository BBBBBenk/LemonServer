package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.condition.GoodCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Good;
import com.mimumi.lemonserver.entity.GoodFile;
import com.mimumi.lemonserver.entity.UserPermitLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface IUserPermitService {

    int insert(UserPermitLog record);

    UserPermitLog getByUserGoodId(Integer userid, Integer goodid);
}
