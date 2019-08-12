package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Config;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMapper {

    int delete(Integer configid);

    int insert(Config record);

    Config getByKey(String configkey);

    List<Config> getAll();


    int update(Config record);
}