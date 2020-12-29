package com.example.bq.edmp.work.order.bean;

import java.util.List;

public class OrderTJBean {
    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":null,"customerName":null},"rows":[{"id":21,"owner":null,"code":"DD20201222142253","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":39204,"orderItems":[{"id":{"ordersId":21,"packagingId":1},"qty":100,"price":99,"settlement":9900,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null},{"id":{"ordersId":21,"packagingId":6},"qty":444,"price":66,"settlement":29304,"ordersId":null,"packagingId":null,"packagingName":"300公斤/袋","tplNo":null,"sendOutStatus":null}],"customerName":"王二麻子"},{"id":20,"owner":null,"code":"DD20201222142215","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":19,"owner":null,"code":"DD20201222142038","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:20","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":18,"owner":null,"code":"DD20201222140219","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"乾隆","mobTel":"13514533689","address":"北京市 东城区","addedTime":"2020-12-22 14:02","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐"},{"id":17,"owner":null,"code":"DD20201222140054","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"乐乐呵呵","mobTel":"13514533698","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:00","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"嘻嘻哈哈"},{"id":16,"owner":null,"code":"DD20201222135601","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:56","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":15,"owner":null,"code":"DD20201222135312","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:53","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":13,"owner":null,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":"订单1","mobTel":"1234","address":"地址1","addedTime":"2020-12-22 09:46","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":0,"orderItems":[{"id":{"ordersId":13,"packagingId":1},"qty":22,"price":1,"settlement":22,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null}],"customerName":"123"}],"page":1,"pagerow":15,"totalpages":0,"totalrows":8,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":null,"customerName":null}
         * rows : [{"id":21,"owner":null,"code":"DD20201222142253","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":39204,"orderItems":[{"id":{"ordersId":21,"packagingId":1},"qty":100,"price":99,"settlement":9900,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null},{"id":{"ordersId":21,"packagingId":6},"qty":444,"price":66,"settlement":29304,"ordersId":null,"packagingId":null,"packagingName":"300公斤/袋","tplNo":null,"sendOutStatus":null}],"customerName":"王二麻子"},{"id":20,"owner":null,"code":"DD20201222142215","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":19,"owner":null,"code":"DD20201222142038","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:20","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":18,"owner":null,"code":"DD20201222140219","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"乾隆","mobTel":"13514533689","address":"北京市 东城区","addedTime":"2020-12-22 14:02","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"雪碧vs可乐"},{"id":17,"owner":null,"code":"DD20201222140054","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"乐乐呵呵","mobTel":"13514533698","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:00","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"嘻嘻哈哈"},{"id":16,"owner":null,"code":"DD20201222135601","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:56","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":15,"owner":null,"code":"DD20201222135312","customerId":null,"salesId":null,"types":null,"region":null,"contacts":"","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:53","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null,"orderItems":[],"customerName":"王二麻子"},{"id":13,"owner":null,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":"订单1","mobTel":"1234","address":"地址1","addedTime":"2020-12-22 09:46","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":0,"orderItems":[{"id":{"ordersId":13,"packagingId":1},"qty":22,"price":1,"settlement":22,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null}],"customerName":"123"}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 8
         * sumtotal : 0.0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private String page;
        private String pagerow;
        private String totalpages;
        private String totalrows;
        private double sumtotal;
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

        public double getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(double sumtotal) {
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
             * status : null
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
        }

        public static class RowsBean {
            /**
             * id : 21
             * owner : null
             * code : DD20201222142253
             * customerId : null
             * salesId : null
             * types : null
             * region : null
             * contacts : 海蛎煎
             * mobTel : 1351433869
             * address : 山东省 东营市 东营区
             * addedTime : 2020-12-22 14:22
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
             * amount : 39204.0
             * orderItems : [{"id":{"ordersId":21,"packagingId":1},"qty":100,"price":99,"settlement":9900,"ordersId":null,"packagingId":null,"packagingName":"100公斤/袋","tplNo":null,"sendOutStatus":null},{"id":{"ordersId":21,"packagingId":6},"qty":444,"price":66,"settlement":29304,"ordersId":null,"packagingId":null,"packagingName":"300公斤/袋","tplNo":null,"sendOutStatus":null}]
             * customerName : 王二麻子
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
                 * id : {"ordersId":21,"packagingId":1}
                 * qty : 100.0
                 * price : 99.0
                 * settlement : 9900.0
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
                     * ordersId : 21
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
    }




    /*   *//**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},"rows":[{"id":13,"owner":1,"code":null,"customerId":1,"salesId":16,"types":1,"region":null,"contacts":"订单1","mobTel":"1234","address":"地址1","addedTime":"2020-12-22 09:46","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":0},{"id":15,"owner":1,"code":"DD20201222135312","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:53","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":16,"owner":1,"code":"DD20201222135601","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:56","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":17,"owner":1,"code":"DD20201222140054","customerId":23,"salesId":16,"types":1,"region":null,"contacts":"乐乐呵呵","mobTel":"13514533698","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:00","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":18,"owner":1,"code":"DD20201222140219","customerId":24,"salesId":16,"types":1,"region":null,"contacts":"乾隆","mobTel":"13514533689","address":"北京市 东城区","addedTime":"2020-12-22 14:02","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":19,"owner":1,"code":"DD20201222142038","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:20","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":20,"owner":1,"code":"DD20201222142215","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":21,"owner":1,"code":"DD20201222142253","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":8,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
        *//**
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"salesId":null,"types":null,"region":null,"contacts":null,"mobTel":null,"address":null,"addedTime":null,"addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null}
         * rows : [{"id":13,"owner":1,"code":null,"customerId":1,"salesId":16,"types":1,"region":null,"contacts":"订单1","mobTel":"1234","address":"地址1","addedTime":"2020-12-22 09:46","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":0},{"id":15,"owner":1,"code":"DD20201222135312","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:53","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":16,"owner":1,"code":"DD20201222135601","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 13:56","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":17,"owner":1,"code":"DD20201222140054","customerId":23,"salesId":16,"types":1,"region":null,"contacts":"乐乐呵呵","mobTel":"13514533698","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:00","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":18,"owner":1,"code":"DD20201222140219","customerId":24,"salesId":16,"types":1,"region":null,"contacts":"乾隆","mobTel":"13514533689","address":"北京市 东城区","addedTime":"2020-12-22 14:02","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":19,"owner":1,"code":"DD20201222142038","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:20","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":20,"owner":1,"code":"DD20201222142215","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null},{"id":21,"owner":1,"code":"DD20201222142253","customerId":22,"salesId":16,"types":1,"region":null,"contacts":"海蛎煎","mobTel":"1351433869","address":"山东省 东营市 东营区","addedTime":"2020-12-22 14:22","addedOperator":"李四","status":1,"submitTime":null,"submitOperator":null,"allocationTime":null,"allocationOperator":null,"paidConfirmTime":null,"paidConfirmOperator":null,"sendOutTime":null,"signedTime":null,"amount":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 8
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         *//*

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
            *//**
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
             * status : null
             * submitTime : null
             * submitOperator : null
             * allocationTime : null
             * allocationOperator : null
             * paidConfirmTime : null
             * paidConfirmOperator : null
             * sendOutTime : null
             * signedTime : null
             * amount : null
             *//*

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
        }

        public static class RowsBean {
            *//**
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
             * amount : 0
             *//*

            private String id;
            private String owner;
            private String code;
            private String customerId;
            private String customerName;
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
            private List<OrderItem> orderItem;

            public String getCustomerName() {
                return customerName;

            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public List<OrderItem> getOrderItem() {
                return orderItem;
            }

            public void setOrderItem(List<OrderItem> orderItem) {
                this.orderItem = orderItem;
            }

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

            public static class OrderItem {

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

                private String packagingName;//	包装名称	string
                private String price;//	价格	number
                private String qty;//	提货量	number

            }
        }
    }*/
}
