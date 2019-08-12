package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Merchantthumb;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantthumbMapper {
    int deleteByPrimaryKey(Integer thumbid);

    int insert(Merchantthumb record);

    int insertSelective(Merchantthumb record);

    Merchantthumb selectByPrimaryKey(Integer thumbid);

    int updateByPrimaryKeySelective(Merchantthumb record);

    int updateByPrimaryKey(Merchantthumb record);
}