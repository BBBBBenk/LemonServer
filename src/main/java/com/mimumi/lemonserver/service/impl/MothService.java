package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Moth;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.mapper.MothMapper;
import com.mimumi.lemonserver.service.inter.IMothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MothService implements IMothService {
    @Autowired
    MothMapper mothMapper;

    public boolean mothApply(Integer mother, Moth record) {
        record.setMother(mother);
        return mothMapper.insert(record) > 0;
    }

    public boolean deleteMoth(Integer mothid, User current) {
        if(current.getUsertype() != 9) {
            throw new BusinessException("只有管理员才能修改");
        }
        return mothMapper.delete(mothid) > 0;
    }

    public List<Moth> getMothList(Integer applyid) {
        return mothMapper.getmothlist(applyid);
    }

    public Moth checkMoth(Moth record){ return mothMapper.checkMoth(record); }

}
