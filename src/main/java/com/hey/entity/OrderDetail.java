package com.hey.entity;

import java.sql.Timestamp;

/**
 * Created by heer on 2018/6/20.
 */
public class OrderDetail extends BaseEntity {

    private Integer orderStatus;

    private Long userId;

    private String imageUrl;

    private String imageMd5;

    private String tel;

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    private String cardNum;
    private String goodsType;
    private String isBao;
    private String goodsNum;
    private String targetAddr;
    private String userName;
    private String payWay;
    private String region;
    private String businessName;
    private String beginTimeYear;
    private String beginTimeMonth;
    private String beginTimeDay;
    private String endTimeYear;
    private String endTimeMonth;
    private String endTimeDay;
    private String orderTimeYear;
    private String orderTimeMonth;
    private String orderTimeDay;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getIsBao() {
        return isBao;
    }

    public void setIsBao(String isBao) {
        this.isBao = isBao;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBeginTimeYear() {
        return beginTimeYear;
    }

    public void setBeginTimeYear(String beginTimeYear) {
        this.beginTimeYear = beginTimeYear;
    }

    public String getBeginTimeMonth() {
        return beginTimeMonth;
    }

    public void setBeginTimeMonth(String beginTimeMonth) {
        this.beginTimeMonth = beginTimeMonth;
    }

    public String getBeginTimeDay() {
        return beginTimeDay;
    }

    public void setBeginTimeDay(String beginTimeDay) {
        this.beginTimeDay = beginTimeDay;
    }

    public String getEndTimeYear() {
        return endTimeYear;
    }

    public void setEndTimeYear(String endTimeYear) {
        this.endTimeYear = endTimeYear;
    }

    public String getEndTimeMonth() {
        return endTimeMonth;
    }

    public void setEndTimeMonth(String endTimeMonth) {
        this.endTimeMonth = endTimeMonth;
    }

    public String getEndTimeDay() {
        return endTimeDay;
    }

    public void setEndTimeDay(String endTimeDay) {
        this.endTimeDay = endTimeDay;
    }

    public String getOrderTimeYear() {
        return orderTimeYear;
    }

    public void setOrderTimeYear(String orderTimeYear) {
        this.orderTimeYear = orderTimeYear;
    }

    public String getOrderTimeMonth() {
        return orderTimeMonth;
    }

    public void setOrderTimeMonth(String orderTimeMonth) {
        this.orderTimeMonth = orderTimeMonth;
    }

    public String getOrderTimeDay() {
        return orderTimeDay;
    }

    public void setOrderTimeDay(String orderTimeDay) {
        this.orderTimeDay = orderTimeDay;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"orderStatus\":")
                .append(orderStatus);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append(",\"imageMd5\":\"")
                .append(imageMd5).append('\"');
        sb.append(",\"tel\":\"")
                .append(tel).append('\"');
        sb.append(",\"orderNo\":\"")
                .append(orderNo).append('\"');
        sb.append(",\"cardNum\":\"")
                .append(cardNum).append('\"');
        sb.append(",\"goodsType\":\"")
                .append(goodsType).append('\"');
        sb.append(",\"isBao\":\"")
                .append(isBao).append('\"');
        sb.append(",\"goodsNum\":\"")
                .append(goodsNum).append('\"');
        sb.append(",\"targetAddr\":\"")
                .append(targetAddr).append('\"');
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"payWay\":\"")
                .append(payWay).append('\"');
        sb.append(",\"region\":\"")
                .append(region).append('\"');
        sb.append(",\"businessName\":\"")
                .append(businessName).append('\"');
        sb.append(",\"beginTimeYear\":\"")
                .append(beginTimeYear).append('\"');
        sb.append(",\"beginTimeMonth\":\"")
                .append(beginTimeMonth).append('\"');
        sb.append(",\"beginTimeDay\":\"")
                .append(beginTimeDay).append('\"');
        sb.append(",\"endTimeYear\":\"")
                .append(endTimeYear).append('\"');
        sb.append(",\"endTimeMonth\":\"")
                .append(endTimeMonth).append('\"');
        sb.append(",\"endTimeDay\":\"")
                .append(endTimeDay).append('\"');
        sb.append(",\"orderTimeYear\":\"")
                .append(orderTimeYear).append('\"');
        sb.append(",\"orderTimeMonth\":\"")
                .append(orderTimeMonth).append('\"');
        sb.append(",\"orderTimeDay\":\"")
                .append(orderTimeDay).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public OrderDetail(Long id, String update_time, Integer orderStatus, Long userId, String imageUrl, String imageMd5, String tel, String orderNo, String cardNum, String goodsType, String isBao, String goodsNum, String targetAddr, String userName, String payWay, String region, String businessName, String beginTimeYear, String beginTimeMonth, String beginTimeDay, String endTimeYear, String endTimeMonth, String endTimeDay, String orderTimeYear, String orderTimeMonth, String orderTimeDay) {
        super(id, update_time);
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.imageMd5 = imageMd5;
        this.tel = tel;
        this.orderNo = orderNo;
        this.cardNum = cardNum;
        this.goodsType = goodsType;
        this.isBao = isBao;
        this.goodsNum = goodsNum;
        this.targetAddr = targetAddr;
        this.userName = userName;
        this.payWay = payWay;
        this.region = region;
        this.businessName = businessName;
        this.beginTimeYear = beginTimeYear;
        this.beginTimeMonth = beginTimeMonth;
        this.beginTimeDay = beginTimeDay;
        this.endTimeYear = endTimeYear;
        this.endTimeMonth = endTimeMonth;
        this.endTimeDay = endTimeDay;
        this.orderTimeYear = orderTimeYear;
        this.orderTimeMonth = orderTimeMonth;
        this.orderTimeDay = orderTimeDay;
    }

    public OrderDetail() {
    }
}
