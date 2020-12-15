package com.example.bq.edmp.work.shipments.bean;


import java.util.List;

public class DshipmentsListBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"salesId":null,"addedTime":null,"addedOperator":null,"status":"1","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":null,"warehouseName":null,"customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null},"rows":[{"id":5,"owner":null,"code":"133","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"100公斤/袋","unitWeight":3000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"200公斤/袋","unitWeight":1000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"160公斤/袋","unitWeight":1300,"units":"公斤","varietyName":"一号小麦"}],"orderSendOut":null},{"id":6,"owner":null,"code":"43","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null},{"id":7,"owner":null,"code":"2343","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":3,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"salesId":null,"addedTime":null,"addedOperator":null,"status":"1","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":null,"warehouseName":null,"customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null}
         * rows : [{"id":5,"owner":null,"code":"133","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"100公斤/袋","unitWeight":3000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"200公斤/袋","unitWeight":1000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"160公斤/袋","unitWeight":1300,"units":"公斤","varietyName":"一号小麦"}],"orderSendOut":null},{"id":6,"owner":null,"code":"43","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null},{"id":7,"owner":null,"code":"2343","customerId":null,"orgId":null,"warehouseId":null,"salesId":1,"addedTime":"2020-12-09","addedOperator":null,"status":"5","submitTime":null,"submitOperator":null,"approvedTime":null,"sendOutTime":null,"signedTime":null,"orgIds":null,"beginTime":null,"endTime":null,"orgName":"北京分公司","warehouseName":"1号仓库","customerName":null,"salesman":null,"orderItems":[],"orderSendOut":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 3
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private int page;
        private int pagerow;
        private int totalpages;
        private int totalrows;
        private int sumtotal;
        private String sortname;
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

        public int getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(int sumtotal) {
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
             * orgId : 2
             * warehouseId : null
             * salesId : null
             * addedTime : null
             * addedOperator : null
             * status : 1
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * sendOutTime : null
             * signedTime : null
             * orgIds : null
             * beginTime : null
             * endTime : null
             * orgName : null
             * warehouseName : null
             * customerName : null
             * salesman : null
             * orderItems : []
             * orderSendOut : null
             */

            private Object id;
            private int owner;
            private Object code;
            private Object customerId;
            private int orgId;
            private Object warehouseId;
            private Object salesId;
            private Object addedTime;
            private Object addedOperator;
            private String status;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object sendOutTime;
            private Object signedTime;
            private Object orgIds;
            private Object beginTime;
            private Object endTime;
            private Object orgName;
            private Object warehouseName;
            private Object customerName;
            private Object salesman;
            private Object orderSendOut;
            private List<?> orderItems;

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

            public int getOrgId() {
                return orgId;
            }

            public void setOrgId(int orgId) {
                this.orgId = orgId;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public Object getSalesId() {
                return salesId;
            }

            public void setSalesId(Object salesId) {
                this.salesId = salesId;
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

            public Object getSendOutTime() {
                return sendOutTime;
            }

            public void setSendOutTime(Object sendOutTime) {
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

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public Object getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(Object warehouseName) {
                this.warehouseName = warehouseName;
            }

            public Object getCustomerName() {
                return customerName;
            }

            public void setCustomerName(Object customerName) {
                this.customerName = customerName;
            }

            public Object getSalesman() {
                return salesman;
            }

            public void setSalesman(Object salesman) {
                this.salesman = salesman;
            }

            public Object getOrderSendOut() {
                return orderSendOut;
            }

            public void setOrderSendOut(Object orderSendOut) {
                this.orderSendOut = orderSendOut;
            }

            public List<?> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<?> orderItems) {
                this.orderItems = orderItems;
            }
        }

        public static class RowsBean {
            /**
             * id : 5
             * owner : null
             * code : 133
             * customerId : null
             * orgId : null
             * warehouseId : null
             * salesId : 1
             * addedTime : 2020-12-09
             * addedOperator : null
             * status : 5
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * sendOutTime : null
             * signedTime : null
             * orgIds : null
             * beginTime : null
             * endTime : null
             * orgName : 北京分公司
             * warehouseName : 1号仓库
             * customerName : null
             * salesman : null
             * orderItems : [{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"100公斤/袋","unitWeight":3000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"200公斤/袋","unitWeight":1000,"units":"公斤","varietyName":"一号小麦"},{"id":null,"qty":null,"price":null,"ordersId":5,"packagingName":"160公斤/袋","unitWeight":1300,"units":"公斤","varietyName":"一号小麦"}]
             * orderSendOut : null
             */

            private String id;
            private String owner;
            private String code;
            private String customerId;
            private String orgId;
            private String warehouseId;
            private String salesId;
            private String addedTime;
            private String addedOperator;
            private String status;
            private String submitTime;
            private String submitOperator;
            private String approvedTime;
            private String sendOutTime;
            private String signedTime;
            private String orgIds;
            private String beginTime;
            private String endTime;
            private String orgName;
            private String warehouseName;
            private String customerName;
            private String salesman;
            private String orderSendOut;
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

            public String getOrgId() {
                return orgId;
            }

            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            public String getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(String warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getSalesId() {
                return salesId;
            }

            public void setSalesId(String salesId) {
                this.salesId = salesId;
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

            public String getApprovedTime() {
                return approvedTime;
            }

            public void setApprovedTime(String approvedTime) {
                this.approvedTime = approvedTime;
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

            public String getOrgIds() {
                return orgIds;
            }

            public void setOrgIds(String orgIds) {
                this.orgIds = orgIds;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
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

            public String getOrderSendOut() {
                return orderSendOut;
            }

            public void setOrderSendOut(String orderSendOut) {
                this.orderSendOut = orderSendOut;
            }

            public List<OrderItemsBean> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<OrderItemsBean> orderItems) {
                this.orderItems = orderItems;
            }

            public static class OrderItemsBean {
                /**
                 * id : null
                 * qty : null
                 * price : null
                 * ordersId : 5
                 * packagingName : 100公斤/袋
                 * unitWeight : 3000
                 * units : 公斤
                 * varietyName : 一号小麦
                 */

                private String id;
                private String qty;
                private String price;
                private String ordersId;
                private String packagingName;
                private String unitWeight;
                private String units;
                private String varietyName;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getQty() {
                    return qty;
                }

                public void setQty(String qty) {
                    this.qty = qty;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOrdersId() {
                    return ordersId;
                }

                public void setOrdersId(String ordersId) {
                    this.ordersId = ordersId;
                }

                public String getPackagingName() {
                    return packagingName;
                }

                public void setPackagingName(String packagingName) {
                    this.packagingName = packagingName;
                }

                public String getUnitWeight() {
                    return unitWeight;
                }

                public void setUnitWeight(String unitWeight) {
                    this.unitWeight = unitWeight;
                }

                public String getUnits() {
                    return units;
                }

                public void setUnits(String units) {
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
}
