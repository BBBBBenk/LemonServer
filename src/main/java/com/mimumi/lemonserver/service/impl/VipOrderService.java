package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Viporder;
import com.mimumi.lemonserver.mapper.ViporderMapper;
import com.mimumi.lemonserver.service.inter.IVipOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/8/6.
 */
@Service
public class VipOrderService implements IVipOrderService {
    @Autowired
    ViporderMapper viporderMapper;

    public boolean insert(Viporder record)  { return viporderMapper.insert(record)>0; }

    public Viporder getByOutTradeNo(String outtradeno) {
        return viporderMapper.getByOutTradeNo(outtradeno);
    }

    public boolean paySuccessUpdate(Viporder record) { return viporderMapper.paySuccessUpdate(record)>0; }

    public Viporder checkPaied(Viporder record) { return viporderMapper.checkPaied(record); }

}
