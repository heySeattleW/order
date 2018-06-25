package com.hey.dao;

import com.github.pagehelper.Page;
import com.hey.entity.Image;
import com.hey.entity.Order;
import com.hey.entity.SysMember;
import com.hey.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by heer on 2018/6/20.
 */
@Mapper
public interface BaseDao {

    @Select("select * from user")
    Page<User> findAllUser();

    @Select("select * from user where tel=#{tel}")
    Page<User> findUserByTel(String tel);

    /**
     * 保存图片（公章）
     * @param
     */
    @Insert("insert into image(image_path,image_url,image_md5,update_time) values (#{imagePath},#{imageUrl},#{imageMd5},now())")
    void saveImage(@Param("imageMd5")String imageMd5,@Param("imageUrl")String imageUrl,@Param("imagePath")String imagePath);

    /**
     * 判断是否有这个公章
     * @param imageMd5
     * @return
     */
    @Select("select count(1) from image where image_md5=#{imageMd5}")
    boolean imageIsUnique(String imageMd5);


    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_id,tel,desc,password,user_status,update_time)" +
            " values(#{userId},#{tel},#{desc},#{password},0,now())")
    Long saveUser(User user);

    /**
     * 用户登录
     * @param tel
     * @param password
     * @return
     */
    @Select("select * from user where tel=#{tel} and password=#{password} limit 1")
    User userLogin(@Param("tel") String tel,@Param("password") String password);

    /**
     * 保存订单
     * @param order
     * @return
     */
    @Insert("insert into order(order_no,update_time,order_status,user_id,image_url,image_md5,tel,card_num,goods_type,isBao,goods_num,target_addr,user_name,pay_way,region,business_name,begin_time_year,begin_time_month,begin_time_day,end_time_year,end_time_month,end_time_day,order_time_year,order_time_month,order_time_day) " +
            "values (#{orderNo},now(),0,#{userId},#{imageUrl},#{imageMd5},#{tel},#{cardNum},#{goodsType},#{isBao},#{goodsNum},#{targetAddr},#{userName},#{payWay},#{region},#{businessName},#{beginTimeYear},#{beginTimeMonth},#{beginTimeDay},#{endTimeYear},#{endTimeMonth},#{endTimeDay},#{orderTimeYear},#{orderTimeMonth},#{orderTimeDay})")
    Long saveOrder(Order order);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("<script>" +
            "update user\n" +
            "        <set>\n" +
            "            <if test=\"tel!=null and tel!=''\">\n" +
            "                tel=#{tel},\n" +
            "            </if>\n" +
            "            <if test=\"desc!=null and desc!=''\">\n" +
            "                'desc'=#{desc},\n" +
            "            </if>\n" +
            "            <if test=\"password!=null and password!=''\">\n" +
            "                password=#{password},\n" +
            "            </if>\n" +
            "            <if test=\"user_status!=null\">\n" +
            "                user_status=#{userStatus},\n" +
            "            </if>\n" +
            "            update_time=now()\n" +
            "        </set>\n" +
            "        where user_id=#{userId}" +
            "</script>")
    void updateUser(User user);

    /**
     * 条件查询公章列表
     * @return
     */
    @Select("<script>select * from image where 1=1\n" +
            "        <if test=\"imageMd5!=null and imageMd5!=''\">\n" +
            "            and image_md5 like\n" +
            "            concat(concat('%',#{imageMd5}),'%')\n" +
            "        </if>\n" +
            "        order by update_time\n" +
            "        <if test=\"flag==0\">\n" +
            "            asc\n" +
            "        </if>\n" +
            "        <if test=\"flag==1\">\n" +
            "            desc\n" +
            "        </if></script>")
    Page<Image> getImageList(@Param("imageMd5") String imageMd5,@Param("flag") int flag);


    /**
     * 条件获取用户列表
     * @param flag
     * @param userStatus
     * @param tel
     * @return
     */
    @Select("<script>select * from user where 1=1\n" +
            "        <if test=\"tel!=null and tel!=''\">\n" +
            "            and tel like\n" +
            "            concat(concat('%',#{tel}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"userStatus!=null\">\n" +
            "            and user_status=#{userStatus}\n" +
            "        </if>\n" +
            "        order by update_time\n" +
            "        <if test=\"flag==0\">\n" +
            "            asc\n" +
            "        </if>\n" +
            "        <if test=\"flag==1\">\n" +
            "            desc\n" +
            "        </if></script>")
    Page<User> getUserList(@Param("flag")int flag,@Param("userStatus")int userStatus,@Param("tel")String tel);


    /**
     * 条件查询订单列表
     * @param tel
     * @param cardNum
     * @param imageMd5
     * @param userId
     * @param orderStatus
     * @param flag
     * @return
     */
    @Select("<script>select * from order where 1=1\n" +
            "        <if test=\"tel!=null and tel!=''\">\n" +
            "            and tel like\n" +
            "            concat(concat('%',#{tel}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"cardNum!=null and cardNum!=''\">\n" +
            "            and card_num like\n" +
            "            concat(concat('%',#{cardNum}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"imageMd5!=null and imageMd5!=''\">\n" +
            "            and image_md5 like\n" +
            "            concat(concat('%',#{imageMd5}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"orderNo!=null and orderNo!=''\">\n" +
            "            and order_no like\n" +
            "            concat(concat('%',#{orderNo}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"userId!=null\">\n" +
            "            and user_id=#{userId}\n" +
            "        </if>\n" +
            "        <if test=\"orderStatus!=null\">\n" +
            "            and order_status=#{orderStatus}\n" +
            "        </if>\n" +
            "        order by update_time\n" +
            "        <if test=\"flag==0\">\n" +
            "            asc\n" +
            "        </if>\n" +
            "        <if test=\"flag==1\">\n" +
            "            desc\n" +
            "        </if></script>")
    Page<Order> getOrderList(@Param("tel")String tel,@Param("cardNum")String cardNum,@Param("imageMd5")String imageMd5,@Param("userId")Long userId,@Param("orderStatus")Integer orderStatus,@Param("flag") int flag,@Param("orderNo")String orderNo);







    //管理员相关
    //管理员登录
    @Select("select * from sys_member where sys_name=#{sysName} and sys_pass=#{sysPass} limit 1")
    SysMember sysLogin(@Param("sysName") String sysName,@Param("sysPass")String sysPass);

    //通过sysId判断是不是经理
    @Select("select sys_power from sys_member where sys_id=#{sysId}")
    boolean sysUserIsManager(Long sysId);

    //经理添加管理员
    @Insert("insert into sys_member(sys_id,sys_name,sys_pass,sys_power,update_time) values (#{sysId},#{sysName},#{sysPass},0,now())")
    void addSysUser(SysMember sysMember);

    //添加管理员之前判断管理员是否存在
    @Select("select count(1) from sys_member where sys_name=#{sysName}")
    boolean sysNameIsExist(String sysName);

    //经理删除管理员
    @Delete("delete from sys_member where sys_id=#{sysId}")
    void deleteSysUser(Long sysId);

    //管理员列表
    @Select("select * from sys_member")
    Page<SysMember> getSysUserList();

}
