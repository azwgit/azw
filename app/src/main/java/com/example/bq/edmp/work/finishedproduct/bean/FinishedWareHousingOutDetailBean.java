package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class FinishedWareHousingOutDetailBean implements Serializable {


    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":66,"code":"CK20201211140543","orgId":2,"warehouseId":1,"owner":1,"type1":2,"type2":3,"businessFormId":null,"addedTime":"2020-12-11","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"beginTime":null,"endTime":null,"packagingId":null,"varietyId":null,"varietyName":null,"warehouseName":"1号仓库","orgName":"北京分公司","subQty":null,"ordersCode":null,"packagingName":null,"processCode":null,"stockAllots":{"id":null,"code":"CX","types":null,"outOrgId":null,"outWarehouse":null,"inOrgId":null,"inWarehouse":null,"addedOperatorId":null,"addedOperator":null,"addedTime":null,"status":null,"reason":"成品出库","approvedTime":null,"stockAddId":null,"outConfirmOperator":null,"outContirmTime":null,"stockSubId":null,"inConfirmOperator":null,"inContirmTime":null,"warehouseName":"北京分公司 1号仓库"},"stockSubItems":[{"id":null,"subQty":100,"subWeight":null,"varietyName":"一号小麦 100公斤/袋"}]}
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
         * id : 66
         * code : CK20201211140543
         * orgId : 2
         * warehouseId : 1
         * owner : 1
         * type1 : 2
         * type2 : 3
         * businessFormId : null
         * addedTime : 2020-12-11
         * addedOperator : 管理员
         * finishedTime : null
         * finishedOperator : null
         * remark : null
         * beginTime : null
         * endTime : null
         * packagingId : null
         * varietyId : null
         * varietyName : null
         * warehouseName : 1号仓库
         * orgName : 北京分公司
         * subQty : null
         * ordersCode : null
         * packagingName : null
         * processCode : null
         * stockAllots : {"id":null,"code":"CX","types":null,"outOrgId":null,"outWarehouse":null,"inOrgId":null,"inWarehouse":null,"addedOperatorId":null,"addedOperator":null,"addedTime":null,"status":null,"reason":"成品出库","approvedTime":null,"stockAddId":null,"outConfirmOperator":null,"outContirmTime":null,"stockSubId":null,"inConfirmOperator":null,"inContirmTime":null,"warehouseName":"北京分公司 1号仓库"}
         * stockSubItems : [{"id":null,"subQty":100,"subWeight":null,"varietyName":"一号小麦 100公斤/袋"}]
         */

        private int id;
        private String code;
        private int orgId;
        private int warehouseId;
        private int owner;
        private int type1;
        private int type2;
        private Object businessFormId;
        private String addedTime;
        private String addedOperator;
        private Object finishedTime;
        private Object finishedOperator;
        private Object remark;
        private Object beginTime;
        private Object endTime;
        private Object packagingId;
        private Object varietyId;
        private Object varietyName;
        private String warehouseName;
        private String orgName;
        private Object subQty;
        private String ordersCode;
        private Object packagingName;
        private Object processCode;
        private StockAllotsBean stockAllots;
        private List<StockSubItemsBean> stockSubItems;

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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
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

        public String getAddedOperator() {
            return addedOperator;
        }

        public void setAddedOperator(String addedOperator) {
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

        public Object getPackagingId() {
            return packagingId;
        }

        public void setPackagingId(Object packagingId) {
            this.packagingId = packagingId;
        }

        public Object getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(Object varietyId) {
            this.varietyId = varietyId;
        }

        public Object getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(Object varietyName) {
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

        public Object getSubQty() {
            return subQty;
        }

        public void setSubQty(Object subQty) {
            this.subQty = subQty;
        }

        public String getOrdersCode() {
            return ordersCode;
        }

        public void setOrdersCode(String ordersCode) {
            this.ordersCode = ordersCode;
        }

        public Object getPackagingName() {
            return packagingName;
        }

        public void setPackagingName(Object packagingName) {
            this.packagingName = packagingName;
        }

        public Object getProcessCode() {
            return processCode;
        }

        public void setProcessCode(Object processCode) {
            this.processCode = processCode;
        }

        public StockAllotsBean getStockAllots() {
            return stockAllots;
        }

        public void setStockAllots(StockAllotsBean stockAllots) {
            this.stockAllots = stockAllots;
        }

        public List<StockSubItemsBean> getStockSubItems() {
            return stockSubItems;
        }

        public void setStockSubItems(List<StockSubItemsBean> stockSubItems) {
            this.stockSubItems = stockSubItems;
        }

        public static class StockAllotsBean {
            /**
             * id : null
             * code : CX
             * types : null
             * outOrgId : null
             * outWarehouse : null
             * inOrgId : null
             * inWarehouse : null
             * addedOperatorId : null
             * addedOperator : null
             * addedTime : null
             * status : null
             * reason : 成品出库
             * approvedTime : null
             * stockAddId : null
             * outConfirmOperator : null
             * outContirmTime : null
             * stockSubId : null
             * inConfirmOperator : null
             * inContirmTime : null
             * warehouseName : 北京分公司 1号仓库
             */

            private Object id;
            private String code;
            private Object types;
            private Object outOrgId;
            private Object outWarehouse;
            private Object inOrgId;
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

            public Object getOutOrgId() {
                return outOrgId;
            }

            public void setOutOrgId(Object outOrgId) {
                this.outOrgId = outOrgId;
            }

            public Object getOutWarehouse() {
                return outWarehouse;
            }

            public void setOutWarehouse(Object outWarehouse) {
                this.outWarehouse = outWarehouse;
            }

            public Object getInOrgId() {
                return inOrgId;
            }

            public void setInOrgId(Object inOrgId) {
                this.inOrgId = inOrgId;
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

        public static class StockSubItemsBean {
            /**
             * id : null
             * subQty : 100.0
             * subWeight : null
             * varietyName : 一号小麦 100公斤/袋
             */

            private Object id;
            private double subQty;
            private Object subWeight;
            private String varietyName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public double getSubQty() {
                return subQty;
            }

            public void setSubQty(double subQty) {
                this.subQty = subQty;
            }

            public Object getSubWeight() {
                return subWeight;
            }

            public void setSubWeight(Object subWeight) {
                this.subWeight = subWeight;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }
        }
    }
}
