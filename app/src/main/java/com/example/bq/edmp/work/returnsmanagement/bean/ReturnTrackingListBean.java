package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class ReturnTrackingListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"ordersId":null,"types":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":16,"cgCustomerId":null,"amount":null,"addedTime":null,"addedOperator":null,"status":0,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":null,"orderCode":null,"orderCustomerName":null,"sendOutTimes":null,"varietyName":null,"itemId":null,"price":null},"rows":[{"id":11,"owner":null,"code":"TH20210113132152","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":1000,"addedTime":"2021-01-13T05:21:52.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":50,"orderCode":"DD20201223151051","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null},{"id":12,"owner":null,"code":"TH20210113132436","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":1200,"addedTime":"2021-01-13T05:24:36.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":60,"orderCode":"DD20201223151051","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null},{"id":13,"owner":null,"code":"TH20210113141005","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":550,"addedTime":"2021-01-13T06:10:05.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":50,"orderCode":"DD20201223150923","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":3,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"ordersId":null,"types":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":16,"cgCustomerId":null,"amount":null,"addedTime":null,"addedOperator":null,"status":0,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":null,"orderCode":null,"orderCustomerName":null,"sendOutTimes":null,"varietyName":null,"itemId":null,"price":null}
         * rows : [{"id":11,"owner":null,"code":"TH20210113132152","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":1000,"addedTime":"2021-01-13T05:21:52.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":50,"orderCode":"DD20201223151051","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null},{"id":12,"owner":null,"code":"TH20210113132436","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":1200,"addedTime":"2021-01-13T05:24:36.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":60,"orderCode":"DD20201223151051","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null},{"id":13,"owner":null,"code":"TH20210113141005","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"cgCustomerId":null,"amount":550,"addedTime":"2021-01-13T06:10:05.000+0000","addedOperator":null,"status":2,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":50,"orderCode":"DD20201223150923","orderCustomerName":"雪碧vs可乐","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋","itemId":null,"price":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 3
         * sumtotal : 0.0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private int page;
        private int pagerow;
        private int totalpages;
        private int totalrows;
        private double sumtotal;
        private Object sortname;
        private String sortorder;
        private List<RowsBean> rows;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPagerow() {
            return pagerow;
        }

        public void setPagerow(int pagerow) {
            this.pagerow = pagerow;
        }

        public int getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(int totalpages) {
            this.totalpages = totalpages;
        }

        public int getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }

        public double getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(double sumtotal) {
            this.sumtotal = sumtotal;
        }

        public Object getSortname() {
            return sortname;
        }

        public void setSortname(Object sortname) {
            this.sortname = sortname;
        }

        public String getSortorder() {
            return sortorder;
        }

        public void setSortorder(String sortorder) {
            this.sortorder = sortorder;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class FilterBean {
            /**
             * id : null
             * owner : 1
             * code : null
             * customerId : null
             * ordersId : null
             * types : null
             * orgId : null
             * warehouseId : null
             * contacts : null
             * mobTel : null
             * region : null
             * address : null
             * salesId : 16
             * cgCustomerId : null
             * amount : null
             * addedTime : null
             * addedOperator : null
             * status : 0
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * finishedOperator : null
             * finishedTime : null
             * type : null
             * returnQty : null
             * orderCode : null
             * orderCustomerName : null
             * sendOutTimes : null
             * varietyName : null
             * itemId : null
             * price : null
             */

            private Object id;
            private int owner;
            private Object code;
            private Object customerId;
            private Object ordersId;
            private Object types;
            private Object orgId;
            private Object warehouseId;
            private Object contacts;
            private Object mobTel;
            private Object region;
            private Object address;
            private int salesId;
            private Object cgCustomerId;
            private Object amount;
            private Object addedTime;
            private Object addedOperator;
            private int status;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object finishedOperator;
            private Object finishedTime;
            private Object type;
            private Object returnQty;
            private Object orderCode;
            private Object orderCustomerName;
            private Object sendOutTimes;
            private Object varietyName;
            private Object itemId;
            private Object price;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public int getOwner() {
                return owner;
            }

            public void setOwner(int owner) {
                this.owner = owner;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public Object getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(Object ordersId) {
                this.ordersId = ordersId;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
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

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
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

            public Object getAmount() {
                return amount;
            }

            public void setAmount(Object amount) {
                this.amount = amount;
            }

            public Object getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(Object addedTime) {
                this.addedTime = addedTime;
            }

            public Object getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(Object addedOperator) {
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

            public Object getOrderCustomerName() {
                return orderCustomerName;
            }

            public void setOrderCustomerName(Object orderCustomerName) {
                this.orderCustomerName = orderCustomerName;
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

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }
        }

        public static class RowsBean {
            /**
             * id : 11
             * owner : null
             * code : TH20210113132152
             * customerId : null
             * ordersId : null
             * types : 1
             * orgId : null
             * warehouseId : null
             * contacts : null
             * mobTel : null
             * region : null
             * address : null
             * salesId : null
             * cgCustomerId : null
             * amount : 1000.0
             * addedTime : 2021-01-13T05:21:52.000+0000
             * addedOperator : null
             * status : 2
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * finishedOperator : null
             * finishedTime : null
             * type : null
             * returnQty : 50.0
             * orderCode : DD20201223151051
             * orderCustomerName : 雪碧vs可乐
             * sendOutTimes : null
             * varietyName : 一号小麦 100公斤/袋
             * itemId : null
             * price : null
             */

            private int id;
            private Object owner;
            private String code;
            private Object customerId;
            private Object ordersId;
            private int types;
            private Object orgId;
            private Object warehouseId;
            private Object contacts;
            private Object mobTel;
            private Object region;
            private Object address;
            private Object salesId;
            private Object cgCustomerId;
            private double amount;
            private String addedTime;
            private Object addedOperator;
            private int status;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object finishedOperator;
            private Object finishedTime;
            private Object type;
            private double returnQty;
            private String orderCode;
            private String orderCustomerName;
            private Object sendOutTimes;
            private String varietyName;
            private Object itemId;
            private Object price;
            private String customerName;

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

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

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public Object getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(Object ordersId) {
                this.ordersId = ordersId;
            }

            public int getTypes() {
                return types;
            }

            public void setTypes(int types) {
                this.types = types;
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

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getSalesId() {
                return salesId;
            }

            public void setSalesId(Object salesId) {
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

            public Object getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(Object addedOperator) {
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

            public String getOrderCustomerName() {
                return orderCustomerName;
            }

            public void setOrderCustomerName(String orderCustomerName) {
                this.orderCustomerName = orderCustomerName;
            }

            public Object getSendOutTimes() {
                return sendOutTimes;
            }

            public void setSendOutTimes(Object sendOutTimes) {
                this.sendOutTimes = sendOutTimes;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public Object getItemId() {
                return itemId;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }
        }
    }
}
