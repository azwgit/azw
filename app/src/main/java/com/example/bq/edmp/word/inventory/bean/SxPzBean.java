package com.example.bq.edmp.word.inventory.bean;

import java.util.List;

public class SxPzBean {

    /**
     * code : 200
     * msg : 品种查询成功
     * data : [{"id":1,"owner":null,"cropId":null,"name":"一号小麦","types":null,"yield":null,"remark":null,"status":null},{"id":2,"owner":null,"cropId":null,"name":"二号小麦","types":null,"yield":null,"remark":null,"status":null},{"id":3,"owner":null,"cropId":null,"name":"一号玉米","types":null,"yield":null,"remark":null,"status":null},{"id":4,"owner":null,"cropId":null,"name":"一号辣椒","types":null,"yield":null,"remark":null,"status":null}]
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
         */

        private String id;
        private String owner;
        private String cropId;
        private String name;
        private String types;
        private String yield;
        private String remark;
        private String status;

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

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getCropId() {
            return cropId;
        }

        public void setCropId(String cropId) {
            this.cropId = cropId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getYield() {
            return yield;
        }

        public void setYield(String yield) {
            this.yield = yield;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
