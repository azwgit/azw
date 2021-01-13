package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;

public class ApplyForRefundBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":null,"owner":null,"qty":60,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":37,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":null,"varietyName":"一号小麦 100公斤/袋","code":"DD20201223150923","customerName":null,"orgName":null,"price":11,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null}
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
         * id : null
         * owner : null
         * qty : 60.0
         * orgId : null
         * warehouseId : null
         * addedTimes : null
         * sendOutTimes : null
         * sendOutOperator : null
         * types : null
         * truckId : null
         * tplName : null
         * tplNo : null
         * remark : null
         * signedTime : null
         * signedOperator : null
         * status : null
         * ordersId : 37
         * itemIds : null
         * qtys : null
         * orgIds : null
         * itemId : 1
         * customerId : null
         * warehouseName : null
         * varietyName : 一号小麦 100公斤/袋
         * code : DD20201223150923
         * customerName : null
         * orgName : null
         * price : 11.0
         * salesId : null
         * region : null
         * contacts : null
         * mobTel : null
         * address : null
         * warehouseIds : null
         */

        private Object id;
        private Object owner;
        private double qty;
        private Object orgId;
        private Object warehouseId;
        private Object addedTimes;
        private Object sendOutTimes;
        private Object sendOutOperator;
        private Object types;
        private Object truckId;
        private Object tplName;
        private Object tplNo;
        private Object remark;
        private Object signedTime;
        private Object signedOperator;
        private Object status;
        private int ordersId;
        private Object itemIds;
        private Object qtys;
        private Object orgIds;
        private int itemId;
        private Object customerId;
        private Object warehouseName;
        private String varietyName;
        private String code;
        private Object customerName;
        private Object orgName;
        private double price;
        private Object salesId;
        private Object region;
        private Object contacts;
        private Object mobTel;
        private Object address;
        private Object warehouseIds;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public Object getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Object warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Object getAddedTimes() {
            return addedTimes;
        }

        public void setAddedTimes(Object addedTimes) {
            this.addedTimes = addedTimes;
        }

        public Object getSendOutTimes() {
            return sendOutTimes;
        }

        public void setSendOutTimes(Object sendOutTimes) {
            this.sendOutTimes = sendOutTimes;
        }

        public Object getSendOutOperator() {
            return sendOutOperator;
        }

        public void setSendOutOperator(Object sendOutOperator) {
            this.sendOutOperator = sendOutOperator;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public Object getTruckId() {
            return truckId;
        }

        public void setTruckId(Object truckId) {
            this.truckId = truckId;
        }

        public Object getTplName() {
            return tplName;
        }

        public void setTplName(Object tplName) {
            this.tplName = tplName;
        }

        public Object getTplNo() {
            return tplNo;
        }

        public void setTplNo(Object tplNo) {
            this.tplNo = tplNo;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(Object signedTime) {
            this.signedTime = signedTime;
        }

        public Object getSignedOperator() {
            return signedOperator;
        }

        public void setSignedOperator(Object signedOperator) {
            this.signedOperator = signedOperator;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public int getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public Object getItemIds() {
            return itemIds;
        }

        public void setItemIds(Object itemIds) {
            this.itemIds = itemIds;
        }

        public Object getQtys() {
            return qtys;
        }

        public void setQtys(Object qtys) {
            this.qtys = qtys;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(Object warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getCustomerName() {
            return customerName;
        }

        public void setCustomerName(Object customerName) {
            this.customerName = customerName;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getSalesId() {
            return salesId;
        }

        public void setSalesId(Object salesId) {
            this.salesId = salesId;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public Object getContacts() {
            return contacts;
        }

        public void setContacts(Object contacts) {
            this.contacts = contacts;
        }

        public Object getMobTel() {
            return mobTel;
        }

        public void setMobTel(Object mobTel) {
            this.mobTel = mobTel;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getWarehouseIds() {
            return warehouseIds;
        }

        public void setWarehouseIds(Object warehouseIds) {
            this.warehouseIds = warehouseIds;
        }
    }
}
