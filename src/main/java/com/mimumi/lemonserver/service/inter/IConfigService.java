package com.mimumi.lemonserver.service.inter;

import com.mimumi.lemonserver.entity.Config;

/**
 * Created by Administrator on 2019/4/19.
 */
public interface IConfigService {

    Config getByKey(String configkey);

    boolean update(Config record);

    void updateList(String record);
}
