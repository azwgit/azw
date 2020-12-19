package com.example.bq.edmp.work.detection.bean;

import java.util.List;

public class DetectionCkBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":1,"orgId":2,"name":"大豆仓库","types":1,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":4,"owner":1,"orgId":2,"name":"玉米仓库","types":1,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":5,"owner":1,"orgId":2,"name":"辣椒仓库","types":2,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":6,"owner":1,"orgId":2,"name":"大麦仓库","types":2,"wardenId":1,"orgIds":null,"orgName":null,"wardenName":null},{"id":7,"owner":1,"orgId":2,"name":"高粱仓库","types":2,"wardenId":1,"orgIds":null,"orgName":null,"wardenName":null},{"id":8,"owner":1,"orgId":2,"name":"萝卜仓库","types":2,"wardenId":1,"orgIds":null,"orgName":null,"wardenName":null},{"id":10,"owner":1,"orgId":2,"name":"尖椒仓库","types":3,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null},{"id":11,"owner":1,"orgId":2,"name":"白萝卜仓库","types":1,"wardenId":null,"orgIds":null,"orgName":null,"wardenName":null}]
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
         * owner : 1
         * orgId : 2
         * name : 大豆仓库
         * types : 1
         * wardenId : null
         * orgIds : null
         * orgName : null
         * wardenName : null
         */

        private String id;
        private String owner;
        private String orgId;
        private String name;
        private String types;
        private String wardenId;
        private String orgIds;
        private String orgName;
        private String wardenName;

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

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
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

        public String getWardenId() {
            return wardenId;
        }

        public void setWardenId(String wardenId) {
            this.wardenId = wardenId;
        }

        public String getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(String orgIds) {
            this.orgIds = orgIds;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getWardenName() {
            return wardenName;
        }

        public void setWardenName(String wardenName) {
            this.wardenName = wardenName;
        }
    }
}
