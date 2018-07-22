package com.hey.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hey.dao.BaseDao;
import com.hey.entity.Image;
import com.hey.entity.OrderDetail;
import com.hey.entity.SysMember;
import com.hey.entity.User;
import com.hey.enums.CodeStatus;
import com.hey.result.MultiResult;
import com.hey.result.ResultPageInfo;
import com.hey.result.SingleResult;
import com.hey.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

import static com.hey.enums.CodeStatus.FAIL_DOWNLOAD;
import static com.hey.enums.CodeStatus.MISS_IMAGE;
import static com.hey.enums.CodeStatus.TEL_EXIST;

/**
 * Created by heer on 2018/6/20.
 */
@Service
public class BaseService {

    @Autowired
    private BaseDao baseDao;

    public static final String SAVE_PATH = "";

    //保存图片信息到数据库
    public SingleResult saveImage(String imageUrl, String imagePath,String imageMd5,String tel){
        baseDao.saveImage(tel,imageMd5,imageUrl,imagePath);
        Map map = new HashMap();
        map.put("imageMd5",imageMd5);
        map.put("imageUrl",imageUrl);
        return new SingleResult(map);
    }

    /**
     * 保存用户
     * @param userString
     * @return
     */
    @Transactional
    public SingleResult saveUser(String userString,String imageUrl,String clientMd5){
        User user = JSON.parseObject(userString,new TypeReference<User>(){});
        SingleResult result = new SingleResult();
        String fileName = System.currentTimeMillis()+".png";
//        try {
//            ImageMd5Util.downLoadFromUrl(imageUrl,fileName,SAVE_PATH);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setMsg(FAIL_DOWNLOAD);
//            return result;
//        }
        String imageMd5 = ImageMd5Util.getMD5(imageUrl);
        if(imageIsUnique(imageMd5,clientMd5)){
            //图片是唯一的
            //判断电话号码是否存在
            boolean flag = baseDao.telIsExist(user.getTel());
            if(!flag){
                Long userId = Long.valueOf(RandomNumberGenerator.generateNumber2());
                user.setUserId(userId);
                user.setImageMd5(imageMd5);
                user.setImageUrl(imageUrl);
                //密码MD5加密
                user.setPassword(Md5Util.MD5(user.getPassword()));
                baseDao.saveUser(user);
                baseDao.saveUserUpdateImage(userId,user.getTel(),imageMd5);
                result.setData(userId);
            }else {
                result.setMsg(TEL_EXIST);
            }
        }else {
            //图片不是唯一的，驳回注册请求
            result.setMsg(CodeStatus.MISS_IMAGE);
        }
        return result;
    }

    /**
     * 用户登录
     * @param tel
     * @param password
     * @return
     */
    public SingleResult userLogin(String tel,String password){
        password = Md5Util.MD5(password);
        User info = baseDao.userLogin(tel, password);
        SingleResult result = new SingleResult();
        if(info!=null){
            //成功
            result.setData(info);
        }else {
            //失败
            result.setMsg(CodeStatus.MISS_NAME);
        }
        return result;
    }

    public boolean imageIsUnique(String imageMd5,String clientMd5){
        if (imageMd5.equals(clientMd5)){
            return true;
        }
        return false;
    }


