package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Viporder;
import org.springframework.stereotype.Repository;

@Repository
public interface ViporderMapper {
    int delete(Integer vorderid);

    int insert(Viporder record);

    Viporder select(Integer vorderid);

    int update(Viporder record);

    Viporder getByOutTradeNo(String outtradeno);

    int paySuccessUpdate(Viporder record);

    Viporder checkPaied(Viporder record);

}