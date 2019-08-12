package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Merchant;
import com.mimumi.lemonserver.entity.User;

import java.util.List;

public interface IMerchantService {

    boolean enteringMerchant(Merchant record, String jsonStr, User current);

    PageVo<Merchant> selectByCond(int page, int rows);

    Merchant select(Integer merchantid);

    List<Merchant> getIndexMerchant();
}
