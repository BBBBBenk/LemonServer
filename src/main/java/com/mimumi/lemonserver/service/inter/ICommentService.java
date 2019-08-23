package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Comment;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface ICommentService {
    List<Comment> getCommentList(Integer goodid);

    boolean insert(Comment record);

    Comment select(Integer comid);
}
