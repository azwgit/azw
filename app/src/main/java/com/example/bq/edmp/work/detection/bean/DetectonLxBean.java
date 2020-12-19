package com.example.bq.edmp.work.detection.bean;

import java.util.List;

public class DetectonLxBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":null,"cropId":null,"types":1,"name":null,"remark":null,"testPlanItems":[],"testName":"小麦一期种植"},{"id":2,"owner":null,"cropId":null,"types":1,"name":null,"remark":null,"testPlanItems":[],"testName":"玉米二期种植"},{"id":3,"owner":null,"cropId":null,"types":2,"name":null,"remark":null,"testPlanItems":[],"testName":"辣椒三期种植"}]
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
         * owner : null
         * cropId : null
         * types : 1
         * name : null
         * remark : null
         * testPlanItems : []
         * testName : 小麦一期种植
         */

        private String id;
        private String owner;
        private String cropId;
        private String types;
        private String name;
        private String remark;
        private String testName;


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

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
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

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

    }
}
