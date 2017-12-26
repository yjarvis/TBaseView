package com.jarvis.tbaseview.constants;

/**
 * 银联支付参数
 * Created by tansheng on 2017/8/10.
 */
public class CpayData {
    private String sendUrl;
    private CpayParam params;

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public CpayParam getParams() {
        return params;
    }

    public void setParams(CpayParam params) {
        this.params = params;
    }
}
