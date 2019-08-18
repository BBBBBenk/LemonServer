package com.mimumi.lemonserver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Merchant;
import com.mimumi.lemonserver.entity.Merchantfile;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.mapper.MerchantMapper;
import com.mimumi.lemonserver.mapper.MerchantfileMapper;
import com.mimumi.lemonserver.service.inter.IMerchantService;
import com.mimumi.lemonserver.utils.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService implements IMerchantService {

    @Autowired
    MerchantMapper merchantdao;

    @Autowired
    MerchantfileMapper merchantfile;

    public boolean enteringMerchant(Merchant record, String jsonStr, User current) {
        record.setIsrecovery(true);
        record.setAnnouncer(current.getUserid());
        boolean isEntering = merchantdao.insert(record) > 0;
        if(jsonStr != null){
            List<Merchantfile> pic = ArrayUtil.strToList(jsonStr, Merchantfile.class);
            for(Merchantfile item : pic){
                item.setMerchantid(record.getMerchantid());
                merchantfile.insert(item);
            }
        }
        return isEntering;
    }

    public PageVo<Merchant> selectByCond(int page, int rows){
        Page pageHelper = PageHelper.startPage(page, rows, true);
        List<Merchant> pageList=merchantdao.selectByCond();
        PageVo<Merchant> pager=new PageVo<Merchant>(pageHelper.getTotal());
        pager.setRows(pageList);
        return pager;
    }

    public Merchant select(Integer merchantid) {
        return merchantdao.select(merchantid);
    }

    public List<Merchant> getIndexMerchant() {
        return merchantdao.getIndexMerchant();
    }

}
