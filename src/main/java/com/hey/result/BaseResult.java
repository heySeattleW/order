package com.hey.result;

import com.hey.enums.CodeStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by heer on 2018/3/29.
 */

public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "msg",dataType = "String",notes = "返回消息")
    public String msg = CodeStatus.SUCCESS.getMsg();

    @ApiModelProperty(name = "code",dataType = "int",notes = "返回码")
    public int code = CodeStatus.SUCCESS.getCode();

    public BaseResult() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public BaseResult(Throwable e) {
//        super();
//        this.msg = e.toString();
//        this.code = CodeStatus.ERROR.getCode();
//    }

    public String getMsg(CodeStatus code) {
        String msg = code.getMsg(code.getCode());
        return this.msg.equals(msg)?msg:null;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMsg(CodeStatus code) {
        this.code = code.getCode();
        this.msg = code.getMsg(code.getCode());
    }


}
