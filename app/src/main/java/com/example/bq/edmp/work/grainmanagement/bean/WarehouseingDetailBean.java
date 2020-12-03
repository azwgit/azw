package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class WarehouseingDetailBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"code":"143","warehouseId":1,"type1":1,"type2":3,"businessFormId":null,"addedTime":"2020-12-02","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":13,"testingItems":null,"stockAllots":{"id":null,"code":"1","types":null,"outWarehouse":null,"inWarehouse":null,"addedOperatorId":null,"addedOperator":null,"addedTime":null,"status":null,"reason":"hajk","approvedTime":null,"stockAddId":null,"outConfirmOperator":null,"outContirmTime":null,"stockSubId":null,"inConfirmOperator":null,"inContirmTime":null,"warehouseName":"2号仓库"}}
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
         * code : 143
         * warehouseId : 1
         * type1 : 1
         * type2 : 3
         * businessFormId : null
         * addedTime : 2020-12-02
         * addedOperator : null
         * finishedTime : null
         * finishedOperator : null
         * remark : null
         * orgId : null
         * orgIds : null
         * beginTime : null
         * endTime : null
         * varietyId : null
         * varietyName : 一号小麦
         * warehouseName : 1号仓库
         * orgName : 北京分公司
         * addQty : 13.0
         * testingItems : null
         * stockAllots : {"id":null,"code":"1","types":null,"outWarehouse":null,"inWarehouse":null,"addedOperatorId":null,"addedOperator":null,"addedTime":null,"status":null,"reason":"hajk","approvedTime":null,"stockAddId":null,"outConfirmOperator":null,"outContirmTime":null,"stockSubId":null,"inConfirmOperator":null,"inContirmTime":null,"warehouseName":"2号仓库"}
         */

        private int id;
        private String code;
        private int warehouseId;
        private int type1;
        private int type2;
        private Object businessFormId;
        private String addedTime;
        private Object addedOperator;
        private Object finishedTime;
        private Object finishedOperator;
        private Object remark;
        private Object orgId;
        private Object orgIds;
        private Object beginTime;
        private Object endTime;
        private Object varietyId;
        private String varietyName;
        private String warehouseName;
        private String orgName;
        private double addQty;
        private List<TestingItemsBean> testingItems;
        private StockAllotsBean stockAllots;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getType1() {
            return type1;
        }

        public void setType1(int type1) {
            this.type1 = type1;
        }

        public int getType2() {
            return type2;
        }

        public void setType2(int type2) {
            this.type2 = type2;
        }

        public Object getBusinessFormId() {
            return businessFormId;
        }

        public void setBusinessFormId(Object businessFormId) {
            this.businessFormId = businessFormId;
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

        public Object getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(Object finishedTime) {
            this.finishedTime = finishedTime;
        }

        public Object getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(Object finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
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

        public Object getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(Object varietyId) {
            this.varietyId = varietyId;
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

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public double getAddQty() {
            return addQty;
        }

        public void setAddQty(double addQty) {
            this.addQty = addQty;
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
             * value : 0
             * results : null
             * name : 水分
             */

            private Object id;
            private String value;
            private Object results;
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

            public Object getResults() {
                return results;
            }

            public void setResults(Object results) {
                this.results = results;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public StockAllotsBean getStockAllots() {
            return stockAllots;
        }

        public void setStockAllots(StockAllotsBean stockAllots) {
            this.stockAllots = stockAllots;
        }

        public static class StockAllotsBean {
            /**
             * id : null
             * code : 1
             * types : null
             * outWarehouse : null
             * inWarehouse : null
             * addedOperatorId : null
             * addedOperator : null
             * addedTime : null
             * status : null
             * reason : hajk
             * approvedTime : null
             * stockAddId : null
             * outConfirmOperator : null
             * outContirmTime : null
             * stockSubId : null
             * inConfirmOperator : null
             * inContirmTime : null
             * warehouseName : 2号仓库
             */

            private Object id;
            private String code;
            private Object types;
            private Object outWarehouse;
            private Object inWarehouse;
            private Object addedOperatorId;
            private Object addedOperator;
            private Object addedTime;
            private Object status;
            private String reason;
            private Object approvedTime;
            private Object stockAddId;
            private Object outConfirmOperator;
            private Object outContirmTime;
            private Object stockSubId;
            private Object inConfirmOperator;
            private Object inContirmTime;
            private String warehouseName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }

            public Object getOutWarehouse() {
                return outWarehouse;
            }

            public void setOutWarehouse(Object outWarehouse) {
                this.outWarehouse = outWarehouse;
            }

            public Object getInWarehouse() {
                return inWarehouse;
            }

            public void setInWarehouse(Object inWarehouse) {
                this.inWarehouse = inWarehouse;
            }

            public Object getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(Object addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
            }

            public Object getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(Object addedOperator) {
                this.addedOperator = addedOperator;
            }

            public Object getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(Object addedTime) {
                this.addedTime = addedTime;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public Object getApprovedTime() {
                return approvedTime;
            }

            public void setApprovedTime(Object approvedTime) {
                this.approvedTime = approvedTime;
            }

            public Object getStockAddId() {
                return stockAddId;
            }

            public void setStockAddId(Object stockAddId) {
                this.stockAddId = stockAddId;
            }

            public Object getOutConfirmOperator() {
                return outConfirmOperator;
            }

            public void setOutConfirmOperator(Object outConfirmOperator) {
                this.outConfirmOperator = outConfirmOperator;
            }

            public Object getOutContirmTime() {
                return outContirmTime;
            }

            public void setOutContirmTime(Object outContirmTime) {
                this.outContirmTime = outContirmTime;
            }

            public Object getStockSubId() {
                return stockSubId;
            }

            public void setStockSubId(Object stockSubId) {
                this.stockSubId = stockSubId;
            }

            public Object getInConfirmOperator() {
                return inConfirmOperator;
            }

            public void setInConfirmOperator(Object inConfirmOperator) {
                this.inConfirmOperator = inConfirmOperator;
            }

            public Object getInContirmTime() {
                return inContirmTime;
            }

            public void setInContirmTime(Object inContirmTime) {
                this.inContirmTime = inContirmTime;
            }

            public String getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(String warehouseName) {
                this.warehouseName = warehouseName;
            }
        }
    }
}
