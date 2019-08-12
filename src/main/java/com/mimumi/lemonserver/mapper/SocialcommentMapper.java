package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Socialcomment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialcommentMapper {
    int delete(Integer commentid);

    int insert(Socialcomment record);

    Socialcomment select(Integer commentid);

    int update(Socialcomment record);

    List<Socialcomment> geCommentList(Integer socialid);
}