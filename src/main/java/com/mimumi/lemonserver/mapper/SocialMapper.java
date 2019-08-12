package com.mimumi.lemonserver.mapper;

import com.mimumi.lemonserver.entity.Social;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialMapper {
    int delete(Integer socialid);

    int insert(Social record);

    Social select(Integer socialid);

    int update(Social record);

    List<Social> selectByCond(Integer userid);
}