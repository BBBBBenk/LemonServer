package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Merchantfile;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantfileMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(Merchantfile record);

    int insertSelective(Merchantfile record);

    Merchantfile selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(Merchantfile record);

    int updateByPrimaryKey(Merchantfile record);
}