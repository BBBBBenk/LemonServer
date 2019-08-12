package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer companyid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}