    /**
     * 保存订单
     * @param orderString
     * @param clientMd5
     * @return
     */
    public SingleResult saveOrder(String orderString,String clientMd5){
        OrderDetail order = JSON.parseObject(orderString,new TypeReference<OrderDetail>(){});
        String fileName = System.currentTimeMillis()+".png";
        SingleResult result = new SingleResult();
//        try {
//            ImageMd5Util.downLoadFromUrl(imageUrl,fileName,SAVE_PATH);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setMsg(FAIL_DOWNLOAD);
//            return result;
//        }
        User u = baseDao.getMd5ByTel(order.getTel());
        if(imageIsUnique(u.getImageMd5(),clientMd5)){
            //图片是唯一的，保存订单
            String orderNo = UUIDUtil.getTimeStamp();
            order.setOrderNo(orderNo);
            if(order.getOrderTimeYear()==null){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH )+1;
                int day = cal.get(Calendar.DATE);
                order.setOrderTimeYear(year+"");
                order.setOrderTimeMonth(month+"");
                order.setOrderTimeDay(day+"");
            }
            baseDao.saveOrder(order);
            result.setData(orderNo);
            return result;
        }else {
            //图片不是唯一的，驳回注册请求
            result.setMsg(MISS_IMAGE);
            return result;
        }
    }

    /**
     * 更新用户信息
     * @param userString
     */
    public SingleResult updateUser(String userString,String clientMd5){
        String fileName = System.currentTimeMillis()+".png";

        SingleResult result = new SingleResult();
        User user = JSON.parseObject(userString, new TypeReference<User>() {});
        User u = baseDao.getMd5ByTel(user.getTel());
        if(clientMd5!=null) {
            if (imageIsUnique(u.getImageMd5(), clientMd5)) {
                //密码MD5加密
                user.setPassword(Md5Util.MD5(user.getPassword()));
                //判断公章
                baseDao.updateUser(user);
                return result;
            } else {
                //图片不是唯一的，驳回注册请求
                result.setMsg(MISS_IMAGE);
                return result;
            }
        }else {
            baseDao.updateUser(user);
            return result;
        }
    }

    /**
     * 条件查询用户列表
     * @param
     * @param flag
     * @return
     */
    public MultiResult getUserList(String tel,Integer flag,Integer userStatus,int start,int size,Long userId){
        PageHelper.startPage(start,size);
        Page<User> userPage = baseDao.getUserList(flag, userStatus, tel,userId);
        ResultPageInfo pageInfo = new ResultPageInfo(userPage.getPages(),userPage.getPageNum(),userPage.getTotal(),userPage.getPageSize());
        String userList = JSON.toJSONString(userPage.getResult());
        List<User> users = JSON.parseObject(userList,new TypeReference<List<User>>(){});
        return new MultiResult(users,pageInfo);
    }

    /**
     * 条件查询订单列表
     * @param
     * @param flag
     * @return
     */
    public MultiResult getOrderList(String tel, String cardNum, String imageMd5, Integer flag, Long userId, String orderNo, Integer orderStatus, int start, int size, String time1, String time2, Integer isExport, HttpServletResponse response){
        PageHelper.startPage(start,size);
        Page<OrderDetail> orderPage = baseDao.getOrderList(tel,cardNum,imageMd5,userId,orderStatus,flag,orderNo,time1,time2);
        ResultPageInfo pageInfo = new ResultPageInfo(orderPage.getPages(),orderPage.getPageNum(),orderPage.getTotal(),orderPage.getPageSize());
        String orderList = JSON.toJSONString(orderPage.getResult());
        List<OrderDetail> users = JSON.parseObject(orderList,new TypeReference<List<OrderDetail>>(){});
        if(isExport!=null){
            if (isExport==1) {
                ExcelUtil.exportExcel(users, "订单记录表", "orderDetail", OrderDetail.class, "订单.xls", response);
            }
        }
        return new MultiResult(users,pageInfo);
    }

    /**
     * 条件查询公章列表
     * @param imageMd5
     * @param flag
     * @return
     */
    public MultiResult getImageList(String imageMd5,Integer flag,int start,int size,String tel){
        PageHelper.startPage(start,size);
        Page<Image> imagePage = baseDao.getImageList(imageMd5,tel,flag);
        ResultPageInfo pageInfo = new ResultPageInfo(imagePage.getPages(),imagePage.getPageNum(),imagePage.getTotal(),imagePage.getPageSize());
        String imageList = JSON.toJSONString(imagePage.getResult());
        List<Image> images = JSON.parseObject(imageList,new TypeReference<List<Image>>(){});
        return new MultiResult(images,pageInfo);
    }

    /**
     * 管理员登录
     * @param sysName
     * @param sysPass
     * @return
     */
    public SingleResult sysLogin(String sysName,String sysPass){
        sysPass = Md5Util.MD5(sysPass);
        SysMember sysMember = baseDao.sysLogin(sysName, sysPass);
        SingleResult result = new SingleResult();
        if(sysMember!=null){
            result.setData(sysMember);
            return result;
        }else {
            result.setMsg(CodeStatus.MISS_NAME);
            return result;
        }
    }

    /**
     * 经理添加管理员
     * @param sysMemberString
     * @param operatorId
     * @return
     */
    public SingleResult addSysUser(String sysMemberString,Long operatorId){
        SysMember sysMember = JSON.parseObject(sysMemberString,new TypeReference<SysMember>(){});
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            //是经理
            //在判断管理员名是否存在
            if(baseDao.sysNameIsExist(sysMember.getSysName())){
                //管理员名存在
                result.setMsg(CodeStatus.NAME_ALREADY_EXIST);
            }else {
                sysMember.setSysPass(Md5Util.MD5(sysMember.getSysPass()));
                baseDao.addSysUser(sysMember);
            }
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 经理更新订单
     * @param orderString
     * @param operatorId
     * @return
     */
    public SingleResult updateOrder(String orderString,Long operatorId){
        OrderDetail order = JSON.parseObject(orderString,new TypeReference<OrderDetail>(){});
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            //是经理
            baseDao.updateOrder(order);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 经理删除管理员
     * @param sysId
     * @param operatorId
     * @return
     */
    public SingleResult deleteSysUser(Long sysId,Long operatorId){
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            baseDao.deleteSysUser(sysId);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 经理删除用户
     * @param userId
     * @param operatorId
     * @return
     */
    public SingleResult deleteUser(Long userId,Long operatorId){
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            baseDao.deleteUser(userId);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 经理删除公章
     * @param imageId
     * @param operatorId
     * @return
     */
    public SingleResult deleteImage(Long imageId,Long operatorId){
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            baseDao.deleteImage(imageId);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 经理删除订单
     * @param orderId
     * @param operatorId
     * @return
     */
    public SingleResult deleteOrder(Long orderId,Long operatorId){
        SingleResult result = new SingleResult();
        if(baseDao.sysUserIsManager(operatorId)){
            baseDao.deleteOrder(orderId);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }

    /**
     * 获取管理员列表
     * @param operatorId
     * @param start
     * @param size
     * @return
     */
    public MultiResult getSysUser(Long operatorId,Integer start,Integer size){
        MultiResult result = new MultiResult();
        if(baseDao.sysUserIsManager(operatorId)){
            PageHelper.startPage(start,size);
//            PageHelper.offsetPage(start,size);
            Page<SysMember> members = baseDao.getSysUserList();
            ResultPageInfo pageInfo = new ResultPageInfo(members.getPages(),members.getPageNum(),members.getTotal(),members.getPageSize());
            String memberList = JSON.toJSONString(members.getResult());
            List<SysMember> sysMembers = JSON.parseObject(memberList,new TypeReference<List<SysMember>>(){});
            result.setData(sysMembers,pageInfo);
        }else {
            //不是经理
            result.setMsg(CodeStatus.NO_PERMISSION);
        }
        return result;
    }



    public Page<User> findAllUser(int start,int size){
        PageHelper.startPage(start, size);
        Page<User> userPage = baseDao.findAllUser();
        return userPage;
    }

    public MultiResult findUserByTel(int start, int size, String tel){
        PageHelper.startPage(start,size);
        Page<User> userPage = baseDao.findUserByTel(tel);
        ResultPageInfo pageInfo = new ResultPageInfo(userPage.getPages(),userPage.getPageNum(),userPage.getTotal(),userPage.getPageSize());
        String userList = JSON.toJSONString(userPage.getResult());
        return new MultiResult(userList,pageInfo);
    }


}
