package com.example.bq.edmp.work.marketing.bean;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class CityModel implements IPickerViewData {

    private String id;
    private String parentId;
    private String name;
    private String fullName;
    private int status;
    private List<CityModel> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CityModel> getChildren() {
        return children;
    }

    public void setChildren(List<CityModel> children) {
        this.children = children;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
