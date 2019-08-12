package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Socialcomment;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "SocialCommentController", description = "动态评论接口")
@RequestMapping("/scomment")
public class SocialCommentController extends BaseController {

    @ApiOperation(value = "获取需求留言")
    @RequestMapping(value="/getcomment",method = RequestMethod.GET)
    public ResponseResult getComment(Integer socialid) {
        ResponseResult result = new ResponseResult();
        result.setData(socialCommentService.getCommentList(socialid));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "留言")
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
