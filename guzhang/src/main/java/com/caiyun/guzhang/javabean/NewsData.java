package com.caiyun.guzhang.javabean;

/**
 * Created by Administrator on 2015/7/15.内参数据
 * "id": "69535",
 "text": "\n\t\t\t\t\t\t　　投保基金最新数据显示，上周（7月6日至10日）证券保证金净流出1757亿元。上周证券交易结算资金银证转账增加额为1.47万亿，减少额为1.64……",
 "time": "1436949207",
 "title": "上周保证金净流出1757亿元",
 "classname": "上证快讯"
 */
public class NewsData {
    private String id;
    private String text;
    private  String time;
    private  String title;
    private String classname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
