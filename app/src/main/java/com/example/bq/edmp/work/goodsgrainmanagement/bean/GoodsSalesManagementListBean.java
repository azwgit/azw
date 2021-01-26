package com.example.bq.edmp.work.goodsgrainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsSalesManagementListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":null,"addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":null,"warehouseName":null,"customerName":null,"cgOrderItems":null},"rows":[{"id":1,"owner":null,"code":"12","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":0,"addedTime":"2021-01-25 13:18","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"大豆仓库","customerName":"123","cgOrderItems":[{"id":{"itemId":1,"cgOrderId":1},"qty":13,"price":321,"itemId":null,"cgOrderId":null}]}],"page":1,"pagerow":15,"totalpages":0,"totalrows":1,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":null,"addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":null,"warehouseName":null,"customerName":null,"cgOrderItems":null}
         * rows : [{"id":1,"owner":null,"code":"12","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":0,"addedTime":"2021-01-25 13:18","addedOperator":null,"status":1,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"大豆仓库","customerName":"123","cgOrderItems":[{"id":{"itemId":1,"cgOrderId":1},"qty":13,"price":321,"itemId":null,"cgOrderId":null}]}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 1
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
             * orgId : 2
             * warehouseId : null
             * contacts : null
             * mobTel : null
             * region : null
             * address : null
             * amount : null
             * addedTime : null
             * addedOperator : null
             * status : 1
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * substockOperator : null
             * substockTime : null
             * finishedOperator : null
             * finishedTime : null
             * orgName : null
             * warehouseName : null
             * customerName : null
             * cgOrderItems : null
             */

            private Object id;
            private int owner;
            private Object code;
            private Object customerId;
            private int orgId;
            private Object warehouseId;
            private Object contacts;
            private Object mobTel;
            private Object region;
            private Object address;
            private Object amount;
            private Object addedTime;
            private Object addedOperator;
            private int status;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object substockOperator;
            private Object substockTime;
            private Object finishedOperator;
            private Object finishedTime;
            private Object orgName;
            private Object warehouseName;
            private Object customerName;
            private Object cgOrderItems;

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

            public Object getSubstockOperator() {
                return substockOperator;
            }

            public void setSubstockOperator(Object substockOperator) {
                this.substockOperator = substockOperator;
            }

            public Object getSubstockTime() {
                return substockTime;
            }

            public void setSubstockTime(Object substockTime) {
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

            public Object getCgOrderItems() {
                return cgOrderItems;
            }

            public void setCgOrderItems(Object cgOrderItems) {
                this.cgOrderItems = cgOrderItems;
            }
        }

        public static class RowsBean {
            /**
             * id : 1
             * owner : null
             * code : 12
             * customerId : null
             * orgId : null
             * warehouseId : null
             * contacts : null
             * mobTel : null
             * region : null
             * address : null
             * amount : 0.0
             * addedTime : 2021-01-25 13:18
             * addedOperator : null
             * status : 1
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
             * cgOrderItems : [{"id":{"itemId":1,"cgOrderId":1},"qty":13,"price":321,"itemId":null,"cgOrderId":null}]
             */

            private int id;
            private Object owner;
            private String code;
            private Object customerId;
            private Object orgId;
            private Object warehouseId;
            private Object contacts;
            private Object mobTel;
            private Object region;
            private Object address;
            private double amount;
            private String addedTime;
            private String addedOperator;
            private int status;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object substockOperator;
            private Object substockTime;
            private Object finishedOperator;
            private Object finishedTime;
            private String orgName;
            private String warehouseName;
            private String customerName;
            private List<CgOrderItemsBean> cgOrderItems;

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

            public Object getSubstockOperator() {
                return substockOperator;
            }

            public void setSubstockOperator(Object substockOperator) {
                this.substockOperator = substockOperator;
            }

            public Object getSubstockTime() {
                return substockTime;
            }

            public void setSubstockTime(Object substockTime) {
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
                 */

                private IdBean id;
                private double qty;
                private double price;
                private Object itemId;
                private Object cgOrderId;
                private String itemName;

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
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

                public Object getItemId() {
                    return itemId;
                }

                public void setItemId(Object itemId) {
                    this.itemId = itemId;
                }

                public Object getCgOrderId() {
                    return cgOrderId;
                }

                public void setCgOrderId(Object cgOrderId) {
                    this.cgOrderId = cgOrderId;
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
}
