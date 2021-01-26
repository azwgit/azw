package com.example.bq.edmp.work.goodsgrainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class SelecGoodsListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":3,"owner":1,"categoryId":3,"categoryFullId":"3","varietyId":6,"cropId":1,"name":"66666","remark":"2","status":1,"addedTime":"2021-01-25","lastUpdateTime":null,"categoryName":null,"varietyName":null}]
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
         * id : 3
         * owner : 1
         * categoryId : 3
         * categoryFullId : 3
         * varietyId : 6
         * cropId : 1
         * name : 66666
         * remark : 2
         * status : 1
         * addedTime : 2021-01-25
         * lastUpdateTime : null
         * categoryName : null
         * varietyName : null
         */

        private int id;
        private int owner;
        private int categoryId;
        private String categoryFullId;
        private int varietyId;
        private int cropId;
        private String name;
        private String remark;
        private int status;
        private String addedTime;
        private Object lastUpdateTime;
        private Object categoryName;
        private Object varietyName;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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
    }
}
