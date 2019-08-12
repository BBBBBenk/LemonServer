package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Comment;
import com.mimumi.lemonserver.mapper.CommentMapper;
import com.mimumi.lemonserver.service.inter.ICommentService;
import com.mimumi.lemonserver.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getCommentList(Integer goodid){
        return commentMapper.geCommentList(goodid);
    }

    public boolean insert(Comment record) {
        return commentMapper.insert(record)>0;
    }

}
