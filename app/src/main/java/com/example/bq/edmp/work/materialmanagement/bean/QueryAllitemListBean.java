package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class QueryAllitemListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":80,"owner":1,"categoryId":15,"categoryFullId":"4","varietyId":0,"cropId":0,"name":"2323","remark":null,"status":1,"addedTime":"2021-02-19","lastUpdateTime":null,"price":0,"categoryName":null,"varietyName":null,"customerPrice":null}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 80
         * owner : 1
         * categoryId : 15
         * categoryFullId : 4
         * varietyId : 0
         * cropId : 0
         * name : 2323
         * remark : null
         * status : 1
         * addedTime : 2021-02-19
         * lastUpdateTime : null
         * price : 0.0
         * categoryName : null
         * varietyName : null
         * customerPrice : null
         */

        private int id;
        private int owner;
        private int categoryId;
        private String categoryFullId;
        private int varietyId;
        private int cropId;
        private String name;
        private Object remark;
        private int status;
        private String addedTime;
        private Object lastUpdateTime;
        private double price;
        private Object categoryName;
        private Object varietyName;
        private Object customerPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryFullId() {
            return categoryFullId;
        }

        public void setCategoryFullId(String categoryFullId) {
            this.categoryFullId = categoryFullId;
        }

        public int getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(int varietyId) {
            this.varietyId = varietyId;
        }

        public int getCropId() {
            return cropId;
        }

        public void setCropId(int cropId) {
            this.cropId = cropId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public Object getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Object lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(Object categoryName) {
            this.categoryName = categoryName;
        }

        public Object getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(Object varietyName) {
            this.varietyName = varietyName;
        }

        public Object getCustomerPrice() {
            return customerPrice;
        }

        public void setCustomerPrice(Object customerPrice) {
            this.customerPrice = customerPrice;
        }
    }
}
