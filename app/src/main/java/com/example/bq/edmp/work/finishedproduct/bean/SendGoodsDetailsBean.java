package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class SendGoodsDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"code":"1","customerId":1,"orgId":1,"warehouseId":1,"salesId":1,"addedTime":"2020-12-08T05:11:30.000+0000","addedOperator":null,"status":"6","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":"2020-12-08T09:10:50.000+0000","signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京爱种网络科技有限公司","warehouseName":"1号仓库","customerName":"张三","salesman":"张三","orderItems":[{"id":null,"qty":null,"price":null,"ordersId":null,"packagingName":"100公斤/袋","unitWeight":null,"units":null,"varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":null,"packagingName":"200公斤/袋","unitWeight":null,"units":null,"varietyName":"一号小麦"}],"orderSendOut":{"ordersId":1,"times":"2020-12-08T09:14:29.000+0000","types":3,"truckId":null,"tplName":null,"tplNo":null,"remark":"不发货","owner":null,"orgId":null,"logisticsName":null,"contacts":null,"license":null}}
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
         * owner : 1
         * code : 1
         * customerId : 1
         * orgId : 1
         * warehouseId : 1
         * salesId : 1
         * addedTime : 2020-12-08T05:11:30.000+0000
         * addedOperator : null
         * status : 6
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * sendOutTime : 2020-12-08T09:10:50.000+0000
         * signedTime : null
         * orgIds : null
         * beginTime : null
         * endTime : null
         * orgName : 北京爱种网络科技有限公司
         * warehouseName : 1号仓库
         * customerName : 张三
         * salesman : 张三
         * orderItems : [{"id":null,"qty":null,"price":null,"ordersId":null,"packagingName":"100公斤/袋","unitWeight":null,"units":null,"varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":null,"packagingName":"200公斤/袋","unitWeight":null,"units":null,"varietyName":"一号小麦"}]
         * orderSendOut : {"ordersId":1,"times":"2020-12-08T09:14:29.000+0000","types":3,"truckId":null,"tplName":null,"tplNo":null,"remark":"不发货","owner":null,"orgId":null,"logisticsName":null,"contacts":null,"license":null}
         */

        private int id;
        private int owner;
        private String code;
        private int customerId;
        private int orgId;
        private int warehouseId;
        private int salesId;
        private String addedTime;
        private Object addedOperator;
        private String status;
        private Object submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private String sendOutTime;
        private Object signedTime;
        private Object orgIds;
        private Object beginTime;
        private Object endTime;
        private String orgName;
        private String warehouseName;
        private String customerName;
        private String salesman;
        private OrderSendOutBean orderSendOut;
        private List<OrderItemsBean> orderItems;

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

        public int getSalesId() {
            return salesId;
        }

        public void setSalesId(int salesId) {
            this.salesId = salesId;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getSendOutTime() {
            return sendOutTime;
        }

        public void setSendOutTime(String sendOutTime) {
            this.sendOutTime = sendOutTime;
        }

        public Object getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(Object signedTime) {
            this.signedTime = signedTime;
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

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSalesman() {
            return salesman;
        }

        public void setSalesman(String salesman) {
            this.salesman = salesman;
        }

        public OrderSendOutBean getOrderSendOut() {
            return orderSendOut;
        }

        public void setOrderSendOut(OrderSendOutBean orderSendOut) {
            this.orderSendOut = orderSendOut;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        public static class OrderSendOutBean {
            /**
             * ordersId : 1
             * times : 2020-12-08T09:14:29.000+0000
             * types : 3
             * truckId : null
             * tplName : null
             * tplNo : null
             * remark : 不发货
             * owner : null
             * orgId : null
             * logisticsName : null
             * contacts : null
             * license : null
             */

            private int ordersId;
            private String times;
            private int types;
            private Object truckId;
            private Object tplName;
            private String tplNo;
            private String remark;
            private Object owner;
            private Object orgId;
            private String logisticsName;
            private String contacts;
            private String license;

            public int getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(int ordersId) {
                this.ordersId = ordersId;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public int getTypes() {
                return types;
            }

            public void setTypes(int types) {
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

            public String getTplNo() {
                return tplNo;
            }

            public void setTplNo(String tplNo) {
                this.tplNo = tplNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public String getLogisticsName() {
                return logisticsName;
            }

            public void setLogisticsName(String logisticsName) {
                this.logisticsName = logisticsName;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getLicense() {
                return license;
            }

            public void setLicense(String license) {
                this.license = license;
            }
        }

        public static class OrderItemsBean {
            /**
             * id : null
             * qty : null
             * price : null
             * ordersId : null
             * packagingName : 100公斤/袋
             * unitWeight : null
             * units : null
             * varietyName : 一号小麦
             */

            private Object id;
            private Double qty;
            private Object price;
            private Object ordersId;
            private String packagingName;
            private Object unitWeight;
            private Object units;
            private String varietyName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Double getQty() {
                return qty;
            }

            public void setQty(Double qty) {
                this.qty = qty;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(Object ordersId) {
                this.ordersId = ordersId;
            }

            public String getPackagingName() {
                return packagingName;
            }

            public void setPackagingName(String packagingName) {
                this.packagingName = packagingName;
            }

            public Object getUnitWeight() {
                return unitWeight;
            }

            public void setUnitWeight(Object unitWeight) {
                this.unitWeight = unitWeight;
            }

            public Object getUnits() {
                return units;
            }

            public void setUnits(Object units) {
                this.units = units;
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
