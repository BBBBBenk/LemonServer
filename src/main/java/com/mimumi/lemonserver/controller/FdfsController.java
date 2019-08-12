package com.mimumi.lemonserver.controller;


import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.fastdfs.FastDFSClient;
import com.mimumi.lemonserver.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fastdfs
 */
@RestController
@Api(value = "FdfsController", description = "文件接口")
@RequestMapping("/fastdfs")
public class FdfsController extends  BaseController {

    @Autowired
    FastDFSClient fastDFSClient;

    @ApiOperation(value = "上传文件")
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public ResponseResult upload() {
        ResponseResult result=new ResponseResult();
        String path=null;
        try
        {
            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
            Iterator<String> itr = mrequest.getFileNames();
            MultipartFile multipartFile = null;
            if (itr.hasNext()) {
                multipartFile = mrequest.getFile(itr.next());
                path = fastDFSClient.uploadFile(multipartFile);
            }
            result.setStatus(Constants.SUCCESS);
            result.setData(path);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
