package com.example.bq.edmp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayInfoBean implements Serializable {
    private String id;
    private String desc;
    private String money;
    private String idx;
    private int clickType;
    private int position;
    private String arriveRegion;
    private String arriveTime;
    private String days;
    private String setOutRegion;
    private String setOutTime;
    private String subsidy;
    private String transport;
    private String transportFee;

    public String getArriveRegion() {
        return arriveRegion;
    }

    public void setArriveRegion(String arriveRegion) {
        this.arriveRegion = arriveRegion;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getSetOutRegion() {
        return setOutRegion;
    }

    public void setSetOutRegion(String setOutRegion) {
        this.setOutRegion = setOutRegion;
    }

    public String getSetOutTime() {
        return setOutTime;
    }

    public void setSetOutTime(String setOutTime) {
        this.setOutTime = setOutTime;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(String transportFee) {
        this.transportFee = transportFee;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    private List<LocalNewMedia> img_list;
    private List<LocalMedia> picList;

    public List<LocalNewMedia> getImg_list() {
        return img_list;
    }

    public void setImg_list(List<LocalNewMedia> img_list) {
        this.img_list = img_list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<LocalMedia> getPicList() {
        return picList;
    }

    public void setPicList(List<LocalMedia> picList) {
        this.picList = picList;
    }
}
