package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Invitecontact;
import com.mimumi.lemonserver.entity.InvitecontactTree;
import com.mimumi.lemonserver.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitecontactMapper {
    int delete(Integer inviteid);

    int insert(Invitecontact record);

    Invitecontact select(Integer inviteid);

    int update(Invitecontact record);

    Invitecontact getupproxy(Invitecontact record);

    List<InvitecontactTree> getFansList(Integer inviter);

    Invitecontact checkHasContact(Invitecontact record);
}