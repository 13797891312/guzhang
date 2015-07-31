package com.caiyun.guzhang.javabean;

/**
 * Created by Administrator on 2015/7/15.
 */

//"rise": "-2.40",
//        "zd": "-94.00",
//        "price": "3830.49",
//        "name": "上证指数"
public class DapanData {
    private double rise;
    private double zd;
    private double price;
    private String name;

    public double getRise() {
        return rise;
    }

    public void setRise(double rise) {
        this.rise = rise;
    }

    public double getZd() {
        return zd;
    }

    public void setZd(double zd) {
        this.zd = zd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
