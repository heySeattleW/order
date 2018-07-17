package com.hey.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.sql.Timestamp;

/**
 * Created by heer on 2018/6/20.
 */
public class OrderDetail extends BaseEntity {

    @Excel(name="订单状态",orderNum = "1",replace = {"未支付_0", "已支付_1","已完成_2"})
    private Integer orderStatus;

    @Excel(name = "用户ID",orderNum = "2")
    private Long userId;

    @Excel(name = "公章URL",orderNum = "3")
    private String imageUrl;

    @Excel(name = "公章MD5",orderNum = "4")
    private String imageMd5;

    @Excel(name = "下单人电话",orderNum = "5")
    private String tel;

    @Excel(name = "收货人电话",orderNum = "6")
    private String receiveTel;

    public OrderDetail(Integer orderStatus, Long userId, String imageUrl, String imageMd5, String tel, String receiveTel, String orderNo, String cardNum, String goodsType, String isBao, String goodsNum, String targetAddr, String userName, String payWay, String region, String businessName, String beginTimeYear, String beginTimeMonth, String beginTimeDay, String endTimeYear, String endTimeMonth, String endTimeDay, String orderTimeYear, String orderTimeMonth, String orderTimeDay) {
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.imageMd5 = imageMd5;
        this.tel = tel;
        this.receiveTel = receiveTel;
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
        sb.append(",\"receiveTel\":\"")
                .append(receiveTel).append('\"');
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

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    @Excel(name = "订单编号",orderNum = "0")
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Excel(name = "车牌号",orderNum = "8")
    private String cardNum;
    @Excel(name = "品种",orderNum = "9")
    private String goodsType;
    @Excel(name = "包或散",orderNum = "10")
    private String isBao;
    @Excel(name = "数量（吨）",orderNum = "11")
    private String goodsNum;
    @Excel(name = "经销店或工地名称",orderNum = "12")
    private String targetAddr;
    @Excel(name = "收货人姓名",orderNum = "7")
    private String userName;
    @Excel(name = "付款方式",orderNum = "13")
    private String payWay;
    @Excel(name = "区域",orderNum = "14")
    private String region;
    @Excel(name = "业务员姓名",orderNum = "15")
    private String businessName;
    @Excel(name = "起始年",orderNum = "16")
    private String beginTimeYear;
    @Excel(name = "起始月",orderNum = "17")
    private String beginTimeMonth;
    @Excel(name = "起始日",orderNum = "18")
    private String beginTimeDay;
    @Excel(name = "终止年",orderNum = "19")
    private String endTimeYear;
    @Excel(name = "终止月",orderNum = "20")
    private String endTimeMonth;
    @Excel(name = "终止日",orderNum = "21")
    private String endTimeDay;
    @Excel(name = "下单年",orderNum = "22")
    private String orderTimeYear;
    @Excel(name = "下单月",orderNum = "23")
    private String orderTimeMonth;
    @Excel(name = "下单日",orderNum = "24")
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
