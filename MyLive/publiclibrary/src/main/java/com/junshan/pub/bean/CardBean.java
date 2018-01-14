package com.junshan.pub.bean;

/**
 * Created by chenjunshan on 12/29/17.
 */

public class CardBean {
    private String text;

    public CardBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
