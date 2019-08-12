package com.mimumi.lemonserver.controller;


import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.service.inter.*;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lthui on 2017/3/16.
 */
@RestController
public class BaseController {



    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Autowired
    IUserService userService;

    @Autowired
    IUserRankService userRankService;


    @Autowired
    IConfigService configService;

    @Autowired
    IGoodService goodService;

    @Autowired
    IGoodFileService goodFileService;

    @Autowired
    IUserPermitService userPermitService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IAccountLogService accountLogService;

    @Autowired
    ISocialService socialService;

    @Autowired
    ISocialThumbService socialThumbService;

    @Autowired
    ISocialCommentService socialCommentService;

    @Autowired
    IInviteContactService inviteContactService;

    @Autowired
    IDividenLogService dividenLogService;

    @Autowired
    IMerchantService merchantService;


    @Autowired
    IMothService mothService;

    @Autowired
    IVipOrderService vipOrderService;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;

    }
}
