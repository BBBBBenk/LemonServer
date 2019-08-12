package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.UserPermitLog;
import com.mimumi.lemonserver.mapper.UserPermitLogMapper;
import com.mimumi.lemonserver.service.inter.IUserPermitService;
import com.mimumi.lemonserver.service.inter.IUserRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class UserPermitService implements IUserPermitService {

   @Autowired
    UserPermitLogMapper userPermitLogMapper;

   public  int insert(UserPermitLog record){
       return userPermitLogMapper.insert(record);
   }

   public  UserPermitLog getByUserGoodId(Integer userid, Integer goodid){
       return userPermitLogMapper.getByUserGoodId(userid,goodid);
   }

}
