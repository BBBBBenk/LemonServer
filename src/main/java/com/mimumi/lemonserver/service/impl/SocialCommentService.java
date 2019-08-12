package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Socialcomment;
import com.mimumi.lemonserver.mapper.SocialcommentMapper;
import com.mimumi.lemonserver.service.inter.ISocialCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialCommentService implements ISocialCommentService {

    @Autowired
    SocialcommentMapper socialcommentMapper;

    public List<Socialcomment> getCommentList(Integer socialid){
        return socialcommentMapper.geCommentList(socialid);
    }

    public boolean insert(Socialcomment record) {
        return socialcommentMapper.insert(record)>0;
    }

}
