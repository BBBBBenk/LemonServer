package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.condition.GoodCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Good;
import com.mimumi.lemonserver.entity.GoodFile;
import com.mimumi.lemonserver.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface IGoodService {

    PageVo<Good> selectByCond(GoodCondition condition, int page, int rows);

    int delete(Integer goodid);

    int insert(Good record,List<GoodFile> pictureList);

    Good getByGoodId(Integer goodid);

    int update(Good record);


    int updateIsVip();

    int updateIsRecovery();

    Good getPhoneByGoodId(Integer goodid);

    boolean viewGood(Integer goodid);

    boolean finishedGood(Integer goodid, User user);

    List<Good> getNewOrderForIndex();

    boolean deleteGood(Integer goodid, User current);
}
