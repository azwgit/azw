package com.example.bq.edmp.work.inventorytransfer.bean;

import java.io.Serializable;
import java.util.List;

public class WareHouseListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":5,"owner":null,"orgId":null,"name":"1号辣椒仓库","types":null,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":6,"owner":null,"orgId":null,"name":"2号辣椒仓库","types":null,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":7,"owner":null,"orgId":null,"name":"3号辣椒仓库33","types":null,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":8,"owner":null,"orgId":null,"name":"1号仓库","types":null,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null}]
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
         * id : 5
         * owner : null
         * orgId : null
         * name : 1号辣椒仓库
         * types : null
         * wardenId : null
         * orgIds : null
         * orgName : null
         * wardenName : null
         */

        private int id;
        private Object owner;
        private Object orgId;
        private String name;
        private Object types;
        private Object wardenId;
        private Object orgIds;
        private Object orgName;
        private Object wardenName;

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

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
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

        public Object getWardenId() {
            return wardenId;
        }

        public void setWardenId(Object wardenId) {
            this.wardenId = wardenId;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public Object getWardenName() {
            return wardenName;
        }

        public void setWardenName(Object wardenName) {
            this.wardenName = wardenName;
        }
    }
}
