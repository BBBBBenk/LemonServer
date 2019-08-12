package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Merchant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {
    int delete(Integer merchantid);

    int insert(Merchant record);

    Merchant select(Integer merchantid);

    int update(Merchant record);

    List<Merchant> selectByCond();

    List<Merchant> getIndexMerchant();
}