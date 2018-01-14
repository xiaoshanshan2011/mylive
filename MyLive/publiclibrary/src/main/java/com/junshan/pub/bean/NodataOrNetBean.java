package com.junshan.pub.bean;

/**
 * 没有数据或者没有网络
 * Created by chenjunshan on 17-4-17.
 */

public class NodataOrNetBean {
    private String title;
    private String content;
    private int img;
    private int type;//1、没网络，2、没数据
    public static final int NODATA = 1;
    public static final int NONET = 2;

    public NodataOrNetBean(String title, String content, int img, int type) {
        this.title = title;
        this.content = content;
        this.img = img;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
