package com.hey.entity;

/**
 * Created by heer on 2018/6/20.
 */
public class User extends BaseEntity{

    private Long userId;

    private String tel;

    private String desc;

    private String password;

    private Integer userStatus;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"tel\":\"")
                .append(tel).append('\"');
        sb.append(",\"desc\":\"")
                .append(desc).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"update_time\":\"")
                .append(update_time).append('\"');
        sb.append(",\"userStatus\":")
                .append(userStatus);
        sb.append('}');
        return sb.toString();
    }
}
