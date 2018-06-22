package com.hey.result;

import com.hey.enums.CodeStatus;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by heer on 2018/6/21.
 */
public class SingleResult<T> extends BaseResult {

    @ApiModelProperty(name = "data",dataType = "Object",notes = "返回数据")
    private T data;

    public SingleResult(T data) {
        super();
        this.data = data;
    }

    public SingleResult() {
    }

    public SingleResult(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = CodeStatus.ERROR.getCode();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"code\":")
                .append(code);
        sb.append('}');
        return sb.toString();
    }
}
