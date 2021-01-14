package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;

public class ReapplyReturnGoods implements Serializable {

    /**
     * code : 200
     * msg : 申请成功
     * data : {"id":35,"owner":1,"code":"TH20210114113420","customerId":24,"ordersId":37,"types":2,"orgId":2,"warehouseId":1,"contacts":"老王","mobTel":"123456789","region":"110105","address":"金台路","salesId":16,"cgCustomerId":24,"amount":550,"addedTime":"2021-01-14","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":null,"orderCode":null,"sendOutTimes":null,"varietyName":null,"itemId":null,"returnPrice":null,"customerName":null,"cgCustomerName":null,"orgName":null,"salePrice":null,"salesAmount":null,"warehouseName":null,"orderOrgName":null,"qty":null,"saleItemId":null,"saleItemName":null}
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
         * id : 35
         * owner : 1
         * code : TH20210114113420
         * customerId : 24
         * ordersId : 37
         * types : 2
         * orgId : 2
         * warehouseId : 1
         * contacts : 老王
         * mobTel : 123456789
         * region : 110105
         * address : 金台路
         * salesId : 16
         * cgCustomerId : 24
         * amount : 550.0
         * addedTime : 2021-01-14
         * addedOperator : 李四
         * status : 1
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * finishedOperator : null
         * finishedTime : null
         * type : null
         * returnQty : null
         * orderCode : null
         * sendOutTimes : null
         * varietyName : null
         * itemId : null
         * returnPrice : null
         * customerName : null
         * cgCustomerName : null
         * orgName : null
         * salePrice : null
         * salesAmount : null
         * warehouseName : null
         * orderOrgName : null
         * qty : null
         * saleItemId : null
         * saleItemName : null
         */

        private int id;
        private int owner;
        private String code;
        private int customerId;
        private int ordersId;
        private int types;
        private int orgId;
        private int warehouseId;
        private String contacts;
        private String mobTel;
        private String region;
        private String address;
        private int salesId;
        private int cgCustomerId;
        private double amount;
        private String addedTime;
        private String addedOperator;
        private int status;
        private Object submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private Object finishedOperator;
        private Object finishedTime;
        private Object type;
        private Object returnQty;
        private Object orderCode;
        private Object sendOutTimes;
        private Object varietyName;
        private Object itemId;
        private Object returnPrice;
        private Object customerName;
        private Object cgCustomerName;
        private Object orgName;
        private Object salePrice;
        private Object salesAmount;
        private Object warehouseName;
        private Object orderOrgName;
        private Object qty;
        private Object saleItemId;
        private Object saleItemName;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
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

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSalesId() {
            return salesId;
        }

        public void setSalesId(int salesId) {
            this.salesId = salesId;
        }

        public int getCgCustomerId() {
            return cgCustomerId;
        }

        public void setCgCustomerId(int cgCustomerId) {
            this.cgCustomerId = cgCustomerId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(Object submitTime) {
            this.submitTime = submitTime;
        }

        public Object getSubmitOperator() {
            return submitOperator;
        }

        public void setSubmitOperator(Object submitOperator) {
            this.submitOperator = submitOperator;
        }

        public Object getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(Object approvedTime) {
            this.approvedTime = approvedTime;
        }

        public Object getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(Object finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public Object getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(Object finishedTime) {
            this.finishedTime = finishedTime;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getReturnQty() {
            return returnQty;
        }

        public void setReturnQty(Object returnQty) {
            this.returnQty = returnQty;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public Object getSendOutTimes() {
            return sendOutTimes;
        }

        public void setSendOutTimes(Object sendOutTimes) {
            this.sendOutTimes = sendOutTimes;
        }

        public Object getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(Object varietyName) {
            this.varietyName = varietyName;
        }

        public Object getItemId() {
            return itemId;
        }

        public void setItemId(Object itemId) {
            this.itemId = itemId;
        }

        public Object getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(Object returnPrice) {
            this.returnPrice = returnPrice;
        }

        public Object getCustomerName() {
            return customerName;
        }

        public void setCustomerName(Object customerName) {
            this.customerName = customerName;
        }

        public Object getCgCustomerName() {
            return cgCustomerName;
        }

        public void setCgCustomerName(Object cgCustomerName) {
            this.cgCustomerName = cgCustomerName;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public Object getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(Object salePrice) {
            this.salePrice = salePrice;
        }

        public Object getSalesAmount() {
            return salesAmount;
        }

        public void setSalesAmount(Object salesAmount) {
            this.salesAmount = salesAmount;
        }

        public Object getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(Object warehouseName) {
            this.warehouseName = warehouseName;
        }

        public Object getOrderOrgName() {
            return orderOrgName;
        }

        public void setOrderOrgName(Object orderOrgName) {
            this.orderOrgName = orderOrgName;
        }

        public Object getQty() {
            return qty;
        }

        public void setQty(Object qty) {
            this.qty = qty;
        }

        public Object getSaleItemId() {
            return saleItemId;
        }

        public void setSaleItemId(Object saleItemId) {
            this.saleItemId = saleItemId;
        }

        public Object getSaleItemName() {
            return saleItemName;
        }

        public void setSaleItemName(Object saleItemName) {
            this.saleItemName = saleItemName;
        }
    }
}
