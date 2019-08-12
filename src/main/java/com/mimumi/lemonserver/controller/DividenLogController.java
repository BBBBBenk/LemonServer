package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "DividenLogController", description = "用户接口")
@RequestMapping("/divi")
public class DividenLogController extends BaseController {

    @ApiOperation(value = "获取我的分红")
    @RequestMapping(value = "/getmydivi",method = RequestMethod.GET)
    public ResponseResult getMyDividen(){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData(dividenLogService.getMyTotalDivi(UserUtil.getCurrentUser().getUserid()));
        return result;
    }

    @ApiOperation(value = "获取我今日的分红")
    @RequestMapping(value = "/getmytddivi",method = RequestMethod.GET)
    public ResponseResult getMyTodayDividen(){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData(dividenLogService.getMyTodayDivi(UserUtil.getCurrentUser().getUserid()));
        return result;
    }

}
