package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.condition.GoodCondition;
import com.mimumi.lemonserver.entity.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodMapper {


    List<Good> selectByCond(GoodCondition  condition);

    int delete(Integer goodid);

    int insert(Good record);

    Good getByGoodId(Integer goodid);

    int update(Good record);

    int updateIsVip(@Param("intervalhour") int intervalhour,@Param("clickcount") int clickcount);

    int updateIsRecovery(@Param("recoverydays") int recoverydays);

    Good getPhoneByGoodId(Integer goodid);

    List<Good> getneworder();

    int finishedGood(@Param("goodid") Integer goodid);

    int viewgood(Integer goodid);
}