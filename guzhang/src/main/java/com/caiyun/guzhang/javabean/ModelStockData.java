package com.caiyun.guzhang.javabean;


import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2015/7/29.数据库股票模型
 */

public class ModelStockData extends DataSupport {
    private long seachTime;
    private int id;
    private String name;
    private String code;
    private int isZixuan;
    private String singleName;
    private int isHistory;

    public long getSeachTime() {
        return seachTime;
    }

    public void setSeachTime(long seachTime) {
        this.seachTime = seachTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = singleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIsZixuan() {
        return isZixuan;
    }

    public void setIsZixuan(int isZixuan) {
        this.isZixuan = isZixuan;
    }

    public int getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(int isHistory) {
        this.isHistory = isHistory;
    }
}
