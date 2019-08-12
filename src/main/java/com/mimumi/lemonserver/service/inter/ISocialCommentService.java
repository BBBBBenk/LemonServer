package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Socialcomment;

import java.util.List;

public interface ISocialCommentService {
    List<Socialcomment> getCommentList(Integer socialid);

    boolean insert(Socialcomment record);
}
