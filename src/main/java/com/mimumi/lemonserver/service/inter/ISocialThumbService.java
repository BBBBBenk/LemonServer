package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Socialthumb;

public interface ISocialThumbService {
    boolean thumb(Integer userid, Socialthumb record);
    boolean thumbdown(Integer userid, Socialthumb record);
}
