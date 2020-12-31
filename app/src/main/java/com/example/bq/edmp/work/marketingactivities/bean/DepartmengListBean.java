package com.example.bq.edmp.work.marketingactivities.bean;

import java.io.Serializable;
import java.util.List;

public class DepartmengListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":22,"owner":null,"parentId":null,"fullId":null,"fullName":null,"types":null,"name":"技术部","managerId":null,"bossId":null,"status":null},{"id":23,"owner":null,"parentId":null,"fullId":null,"fullName":null,"types":null,"name":"运营部","managerId":null,"bossId":null,"status":null},{"id":24,"owner":null,"parentId":null,"fullId":null,"fullName":null,"types":null,"name":"财务部","managerId":null,"bossId":null,"status":null}]
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
         * id : 22
         * owner : null
         * parentId : null
         * fullId : null
         * fullName : null
         * types : null
         * name : 技术部
         * managerId : null
         * bossId : null
         * status : null
         */

        private int id;
        private Object owner;
        private Object parentId;
        private Object fullId;
        private Object fullName;
        private Object types;
        private String name;
        private Object managerId;
        private Object bossId;
        private Object status;

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

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getFullId() {
            return fullId;
        }

        public void setFullId(Object fullId) {
            this.fullId = fullId;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getManagerId() {
            return managerId;
        }

        public void setManagerId(Object managerId) {
            this.managerId = managerId;
        }

        public Object getBossId() {
            return bossId;
        }

        public void setBossId(Object bossId) {
            this.bossId = bossId;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
