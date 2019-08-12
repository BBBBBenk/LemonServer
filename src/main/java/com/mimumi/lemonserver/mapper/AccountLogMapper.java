package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.AccountLog;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLogMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(AccountLog record);

    int insertSelective(AccountLog record);

    AccountLog selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(AccountLog record);

    int updateByPrimaryKey(AccountLog record);
}