package com.mimumi.lemonserver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mimumi.lemonserver.condition.GoodCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Good;
import com.mimumi.lemonserver.entity.GoodFile;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.fastdfs.FastDFSClient;
import com.mimumi.lemonserver.mapper.GoodFileMapper;
import com.mimumi.lemonserver.mapper.GoodMapper;
import com.mimumi.lemonserver.mapper.MothMapper;
import com.mimumi.lemonserver.mapper.UserPermitLogMapper;
import com.mimumi.lemonserver.service.inter.IConfigService;
import com.mimumi.lemonserver.service.inter.IGoodService;
import com.mimumi.lemonserver.service.inter.IUserService;
import org.apache.http.auth.BasicUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class GoodService implements IGoodService{

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    GoodFileMapper goodFileMapper;

    @Autowired
    IConfigService configService;

    @Autowired
    FastDFSClient fastDFSClient;

    @Autowired
    UserPermitLogMapper userPermitLogMapper;

    @Autowired
    MothMapper mothMapper;

    public PageVo<Good> selectByCond(GoodCondition condition, int page, int rows){
        Page pageHelper = PageHelper.startPage(page, rows, true);
        List<Good> pageList=goodMapper.selectByCond(condition);
        PageVo<Good> pager=new PageVo<Good>(pageHelper.getTotal());
        pager.setRows(pageList);
        return pager;
    }

    public  int delete(Integer goodid){
        int result=goodFileMapper.deleteByGoodId(goodid);

        result=goodMapper.delete(goodid);

        return result;
    }

    public int insert(Good record,List<GoodFile> pictureList){
        int result = goodMapper.insert( record);
        if(pictureList != null) {
            for(GoodFile picture : pictureList){
                picture.setGoodid(record.getGoodid());
                goodFileMapper.insert(picture);
            }
        }

        return result;
    }

    public Good getByGoodId(Integer goodid){
        return goodMapper.getByGoodId(goodid);
    }

    public int update(Good record){
        return goodMapper.update(record);
    }

    public boolean finishedGood(Integer goodid, User user){
        Good goodInfo = getByGoodId(goodid);
        boolean result = false;
        if((goodInfo.getPublisherid() == user.getUserid()) || (user.getUsertype() == 9)){
            result = goodMapper.finishedGood(goodid)>0;
        }else{
            throw new BusinessException("身份错误");
        }
        return result;
    }


    public int updateIsVip(){
        int intervalHour=Integer.parseInt(configService.getByKey("check_interval_hours").getConfigvalue());
        int clickCount=Integer.parseInt(configService.getByKey("check_click_times").getConfigvalue());
        return goodMapper.updateIsVip(intervalHour,clickCount);
    }

    public  int updateIsRecovery(){
        int recoveryDays=Integer.parseInt(configService.getByKey("good_recovery_days").getConfigvalue());
        return goodMapper.updateIsRecovery(recoveryDays);
    }

    public Good getPhoneByGoodId(Integer goodid){
        return goodMapper.getPhoneByGoodId(goodid);
    }

    public boolean viewGood(Integer goodid) { return goodMapper.viewgood(goodid)>0; }

    public List<Good> getNewOrderForIndex(){ return goodMapper.getneworder(); }

    public boolean deleteGood(Integer goodid, User current) {
        boolean result = false;
        Good deleteObj = getByGoodId(goodid);
        if(current.getUsertype() == 9 || current.getUserid() == deleteObj.getPublisherid()){
            //删除图片 释放内存
            List<GoodFile> deleteFileObj = deleteObj.getGoodPictureList();
            if(deleteFileObj.size() > 0){
                for(GoodFile item : deleteFileObj) {
                    fastDFSClient.deleteFile(item.getPath());
                }
            }

            //删除图片记录
            goodFileMapper.deleteByGoodId(goodid);

            //删除购买记录
            userPermitLogMapper.delete(goodid);

            //删除有关这个需求的评论
            mothMapper.delete(goodid);

            //最后删除这个需求记录
            result = delete(goodid) > 0;
        }else{
            throw new BusinessException("无权操作");
        }
        return result;
    }

}
