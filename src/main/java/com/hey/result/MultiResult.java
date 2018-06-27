package com.hey.result;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by heer on 2018/3/29.
 */

public class MultiResult<T> extends BaseResult {

    private T data;

    private ResultPageInfo pageInfo;

    public ResultPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ResultPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public MultiResult() {
        super();
    }

    public MultiResult(T data) {
        super();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MultiResult(T data, ResultPageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public void setData(T data, ResultPageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"pageInfo\":")
                .append(pageInfo);
        sb.append(",\"code\":")
                .append(code);
        sb.append('}');
        return sb.toString();
    }
}
