package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class VarietiesListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":1,"cropId":1,"name":"一号小麦","types":null,"yield":0,"remark":null,"status":1}]
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
         * owner : 1
         * cropId : 1
         * name : 一号小麦
         * types : null
         * yield : 0.0
         * remark : null
         * status : 1
         */

        private int id;
        private int owner;
        private int cropId;
        private String name;
        private Object types;
        private double yield;
        private Object remark;
        private int status;

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

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public double getYield() {
            return yield;
        }

        public void setYield(double yield) {
            this.yield = yield;
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
    }
}
