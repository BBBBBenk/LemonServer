package com.mimumi.lemonserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mimumi.lemonserver.condition.GoodCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Good;
import com.mimumi.lemonserver.entity.GoodFile;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.fastdfs.FastDFSClient;
import com.mimumi.lemonserver.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品
 */
@RestController
@Api(value = "GoodController", description = "商品接口")
@RequestMapping("/good")
public class GoodController extends  BaseController {

    @Autowired
    FastDFSClient fastDFSClient;

    @ApiOperation(value = "获取商品列表")
    @RequestMapping(value = "/getpaginglist",method = RequestMethod.POST)
    public ResponseResult getPagingList(GoodCondition condition, int page, int rows){
        ResponseResult result=new ResponseResult();
        PageVo<Good> list = goodService.selectByCond(condition, page, rows);
        result.setStatus(Constants.SUCCESS);
        result.setData(list);
        return result;
    }


    @ApiOperation(value = "通过id获取商品")
    @RequestMapping(value="/getbygoodid",method = RequestMethod.GET)
    public ResponseResult getByToolId(Integer goodid) {
        ResponseResult result=new ResponseResult();
        Good tool=goodService.getByGoodId(goodid);
        result.setStatus(Constants.SUCCESS);
        result.setData(tool);
        return result;
    }

    @ApiOperation(value = "点击量增加")
    @RequestMapping(value="/viewgood",method = RequestMethod.POST)
    public ResponseResult viewGood(Integer goodid) {
        ResponseResult result=new ResponseResult();
        boolean tool = goodService.viewGood(goodid);
        result.setStatus(Constants.SUCCESS);
        result.setData(tool);
        return result;
    }


    @ApiOperation(value = "添加商品")
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public ResponseResult insert(Good record,String pictureArray) {
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getIsbacklist() == 0){
            //解析Json数组
            List<GoodFile> pictureList = JSONObject.parseArray(pictureArray, GoodFile.class);
            record.setIsvip(1); //优先发布到Vip区
            record.setStatus(0);
            record.setPublisherid(currentUser.getUserid()); //发布人id
            record.setClickcount(0);//默认点击量
            int tmp = goodService.insert(record,pictureList);
            result.setStatus(Constants.SUCCESS);
        }else{
            throw new BusinessException("您已被管理员拉黑，无法发布");
        }

        return result;
    }


    @ApiOperation(value = "删除工具")
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResponseResult delete(Integer toolid) {
        ResponseResult result = new ResponseResult();
        int tmp=goodService.delete(toolid);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "获取单次消费积分")
    @RequestMapping(value="/getcost",method = RequestMethod.POST)
    public ResponseResult getCostForViewPhone() {
        ResponseResult result = new ResponseResult();
        int tmp = Integer.parseInt(configService.getByKey("view_consume_integration").getConfigvalue());
        result.setData(tmp);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "结单")
    @RequestMapping(value="/finished",method = RequestMethod.POST)
    public ResponseResult finishedGood(Integer goodid) {
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        boolean tmp = goodService.finishedGood(goodid, currentUser);
        result.setData(tmp);
        result.setStatus(Constants.SUCCESS);
        result.setMessage("结单成功");
        return result;
    }

    @ApiOperation(value = "获取最新的3条需求")
    @RequestMapping(value="/getneworder",method = RequestMethod.GET)
    public ResponseResult getNewOrderForIndex(){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData(goodService.getNewOrderForIndex());
        return result;
    }

    @ApiOperation(value = "删除需求")
    @RequestMapping(value="/deletegood",method = RequestMethod.GET)
    public ResponseResult deleteGood(Integer goodid) {
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        result.setData(goodService.deleteGood(goodid, current));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
