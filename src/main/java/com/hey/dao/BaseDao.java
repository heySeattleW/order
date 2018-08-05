package com.hey.dao;

import com.github.pagehelper.Page;
import com.hey.entity.Image;
import com.hey.entity.OrderDetail;
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
    @Insert("insert into image(image_path,image_url,image_md5,update_time,tel) values (#{imagePath},#{imageUrl},#{imageMd5},now(),#{tel})")
    void saveImage(@Param("tel")String tel,@Param("imageMd5")String imageMd5,@Param("imageUrl")String imageUrl,@Param("imagePath")String imagePath);

    /**
     * 判断是否有这个公章
     * @param imageMd5
     * @return
     */
    @Select("select count(1) from image where image_md5=#{imageMd5} and tel=#{tel}")
    boolean imageIsUnique(@Param("imageMd5") String imageMd5,@Param("tel")String tel);

    /**
     * 判断电话号码是否存在
     * @param tel
     * @return
     */
    @Select("select count(1) from user where tel=#{tel}")
    boolean telIsExist(String tel);


    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_id,tel,user_desc,password,user_status,update_time,image_url,image_md5)" +
            " values(#{userId},#{tel},#{userDesc},#{password},1,now(),#{imageUrl},#{imageMd5})")
    Long saveUser(User user);

    /**
     * 更新公章对应的用户信息
     * @param userId
     * @param tel
     * @param imageMd5
     */
    @Update("update image set user_id=#{userId} and tel=#{tel} where image_md5=#{imageMd5}")
    void saveUserUpdateImage(@Param("userId") Long userId,@Param("tel") String tel,@Param("imageMd5") String imageMd5);

    /**
     * 用户登录
     * @param tel
     * @param password
     * @return
     */
    @Select("select * from user where tel=#{tel} and password=#{password} and user_status=2 limit 1")
    User userLogin(@Param("tel") String tel,@Param("password") String password);

    /**
     * 保存订单
     * @param order
     * @return
     */
    @Insert("insert into order_detail(order_no,update_time,order_status,user_id,image_url,image_md5,tel,card_num,goods_type,is_bao,goods_num,target_addr,user_name,pay_way,region,business_name,begin_time_year,begin_time_month,begin_time_day,end_time_year,end_time_month,end_time_day,order_time_year,order_time_month,order_time_day,receive_tel) " +
            "values (#{orderNo},now(),1,#{userId},#{imageUrl},#{imageMd5},#{tel},#{cardNum},#{goodsType},#{isBao},#{goodsNum},#{targetAddr},#{userName},#{payWay},#{region},#{businessName},#{beginTimeYear},#{beginTimeMonth},#{beginTimeDay},#{endTimeYear},#{endTimeMonth},#{endTimeDay},#{orderTimeYear},#{orderTimeMonth},#{orderTimeDay},#{receiveTel})")
    Long saveOrder(OrderDetail order);

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
            "            <if test=\"userDesc!=null and userDesc!=''\">\n" +
            "                user_desc=#{userDesc},\n" +
            "            </if>\n" +
            "            <if test=\"password!=null and password!=''\">\n" +
            "                password=#{password},\n" +
            "            </if>\n" +
            "            <if test=\"userStatus!=null\">\n" +
            "                user_status=#{userStatus},\n" +
            "            </if>\n" +
            "            update_time=now()\n" +
            "        </set>\n" +
            "        where tel=#{tel}" +
            "</script>")
    void updateUser(User user);

    /**
     * 删除公章
     * @param id
     */
    @Delete("delete from image where id=#{id}")
    void deleteImage(Long id);

    /**
     * 条件查询公章列表
     * @return
     */
    @Select("<script>select * from image where 1=1\n" +
            "        <if test=\"imageMd5!=null and imageMd5!=''\">\n" +
            "            and image_md5 like\n" +
            "            concat(concat('%',#{imageMd5}),'%')\n" +
            "        </if>\n" +
            "        <if test=\"tel!=null and tel!=''\">\n" +
            "            and tel like\n" +
            "            concat(concat('%',#{tel}),'%')\n" +
            "        </if>\n" +
            "        order by update_time\n" +
            "        <if test=\"flag==0\">\n" +
            "            asc\n" +
            "        </if>\n" +
            "        <if test=\"flag==1\">\n" +
            "            desc\n" +
            "        </if></script>")
    Page<Image> getImageList(@Param("imageMd5") String imageMd5,@Param("tel") String tel,@Param("flag") Integer flag);


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
            "        <if test=\"userId!=null\">\n" +
            "            and user_id=#{userId}\n" +
            "        </if>\n" +
            "        order by update_time\n" +
            "        <if test=\"flag==0\">\n" +
            "            asc\n" +
            "        </if>\n" +
            "        <if test=\"flag==1\">\n" +
            "            desc\n" +
            "        </if></script>")
    Page<User> getUserList(@Param("flag")Integer flag,@Param("userStatus")Integer userStatus,@Param("tel")String tel,@Param("userId")Long userId);


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
    @Select("<script>select * from order_detail where 1=1\n" +
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
            "        <if test=\"time1!=null and time2!=null\">\n" +
            "            and update_time between time1 and time2\n" +
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
    Page<OrderDetail> getOrderList(@Param("tel")String tel, @Param("cardNum")String cardNum, @Param("imageMd5")String imageMd5, @Param("userId")Long userId, @Param("orderStatus")Integer orderStatus, @Param("flag") Integer flag, @Param("orderNo")String orderNo,@Param("time1")String time1,@Param("time2")String time2);


    /**
     * 删除订单
     * @param id
     */
    @Delete("delete from order_detail where id=#{id}")
    void deleteOrder(Long id);

    /**
     * 删除用户
     * @param userId
     */
    @Delete("delete from user where user_id=#{userId}")
    void deleteUser(Long userId);

    /**
     * 更新订单
     * @param order
     */
    @Update("update order_detail set order_status=#{orderStatus} where id=#{id}")
    void updateOrder(OrderDetail order);






    //管理员相关
    //管理员登录
    @Select("select * from sys_member where sys_name=#{sysName} and sys_pass=#{sysPass} limit 1")
    SysMember sysLogin(@Param("sysName") String sysName,@Param("sysPass")String sysPass);

    //通过sysId判断是不是经理
    @Select("select sys_power from sys_member where sys_id=#{sysId}")
    Boolean sysUserIsManager(Long sysId);

    //经理添加管理员
    @Insert("insert into sys_member(sys_id,sys_name,sys_pass,sys_power,update_time) values (#{sysId},#{sysName},#{sysPass},0,now())")
    void addSysUser(SysMember sysMember);

    //添加管理员之前判断管理员是否存在
    @Select("select count(1) from sys_member where sys_name=#{sysName}")
    Boolean sysNameIsExist(String sysName);

    //经理删除管理员
    @Delete("delete from sys_member where sys_id=#{sysId}")
    void deleteSysUser(Long sysId);

    //管理员列表
    @Select("select * from sys_member")
    Page<SysMember> getSysUserList();

    @Insert("insert into image(image_path,image_url,image_md5) values (#{imagePath},#{imageUrl},#{imageMd5})")
    void addImage(Image image);


    //通过电话获取md5
    @Select("select * from user where tel=#{tel}")
    User getMd5ByTel(String tel);

    //通过userId获取md5
    @Select("select * from user where user_id=#{userId}")
    User getMd5ByUserId(Long userId);

}
