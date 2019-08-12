package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Socialfile;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialfileMapper {
    int delete(Integer fileid);

    int insert(Socialfile record);

    Socialfile select(Integer fileid);

    int update(Socialfile record);
}