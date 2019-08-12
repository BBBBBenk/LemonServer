package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Good;
import com.mimumi.lemonserver.entity.Moth;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "MothController", description = "评价接口")
@RequestMapping("/moth")
public class MothController extends BaseController {

    @ApiOperation(value = "新增评价")
    @RequestMapping(value="/moth",method = RequestMethod.POST)
    public ResponseResult mothApply(Moth record) {
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        result.setData(mothService.mothApply(current.getUserid(), record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "管理员删除")
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResponseResult mothDelete(Integer mothid) {
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        result.setData(mothService.deleteMoth(mothid, current));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "获取评价列表")
    @RequestMapping(value="/getmothlist",method = RequestMethod.GET)
    public ResponseResult getMothList(Integer applyid) {
        ResponseResult result = new ResponseResult();
        result.setData(mothService.getMothList(applyid));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "检查是否评论过")
    @RequestMapping(value="/checkmoth",method = RequestMethod.POST)
    public ResponseResult checkMoth(Moth record) {
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        record.setMother(current.getUserid());
        boolean tmp = mothService.checkMoth(record) != null ? true : false;
        result.setData(tmp);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
