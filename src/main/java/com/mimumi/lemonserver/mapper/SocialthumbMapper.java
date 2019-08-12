package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Socialthumb;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialthumbMapper {

    int delete(Socialthumb record);

    int insert(Socialthumb record);

    Socialthumb select(Integer thumbid);

    int update(Socialthumb record);

}