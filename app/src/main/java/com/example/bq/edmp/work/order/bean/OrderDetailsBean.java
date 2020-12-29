package com.example.bq.edmp.work.order.bean;

import java.util.List;

public class OrderDetailsBean {
    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":13,"owner":1,"code":null,"customerId":1,"salesId":16,"types":1,"region":null,"contacts":"订单1","mobTel":"1234","address":"地址1","addedTime":"2020-12-22 09:46","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":0,"orderItems":[{"id":{"ordersId":13,"packagingId":1},"qty":22,"price":1,"settlement":22,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null}],"customerName":null}
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
         * id : 13
         * owner : 1
         * code : null
         * customerId : 1
         * salesId : 16
         * types : 1
         * region : null
         * contacts : 订单1
         * mobTel : 1234
         * address : 地址1
         * addedTime : 2020-12-22 09:46
         * addedOperator : null
         * status : 1
         * submitTime : null
         * submitOperator : null
         * allocationTime : null
         * allocationOperator : null
         * paidConfirmTime : null
         * paidConfirmOperator : null
         * sendOutTime : null
         * signedTime : null
         * amount : 0.0
         * orderItems : [{"id":{"ordersId":13,"packagingId":1},"qty":22,"price":1,"settlement":22,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null}]
         * customerName : null
         */

        private String id;
        private String owner;
        private String code;
        private String customerId;
        private String salesId;
        private String types;
        private String region;
        private String contacts;
        private String mobTel;
        private String address;
        private String addedTime;
        private String addedOperator;
        private String status;
        private String submitTime;
        private String submitOperator;
        private String allocationTime;
        private String allocationOperator;
        private String paidConfirmTime;
        private String paidConfirmOperator;
        private String sendOutTime;
        private String signedTime;
        private double amount;
        private String customerName;
        private List<OrderItemsBean> orderItems;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getSalesId() {
            return salesId;
        }

        public void setSalesId(String salesId) {
            this.salesId = salesId;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public String getSubmitOperator() {
            return submitOperator;
        }

        public void setSubmitOperator(String submitOperator) {
            this.submitOperator = submitOperator;
        }

        public String getAllocationTime() {
            return allocationTime;
        }

        public void setAllocationTime(String allocationTime) {
            this.allocationTime = allocationTime;
        }

        public String getAllocationOperator() {
            return allocationOperator;
        }

        public void setAllocationOperator(String allocationOperator) {
            this.allocationOperator = allocationOperator;
        }

        public String getPaidConfirmTime() {
            return paidConfirmTime;
        }

        public void setPaidConfirmTime(String paidConfirmTime) {
            this.paidConfirmTime = paidConfirmTime;
        }

        public String getPaidConfirmOperator() {
            return paidConfirmOperator;
        }

        public void setPaidConfirmOperator(String paidConfirmOperator) {
            this.paidConfirmOperator = paidConfirmOperator;
        }

        public String getSendOutTime() {
            return sendOutTime;
        }

        public void setSendOutTime(String sendOutTime) {
            this.sendOutTime = sendOutTime;
        }

        public String getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(String signedTime) {
            this.signedTime = signedTime;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        public static class OrderItemsBean {
            /**
             * id : {"ordersId":13,"packagingId":1}
             * qty : 22.0
             * price : 1.0
             * settlement : 22.0
             * ordersId : null
             * packagingId : null
             * packagingName : 100公斤/袋
             * tplNo : null
             * sendOutStatus : null
             */

            private IdBean id;
            private double qty;
            private double price;
            private double settlement;
            private String ordersId;
            private String packagingId;
            private String packagingName;
            private String tplNo;
            private String sendOutStatus;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getSettlement() {
                return settlement;
            }

            public void setSettlement(double settlement) {
                this.settlement = settlement;
            }

            public String getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(String ordersId) {
                this.ordersId = ordersId;
            }

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
            }

            public String getPackagingName() {
                return packagingName;
            }

            public void setPackagingName(String packagingName) {
                this.packagingName = packagingName;
            }

            public String getTplNo() {
                return tplNo;
            }

            public void setTplNo(String tplNo) {
                this.tplNo = tplNo;
            }

            public String getSendOutStatus() {
                return sendOutStatus;
            }

            public void setSendOutStatus(String sendOutStatus) {
                this.sendOutStatus = sendOutStatus;
            }

            public static class IdBean {
                /**
                 * ordersId : 13
                 * packagingId : 1
                 */

                private String ordersId;
                private String packagingId;

                public String getOrdersId() {
                    return ordersId;
                }

                public void setOrdersId(String ordersId) {
                    this.ordersId = ordersId;
                }

                public String getPackagingId() {
                    return packagingId;
                }

                public void setPackagingId(String packagingId) {
                    this.packagingId = packagingId;
                }
            }
        }
    }





    /* *//**
     * code : 200
     * msg : 查询成功
     * data : [{"id":2,"owner":1,"cropId":2,"types":null,"name":null,"remark":null,"testPlanItems":[{"id":1,"testPlanId":2,"name":"水分","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1},{"id":2,"testPlanId":2,"name":"杂质","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":2},{"id":3,"testPlanId":2,"name":"颗粒饱满","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1}]}]
     *//*

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
        public String getTotalPrice() {
            return TotalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            TotalPrice = totalPrice;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAllocationTime() {
            return allocationTime;
        }

        public void setAllocationTime(String allocationTime) {
            this.allocationTime = allocationTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }

        public String getPaidConfirmTime() {
            return paidConfirmTime;
        }

        public void setPaidConfirmTime(String paidConfirmTime) {
            this.paidConfirmTime = paidConfirmTime;
        }

        public String getSalesId() {
            return salesId;
        }

        public void setSalesId(String salesId) {
            this.salesId = salesId;
        }

        public String getSendOutTime() {
            return sendOutTime;
        }

        public void setSendOutTime(String sendOutTime) {
            this.sendOutTime = sendOutTime;
        }

        public String getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(String signedTime) {
            this.signedTime = signedTime;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }

        *//**
         * id : 1
         * testPlanId : 2
         * name : 水分
         * unit : null
         * lowerLimit : null
         * upperLimit : null
         * valueType : 1
         *//*


        private String TotalPrice;//	总价	number
        private String address;//	送货地址	string
        private String allocationTime;//	分配时间	string
        private String code;//	订单号	string
        private String contacts;//	联系人	string
        private String customerId;//	客户id	number
        private String customerName;//	客户名称	string
        private String mobTel;//	联系电话	number
        private String paidConfirmTime;//	回款时间	string
        private String salesId;//	销售经理名称	string
        private String sendOutTime;//	发货时间	string
        private String signedTime;//	完成时间	string
        private String submitTime;//提交时间
        private List<OrderItem> orderItems;

        public static class OrderItem {

            private String packagingId;//	包装id	number
            private String packagingName;//	包装名称	string

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
            }

            public String getPackagingName() {
                return packagingName;
            }

            public void setPackagingName(String packagingName) {
                this.packagingName = packagingName;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getQyt() {
                return qyt;
            }

            public void setQyt(String qyt) {
                this.qyt = qyt;
            }

            public String getTplNo() {
                return tplNo;
            }

            public void setTplNo(String tplNo) {
                this.tplNo = tplNo;
            }

            private double price;//	价格	number
            private String qyt;//	发货量	number
            private String tplNo;//	物流单号	string

        }

    }*/
}
