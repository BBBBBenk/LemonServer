package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Dividenlog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividenlogMapper {
    int delete(Integer dividenid);

    int insert(Dividenlog record);

    Dividenlog select(Integer dividenid);

    int update(Dividenlog record);

    List<Dividenlog> getdividen(Integer dividener);

    List<Dividenlog> getdividentoday(Integer dividener);
}