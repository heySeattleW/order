package com.hey.controller;

import com.hey.result.MultiResult;
import com.hey.result.SingleResult;
import com.hey.service.BaseService;
import com.hey.util.ImageMd5Util;
import com.hey.util.UploadSomething;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heer on 2018/6/11.
 */
@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    public static final String IMAGE_DIR = "/WEB-INF/classes/public/image";
    public static final String SERVER_URL = "http://47.96.237.198/order/image/";

    @PostMapping(value = "/register", produces = "application/json")
    @ApiOperation(value = "注册用户进入系统", httpMethod = "POST")
    public SingleResult addUser(@ApiParam(name = "user", value = "用户实体类", required = true)
                                @RequestParam(value = "user", required = true) String user,
                                @ApiParam(name = "imageUrl", value = "公章路径", required = true)
                                @RequestParam(value = "imageUrl", required = true) String imageUrl,
                                @ApiParam(name = "clientMd5", value = "客户端生成的md5", required = true)
                                @RequestParam(value = "clientMd5", required = true) String clientMd5
    ) {
        return baseService.saveUser(user, imageUrl, clientMd5);
    }

    @PostMapping(value = "/user/login", produces = "application/json")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public SingleResult userLogin(@ApiParam(name = "tel", value = "电话号码", required = true)
                                  @RequestParam(value = "tel", required = true) String tel,
                                  @ApiParam(name = "password", value = "密码", required = true)
                                  @RequestParam(value = "password", required = true) String password
    ) {
        return baseService.userLogin(tel, password);
    }


    @PostMapping(value = "/user/update", produces = "application/json")
    @ApiOperation(value = "修改用户信息", httpMethod = "POST")
    public SingleResult updateUserInfo(@ApiParam(name = "user", value = "用户实体类", required = true)
                                       @RequestParam(value = "user", required = true) String user,
                                       @ApiParam(name = "clientMd5", value = "客户端md5", required = false)
                                       @RequestParam(value = "clientMd5", required = false) String clientMd5
    ) {
        return baseService.updateUser(user, clientMd5);
    }

    @PostMapping(value = "/upload/image")
    @ApiOperation(value = "上传图片", httpMethod = "POST")
    public SingleResult userUploadImage(@ApiParam(name = "image", value = "图片", required = true)
                                        @RequestBody(required = true) MultipartFile image,
                                        @ApiParam(name = "flag", value = "是否需要保存，保存为1，不保存为0，用户第一次提交需要保存。后面申请发货和注册就不保存", required = true)
                                        @RequestParam(value = "flag", required = true) Integer flag,
                                        @ApiParam(name = "tel", value = "电话号码", required = false)
                                        @RequestParam(value = "tel", required = false) String tel,
                                        HttpServletRequest request) {
        String path = request.getServletContext().getRealPath(IMAGE_DIR);
        String temp = UploadSomething.uploadImg(path, image, IMAGE_DIR);
        String imageUrl = SERVER_URL + temp;
        String imagePath = IMAGE_DIR + temp;
        String fileName = System.currentTimeMillis() + ".png";
        SingleResult result = new SingleResult();
//             try {
//                 ImageMd5Util.downLoadFromUrl(imageUrl,fileName,SAVE_PATH);
//             }catch (Exception e){
//                 e.printStackTrace();
//                 result.setMsg(FAIL_DOWNLOAD);
//                 return result;
//             }
        String imageMd5 = ImageMd5Util.getMD5(imageUrl);
        if (flag == 1) {
            //保存到数据库
            return baseService.saveImage(imageUrl, imagePath, imageMd5, tel);
        }
        Map map = new HashMap();
        map.put("imageUrl", imageUrl);
        map.put("imageMd5", imageMd5);
        return new SingleResult(map);
    }

    @PostMapping(value = "/upload/image/batch")
    @ApiOperation(value = "批量上传图片", httpMethod = "POST")
    public SingleResult uploadImages(@ApiParam(name = "image", value = "图片", required = true)
                                     @RequestBody(required = true) MultipartFile[] image,
                                     @ApiParam(name = "flag", value = "是否需要保存，保存为1，不保存为0，用户第一次提交需要保存。后面申请发货和注册就不保存", required = true)
                                     @RequestParam(value = "flag", required = true) Integer flag,
                                     HttpServletRequest request) {
        String[] temps = {};
        if (image.length > 0) {
            for (int i = 0; i < image.length; i++) {
                String path = request.getServletContext().getRealPath(IMAGE_DIR);
                String temp = UploadSomething.uploadImg(path, image[i], IMAGE_DIR);
                String imageUrl = SERVER_URL + temp;
                String imagePath = IMAGE_DIR + temp;
                if (flag == 1) {
                    //保存到数据库
//                    baseService.saveImage(imageUrl, imagePath);
                }
            }
        }
        return new SingleResult();
    }

