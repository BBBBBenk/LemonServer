package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Recharge;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeMapper {
    int delete(Integer rechargeid);

    int insert(Recharge record);

    Recharge select(Integer rechargeid);

    int update(Recharge record);
}