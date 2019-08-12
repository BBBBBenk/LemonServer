package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Moth;
import com.mimumi.lemonserver.entity.User;

import java.util.List;

public interface IMothService {
    boolean mothApply(Integer mother, Moth record);

    boolean deleteMoth(Integer mothid, User current);

    List<Moth> getMothList(Integer applyid);

    Moth checkMoth(Moth record);
}
