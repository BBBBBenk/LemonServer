package com.mimumi.lemonserver.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户权限
 */
@RestController
@Api(value = "UserPermitController", description = "用户权限接口")
@RequestMapping("/userpermit")
public class UserPermitController extends  BaseController {



}
