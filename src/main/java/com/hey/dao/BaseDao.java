package com.hey.dao;

import com.github.pagehelper.Page;
import com.hey.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by heer on 2018/6/20.
 */
@Mapper
public interface BaseDao {

    @Select("select * from user")
    Page<User> findAllUser();

    @Select("select * from user where tel=#{tel}")
    Page<User> findUserByTel(String tel);

}
