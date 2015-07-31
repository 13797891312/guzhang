package com.caiyun.guzhang.javabean;

/**
 * Created by Administrator on 2015/7/15.热门题材数据
 */

//"id": "1186",
//        "longtou": "002024",
//        "title": "消费金融",
//        "price": "14.83",
//        "longtouname": "苏宁云商",
//        "coderise": "4.44"
public class HotData {
    private String id;
    private String longtou;
    private String title;
    private float price;
    private float coderise;
    private String longtouname;
    private boolean isLoading;
    private double hotrise=200;

    public double getHotrise() {
        return hotrise;
    }

    public void setHotrise(double hotrise) {
        this.hotrise = hotrise;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public String getLongtouname() {
        return longtouname;
    }

    public void setLongtouname(String longtouname) {
        this.longtouname = longtouname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongtou() {
        return longtou;
    }

    public void setLongtou(String longtou) {
        this.longtou = longtou;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCoderise() {
        return coderise;
    }

    public void setCoderise(float coderise) {
        this.coderise = coderise;
    }
}
