package com.hey.enums;

/**
 * Created by heer on 2018/3/29.
 */
public enum CodeStatus {

    SUCCESS(200,"成功"),
    NO_DATA(201,"没数据啦"),
    PARAMETERS_ERROR(400,"参数错误"),
    NO_PERMISSION(401,"无权访问"),
    TOKEN_EXPIRE(403,"凭证过期"),
    NO_AUTHENTICATION(402,"未认证"),
    NAME_ALREADY_EXIST(405,"用户名已存在"),
    ERROR(500,"服务器错误"),
    MISS_NAME(501,"账号密码不存在");

    private int code;
    private String msg;

    CodeStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(int index) {
        for (CodeStatus c : CodeStatus.values()) {
            if (c.getCode() == index) {
                return c.msg;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CodeStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
