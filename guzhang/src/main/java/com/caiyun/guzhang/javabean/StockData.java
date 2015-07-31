package com.caiyun.guzhang.javabean;

/**
 * Created by Administrator on 2015/7/16.  股票数据
 */

//"code":"","name":null,"price":null,"rise":null
public class StockData {
    private int id;
    private String code;
    private String name;
    private double price;
    private double rise;
    private int islongtou;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIslongtou() {
        return islongtou;
    }

    public void setIslongtou(int islongtou) {
        this.islongtou = islongtou;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRise() {
        return rise;
    }

    public void setRise(double rise) {
        this.rise = rise;
    }
}
