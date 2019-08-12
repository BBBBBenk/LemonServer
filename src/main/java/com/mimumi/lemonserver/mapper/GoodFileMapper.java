package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.GoodFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodFileMapper {
    int delete(Integer fileid);

    int insert(GoodFile record);

    GoodFile selectByPrimaryKey(Integer fileid);

    List<GoodFile> getGoodPictureList(Integer goodid);

    int deleteByGoodId(Integer goodid);

    int update(GoodFile record);
}