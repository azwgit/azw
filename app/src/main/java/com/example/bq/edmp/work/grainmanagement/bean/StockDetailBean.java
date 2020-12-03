package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class StockDetailBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"warehouseId":1,"varietyId":1,"qty":123,"lockedQty":0,"orgIds":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦","warehouseName":"1号仓库","stockRecords":[{"id":null,"warehouseId":null,"varietyId":null,"operationType":1,"operationTime":"2020-12-02T07:33:59.000+0000","qtys":31,"residueQtys":221,"businessFormId":null,"remark":null},{"id":null,"warehouseId":null,"varietyId":null,"operationType":2,"operationTime":"2020-12-02T07:34:19.000+0000","qtys":332,"residueQtys":21,"businessFormId":null,"remark":null}]}
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
         * warehouseId : 1
         * varietyId : 1
         * qty : 123.0
         * lockedQty : 0.0
         * orgIds : null
         * cropId : null
         * orgId : null
         * orgName : 北京分公司
         * varietyName : 一号小麦
         * warehouseName : 1号仓库
         * stockRecords : [{"id":null,"warehouseId":null,"varietyId":null,"operationType":1,"operationTime":"2020-12-02T07:33:59.000+0000","qtys":31,"residueQtys":221,"businessFormId":null,"remark":null},{"id":null,"warehouseId":null,"varietyId":null,"operationType":2,"operationTime":"2020-12-02T07:34:19.000+0000","qtys":332,"residueQtys":21,"businessFormId":null,"remark":null}]
         */

        private int warehouseId;
        private int varietyId;
        private double qty;
        private double lockedQty;
        private Object orgIds;
        private Object cropId;
        private Object orgId;
        private String orgName;
        private String varietyName;
        private String warehouseName;
        private List<StockRecordsBean> stockRecords;

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(int varietyId) {
            this.varietyId = varietyId;
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

        public List<StockRecordsBean> getStockRecords() {
            return stockRecords;
        }

        public void setStockRecords(List<StockRecordsBean> stockRecords) {
            this.stockRecords = stockRecords;
        }

        public static class StockRecordsBean {
            /**
             * id : null
             * warehouseId : null
             * varietyId : null
             * operationType : 1
             * operationTime : 2020-12-02T07:33:59.000+0000
             * qtys : 31.0
             * residueQtys : 221.0
             * businessFormId : null
             * remark : null
             */

            private Object id;
            private Object warehouseId;
            private Object varietyId;
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

            public Object getVarietyId() {
                return varietyId;
            }

            public void setVarietyId(Object varietyId) {
                this.varietyId = varietyId;
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
