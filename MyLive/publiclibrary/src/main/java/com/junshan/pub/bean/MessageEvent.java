package com.junshan.pub.bean;

/**
 * Created by chenjunshan on 17-4-22.
 */

public class MessageEvent {
    private int type;//消息类型
    private String msg;//消息内容
    private Object bean;//消息内容


    public MessageEvent(int type) {
        this.type = type;
    }

    public MessageEvent(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public MessageEvent(int type, Object bean) {
        this.type = type;
        this.bean = bean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
