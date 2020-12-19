package com.example.bq.edmp.work.detection.bean;

import java.util.List;

public class DetectionTestingBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":2,"owner":1,"cropId":2,"types":null,"name":null,"remark":null,"testPlanItems":[{"id":1,"testPlanId":2,"name":"水分","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1},{"id":2,"testPlanId":2,"name":"杂质","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":2},{"id":3,"testPlanId":2,"name":"颗粒饱满","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1}]}]
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
         * testPlanId : 2
         * name : 水分
         * unit : null
         * lowerLimit : null
         * upperLimit : null
         * valueType : 1
         */

        private String id;
        private String testPlanId;
        private String name;
        private String unit;
        private String lowerLimit;
        private String upperLimit;
        private String valueType;
        private String content;

        public String getContent() {
            if(content==null){
                return  "";
            }
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTestPlanId() {
            return testPlanId;
        }

        public void setTestPlanId(String testPlanId) {
            this.testPlanId = testPlanId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getLowerLimit() {
            return lowerLimit;
        }

        public void setLowerLimit(String lowerLimit) {
            this.lowerLimit = lowerLimit;
        }

        public String getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(String upperLimit) {
            this.upperLimit = upperLimit;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }
    }
}
