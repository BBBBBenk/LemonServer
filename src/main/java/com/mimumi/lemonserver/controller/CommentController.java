package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Comment;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 留言
 */
@RestController
@Api(value = "CommentController", description = "留言接口")
@RequestMapping("/comment")
public class CommentController extends  BaseController {

    @ApiOperation(value = "获取需求留言")
    @RequestMapping(value="/getcomment",method = RequestMethod.POST)
    public ResponseResult getComment(Integer goodid) {
        ResponseResult result = new ResponseResult();
        result.setData(commentService.getCommentList(goodid));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @ApiOperation(value = "留言")
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public ResponseResult insertComment(Comment record) {
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        record.setFromuid(currentUser.getUserid());
        commentService.insert(record);
        result.setData(commentService.select(record.getComid()));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
