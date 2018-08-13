package com.demo.liuhf.mybaselibrarys.view;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/13.
 * 描述：
 * 修改历史：
 */

public class UpdataBean {
    private String date;
    private String size;
    private String vodeNum;
    private String conter;
    private String img;
    private String Url;
    private boolean isupdata=false;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVodeNum() {
        return vodeNum;
    }

    public void setVodeNum(String vodeNum) {
        this.vodeNum = vodeNum;
    }

    public String getConter() {
        return conter;
    }

    public void setConter(String conter) {
        this.conter = conter;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIsupdata() {
        return isupdata;
    }

    public void setIsupdata(boolean isupdata) {

        this.isupdata = isupdata;
    }
}
