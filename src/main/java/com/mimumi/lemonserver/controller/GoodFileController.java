package com.mimumi.lemonserver.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品文件
 */
@RestController
@Api(value = "GoodFileController", description = "商品文件接口")
@RequestMapping("/goodfile")
public class GoodFileController extends  BaseController {



}
