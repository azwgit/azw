package com.example.bq.edmp.work.grainsale.bean;

import java.util.List;

public class SaleHistoryDetailsBean {

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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":null,"addedOperator":null,"status":10,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":null,"warehouseName":null,"customerName":null,"cgOrderItems":null}
         * rows : [{"id":1,"owner":null,"code":"12","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":0,"addedTime":"2021-01-25 13:18","addedOperator":"132","status":5,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"大豆仓库","customerName":"123","cgOrderItems":[{"id":null,"qty":13,"price":321,"itemId":1,"cgOrderId":1,"itemName":"哈哈"}]},{"id":2,"owner":null,"code":"XX20210125175942","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":"2021-01-25 17:59","addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"尖椒仓库","customerName":"雪碧vs可乐","cgOrderItems":[]},{"id":3,"owner":null,"code":"XX20210125183819","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":"2021-01-25 18:38","addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"尖椒仓库","customerName":"雪碧vs可乐","cgOrderItems":[]}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 3
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         */

        private String amount;
        private String addedOperator;
        private String addedTime;
        private String address;
        private String approvedTime;
        private String code;
        private String contacts;
        private String customerName;
        private String finishedOperator;
        private String finishedTime;
        private String mobTel;
        private String orgName;
        private String region;
        private String status;
        private String submitOperator;
        private String submitTime;
        private String substockOperator;
        private String substockTime;
        private String warehouseName;
        private List<CgOrderItemsBean> cgOrderItems;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(String approvedTime) {
            this.approvedTime = approvedTime;
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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(String finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSubmitOperator() {
            return submitOperator;
        }

        public void setSubmitOperator(String submitOperator) {
            this.submitOperator = submitOperator;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public String getSubstockOperator() {
            return substockOperator;
        }

        public void setSubstockOperator(String substockOperator) {
            this.substockOperator = substockOperator;
        }

        public String getSubstockTime() {
            return substockTime;
        }

        public void setSubstockTime(String substockTime) {
            this.substockTime = substockTime;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }



        public List<CgOrderItemsBean> getCgOrderItems() {
            return cgOrderItems;
        }

        public void setCgOrderItems(List<CgOrderItemsBean> cgOrderItems) {
            this.cgOrderItems = cgOrderItems;
        }

        public static class CgOrderItemsBean {
            /**
             * id : null
             * qty : 13
             * price : 321
             * itemId : 1
             * cgOrderId : 1
             * itemName : 哈哈
             */

            private String cgOrderId;
            private String itemName;
            private String itemId;

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getCgOrderId() {
                return cgOrderId;
            }

            public void setCgOrderId(String cgOrderId) {
                this.cgOrderId = cgOrderId;
            }

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            private String price;
            private String qty;

        }



    }
}
