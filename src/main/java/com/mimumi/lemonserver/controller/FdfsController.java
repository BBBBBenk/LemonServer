package com.mimumi.lemonserver.controller;


import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.fastdfs.FastDFSClient;
import com.mimumi.lemonserver.utils.*;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * fastdfs
 */
@RestController
@RequestMapping("/fastdfs")
public class FdfsController extends  BaseController {

    @Autowired
    FastDFSClient fastDFSClient;

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

    @RequestMapping(value="/muupload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult webUpload(@RequestParam MultipartFile[] mpfs) throws Exception{
        ResponseResult result=new ResponseResult();
        // 上传文件返回的路径集合
        List<String> arrayList = new ArrayList<String>();

        for (MultipartFile mpf : mpfs) {

            // 将文件上传到分布式文件系统，并返回文件的存储路径及名称
            String uploadFile = fastDFSClient.uploadFile(mpf);
            arrayList.add(uploadFile);
        }
        result.setData(arrayList);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
