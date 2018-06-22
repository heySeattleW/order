package com.hey.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hey.dao.BaseDao;
import com.hey.entity.User;
import com.hey.result.MultiResult;
import com.hey.result.ResultPageInfo;
import com.hey.util.ImageMd5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heer on 2018/6/20.
 */
@Service
public class BaseService {

    @Autowired
    private BaseDao baseDao;

    //保存图片信息到数据库
    public void saveImage(String imageUrl,String imagePath){
        String imageMd5 = ImageMd5Util.getMD5(imageUrl);

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
