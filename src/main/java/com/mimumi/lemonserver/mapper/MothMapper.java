package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Moth;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MothMapper {
    int delete(Integer applyid);

    int insert(Moth record);

    Moth select(Integer mothid);

    int update(Moth record);

    List<Moth> getmothlist(Integer applyid);

    Moth checkMoth(Moth record);
}