package com.example.bq.edmp.work.inventorytransfer.bean;

import java.io.Serializable;
import java.util.List;

public class EditFinishedProductAllocationBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":3,"code":"2431","owner":1,"types":2,"outOrgId":2,"outWarehouse":1,"inOrgId":1,"inWarehouse":2,"addedOperatorId":null,"addedOperator":null,"addedTime":null,"status":2,"reason":"43","submitTime":"2020-12-15T08:22:01.000+0000","approvedTime":null,"stockAddId":35,"outConfirmOperator":null,"outContirmTime":null,"stockSubId":null,"inConfirmOperator":null,"inContirmTime":null,"warehouseName":null,"beginTime":null,"endTime":null,"orgId":null,"inOrgWarehouseName":null,"outOrgWarehouseName":null,"stockAllotItems":[{"id":null,"qty":322,"varietyName":"一号小麦 100公斤/袋"}]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 3
         * code : 2431
         * owner : 1
         * types : 2
         * outOrgId : 2
         * outWarehouse : 1
         * inOrgId : 1
         * inWarehouse : 2
         * addedOperatorId : null
         * addedOperator : null
         * addedTime : null
         * status : 2
         * reason : 43
         * submitTime : 2020-12-15T08:22:01.000+0000
         * approvedTime : null
         * stockAddId : 35
         * outConfirmOperator : null
         * outContirmTime : null
         * stockSubId : null
         * inConfirmOperator : null
         * inContirmTime : null
         * warehouseName : null
         * beginTime : null
         * endTime : null
         * orgId : null
         * inOrgWarehouseName : null
         * outOrgWarehouseName : null
         * stockAllotItems : [{"id":null,"qty":322,"varietyName":"一号小麦 100公斤/袋"}]
         */

        private int id;
        private String code;
        private int owner;
        private int types;
        private int outOrgId;
        private int outWarehouse;
        private int inOrgId;
        private int inWarehouse;
        private String addedOperatorId;
        private String addedOperator;
        private String addedTime;
        private int status;
        private String reason;
        private String submitTime;
        private String approvedTime;
        private int stockAddId;
        private String outConfirmOperator;
        private String outContirmTime;
        private String stockSubId;
        private String inConfirmOperator;
        private String inContirmTime;
        private String warehouseName;
        private String beginTime;
        private String endTime;
        private String orgId;
        private String inOrgWarehouseName;
        private String outOrgWarehouseName;
        private String inOrgName;
        private String inWarehouseName;
        private String outOrgName;
        private String outWarehouseName;
        private String orgName;
        private String subCode;
        private String addCode;
        public String getSubCode() {
            return subCode;
        }

        public String getAddCode() {
            return addCode;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getInOrgName() {
            return inOrgName;
        }

        public void setInOrgName(String inOrgName) {
            this.inOrgName = inOrgName;
        }

        public String getInWarehouseName() {
            return inWarehouseName;
        }

        public void setInWarehouseName(String inWarehouseName) {
            this.inWarehouseName = inWarehouseName;
        }

        public String getOutOrgName() {
            return outOrgName;
        }

        public void setOutOrgName(String outOrgName) {
            this.outOrgName = outOrgName;
        }

        public String getOutWarehouseName() {
            return outWarehouseName;
        }

        public void setOutWarehouseName(String outWarehouseName) {
            this.outWarehouseName = outWarehouseName;
        }

        private List<StockAllotItemsBean> stockAllotItems;

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

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public int getOutOrgId() {
            return outOrgId;
        }

        public void setOutOrgId(int outOrgId) {
            this.outOrgId = outOrgId;
        }

        public int getOutWarehouse() {
            return outWarehouse;
        }

        public void setOutWarehouse(int outWarehouse) {
            this.outWarehouse = outWarehouse;
        }

        public int getInOrgId() {
            return inOrgId;
        }

        public void setInOrgId(int inOrgId) {
            this.inOrgId = inOrgId;
        }

        public int getInWarehouse() {
            return inWarehouse;
        }

        public void setInWarehouse(int inWarehouse) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public String getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(String approvedTime) {
            this.approvedTime = approvedTime;
        }

        public int getStockAddId() {
            return stockAddId;
        }

        public void setStockAddId(int stockAddId) {
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

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getInOrgWarehouseName() {
            return inOrgWarehouseName;
        }

        public void setInOrgWarehouseName(String inOrgWarehouseName) {
            this.inOrgWarehouseName = inOrgWarehouseName;
        }

        public String getOutOrgWarehouseName() {
            return outOrgWarehouseName;
        }

        public void setOutOrgWarehouseName(String outOrgWarehouseName) {
            this.outOrgWarehouseName = outOrgWarehouseName;
        }

        public List<StockAllotItemsBean> getStockAllotItems() {
            return stockAllotItems;
        }

        public void setStockAllotItems(List<StockAllotItemsBean> stockAllotItems) {
            this.stockAllotItems = stockAllotItems;
        }

        public static class StockAllotItemsBean implements Serializable{
            /**
             * id : null
             * qty : 322.0
             * varietyName : 一号小麦 100公斤/袋
             */

            private String id;
            private double qty;
            private String varietyName;
            private String inItemName;
            private String outItemName;
            private int inItemId;
            private int outItemId;

            public String getInItemName() {
                return inItemName;
            }

            public String getOutItemName() {
                return outItemName;
            }

            public void setInItemName(String inItemName) {
                this.inItemName = inItemName;
            }

            public void setOutItemName(String outItemName) {
                this.outItemName = outItemName;
            }

            public int getInItemId() {
                return inItemId;
            }

            public void setInItemId(int inItemId) {
                this.inItemId = inItemId;
            }

            public int getOutItemId() {
                return outItemId;
            }

            public void setOutItemId(int outItemId) {
                this.outItemId = outItemId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public double getQty() {
                return qty;
            }

            public void setQty(double qty) {
                this.qty = qty;
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
