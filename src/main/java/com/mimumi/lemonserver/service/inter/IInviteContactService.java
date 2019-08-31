package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Invitecontact;
import com.mimumi.lemonserver.entity.InvitecontactTree;
import com.mimumi.lemonserver.entity.User;

import java.util.List;

public interface IInviteContactService {
    boolean insert(Invitecontact record);

    Invitecontact getUpProxy(Invitecontact record);

    List<InvitecontactTree> getFansList(Integer inviter);

    Invitecontact checkHasContact(Invitecontact record);
}
