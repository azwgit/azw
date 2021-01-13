package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":1,"categoryId":3,"categoryFullId":"3","name":"test1","remark":"1","status":1,"addedTime":null,"lastUpdateTime":null},{"id":2,"owner":1,"categoryId":3,"categoryFullId":"3","name":"test2","remark":"1","status":1,"addedTime":null,"lastUpdateTime":null}]
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
         * categoryId : 3
         * categoryFullId : 3
         * name : test1
         * remark : 1
         * status : 1
         * addedTime : null
         * lastUpdateTime : null
         */

        private int id;
        private int owner;
        private int categoryId;
        private String categoryFullId;
        private String name;
        private String remark;
        private int status;
        private Object addedTime;
        private Object lastUpdateTime;

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

        public Object getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(Object addedTime) {
            this.addedTime = addedTime;
        }

        public Object getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Object lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}

