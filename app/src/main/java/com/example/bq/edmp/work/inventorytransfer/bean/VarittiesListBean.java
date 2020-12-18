package com.example.bq.edmp.work.inventorytransfer.bean;

import java.io.Serializable;
import java.util.List;

public class VarittiesListBean implements Serializable {

    /**
     * code : 200
     * msg : 品种查询成功
     * data : [{"id":1,"owner":null,"cropId":null,"name":"一号小麦","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":2,"owner":null,"cropId":null,"name":"二号小麦","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":3,"owner":null,"cropId":null,"name":"一号玉米","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":4,"owner":null,"cropId":null,"name":"一号辣椒","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":5,"owner":null,"cropId":null,"name":"测试辣椒2","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":6,"owner":null,"cropId":null,"name":"测试小麦333","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":7,"owner":null,"cropId":null,"name":"测试品种1","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":8,"owner":null,"cropId":null,"name":"测试品种2","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":9,"owner":null,"cropId":null,"name":"小麦","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":10,"owner":null,"cropId":null,"name":"测试品种","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":11,"owner":null,"cropId":null,"name":"测试品种","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null},{"id":12,"owner":null,"cropId":null,"name":"测试品种1","types":null,"yield":null,"remark":null,"status":null,"cropName":null,"features":null,"featuresall":null}]
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
         * id : 1
         * owner : null
         * cropId : null
         * name : 一号小麦
         * types : null
         * yield : null
         * remark : null
         * status : null
         * cropName : null
         * features : null
         * featuresall : null
         */

        private int id;
        private Object owner;
        private Object cropId;
        private String name;
        private Object types;
        private Object yield;
        private Object remark;
        private Object status;
        private Object cropName;
        private Object features;
        private Object featuresall;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public Object getCropId() {
            return cropId;
        }

        public void setCropId(Object cropId) {
            this.cropId = cropId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public Object getYield() {
            return yield;
        }

        public void setYield(Object yield) {
            this.yield = yield;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getCropName() {
            return cropName;
        }

        public void setCropName(Object cropName) {
            this.cropName = cropName;
        }

        public Object getFeatures() {
            return features;
        }

        public void setFeatures(Object features) {
            this.features = features;
        }

        public Object getFeaturesall() {
            return featuresall;
        }

        public void setFeaturesall(Object featuresall) {
            this.featuresall = featuresall;
        }
    }
}
