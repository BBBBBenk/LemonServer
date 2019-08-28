package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Social;
import com.mimumi.lemonserver.entity.Socialthumb;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social")
public class SocialController extends BaseController {

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public ResponseResult insertSocial(Social record, String picArray) {
        ResponseResult result = new ResponseResult();
        boolean tmp = socialService.InsertSocial(UserUtil.getCurrentUser().getUserid(), record, picArray);
        result.setStatus(Constants.SUCCESS);
        result.setData(tmp);
        return result;
    }

    @RequestMapping(value="/getbysocialid",method = RequestMethod.GET)
    public ResponseResult getByToolId(Integer socialid) {
        ResponseResult result=new ResponseResult();
        Social social = socialService.getSocialById(socialid);
        result.setStatus(Constants.SUCCESS);
        result.setData(social);
        return result;
    }

    @RequestMapping(value="/getlist",method = RequestMethod.GET)
    public ResponseResult getListPageVo(int page, int rows) {
        ResponseResult result = new ResponseResult();
        result.setData(socialService.selectByCond(UserUtil.getCurrentUser().getUserid(), page, rows));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/thumb",method = RequestMethod.POST)
    public ResponseResult thumbSomeSocial(Socialthumb record) {
        ResponseResult result = new ResponseResult();
        result.setData(socialThumbService.thumb(UserUtil.getCurrentUser().getUserid(), record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/thumbdown",method = RequestMethod.POST)
    public ResponseResult thumbDownSomeSocial(Socialthumb record) {
        ResponseResult result = new ResponseResult();
        result.setData(socialThumbService.thumbdown(UserUtil.getCurrentUser().getUserid(), record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
