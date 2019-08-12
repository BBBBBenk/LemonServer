package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Viporder;

/**
 * Created by Administrator on 2019/8/6.
 */
public interface IVipOrderService {

    boolean insert(Viporder record);

    Viporder getByOutTradeNo(String outtradeno);

    boolean paySuccessUpdate(Viporder record);

    Viporder checkPaied(Viporder record);

}
