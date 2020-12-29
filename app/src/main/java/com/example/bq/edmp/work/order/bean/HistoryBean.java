package com.example.bq.edmp.work.order.bean;

import java.util.List;

public class HistoryBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":0,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":null,"customerName":null,"saleName":null,"balance":null},"rows":[{"id":35,"owner":null,"code":"DD20201223150442","customerId":null,"salesId":null,"types":null,"region":"110107","contacts":"123","mobTel":"123","address":"123","addedTime":"2020-12-23 15:04","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"爱种网","saleName":null,"balance":null},{"id":36,"owner":null,"code":"DD20201223150540","customerId":null,"salesId":null,"types":null,"region":"110109","contacts":"456","mobTel":"654","address":"456","addedTime":"2020-12-23 15:05","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"七喜vs冰红茶","saleName":null,"balance":null},{"id":37,"owner":null,"code":"DD20201223150923","customerId":null,"salesId":null,"types":null,"region":"110105","contacts":"老王","mobTel":"123456789","address":"金台路","addedTime":"2020-12-23 15:09","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null},{"id":38,"owner":null,"code":"DD20201223151051","customerId":null,"salesId":null,"types":null,"region":"110102","contacts":"345","mobTel":"345","address":"345","addedTime":"2020-12-23 15:10","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null},{"id":39,"owner":null,"code":"DD20201223151837","customerId":null,"salesId":null,"types":null,"region":"110106","contacts":"123","mobTel":"123","address":"123","addedTime":"2020-12-23 15:18","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":5,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":0,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":null,"customerName":null,"saleName":null,"balance":null}
         * rows : [{"id":35,"owner":null,"code":"DD20201223150442","customerId":null,"salesId":null,"types":null,"region":"110107","contacts":"123","mobTel":"123","address":"123","addedTime":"2020-12-23 15:04","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"爱种网","saleName":null,"balance":null},{"id":36,"owner":null,"code":"DD20201223150540","customerId":null,"salesId":null,"types":null,"region":"110109","contacts":"456","mobTel":"654","address":"456","addedTime":"2020-12-23 15:05","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"七喜vs冰红茶","saleName":null,"balance":null},{"id":37,"owner":null,"code":"DD20201223150923","customerId":null,"salesId":null,"types":null,"region":"110105","contacts":"老王","mobTel":"123456789","address":"金台路","addedTime":"2020-12-23 15:09","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null},{"id":38,"owner":null,"code":"DD20201223151051","customerId":null,"salesId":null,"types":null,"region":"110102","contacts":"345","mobTel":"345","address":"345","addedTime":"2020-12-23 15:10","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null},{"id":39,"owner":null,"code":"DD20201223151837","customerId":null,"salesId":null,"types":null,"region":"110106","contacts":"123","mobTel":"123","address":"123","addedTime":"2020-12-23 15:18","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐","saleName":null,"balance":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 5
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private String page;
        private String pagerow;
        private String totalpages;
        private String totalrows;
        private String sumtotal;
        private String sortname;
        private String sortorder;
        private List<RowsBean> rows;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPagerow() {
            return pagerow;
        }

        public void setPagerow(String pagerow) {
            this.pagerow = pagerow;
        }

        public String getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(String totalpages) {
            this.totalpages = totalpages;
        }

        public String getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(String totalrows) {
            this.totalrows = totalrows;
        }

        public String getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(String sumtotal) {
            this.sumtotal = sumtotal;
        }

        public String getSortname() {
            return sortname;
        }

        public void setSortname(String sortname) {
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
             * salesId : null
             * types : null
             * region : null
             * contacts : null
             * mobTel : null
             * address : null
             * addedTime : null
             * addedOperator : null
             * status : 0
             * submitTime : null
             * submitOperator : null
             * allocationTime : null
             * allocationOperator : null
             * paidConfirmTime : null
             * paidConfirmOperator : null
             * sendOutTime : null
             * signedTime : null
             * amount : null
             * orderItems : null
             * customerName : null
             * saleName : null
             * balance : null
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
            private String amount;
            private String orderItems;
            private String customerName;
            private String saleName;
            private String balance;

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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(String orderItems) {
                this.orderItems = orderItems;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }
        }

        public static class RowsBean {
            /**
             * id : 35
             * owner : null
             * code : DD20201223150442
             * customerId : null
             * salesId : null
             * types : null
             * region : 110107
             * contacts : 123
             * mobTel : 123
             * address : 123
             * addedTime : 2020-12-23 15:04
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
             * amount : null
             * orderItems : []
             * customerName : 爱种网
             * saleName : null
             * balance : null
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
            private String amount;
            private String customerName;
            private String saleName;
            private String balance;
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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public List<OrderItemsBean> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<OrderItemsBean> orderItems) {
                this.orderItems = orderItems;
            }

            public static class OrderItemsBean {
                private String packagingName;//	包装名称	string

                public String getPackagingName() {
                    return packagingName;
                }

                public void setPackagingName(String packagingName) {
                    this.packagingName = packagingName;
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

                private String price;//	价格	number
                private String qty;//	数量	number



            }
        }
    }
}
