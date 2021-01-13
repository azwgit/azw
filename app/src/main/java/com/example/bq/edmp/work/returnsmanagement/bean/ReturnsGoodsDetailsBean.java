package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;

public class ReturnsGoodsDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":6,"owner":1,"code":"TH20210113093217","customerId":6,"ordersId":35,"types":1,"orgId":2,"warehouseId":1,"contacts":"123","mobTel":"123","region":"北京市 石景山区","address":"123","salesId":13,"cgCustomerId":null,"amount":null,"addedTime":"2021-01-13T01:32:17.000+0000","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":200,"orderCode":"DD20201223150442","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":11,"customerName":"爱种网","cgCustomerName":null,"orgName":"北京爱种网络科技有限公司 - 北京分公司","salePrice":null,"salesAmount":null,"warehouseName":"大豆仓库","orderOrgName":"北京分公司","qty":60}
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
         * id : 6
         * owner : 1
         * code : TH20210113093217
         * customerId : 6
         * ordersId : 35
         * types : 1
         * orgId : 2
         * warehouseId : 1
         * contacts : 123
         * mobTel : 123
         * region : 北京市 石景山区
         * address : 123
         * salesId : 13
         * cgCustomerId : null
         * amount : null
         * addedTime : 2021-01-13T01:32:17.000+0000
         * addedOperator : 李四
         * status : 1
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * finishedOperator : null
         * finishedTime : null
         * type : null
         * returnQty : 200.0
         * orderCode : DD20201223150442
         * sendOutTimes : null
         * varietyName : 一号小麦 100公斤/袋
         * itemId : null
         * price : 11.0
         * customerName : 爱种网
         * cgCustomerName : null
         * orgName : 北京爱种网络科技有限公司 - 北京分公司
         * salePrice : null
         * salesAmount : null
         * warehouseName : 大豆仓库
         * orderOrgName : 北京分公司
         * qty : 60.0
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
        private Object cgCustomerId;
        private double amount;
        private String addedTime;
        private String addedOperator;
        private int status;
        private Object submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private Object finishedOperator;
        private String finishedTime;
        private Object type;
        private double returnQty;
        private String orderCode;
        private String sendOutTimes;
        private String varietyName;
        private String itemId;
        private double price;
        private String customerName;
        private String cgCustomerName;
        private String orgName;
        private double salePrice;
        private double salesAmount;
        private String warehouseName;
        private String orderOrgName;
        private double qty;
        private double returnPrice;
        private String saleItemId;
        private String saleItemName;

        public String getSaleItemId() {
            return saleItemId;
        }

        public void setSaleItemId(String saleItemId) {
            this.saleItemId = saleItemId;
        }

        public String getSaleItemName() {
            return saleItemName;
        }

        public void setSaleItemName(String saleItemName) {
            this.saleItemName = saleItemName;
        }

        public double getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(double returnPrice) {
            this.returnPrice = returnPrice;
        }

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

        public Object getCgCustomerId() {
            return cgCustomerId;
        }

        public void setCgCustomerId(Object cgCustomerId) {
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

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public double getReturnQty() {
            return returnQty;
        }

        public void setReturnQty(double returnQty) {
            this.returnQty = returnQty;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getSendOutTimes() {
            return sendOutTimes;
        }

        public void setSendOutTimes(String sendOutTimes) {
            this.sendOutTimes = sendOutTimes;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCgCustomerName() {
            return cgCustomerName;
        }

        public void setCgCustomerName(String cgCustomerName) {
            this.cgCustomerName = cgCustomerName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public double getSalesAmount() {
            return salesAmount;
        }

        public void setSalesAmount(double salesAmount) {
            this.salesAmount = salesAmount;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getOrderOrgName() {
            return orderOrgName;
        }

        public void setOrderOrgName(String orderOrgName) {
            this.orderOrgName = orderOrgName;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }
    }
}
