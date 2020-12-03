package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;

public class TraeBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":41,"owner":1,"orgId":2,"code":"SG2020123111022","farmId":2,"farmerId":2,"varietyId":2,"warehouseId":3,"addedTime":"2020-12-03","addedOperator":"李四","grossWeight":5000,"tareWeight":0,"netWeight":100,"stockAddId":17,"testingId":null,"beginTime":null,"endTime":null,"farmName":null,"farmerName":"李四","orgName":null,"varietyName":"二号小麦","warehouseName":"3号仓库","orgIds":null,"status":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null,"messages":null}
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
         * id : 41
         * owner : 1
         * orgId : 2
         * code : SG2020123111022
         * farmId : 2
         * farmerId : 2
         * varietyId : 2
         * warehouseId : 3
         * addedTime : 2020-12-03
         * addedOperator : 李四
         * grossWeight : 5000.0
         * tareWeight : 0.0
         * netWeight : 100.0
         * stockAddId : 17
         * testingId : null
         * beginTime : null
         * endTime : null
         * farmName : null
         * farmerName : 李四
         * orgName : null
         * varietyName : 二号小麦
         * warehouseName : 3号仓库
         * orgIds : null
         * status : null
         * testingItems : null
         * testPlanItemId : null
         * testingItemValue : null
         * messages : null
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
        private String addedOperator;
        private double grossWeight;
        private double tareWeight;
        private double netWeight;
        private int stockAddId;
        private Object testingId;
        private Object beginTime;
        private Object endTime;
        private Object farmName;
        private String farmerName;
        private Object orgName;
        private String varietyName;
        private String warehouseName;
        private Object orgIds;
        private Object status;
        private Object testingItems;
        private Object testPlanItemId;
        private Object testingItemValue;
        private Object messages;

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

        public String getAddedOperator() {
            return addedOperator;
        }

        public void setAddedOperator(String addedOperator) {
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

        public int getStockAddId() {
            return stockAddId;
        }

        public void setStockAddId(int stockAddId) {
            this.stockAddId = stockAddId;
        }

        public Object getTestingId() {
            return testingId;
        }

        public void setTestingId(Object testingId) {
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

        public Object getFarmName() {
            return farmName;
        }

        public void setFarmName(Object farmName) {
            this.farmName = farmName;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(Object testingItems) {
            this.testingItems = testingItems;
        }

        public Object getTestPlanItemId() {
            return testPlanItemId;
        }

        public void setTestPlanItemId(Object testPlanItemId) {
            this.testPlanItemId = testPlanItemId;
        }

        public Object getTestingItemValue() {
            return testingItemValue;
        }

        public void setTestingItemValue(Object testingItemValue) {
            this.testingItemValue = testingItemValue;
        }

        public Object getMessages() {
            return messages;
        }

        public void setMessages(Object messages) {
            this.messages = messages;
        }
    }
}
