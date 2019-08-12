package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Social;
import com.mimumi.lemonserver.entity.Socialthumb;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "SocialController", description = "动态接口")
@RequestMapping("/social")
public class SocialController extends BaseController {

    @ApiOperation(value = "发布动态")
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public ResponseResult insertSocial(Social record, String picArray) {
        ResponseResult result = new ResponseResult();
        boolean tmp = socialService.InsertSocial(UserUtil.getCurrentUser().getUserid(), record, picArray);
        result.setStatus(Constants.SUCCESS);
        result.setData(tmp);
        return result;
    }

    @ApiOperation(value = "通过id获取动态")
    @RequestMapping(value="/getbysocialid",method = RequestMethod.GET)
    public ResponseResult getByToolId(Integer socialid) {
        ResponseResult result=new ResponseResult();
        Social social = socialService.getSocialById(socialid);
        result.setStatus(Constants.SUCCESS);
        result.setData(social);
        return result;
    }

    @ApiOperation(value = "获取列表")
    @RequestMapping(value="/getlist",method = RequestMethod.GET)
    public ResponseResult getListPageVo(int page, int rows) {
        ResponseResult result = new ResponseResult();
        result.setData(socialService.selectByCond(UserUtil.getCurrentUser().getUserid(), page, rows));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "点赞")
    @RequestMapping(value="/thumb",method = RequestMethod.POST)
    public ResponseResult thumbSomeSocial(Socialthumb record) {
        ResponseResult result = new ResponseResult();
        result.setData(socialThumbService.thumb(UserUtil.getCurrentUser().getUserid(), record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "取消点赞")
    @RequestMapping(value="/thumbdown",method = RequestMethod.POST)
    public ResponseResult thumbDownSomeSocial(Socialthumb record) {
        ResponseResult result = new ResponseResult();
        result.setData(socialThumbService.thumbdown(UserUtil.getCurrentUser().getUserid(), record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
