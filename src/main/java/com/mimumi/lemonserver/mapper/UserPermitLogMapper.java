package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.UserPermitLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermitLogMapper {

    int insert(UserPermitLog record);

    UserPermitLog getByUserGoodId(@Param("userid")Integer userid,@Param("goodid") Integer goodid);

    int delete(Integer goodid);

}