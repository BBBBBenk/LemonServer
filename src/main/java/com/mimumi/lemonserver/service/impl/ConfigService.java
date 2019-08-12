package com.mimumi.lemonserver.service.impl;

import com.mimumi.lemonserver.entity.Config;
import com.mimumi.lemonserver.mapper.ConfigMapper;
import com.mimumi.lemonserver.service.inter.IConfigService;
import com.mimumi.lemonserver.service.inter.IUserService;
import com.mimumi.lemonserver.utils.RedisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class ConfigService implements IConfigService{

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    RedisUtil redisUtil;

    String redisKey="SystemConfig";

    public Config getByKey(String configkey){
        Config config=configMapper.getByKey(configkey);
       /* List<Config> configList=getAll();
        for(Config item :configList){
            if(item.getConfigkey().equals(configkey)){
                config=item;
                break;
            }
        }*/
        return config;
    }

    public List<Config> getAll(){
        List<Config>  configList=null;
        if(!redisUtil.hasKey(redisKey)){
            configList=configMapper.getAll();
            redisUtil.set(redisKey,configList,60*60*24);
        }else{
            configList=( List<Config>)redisUtil.get(redisKey);
        }
        return configList;
    }

    public boolean update(Config record){
        return configMapper.update(record)>0;
    }

    public void updateList(String record){
        List<Config> recordList = strToList(record, Config.class);
        for(int i = 0; i<recordList.size(); i++){
            update(recordList.get(i));
        }
    }

    public <T> List<T> strToList(String str, Class<T> clazz) {
        JSONArray json = JSONArray.fromObject(str);
        JSONObject object = null;
        T t = null;
        List<T> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            object = JSONObject.fromObject(json.get(i));
            t = (T) JSONObject.toBean(object, clazz);
            list.add(t);
        }
        return list;
    }

    
}
