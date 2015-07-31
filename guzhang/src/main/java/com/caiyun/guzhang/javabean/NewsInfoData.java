package com.caiyun.guzhang.javabean;

/**
 * Created by Administrator on 2015/7/16. 内参正文数据
 */

//"id": "69642",
//        "newstext": "\n\t\t\t\t\t\t<p>　　中国证券网讯 沪指企稳回升，指数翻红，重回3800点位。创业板指上涨2%，指数大幅震荡。截至10点32分，沪指3820.49报点，涨0.39%；深指报12381.04点，涨2.05%。创业板指报2641.85点，涨2.00%。</p><p>　　板块上看，民航机场、海工装备、军工、食品安全、通用航空等板块涨幅居前，石油、保险、银行、中字头等板块跌幅领先。</p>",
//        "onclick": "2",
//        "title": "沪指翻红重回3800点 创业板指大幅震荡涨2%",
//        "classname": "上证快讯",
//        "newstime": "7分钟前 10:36",
//        "classid": "5"
public class NewsInfoData {
    private String id;
    private String newstext;
    private int onclick;
    private String title;
    private String classname;
    private String newstime;
    private int classid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewstext() {
        return newstext;
    }

    public void setNewstext(String newstext) {
        this.newstext = newstext;
    }

    public int getOnclick() {
        return onclick;
    }

    public void setOnclick(int onclick) {
        this.onclick = onclick;
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

    public String getNewstime() {
        return newstime;
    }

    public void setNewstime(String newstime) {
        this.newstime = newstime;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }
}
