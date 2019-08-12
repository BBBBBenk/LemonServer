package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Companyfile;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyfileMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(Companyfile record);

    int insertSelective(Companyfile record);

    Companyfile selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(Companyfile record);

    int updateByPrimaryKey(Companyfile record);
}