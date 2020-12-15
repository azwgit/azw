package com.example.bq.edmp.work.baozhuang;

import java.util.List;

public class BzBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"varietyId":1,"owner":1,"name":"100公斤/袋","units":"公斤","unitWeight":3000,"status":1,"varietys":null,"varietyPackagingName":"一号小麦  100公斤/袋"},{"id":2,"varietyId":1,"owner":1,"name":"200公斤/袋","units":"公斤","unitWeight":1000,"status":1,"varietys":null,"varietyPackagingName":"一号小麦  200公斤/袋"},{"id":3,"varietyId":1,"owner":1,"name":"160公斤/袋","units":"公斤","unitWeight":1300,"status":1,"varietys":null,"varietyPackagingName":"一号小麦  160公斤/袋"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * varietys : null
         * varietyPackagingName : 一号小麦  100公斤/袋
         */

        private String id;
        private String varietyId;
        private String owner;
        private String name;
        private String units;
        private String unitWeight;
        private String status;
        private String varietys;
        private String varietyPackagingName;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
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

        public String getUnitWeight() {
            return unitWeight;
        }

        public void setUnitWeight(String unitWeight) {
            this.unitWeight = unitWeight;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVarietys() {
            return varietys;
        }

        public void setVarietys(String varietys) {
            this.varietys = varietys;
        }

        public String getVarietyPackagingName() {
            return varietyPackagingName;
        }

        public void setVarietyPackagingName(String varietyPackagingName) {
            this.varietyPackagingName = varietyPackagingName;
        }
    }
}
