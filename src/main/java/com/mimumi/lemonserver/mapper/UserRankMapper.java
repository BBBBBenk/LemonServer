package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.UserRank;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRankMapper {

    int delete(Integer rankid);

    int insert(UserRank record);

    UserRank getByRankId(Integer rankid);

    int update(UserRank record);
}