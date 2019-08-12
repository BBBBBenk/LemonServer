package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Socialthumb;
import com.mimumi.lemonserver.mapper.SocialthumbMapper;
import com.mimumi.lemonserver.service.inter.ISocialThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialThumbService implements ISocialThumbService {
    @Autowired
    SocialthumbMapper socialthumbMapper;

    public boolean thumb(Integer userid, Socialthumb record){
        record.setThumber(userid);
        return socialthumbMapper.insert(record) > 0;
    }

    public boolean thumbdown(Integer userid, Socialthumb record){
        record.setThumber(userid);
        return socialthumbMapper.delete(record) > 0;
    }
}