//    @PostMapping(value = "/upload/image/batch")
//    @ApiOperation(value = "批量上传图片(暂不可用)",httpMethod = "POST")
//    public SingleResult userUploadImages(@ApiParam(name="image",value = "图片",required = true)
//                                        @RequestBody(required = true)MultipartFile[] image,
//                                        HttpServletRequest request){
//        String path = request.getServletContext().getRealPath(IMAGE_DIR);
//        String[] temp = UploadSomething.uploadImgs(path,image,IMAGE_DIR);
//        for(int i=0;i<temp.length;i++){
//
//        }
//        String imageUrl = SERVER_URL+temp;
//        String imagePath = IMAGE_DIR+temp;
//        return new SingleResult(imageUrl);
//    }

    @PostMapping(value = "/order/save", produces = "application/json")
    @ApiOperation(value = "保存订单", httpMethod = "POST")
    public SingleResult addOrder(@ApiParam(name = "order", value = "订单实体类", required = true)
                                 @RequestParam(value = "order", required = true) String order,
//                                @ApiParam(name="imageUrl",value = "公章路径",required = true)
//                                @RequestParam(value = "imageUrl",required = true)String imageUrl,
                                 @ApiParam(name = "clientMd5", value = "客户端公章md5", required = true)
                                 @RequestParam(value = "clientMd5", required = true) String clientMd5
    ) {
        return baseService.saveOrder(order, clientMd5);
    }

    @GetMapping(value = "/user/list", produces = "application/json")
    @ApiOperation(value = "条件查询用户列表", httpMethod = "GET")
    public MultiResult getUserList(@ApiParam(name = "flag", value = "flag=0表示按时间从低到高排序，1表示从高到低排序", required = true)
                                   @RequestParam(value = "flag", required = true) Integer flag,
                                   @ApiParam(name = "userStatus", value = "userStatus按用户状态进行筛选", required = false)
                                   @RequestParam(value = "userStatus", required = false) Integer userStatus,
                                   @ApiParam(name = "start", value = "分页起始页", required = true)
                                   @RequestParam(value = "start", required = true) Integer start,
                                   @ApiParam(name = "size", value = "分页大小", required = true)
                                   @RequestParam(value = "size", required = true) Integer size,
                                   @ApiParam(name = "tel", value = "根据号码搜索", required = false)
                                   @RequestParam(value = "tel", required = false) String tel,
                                   @ApiParam(name = "userId", value = "按用户ID进行筛选", required = false)
                                   @RequestParam(value = "userId", required = false) Long userId
    ) {
        return baseService.getUserList(tel, flag, userStatus, start, size, userId);
    }

    @GetMapping(value = "/image/list", produces = "application/json")
    @ApiOperation(value = "条件查询公章列表", httpMethod = "GET")
    public MultiResult getImageList(@ApiParam(name = "flag", value = "flag=0表示按时间从低到高排序，1表示从高到低排序", required = true)
                                    @RequestParam(value = "flag", required = true) Integer flag,
                                    @ApiParam(name = "userStatus", value = "userStatus按用户状态进行筛选", required = false)
                                    @RequestParam(value = "userStatus", required = false) Integer userStatus,
                                    @ApiParam(name = "start", value = "分页起始页", required = true)
                                    @RequestParam(value = "start", required = true) Integer start,
                                    @ApiParam(name = "size", value = "分页大小", required = true)
                                    @RequestParam(value = "size", required = true) Integer size,
                                    @ApiParam(name = "imageMd5", value = "根据图片唯一标示搜索", required = false)
                                    @RequestParam(value = "imageMd5", required = false) String imageMd5,
                                    @ApiParam(name = "tel", value = "根据电话号码搜索", required = false)
                                    @RequestParam(value = "tel", required = false) String tel
    ) {
        return baseService.getImageList(imageMd5, flag, start, size, tel);
    }

    @GetMapping(value = "/order/list", produces = "application/json")
    @ApiOperation(value = "条件查询订单列表", httpMethod = "GET")
    public MultiResult getOrderList(@ApiParam(name = "flag", value = "flag=0表示按时间从低到高排序，1表示从高到低排序", required = true)
                                    @RequestParam(value = "flag", required = true) Integer flag,
                                    @ApiParam(name = "orderStatus", value = "orderStatus按订单状态进行筛选", required = false)
                                    @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                                    @ApiParam(name = "userId", value = "按用户ID进行筛选", required = false)
                                    @RequestParam(value = "userId", required = false) Long userId,
                                    @ApiParam(name = "start", value = "分页起始页", required = true)
                                    @RequestParam(value = "start", required = true) Integer start,
                                    @ApiParam(name = "size", value = "分页大小", required = true)
                                    @RequestParam(value = "size", required = true) Integer size,
                                    @ApiParam(name = "imageMd5", value = "根据图片唯一标示搜索", required = false)
                                    @RequestParam(value = "imageMd5", required = false) String imageMd5,
                                    @ApiParam(name = "cardNum", value = "根据车牌号搜索", required = false)
                                    @RequestParam(value = "cardNum", required = false) String cardNum,
                                    @ApiParam(name = "tel", value = "根据电话号码搜索", required = false)
                                    @RequestParam(value = "tel", required = false) String tel,
                                    @ApiParam(name = "orderNo", value = "根据订单号搜索", required = false)
                                    @RequestParam(value = "orderNo", required = false) String orderNo,
                                    @ApiParam(name = "time1", value = "根据时间搜索的起始时间", required = false)
                                    @RequestParam(value = "time1", required = false) String time1,
                                    @ApiParam(name = "time2", value = "根据时间搜索的终止时间", required = false)
                                    @RequestParam(value = "time2", required = false) String time2,
                                    @ApiParam(name = "isExport", value = "是否导出excel，默认是0，不导出，1是导出", required = false)
                                    @RequestParam(value = "isExport", required = false) Integer isExport,
                                    HttpServletResponse response
    ) {
        return baseService.getOrderList(tel, cardNum, imageMd5, flag, userId, orderNo, orderStatus, start, size, time1, time2, isExport, response);
    }

    @GetMapping(value = "/sys/list", produces = "application/json")
    @ApiOperation(value = "获取管理员列表", httpMethod = "GET")
    public MultiResult getSysUser(@ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                  @RequestParam(value = "operatorId", required = true) Long operatorId,
                                  @ApiParam(name = "start", value = "分页起始页", required = true)
                                  @RequestParam(value = "start", required = true) Integer start,
                                  @ApiParam(name = "size", value = "分页大小", required = true)
                                  @RequestParam(value = "size", required = true) Integer size
    ) {
        return baseService.getSysUser(operatorId, start, size);
    }


    @PostMapping(value = "/sys/login", produces = "application/json")
    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    public SingleResult sysLogin(@ApiParam(name = "sysName", value = "管理员名", required = true)
                                 @RequestParam(value = "sysName", required = true) String sysName,
                                 @ApiParam(name = "sysPass", value = "管理员密码", required = true)
                                 @RequestParam(value = "sysPass", required = true) String sysPass
    ) {
        return baseService.sysLogin(sysName, sysPass);
    }

    @PostMapping(value = "/sys/add", produces = "application/json")
    @ApiOperation(value = "经理添加管理员", httpMethod = "POST")
    public SingleResult addSysUser(@ApiParam(name = "sysMember", value = "管理员实体类", required = true)
                                   @RequestParam(value = "sysMember", required = true) String sysMember,
                                   @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                   @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.addSysUser(sysMember, operatorId);
    }

    @PostMapping(value = "/sys/update/order", produces = "application/json")
    @ApiOperation(value = "经理更新订单", httpMethod = "POST")
    public SingleResult addUpdateOrder(@ApiParam(name = "order", value = "订单实体类", required = true)
                                       @RequestParam(value = "order", required = true) String order,
                                       @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                       @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.updateOrder(order, operatorId);
    }

    @DeleteMapping(value = "/sys/delete", produces = "application/json")
    @ApiOperation(value = "经理删除管理员", httpMethod = "DELETE")
    public SingleResult deleteSysUser(@ApiParam(name = "sysId", value = "被删除管理员ID", required = true)
                                      @RequestParam(value = "sysId", required = true) Long sysId,
                                      @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                      @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.deleteSysUser(sysId, operatorId);
    }

    @DeleteMapping(value = "/sys/delete/order", produces = "application/json")
    @ApiOperation(value = "经理删除订单", httpMethod = "DELETE")
    public SingleResult deleteOrder(@ApiParam(name = "orderId", value = "被删除订单ID", required = true)
                                    @RequestParam(value = "orderId", required = true) Long orderId,
                                    @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                    @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.deleteOrder(orderId, operatorId);
    }

    @DeleteMapping(value = "/sys/delete/user", produces = "application/json")
    @ApiOperation(value = "经理删除用户", httpMethod = "DELETE")
    public SingleResult deleteUser(@ApiParam(name = "userId", value = "被删除用户ID", required = true)
                                   @RequestParam(value = "userId", required = true) Long userId,
                                   @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                   @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.deleteUser(userId, operatorId);
    }

    @DeleteMapping(value = "/sys/delete/image", produces = "application/json")
    @ApiOperation(value = "经理删除公章", httpMethod = "DELETE")
    public SingleResult deleteImage(@ApiParam(name = "imageId", value = "被删除公章ID", required = true)
                                    @RequestParam(value = "imageId", required = true) Long imageId,
                                    @ApiParam(name = "operatorId", value = "操作员ID", required = true)
                                    @RequestParam(value = "operatorId", required = true) Long operatorId
    ) {
        return baseService.deleteImage(imageId, operatorId);
    }

    @GetMapping(value = "/haha", produces = "application/json")
    @ApiOperation(value = "测试用", httpMethod = "GET")
    public SingleResult test1(String hello) {
        return new SingleResult(hello);
    }
}
