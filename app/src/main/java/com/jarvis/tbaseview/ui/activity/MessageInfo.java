package com.jarvis.tbaseview.ui.activity;

/**
 * Created by tansheng on 2017/8/24.
 */

public class MessageInfo {
    private String id;//id
    private String phone;//电话
    private String name;//姓名
    private String body;//内容
    private String date;//日期
    private String type;//收发状态：1表示接受，2表示发送
    private String read;//读取状态：1表示已读，0表示未读


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRead() {
        return read;
    }
    //是否已读，true已读，false未读
    public boolean isRead(){
        return read.equals("0")?false:true;
    }

    public void setRead(String read) {
        this.read = read;
    }
}
