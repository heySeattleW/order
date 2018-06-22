package com.hey.controller;

import com.hey.result.SingleResult;
import com.hey.service.BaseService;
import com.hey.util.UploadSomething;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by heer on 2018/6/11.
 */
@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    public static final String IMAGE_DIR = "/public/image/";
    public static final String SERVER_URL = "";

//    @PostMapping(value = "/register",produces="application/json")
//    @ApiOperation(value = "注册用户进入系统",httpMethod = "POST")
//    public BaseResult addUser(@ApiParam(name="code",value = "code",required = true)
//                          @RequestParam(value = "code",required = true)String code,
//                              @ApiParam(name="nickName",value = "昵称",required = false)
//                          @RequestParam(value = "nickName",required = false)String nickName,
//                              @ApiParam(name="head",value = "头像",required = false)
//                          @RequestParam(value = "head",required = false)String head
//    ){
//        return new BaseResult(userService.registerUser(code,head, nickName));
//    }
//
//
//    @PostMapping(value = "/update",produces="application/json")
//    @ApiOperation(value = "修改用户信息",httpMethod = "POST")
//    public BaseResult updateUserInfo(@ApiParam(name="gisId",value = "gisId",required = false)
//                              @RequestParam(value = "gisId",required = false)Long gisId,
//                              @ApiParam(name="nickName",value = "昵称",required = false)
//                              @RequestParam(value = "nickName",required = false)String nickName,
//                              @ApiParam(name="userId",value = "用户id",required = true)
//                              @RequestParam(value = "userId",required = true)Long userId,
//                             @ApiParam(name="openid",value = "openid",required = true)
//                                 @RequestParam(value = "openid",required = true)String openid
//    ){
//        return new BaseResult(userService.updateUserInfo(gisId, nickName, userId));
//    }

    @PostMapping(value = "/upload/image")
    @ApiOperation(value = "上传图片",httpMethod = "POST")
    public SingleResult userUploadImage(@ApiParam(name="image",value = "图片",required = true)
                                  @RequestBody(required = true)MultipartFile image,
                                      @ApiParam(name="flag",value = "是否需要保存，保存为1，不保存为0，用户第一次提交需要保存。后面申请发货和注册就不保存",required = true)
                                      @RequestParam(value = "flag",required = true)Integer flag,
                                  HttpServletRequest request){
             String path = request.getServletContext().getRealPath(IMAGE_DIR);
             String temp = UploadSomething.uploadImg(path,image,IMAGE_DIR);
             String imageUrl = SERVER_URL+temp;
             String imagePath = IMAGE_DIR+temp;
             if (flag==1){
                 //保存到数据库
             }
             return new SingleResult(imageUrl);
    }


}
