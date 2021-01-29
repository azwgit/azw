package com.example.bq.edmp.work.grainsale.bean;

import java.io.Serializable;
import java.util.List;

public class SaleWarehouseDetailBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"code":"143","owner":1,"orgId":1,"warehouseId":1,"type1":1,"type2":1,"businessFormId":null,"addedTime":"2020-12-02 17:11:21","addedOperator":"1","finishedTime":null,"finishedOperator":null,"remark":null,"beginTime":null,"endTime":null,"packagingId":null,"varietyId":null,"warehouseName":"1号仓库","orgName":"北京爱种网络科技有限公司","addQty":null,"grainPurchaseCode":null,"packagingName":null,"processCode":null,"testingItems":[{"id":null,"value":"1","results":null,"name":"小麦水分"},{"id":null,"value":"2","results":null,"name":"小麦杂质"}],"stockAllots":null,"stockAddItems":[{"id":null,"addQty":12,"addWeight":null,"varietyName":"一号小麦"}]}
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
         * code : 143
         * owner : 1
         * orgId : 1
         * warehouseId : 1
         * type1 : 1
         * type2 : 1
         * businessFormId : null
         * addedTime : 2020-12-02 17:11:21
         * addedOperator : 1
         * finishedTime : null
         * finishedOperator : null
         * remark : null
         * begStringime : null
         * endTime : null
         * packagingId : null
         * varietyId : null
         * warehouseName : 1号仓库
         * orgName : 北京爱种网络科技有限公司
         * addQty : null
         * grainPurchaseCode : null
         * packagingName : null
         * processCode : null
         * testingItems : [{"id":null,"value":"1","results":null,"name":"小麦水分"},{"id":null,"value":"2","results":null,"name":"小麦杂质"}]
         * stockAllots : null
         * stockAddItems : [{"id":null,"addQty":12,"addWeight":null,"varietyName":"一号小麦"}]
         */

        private String id;
        private String ordersCode;
        private String code;
        private String owner;
        private String orgId;
        private String warehouseId;
        private String type1;
        private String type2;
        private String businessFormId;
        private String addedTime;
        private String addedOperator;
        private String finishedTime;
        private String finishedOperator;
        private String remark;
        private String begStringime;
        private String endTime;
        private String packagingId;
        private String varietyId;
        private String warehouseName;
        private String orgName;
        private String addQty;
        private String grainPurchaseCode;
        private String packagingName;
        private String processCode;
        private StockAllotsBean stockAllots;
        private List<StockAddItemsBean> stockAddItems;

        public String getOrdersCode() {
            return ordersCode;
        }

        public void setOrdersCode(String ordersCode) {
            this.ordersCode = ordersCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getBusinessFormId() {
            return businessFormId;
        }

        public void setBusinessFormId(String businessFormId) {
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

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(String finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getBegStringime() {
            return begStringime;
        }

        public void setBegStringime(String begStringime) {
            this.begStringime = begStringime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPackagingId() {
            return packagingId;
        }

        public void setPackagingId(String packagingId) {
            this.packagingId = packagingId;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
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

        public String getAddQty() {
            return addQty;
        }

        public void setAddQty(String addQty) {
            this.addQty = addQty;
        }

        public String getGrainPurchaseCode() {
            return grainPurchaseCode;
        }

        public void setGrainPurchaseCode(String grainPurchaseCode) {
            this.grainPurchaseCode = grainPurchaseCode;
        }

        public String getPackagingName() {
            return packagingName;
        }

        public void setPackagingName(String packagingName) {
            this.packagingName = packagingName;
        }

        public String getProcessCode() {
            return processCode;
        }

        public void setProcessCode(String processCode) {
            this.processCode = processCode;
        }

        public StockAllotsBean getStockAllots() {
            return stockAllots;
        }

        public void setStockAllots(StockAllotsBean stockAllots) {
            this.stockAllots = stockAllots;
        }


        public List<StockAddItemsBean> getStockAddItems() {
            return stockAddItems;
        }

        public void setStockAddItems(List<StockAddItemsBean> stockAddItems) {
            this.stockAddItems = stockAddItems;
        }

        public static class StockAllotsBean {

            private String id;
            private String code;
            private String types;
            private String outWarehouse;
            private String inWarehouse;
            private String addedOperatorId;
            private String addedOperator;
            private String addedTime;
            private String status;
            private String reason;
            private String approvedTime;
            private String stockAddId;
            private String outConfirmOperator;
            private String outContirmTime;
            private String stockSubId;
            private String inConfirmOperator;
            private String inContirmTime;
            private String warehouseName;
            private String remark;
            private List<StockAllotItemsBean> stockAllotItems;

            public List<StockAllotItemsBean> getStockAllotItems() {
                return stockAllotItems;
            }

            public void setStockAllotItems(List<StockAllotItemsBean> stockAllotItems) {
                this.stockAllotItems = stockAllotItems;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public String getOutWarehouse() {
                return outWarehouse;
            }

            public void setOutWarehouse(String outWarehouse) {
                this.outWarehouse = outWarehouse;
            }

            public String getInWarehouse() {
                return inWarehouse;
            }

            public void setInWarehouse(String inWarehouse) {
                this.inWarehouse = inWarehouse;
            }

            public String getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(String addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
            }

            public String getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(String addedOperator) {
                this.addedOperator = addedOperator;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getApprovedTime() {
                return approvedTime;
            }

            public void setApprovedTime(String approvedTime) {
                this.approvedTime = approvedTime;
            }

            public String getStockAddId() {
                return stockAddId;
            }

            public void setStockAddId(String stockAddId) {
                this.stockAddId = stockAddId;
            }

            public String getOutConfirmOperator() {
                return outConfirmOperator;
            }

            public void setOutConfirmOperator(String outConfirmOperator) {
                this.outConfirmOperator = outConfirmOperator;
            }

            public String getOutContirmTime() {
                return outContirmTime;
            }

            public void setOutContirmTime(String outContirmTime) {
                this.outContirmTime = outContirmTime;
            }

            public String getStockSubId() {
                return stockSubId;
            }

            public void setStockSubId(String stockSubId) {
                this.stockSubId = stockSubId;
            }

            public String getInConfirmOperator() {
                return inConfirmOperator;
            }

            public void setInConfirmOperator(String inConfirmOperator) {
                this.inConfirmOperator = inConfirmOperator;
            }

            public String getInContirmTime() {
                return inContirmTime;
            }

            public void setInContirmTime(String inContirmTime) {
                this.inContirmTime = inContirmTime;
            }

            public String getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(String warehouseName) {
                this.warehouseName = warehouseName;
            }

            public static class StockAllotItemsBean {
                private String outItemName;

                public String getOutItemName() {
                    return outItemName;
                }

                public void setOutItemName(String outItemName) {
                    this.outItemName = outItemName;
                }
            }
        }

        public static class StockAddItemsBean {
            /**
             * id : null
             * addQty : 12
             * addWeight : null
             * varietyName : 一号小麦
             */

            private String id;
            private String addQty;
            private String addWeight;
            private String varietyName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddQty() {
                return addQty;
            }

            public void setAddQty(String addQty) {
                this.addQty = addQty;
            }

            public String getAddWeight() {
                return addWeight;
            }

            public void setAddWeight(String addWeight) {
                this.addWeight = addWeight;
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
