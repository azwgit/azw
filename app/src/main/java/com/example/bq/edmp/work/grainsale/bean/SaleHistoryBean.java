package com.example.bq.edmp.work.grainsale.bean;

import java.util.List;

public class SaleHistoryBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"customerId":null,"orgId":2,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":null,"addedOperator":null,"status":10,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":null,"warehouseName":null,"customerName":null,"cgOrderItems":null},"rows":[{"id":1,"owner":null,"code":"12","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":0,"addedTime":"2021-01-25 13:18","addedOperator":"132","status":5,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"大豆仓库","customerName":"123","cgOrderItems":[{"id":null,"qty":13,"price":321,"itemId":1,"cgOrderId":1,"itemName":"哈哈"}]},{"id":2,"owner":null,"code":"XX20210125175942","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":"2021-01-25 17:59","addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"尖椒仓库","customerName":"雪碧vs可乐","cgOrderItems":[]},{"id":3,"owner":null,"code":"XX20210125183819","customerId":null,"orgId":null,"warehouseId":null,"contacts":null,"mobTel":null,"region":null,"address":null,"amount":null,"addedTime":"2021-01-25 18:38","addedOperator":null,"status":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"substockOperator":null,"substockTime":null,"finishedOperator":null,"finishedTime":null,"orgName":"北京分公司","warehouseName":"尖椒仓库","customerName":"雪碧vs可乐","cgOrderItems":[]}],"page":1,"pagerow":15,"totalpages":0,"totalrows":3,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
             * orgId : 2
             * warehouseId : null
             * contacts : null
             * mobTel : null
             * region : null
             * address : null
             * amount : null
             * addedTime : null
             * addedOperator : null
             * status : 10
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

            private String id;
            private String owner;
            private String code;
            private String customerId;
            private String orgId;
            private String warehouseId;
            private String contacts;
            private String mobTel;
            private String region;
            private String address;
            private String amount;
            private String addedTime;
            private String addedOperator;
            private String status;
            private String submitTime;
            private String submitOperator;
            private String approvedTime;
            private String substockOperator;
            private String substockTime;
            private String finishedOperator;
            private String finishedTime;
            private String orgName;
            private String warehouseName;
            private String customerName;
            private String cgOrderItems;

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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
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

            public String getCgOrderItems() {
                return cgOrderItems;
            }

            public void setCgOrderItems(String cgOrderItems) {
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
             * amount : 0
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
             * cgOrderItems : [{"id":null,"qty":13,"price":321,"itemId":1,"cgOrderId":1,"itemName":"哈哈"}]
             */

            private String id;
            private String owner;
            private String code;
            private String customerId;
            private String orgId;
            private String warehouseId;
            private String contacts;
            private String mobTel;
            private String region;
            private String address;
            private String amount;
            private String addedTime;
            private String addedOperator;
            private String status;
            private String submitTime;
            private String submitOperator;
            private String approvedTime;
            private String substockOperator;
            private String substockTime;
            private String finishedOperator;
            private String finishedTime;
            private String orgName;
            private String warehouseName;
            private String customerName;
            private List<CgOrderItemsBean> cgOrderItems;

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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
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
                 * id : null
                 * qty : 13
                 * price : 321
                 * itemId : 1
                 * cgOrderId : 1
                 * itemName : 哈哈
                 */

                private String id;
                private String qty;
                private String price;
                private String itemId;
                private String cgOrderId;
                private String itemName;

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

                public String getItemId() {
                    return itemId;
                }

                public void setItemId(String itemId) {
                    this.itemId = itemId;
                }

                public String getCgOrderId() {
                    return cgOrderId;
                }

                public void setCgOrderId(String cgOrderId) {
                    this.cgOrderId = cgOrderId;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }
            }
        }
    }
}
