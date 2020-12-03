package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class AcquisitionBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"orgId":2,"code":"144332","farmId":1,"farmerId":1,"varietyId":1,"warehouseId":1,"addedTime":"2020-11-27","addedOperator":null,"grossWeight":10,"tareWeight":1,"netWeight":0,"stockAddId":null,"testingId":1,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":"1号仓库","orgIds":null,"status":2,"testingItems":[{"id":null,"value":">10","results":1,"name":"水分"},{"id":null,"value":">2","results":2,"name":"杂质"},{"id":null,"value":">1","results":1,"name":"颗粒饱满"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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
         * code : 144332
         * farmId : 1
         * farmerId : 1
         * varietyId : 1
         * warehouseId : 1
         * addedTime : 2020-11-27
         * addedOperator : null
         * grossWeight : 10.0
         * tareWeight : 1.0
         * netWeight : 0.0
         * stockAddId : null
         * testingId : 1
         * beginTime : null
         * endTime : null
         * farmName : 1号农场
         * farmerName : 张三
         * orgName : 北京分公司
         * varietyName : 一号小麦
         * warehouseName : 1号仓库
         * orgIds : null
         * status : 2
         * testingItems : [{"id":null,"value":">10","results":1,"name":"水分"},{"id":null,"value":">2","results":2,"name":"杂质"},{"id":null,"value":">1","results":1,"name":"颗粒饱满"}]
         */

        private int id;
        private int owner;
        private int orgId;
        private String code;
        private int farmId;
        private int farmerId;
        private int varietyId;
        private int warehouseId;
        private String addedTime;
        private Object addedOperator;
        private double grossWeight;
        private double tareWeight;
        private double netWeight;
        private Object stockAddId;
        private int testingId;
        private Object beginTime;
        private Object endTime;
        private String farmName;
        private String farmerName;
        private String orgName;
        private String varietyName;
        private String warehouseName;
        private Object orgIds;
        private int status;
        private List<TestingItemsBean> testingItems;

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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getFarmId() {
            return farmId;
        }

        public void setFarmId(int farmId) {
            this.farmId = farmId;
        }

        public int getFarmerId() {
            return farmerId;
        }

        public void setFarmerId(int farmerId) {
            this.farmerId = farmerId;
        }

        public int getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(int varietyId) {
            this.varietyId = varietyId;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public Object getAddedOperator() {
            return addedOperator;
        }

        public void setAddedOperator(Object addedOperator) {
            this.addedOperator = addedOperator;
        }

        public double getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(double grossWeight) {
            this.grossWeight = grossWeight;
        }

        public double getTareWeight() {
            return tareWeight;
        }

        public void setTareWeight(double tareWeight) {
            this.tareWeight = tareWeight;
        }

        public double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(double netWeight) {
            this.netWeight = netWeight;
        }

        public Object getStockAddId() {
            return stockAddId;
        }

        public void setStockAddId(Object stockAddId) {
            this.stockAddId = stockAddId;
        }

        public int getTestingId() {
            return testingId;
        }

        public void setTestingId(int testingId) {
            this.testingId = testingId;
        }

        public Object getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Object beginTime) {
            this.beginTime = beginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getFarmName() {
            return farmName;
        }

        public void setFarmName(String farmName) {
            this.farmName = farmName;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
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

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<TestingItemsBean> getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(List<TestingItemsBean> testingItems) {
            this.testingItems = testingItems;
        }

        public static class TestingItemsBean {
            /**
             * id : null
             * value : >10
             * results : 1
             * name : 水分
             */

            private Object id;
            private String value;
            private int results;
            private String name;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getResults() {
                return results;
            }

            public void setResults(int results) {
                this.results = results;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
