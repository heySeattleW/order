package com.hey.entity;

/**
 * Created by heer on 2018/6/20.
 */
public class User extends BaseEntity{

    private Long userId;

    private String tel;

    private String userDesc;

    private String password;

    private Integer userStatus;

    private String imageUrl;

    private String imageMd5;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageMd5() {
        return imageMd5;
    }

    public void setImageMd5(String imageMd5) {
        this.imageMd5 = imageMd5;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }



    public User() {
    }

    public User(Long userId, String tel, String userDesc, String password, Integer userStatus) {
        this.userId = userId;
        this.tel = tel;
        this.userDesc = userDesc;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(Long userId, String tel, String userDesc, String password, Integer userStatus, String imageUrl, String imageMd5) {
        this.userId = userId;
        this.tel = tel;
        this.userDesc = userDesc;
        this.password = password;
        this.userStatus = userStatus;
        this.imageUrl = imageUrl;
        this.imageMd5 = imageMd5;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
}
