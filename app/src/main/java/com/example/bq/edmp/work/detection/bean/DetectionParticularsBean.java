package com.example.bq.edmp.work.detection.bean;

import java.util.List;

public class DetectionParticularsBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"orgId":2,"code":"","testPlanId":1,"varietyId":1,"farmLandId":1,"wareshouseId":1,"tester":16,"addedTime":"2020-12-16","remark":null,"results":1,"testPlanItemId":null,"testingItemValue":null,"startTime":null,"endTime":null,"farmlandcode":null,"orgName":"北京分公司","varietyName":null,"farmLandCode":"1","wareshouseName":"大豆仓库","testPlanType":null,"testPlanName":"一期种植","testPlanItem":[{"id":null,"testPlanId":null,"name":"小麦水分","unit":"%","lowerLimit":6,"upperLimit":13,"valueType":1,"value":"16","results":-1},{"id":null,"testPlanId":null,"name":"小麦杂质","unit":"gl","lowerLimit":4,"upperLimit":16,"valueType":1,"value":"4","results":1}],"testerName":"李四"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * owner : 1
         * orgId : 2
         * code :
         * testPlanId : 1
         * varietyId : 1
         * farmLandId : 1
         * wareshouseId : 1
         * tester : 16
         * addedTime : 2020-12-16
         * remark : null
         * results : 1
         * testPlanItemId : null
         * testingItemValue : null
         * startTime : null
         * endTime : null
         * farmlandcode : null
         * orgName : 北京分公司
         * varietyName : null
         * farmLandCode : 1
         * wareshouseName : 大豆仓库
         * testPlanType : null
         * testPlanName : 一期种植
         * testPlanItem : [{"id":null,"testPlanId":null,"name":"小麦水分","unit":"%","lowerLimit":6,"upperLimit":13,"valueType":1,"value":"16","results":-1},{"id":null,"testPlanId":null,"name":"小麦杂质","unit":"gl","lowerLimit":4,"upperLimit":16,"valueType":1,"value":"4","results":1}]
         * testerName : 李四
         */

        private String id;
        private String owner;
        private String orgId;
        private String code;
        private String testPlanId;
        private String varietyId;
        private String farmLandId;
        private String wareshouseId;
        private String tester;
        private String addedTime;
        private String remark;
        private String results;
        private String testPlanItemId;
        private String testingItemValue;
        private String startTime;
        private String endTime;
        private String orgName;
        private String varietyName;
        private String farmLandCode;
        private String wareshouseName;
        private String testPlanType;
        private String testPlanName;
        private String testerName;
        private List<TestPlanItemBean> testPlanItem;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTestPlanId() {
            return testPlanId;
        }

        public void setTestPlanId(String testPlanId) {
            this.testPlanId = testPlanId;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getFarmLandId() {
            return farmLandId;
        }

        public void setFarmLandId(String farmLandId) {
            this.farmLandId = farmLandId;
        }

        public String getWareshouseId() {
            return wareshouseId;
        }

        public void setWareshouseId(String wareshouseId) {
            this.wareshouseId = wareshouseId;
        }

        public String getTester() {
            return tester;
        }

        public void setTester(String tester) {
            this.tester = tester;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getResults() {
            return results;
        }

        public void setResults(String results) {
            this.results = results;
        }

        public String getTestPlanItemId() {
            return testPlanItemId;
        }

        public void setTestPlanItemId(String testPlanItemId) {
            this.testPlanItemId = testPlanItemId;
        }

        public String getTestingItemValue() {
            return testingItemValue;
        }

        public void setTestingItemValue(String testingItemValue) {
            this.testingItemValue = testingItemValue;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public String getFarmLandCode() {
            return farmLandCode;
        }

        public void setFarmLandCode(String farmLandCode) {
            this.farmLandCode = farmLandCode;
        }

        public String getWareshouseName() {
            return wareshouseName;
        }

        public void setWareshouseName(String wareshouseName) {
            this.wareshouseName = wareshouseName;
        }

        public String getTestPlanType() {
            return testPlanType;
        }

        public void setTestPlanType(String testPlanType) {
            this.testPlanType = testPlanType;
        }

        public String getTestPlanName() {
            return testPlanName;
        }

        public void setTestPlanName(String testPlanName) {
            this.testPlanName = testPlanName;
        }

        public String getTesterName() {
            return testerName;
        }

        public void setTesterName(String testerName) {
            this.testerName = testerName;
        }

        public List<TestPlanItemBean> getTestPlanItem() {
            return testPlanItem;
        }

        public void setTestPlanItem(List<TestPlanItemBean> testPlanItem) {
            this.testPlanItem = testPlanItem;
        }

        public static class TestPlanItemBean {
            /**
             * id : null
             * testPlanId : null
             * name : 小麦水分
             * unit : %
             * lowerLimit : 6.0
             * upperLimit : 13.0
             * valueType : 1
             * value : 16
             * results : -1
             */

            private String id;
            private String testPlanId;
            private String name;
            private String unit;
            private String lowerLimit;
            private String upperLimit;
            private String valueType;
            private String value;
            private String results;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getResults() {
                return results;
            }

            public void setResults(String results) {
                this.results = results;
            }
        }
    }
}
