package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Socialcomment;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scomment")
public class SocialCommentController extends BaseController {

    @RequestMapping(value="/getcomment",method = RequestMethod.GET)
    public ResponseResult getComment(Integer socialid) {
        ResponseResult result = new ResponseResult();
        result.setData(socialCommentService.getCommentList(socialid));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public ResponseResult insertComment(Socialcomment record) {
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        record.setFromuid(currentUser.getUserid());
        result.setData(socialCommentService.insert(record));
        result.setStatus(Constants.SUCCESS);
        return result;
    }
}
