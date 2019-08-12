package com.mimumi.lemonserver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Social;
import com.mimumi.lemonserver.entity.Socialfile;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.mapper.SocialMapper;
import com.mimumi.lemonserver.mapper.SocialfileMapper;
import com.mimumi.lemonserver.service.inter.ISocialService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocialService implements ISocialService {
    @Autowired
    SocialMapper socialMapper;

    @Autowired
    SocialfileMapper socialfileMapper;

    public PageVo<Social> selectByCond(Integer userid, int page, int rows){
        Page pageHelper = PageHelper.startPage(page, rows, true);
        List<Social> pageList = socialMapper.selectByCond(userid);
        PageVo<Social> pager=new PageVo<Social>(pageHelper.getTotal());
        pager.setRows(pageList);
        return pager;
    }

    public Social getSocialById(Integer socialid) { return socialMapper.select(socialid); }

    public boolean InsertSocial(int userid, Social record, String picArray) {

        List<Socialfile> fileArray = strToList(picArray, Socialfile.class);
        record.setPromulgatorid(userid);
        int socialid = socialMapper.insert(record);
        if(socialid != 0) {
            for(Socialfile file : fileArray){
                file.setSocialid(record.getSocialid());
                socialfileMapper.insert(file);
            }
            return true;
        }else{
            throw new BusinessException("发布动态失败，请稍后重试");
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
