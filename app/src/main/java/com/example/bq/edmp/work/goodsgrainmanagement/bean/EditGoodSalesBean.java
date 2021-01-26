package com.example.bq.edmp.work.goodsgrainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class EditGoodSalesBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"code":"12","customerId":1,"orgId":2,"warehouseId":1,"contacts":"1","mobTel":"1","region":"1","address":"1","amount":10000,"addedTime":"2021-01-25 13:18","addedOperator":"132","status":5,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"大豆仓库","customerName":"123","cgOrderItems":[{"id":{"itemId":1,"cgOrderId":1},"qty":13,"price":321,"itemId":null,"cgOrderId":null,"itemName":null},{"id":{"itemId":76,"cgOrderId":1},"qty":100,"price":100,"itemId":null,"cgOrderId":null,"itemName":null}]}
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
         * code : 12
         * customerId : 1
         * orgId : 2
         * warehouseId : 1
         * contacts : 1
         * mobTel : 1
         * region : 1
         * address : 1
         * amount : 10000.0
         * addedTime : 2021-01-25 13:18
         * addedOperator : 132
         * status : 5
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * substockOperator : null
         * substockTime : null
         * finishedOperator : null
         * finishedTime : null
         * orgName : 北京分公司
         * warehouseName : 大豆仓库
         * customerName : 123
         * cgOrderItems : [{"id":{"itemId":1,"cgOrderId":1},"qty":13,"price":321,"itemId":null,"cgOrderId":null,"itemName":null},{"id":{"itemId":76,"cgOrderId":1},"qty":100,"price":100,"itemId":null,"cgOrderId":null,"itemName":null}]
         */

        private int id;
        private int owner;
        private String code;
        private int customerId;
        private int orgId;
        private int warehouseId;
        private String contacts;
        private String mobTel;
        private String region;
        private String address;
        private double amount;
        private String addedTime;
        private String addedOperator;
        private int status;
        private String submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private Object substockOperator;
        private String substockTime;
        private Object finishedOperator;
        private Object finishedTime;
        private String orgName;
        private String warehouseName;
        private String customerName;
        private String orgFullName;

        public String getOrgFullName() {
            return orgFullName;
        }

        public void setOrgFullName(String orgFullName) {
            this.orgFullName = orgFullName;
        }

        private List<CgOrderItemsBean> cgOrderItems;

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

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
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

        public Object getSubstockOperator() {
            return substockOperator;
        }

        public void setSubstockOperator(Object substockOperator) {
            this.substockOperator = substockOperator;
        }

        public String getSubstockTime() {
            return substockTime;
        }

        public void setSubstockTime(String substockTime) {
            this.substockTime = substockTime;
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

        public List<CgOrderItemsBean> getCgOrderItems() {
            return cgOrderItems;
        }

        public void setCgOrderItems(List<CgOrderItemsBean> cgOrderItems) {
            this.cgOrderItems = cgOrderItems;
        }

        public static class CgOrderItemsBean {
            /**
             * id : {"itemId":1,"cgOrderId":1}
             * qty : 13.0
             * price : 321.0
             * itemId : null
             * cgOrderId : null
             * itemName : null
             */

            private IdBean id;
            private double qty;
            private double price;
            private int itemId;
            private Object cgOrderId;
            private String itemName;
            private double itemAmount;

            public double getItemAmount() {
                return itemAmount;
            }

            public void setItemAmount(double itemAmount) {
                this.itemAmount = itemAmount;
            }

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

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public Object getCgOrderId() {
                return cgOrderId;
            }

            public void setCgOrderId(Object cgOrderId) {
                this.cgOrderId = cgOrderId;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public static class IdBean {
                /**
                 * itemId : 1
                 * cgOrderId : 1
                 */

                private int itemId;
                private int cgOrderId;

                public int getItemId() {
                    return itemId;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }

                public int getCgOrderId() {
                    return cgOrderId;
                }

                public void setCgOrderId(int cgOrderId) {
                    this.cgOrderId = cgOrderId;
                }
            }
        }
    }
}
