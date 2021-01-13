package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class PackagingListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"varietyId":1,"owner":1,"name":"100公斤/袋","units":"公斤","unitWeight":3000,"status":1,"price":1},{"id":2,"varietyId":1,"owner":1,"name":"200公斤/袋","units":"公斤","unitWeight":1000,"status":1,"price":1},{"id":3,"varietyId":2,"owner":1,"name":"160公斤/袋","units":"公斤","unitWeight":1300,"status":1,"price":1},{"id":6,"varietyId":3,"owner":1,"name":"300公斤/袋","units":"","unitWeight":300,"status":1,"price":1}]
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
         * varietyId : 1
         * owner : 1
         * name : 100公斤/袋
         * units : 公斤
         * unitWeight : 3000
         * status : 1
         * price : 1.0
         */

        private int id;
        private int varietyId;
        private int owner;
        private String name;
        private String units;
        private int unitWeight;
        private int status;
        private double price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(int varietyId) {
            this.varietyId = varietyId;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public int getUnitWeight() {
            return unitWeight;
        }

        public void setUnitWeight(int unitWeight) {
            this.unitWeight = unitWeight;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
