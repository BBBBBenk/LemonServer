package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/divi")
public class DividenLogController extends BaseController {

    @RequestMapping(value = "/getmydivi",method = RequestMethod.GET)
    public ResponseResult getMyDividen(){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData(dividenLogService.getMyTotalDivi(UserUtil.getCurrentUser().getUserid()));
        return result;
    }

    @RequestMapping(value = "/getmytddivi",method = RequestMethod.GET)
    public ResponseResult getMyTodayDividen(){
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        result.setData(dividenLogService.getMyTodayDivi(UserUtil.getCurrentUser().getUserid()));
        return result;
    }

}
