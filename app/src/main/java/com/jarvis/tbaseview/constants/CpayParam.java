package com.jarvis.tbaseview.constants;

/**
 * 银联支付参数
 * Created by tansheng on 2017/8/10.
 */

public class CpayParam {
    private String TranType;
    private String PayTimeOut;
    private String MerBgUrl;
    private String TranDate;
    private String TranTime;
    private String MerId;
    private String MerResv;
    private String Version;
    private String MerOrderNo;
    private String OrderAmt;
    private String Signature;
    private String BusiType;

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
    }

    public String getPayTimeOut() {
        return PayTimeOut;
    }

    public void setPayTimeOut(String payTimeOut) {
        PayTimeOut = payTimeOut;
    }

    public String getMerBgUrl() {
        return MerBgUrl;
    }

    public void setMerBgUrl(String merBgUrl) {
        MerBgUrl = merBgUrl;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTranTime() {
        return TranTime;
    }

    public void setTranTime(String tranTime) {
        TranTime = tranTime;
    }

    public String getMerId() {
        return MerId;
    }

    public void setMerId(String merId) {
        MerId = merId;
    }

    public String getMerResv() {
        return MerResv;
    }

    public void setMerResv(String merResv) {
        MerResv = merResv;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getMerOrderNo() {
        return MerOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        MerOrderNo = merOrderNo;
    }

    public String getOrderAmt() {
        return OrderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        OrderAmt = orderAmt;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getBusiType() {
        return BusiType;
    }

    public void setBusiType(String busiType) {
        BusiType = busiType;
    }
}
