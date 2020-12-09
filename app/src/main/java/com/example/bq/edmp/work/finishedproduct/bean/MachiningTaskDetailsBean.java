package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class MachiningTaskDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":4,"owner":null,"orgId":null,"code":"JG20201209095938","types":2,"businessFormId":null,"planFinishTime":"2020-12-08","status":3,"addedOperator":null,"addedTime":null,"confirmOperator":"2321","confirmTime":"2020-12-09","finishedTime":"2020-12-09","finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"100公斤/袋","varietyName":"一号小麦","planQty":1250,"finishedQty":0,"orgName":"北京分公司","packagingId":1,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":[{"id":59,"code":"RK20201209154137","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 15:41:37","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":58,"code":"RK20201209153817","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 15:38:17","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":54,"code":"RK20201209141929","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:29","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":52,"code":"RK20201209141907","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:07","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":53,"code":"RK20201209141907","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:07","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":50,"code":"RK20201209141813","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:18:13","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":51,"code":"RK20201209141813","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:18:13","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":48,"code":"RK20201209141556","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:15:56","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":49,"code":"RK20201209141556","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:15:56","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":44,"code":"RK20201209141228","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:12:28","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":45,"code":"RK20201209141228","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:12:28","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":42,"code":"RK20201209140432","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:04:32","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1502,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":43,"code":"RK20201209140432","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:04:32","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1502,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":41,"code":"RK20201209140304","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:03:04","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1501,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":36,"code":"RK12345678912345","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 13:57:54","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1501,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":34,"code":"12","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-08 19:28:41","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1000,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null}],"productWeight":null,"percentage":"0%"}
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
         * id : 4
         * owner : null
         * orgId : null
         * code : JG20201209095938
         * types : 2
         * businessFormId : null
         * planFinishTime : 2020-12-08
         * status : 3
         * addedOperator : null
         * addedTime : null
         * confirmOperator : 2321
         * confirmTime : 2020-12-09
         * finishedTime : 2020-12-09
         * finishedOperator : null
         * acceptedTime : null
         * acceptedOperator : null
         * startTime : null
         * endTime : null
         * varietyPackagingId : null
         * varietyPackagingName : 100公斤/袋
         * varietyName : 一号小麦
         * planQty : 1250.0
         * finishedQty : 0.0
         * orgName : 北京分公司
         * packagingId : 1
         * cpwarehouseId : null
         * fswarehouseId : null
         * ylwarehouseId : null
         * stockAdds : [{"id":59,"code":"RK20201209154137","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 15:41:37","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":58,"code":"RK20201209153817","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 15:38:17","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":54,"code":"RK20201209141929","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:29","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":12322,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":52,"code":"RK20201209141907","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:07","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":53,"code":"RK20201209141907","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:19:07","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":50,"code":"RK20201209141813","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:18:13","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":51,"code":"RK20201209141813","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:18:13","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":48,"code":"RK20201209141556","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:15:56","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":49,"code":"RK20201209141556","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:15:56","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":321,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":44,"code":"RK20201209141228","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:12:28","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":45,"code":"RK20201209141228","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:12:28","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":123,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":42,"code":"RK20201209140432","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:04:32","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1502,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":43,"code":"RK20201209140432","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:04:32","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1502,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":41,"code":"RK20201209140304","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 14:03:04","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1501,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":36,"code":"RK12345678912345","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-09 13:57:54","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1501,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":34,"code":"12","owner":1,"warehouseId":1,"type1":2,"type2":2,"businessFormId":4,"addedTime":"2020-12-08 19:28:41","addedOperator":"管理员","finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":1000,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null}]
         * productWeight : null
         * percentage : 0%
         */

        private int id;
        private Object owner;
        private Object orgId;
        private String code;
        private int types;
        private Object businessFormId;
        private String planFinishTime;
        private int status;
        private Object addedOperator;
        private Object addedTime;
        private String confirmOperator;
        private String confirmTime;
        private String finishedTime;
        private Object finishedOperator;
        private String acceptedTime;
        private String acceptedOperator;
        private Object startTime;
        private Object endTime;
        private Object varietyPackagingId;
        private String varietyPackagingName;
        private String varietyName;
        private double planQty;
        private double finishedQty;
        private String orgName;
        private int packagingId;
        private Object cpwarehouseId;
        private Object fswarehouseId;
        private Object ylwarehouseId;
        private Object productWeight;
        private String percentage;
        private List<StockAddsBean> stockAdds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public Object getBusinessFormId() {
            return businessFormId;
        }

        public void setBusinessFormId(Object businessFormId) {
            this.businessFormId = businessFormId;
        }

        public String getPlanFinishTime() {
            return planFinishTime;
        }

        public void setPlanFinishTime(String planFinishTime) {
            this.planFinishTime = planFinishTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getConfirmOperator() {
            return confirmOperator;
        }

        public void setConfirmOperator(String confirmOperator) {
            this.confirmOperator = confirmOperator;
        }

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public Object getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(Object finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public String getAcceptedTime() {
            return acceptedTime;
        }

        public void setAcceptedTime(String acceptedTime) {
            this.acceptedTime = acceptedTime;
        }

        public String getAcceptedOperator() {
            return acceptedOperator;
        }

        public void setAcceptedOperator(String acceptedOperator) {
            this.acceptedOperator = acceptedOperator;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getVarietyPackagingId() {
            return varietyPackagingId;
        }

        public void setVarietyPackagingId(Object varietyPackagingId) {
            this.varietyPackagingId = varietyPackagingId;
        }

        public String getVarietyPackagingName() {
            return varietyPackagingName;
        }

        public void setVarietyPackagingName(String varietyPackagingName) {
            this.varietyPackagingName = varietyPackagingName;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public double getPlanQty() {
            return planQty;
        }

        public void setPlanQty(double planQty) {
            this.planQty = planQty;
        }

        public double getFinishedQty() {
            return finishedQty;
        }

        public void setFinishedQty(double finishedQty) {
            this.finishedQty = finishedQty;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public int getPackagingId() {
            return packagingId;
        }

        public void setPackagingId(int packagingId) {
            this.packagingId = packagingId;
        }

        public Object getCpwarehouseId() {
            return cpwarehouseId;
        }

        public void setCpwarehouseId(Object cpwarehouseId) {
            this.cpwarehouseId = cpwarehouseId;
        }

        public Object getFswarehouseId() {
            return fswarehouseId;
        }

        public void setFswarehouseId(Object fswarehouseId) {
            this.fswarehouseId = fswarehouseId;
        }

        public Object getYlwarehouseId() {
            return ylwarehouseId;
        }

        public void setYlwarehouseId(Object ylwarehouseId) {
            this.ylwarehouseId = ylwarehouseId;
        }

        public Object getProductWeight() {
            return productWeight;
        }

        public void setProductWeight(Object productWeight) {
            this.productWeight = productWeight;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public List<StockAddsBean> getStockAdds() {
            return stockAdds;
        }

        public void setStockAdds(List<StockAddsBean> stockAdds) {
            this.stockAdds = stockAdds;
        }

        public static class StockAddsBean {
            /**
             * id : 59
             * code : RK20201209154137
             * owner : 1
             * warehouseId : 1
             * type1 : 2
             * type2 : 2
             * businessFormId : 4
             * addedTime : 2020-12-09 15:41:37
             * addedOperator : 管理员
             * finishedTime : null
             * finishedOperator : null
             * remark : null
             * orgId : null
             * orgIds : null
             * beginTime : null
             * endTime : null
             * varietyId : null
             * varietyName : null
             * warehouseName : null
             * orgName : null
             * addQty : 12322.0
             * grainPurchaseCode : null
             * testingItems : null
             * stockAllots : null
             */

            private int id;
            private String code;
            private int owner;
            private int warehouseId;
            private int type1;
            private int type2;
            private int businessFormId;
            private String addedTime;
            private String addedOperator;
            private Object finishedTime;
            private Object finishedOperator;
            private Object remark;
            private Object orgId;
            private Object orgIds;
            private Object beginTime;
            private Object endTime;
            private Object varietyId;
            private Object varietyName;
            private Object warehouseName;
            private Object orgName;
            private double addQty;
            private Object grainPurchaseCode;
            private Object testingItems;
            private Object stockAllots;

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

            public int getBusinessFormId() {
                return businessFormId;
            }

            public void setBusinessFormId(int businessFormId) {
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

            public Object getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(Object varietyName) {
                this.varietyName = varietyName;
            }

            public Object getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(Object warehouseName) {
                this.warehouseName = warehouseName;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public double getAddQty() {
                return addQty;
            }

            public void setAddQty(double addQty) {
                this.addQty = addQty;
            }

            public Object getGrainPurchaseCode() {
                return grainPurchaseCode;
            }

            public void setGrainPurchaseCode(Object grainPurchaseCode) {
                this.grainPurchaseCode = grainPurchaseCode;
            }

            public Object getTestingItems() {
                return testingItems;
            }

            public void setTestingItems(Object testingItems) {
                this.testingItems = testingItems;
            }

            public Object getStockAllots() {
                return stockAllots;
            }

            public void setStockAllots(Object stockAllots) {
                this.stockAllots = stockAllots;
            }
        }
    }
}
