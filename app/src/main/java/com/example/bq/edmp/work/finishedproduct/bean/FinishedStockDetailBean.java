package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class FinishedStockDetailBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":{"warehouseId":2,"itemId":1},"qty":-0.07,"lockedQty":0,"orgIds":null,"warehouseId":null,"itemId":null,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京爱种网络科技有限公司","varietyName":"一号小麦","warehouseName":"2号仓库","packagingName":"100公斤/袋","stockRecords":[{"id":null,"warehouseId":null,"owner":null,"itemId":null,"operationType":5,"operationTime":null,"qtys":0.21,"residueQtys":-0.07,"businessFormId":null,"remark":null}]}
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
         * id : {"warehouseId":2,"itemId":1}
         * qty : -0.07
         * lockedQty : 0.0
         * orgIds : null
         * warehouseId : null
         * itemId : null
         * varietyId : null
         * packagingId : null
         * cropId : null
         * orgId : null
         * orgName : 北京爱种网络科技有限公司
         * varietyName : 一号小麦
         * warehouseName : 2号仓库
         * packagingName : 100公斤/袋
         * stockRecords : [{"id":null,"warehouseId":null,"owner":null,"itemId":null,"operationType":5,"operationTime":null,"qtys":0.21,"residueQtys":-0.07,"businessFormId":null,"remark":null}]
         */

        private IdBean id;
        private double qty;
        private double lockedQty;
        private Object orgIds;
        private Object warehouseId;
        private Object itemId;
        private Object varietyId;
        private Object packagingId;
        private Object cropId;
        private Object orgId;
        private String orgName;
        private String varietyName;
        private String warehouseName;
        private String packagingName;
        private List<StockRecordsBean> stockRecords;

        public IdBean getId() {
            return id;
        }

        public void setId(IdBean id) {
            this.id = id;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getLockedQty() {
            return lockedQty;
        }

        public void setLockedQty(double lockedQty) {
            this.lockedQty = lockedQty;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public Object getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Object warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Object getItemId() {
            return itemId;
        }

        public void setItemId(Object itemId) {
            this.itemId = itemId;
        }

        public Object getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(Object varietyId) {
            this.varietyId = varietyId;
        }

        public Object getPackagingId() {
            return packagingId;
        }

        public void setPackagingId(Object packagingId) {
            this.packagingId = packagingId;
        }

        public Object getCropId() {
            return cropId;
        }

        public void setCropId(Object cropId) {
            this.cropId = cropId;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
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

        public String getPackagingName() {
            return packagingName;
        }

        public void setPackagingName(String packagingName) {
            this.packagingName = packagingName;
        }

        public List<StockRecordsBean> getStockRecords() {
            return stockRecords;
        }

        public void setStockRecords(List<StockRecordsBean> stockRecords) {
            this.stockRecords = stockRecords;
        }

        public static class IdBean {
            /**
             * warehouseId : 2
             * itemId : 1
             */

            private int warehouseId;
            private int itemId;

            public int getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(int warehouseId) {
                this.warehouseId = warehouseId;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }
        }

        public static class StockRecordsBean {
            /**
             * id : null
             * warehouseId : null
             * owner : null
             * itemId : null
             * operationType : 5
             * operationTime : null
             * qtys : 0.21
             * residueQtys : -0.07
             * businessFormId : null
             * remark : null
             */

            private Object id;
            private Object warehouseId;
            private Object owner;
            private Object itemId;
            private int operationType;
            private String operationTime;
            private double qtys;
            private double residueQtys;
            private Object businessFormId;
            private Object remark;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public Object getItemId() {
                return itemId;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public int getOperationType() {
                return operationType;
            }

            public void setOperationType(int operationType) {
                this.operationType = operationType;
            }

            public String getOperationTime() {
                return operationTime;
            }

            public void setOperationTime(String operationTime) {
                this.operationTime = operationTime;
            }

            public double getQtys() {
                return qtys;
            }

            public void setQtys(double qtys) {
                this.qtys = qtys;
            }

            public double getResidueQtys() {
                return residueQtys;
            }

            public void setResidueQtys(double residueQtys) {
                this.residueQtys = residueQtys;
            }

            public Object getBusinessFormId() {
                return businessFormId;
            }

            public void setBusinessFormId(Object businessFormId) {
                this.businessFormId = businessFormId;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }
    }
}
