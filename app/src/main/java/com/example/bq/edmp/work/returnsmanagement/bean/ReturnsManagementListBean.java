package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class ReturnsManagementListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"ordersId":null,"types":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":null,"addedTime":null,"addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":null,"orderCode":null,"orderCustomerName":null,"sendOutTimes":null,"varietyName":null},"rows":[{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"}],"page":1,"pagerow":15,"totalpages":0,"totalrows":6,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"ordersId":null,"types":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":null,"addedTime":null,"addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":null,"orderCode":null,"orderCustomerName":null,"sendOutTimes":null,"varietyName":null}
         * rows : [{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"},{"id":1,"owner":null,"code":"D437589","customerId":null,"ordersId":null,"types":1,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"salesId":null,"amount":0,"addedTime":"2021-01-12T11:20:18.000+0000","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"type":null,"returnQty":10,"orderCode":"1","orderCustomerName":"123","sendOutTimes":null,"varietyName":"一号小麦 100公斤/袋"}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 6
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
             * salesId : null
             * amount : null
             * addedTime : null
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
             * orderCustomerName : null
             * sendOutTimes : null
             * varietyName : null
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
            private Object salesId;
            private Object amount;
            private Object addedTime;
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
            private Object orderCustomerName;
            private Object sendOutTimes;
            private Object varietyName;

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

            public Object getSalesId() {
                return salesId;
            }

            public void setSalesId(Object salesId) {
                this.salesId = salesId;
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
        }

        public static class RowsBean {
            /**
             * id : 1
             * owner : null
             * code : D437589
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
             * amount : 0.0
             * addedTime : 2021-01-12T11:20:18.000+0000
             * addedOperator : null
             * status : 1
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * finishedOperator : null
             * finishedTime : null
             * type : null
             * returnQty : 10.0
             * orderCode : 1
             * orderCustomerName : 123
             * sendOutTimes : null
             * varietyName : 一号小麦 100公斤/袋
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
            private String sendOutTimes;
            private String varietyName;
            private String customerName;
            private double salesAmount;

            public double getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(double salesAmount) {
                this.salesAmount = salesAmount;
            }

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
        }
    }
}
