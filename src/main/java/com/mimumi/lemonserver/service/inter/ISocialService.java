package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Social;

import java.util.List;

public interface ISocialService {

    boolean InsertSocial(int userid, Social record, String picArray);

    PageVo<Social> selectByCond(Integer userid, int page, int rows);

    Social getSocialById(Integer socialid);
}
