package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Invitecontact;
import com.mimumi.lemonserver.entity.InvitecontactTree;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.mapper.InvitecontactMapper;
import com.mimumi.lemonserver.service.inter.IInviteContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteContactService implements IInviteContactService {
    @Autowired
    InvitecontactMapper invitecontactMapper;
    public boolean insert(Invitecontact record) { return invitecontactMapper.insert(record)>0; }

    public Invitecontact getUpProxy(Invitecontact record) { return invitecontactMapper.getupproxy(record); }

    public List<InvitecontactTree> getFansList(Integer inviter) { return invitecontactMapper.getFansList(inviter); }

    public Invitecontact checkHasContact(Invitecontact record) {return invitecontactMapper.checkHasContact(record);}

}
