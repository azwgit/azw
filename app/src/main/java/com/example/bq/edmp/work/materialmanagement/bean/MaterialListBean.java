package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class MaterialListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"code":null,"orgId":2,"deptId":null,"remark":null,"amount":null,"warehouseId":null,"status":1,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":null,"materialPurchaseAnnexs":null,"deptName":null,"username":null,"itemId":null},"rows":[{"id":1,"owner":null,"code":"1","orgId":null,"deptId":null,"remark":null,"amount":1,"warehouseId":null,"status":1,"addedTime":"2021-02-19 15:21","addedOperator":"1","addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":1,"itemId":1},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null},{"id":{"materialPurchaseId":1,"itemId":21},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null}],"materialPurchaseAnnexs":null,"deptName":"北京爱种网络科技有限公司,/北京分公司","username":null,"itemId":null},{"id":1,"owner":null,"code":"1","orgId":null,"deptId":null,"remark":null,"amount":1,"warehouseId":null,"status":1,"addedTime":"2021-02-19 15:21","addedOperator":"1","addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":1,"itemId":1},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null},{"id":{"materialPurchaseId":1,"itemId":21},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null}],"materialPurchaseAnnexs":null,"deptName":"北京爱种网络科技有限公司,/北京分公司","username":null,"itemId":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":2,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"code":null,"orgId":2,"deptId":null,"remark":null,"amount":null,"warehouseId":null,"status":1,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":null,"materialPurchaseAnnexs":null,"deptName":null,"username":null,"itemId":null}
         * rows : [{"id":1,"owner":null,"code":"1","orgId":null,"deptId":null,"remark":null,"amount":1,"warehouseId":null,"status":1,"addedTime":"2021-02-19 15:21","addedOperator":"1","addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":1,"itemId":1},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null},{"id":{"materialPurchaseId":1,"itemId":21},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null}],"materialPurchaseAnnexs":null,"deptName":"北京爱种网络科技有限公司,/北京分公司","username":null,"itemId":null},{"id":1,"owner":null,"code":"1","orgId":null,"deptId":null,"remark":null,"amount":1,"warehouseId":null,"status":1,"addedTime":"2021-02-19 15:21","addedOperator":"1","addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":1,"itemId":1},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null},{"id":{"materialPurchaseId":1,"itemId":21},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null}],"materialPurchaseAnnexs":null,"deptName":"北京爱种网络科技有限公司,/北京分公司","username":null,"itemId":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 2
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
             * orgId : 2
             * deptId : null
             * remark : null
             * amount : null
             * warehouseId : null
             * status : 1
             * addedTime : null
             * addedOperator : null
             * addedOperatorId : null
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * finishedOperator : null
             * finishedTime : null
             * materialPurchaseItems : null
             * materialPurchaseAnnexs : null
             * deptName : null
             * username : null
             * itemId : null
             */

            private Object id;
            private int owner;
            private Object code;
            private int orgId;
            private Object deptId;
            private Object remark;
            private Object amount;
            private Object warehouseId;
            private int status;
            private Object addedTime;
            private Object addedOperator;
            private Object addedOperatorId;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object finishedOperator;
            private Object finishedTime;
            private Object materialPurchaseItems;
            private Object materialPurchaseAnnexs;
            private Object deptName;
            private Object username;
            private Object itemId;

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

            public int getOrgId() {
                return orgId;
            }

            public void setOrgId(int orgId) {
                this.orgId = orgId;
            }

            public Object getDeptId() {
                return deptId;
            }

            public void setDeptId(Object deptId) {
                this.deptId = deptId;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getAmount() {
                return amount;
            }

            public void setAmount(Object amount) {
                this.amount = amount;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public Object getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(Object addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
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

            public Object getMaterialPurchaseItems() {
                return materialPurchaseItems;
            }

            public void setMaterialPurchaseItems(Object materialPurchaseItems) {
                this.materialPurchaseItems = materialPurchaseItems;
            }

            public Object getMaterialPurchaseAnnexs() {
                return materialPurchaseAnnexs;
            }

            public void setMaterialPurchaseAnnexs(Object materialPurchaseAnnexs) {
                this.materialPurchaseAnnexs = materialPurchaseAnnexs;
            }

            public Object getDeptName() {
                return deptName;
            }

            public void setDeptName(Object deptName) {
                this.deptName = deptName;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public Object getItemId() {
                return itemId;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }
        }

        public static class RowsBean {
            /**
             * id : 1
             * owner : null
             * code : 1
             * orgId : null
             * deptId : null
             * remark : null
             * amount : 1.0
             * warehouseId : null
             * status : 1
             * addedTime : 2021-02-19 15:21
             * addedOperator : 1
             * addedOperatorId : null
             * submitTime : null
             * submitOperator : null
             * approvedTime : null
             * finishedOperator : null
             * finishedTime : null
             * materialPurchaseItems : [{"id":{"materialPurchaseId":1,"itemId":1},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null},{"id":{"materialPurchaseId":1,"itemId":21},"qty":1,"price":1,"materialPurchaseId":null,"itemId":null}]
             * materialPurchaseAnnexs : null
             * deptName : 北京爱种网络科技有限公司,/北京分公司
             * username : null
             * itemId : null
             */

            private int id;
            private Object owner;
            private String code;
            private Object orgId;
            private Object deptId;
            private Object remark;
            private double amount;
            private Object warehouseId;
            private int status;
            private String addedTime;
            private String addedOperator;
            private Object addedOperatorId;
            private Object submitTime;
            private Object submitOperator;
            private Object approvedTime;
            private Object finishedOperator;
            private Object finishedTime;
            private Object materialPurchaseAnnexs;
            private String deptName;
            private Object username;
            private Object itemId;
            private List<MaterialPurchaseItemsBean> materialPurchaseItems;

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

            public Object getOrgId() {
                return orgId;
            }

            public void setOrgId(Object orgId) {
                this.orgId = orgId;
            }

            public Object getDeptId() {
                return deptId;
            }

            public void setDeptId(Object deptId) {
                this.deptId = deptId;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public Object getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(Object addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
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

            public Object getMaterialPurchaseAnnexs() {
                return materialPurchaseAnnexs;
            }

            public void setMaterialPurchaseAnnexs(Object materialPurchaseAnnexs) {
                this.materialPurchaseAnnexs = materialPurchaseAnnexs;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public Object getItemId() {
                return itemId;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public List<MaterialPurchaseItemsBean> getMaterialPurchaseItems() {
                return materialPurchaseItems;
            }

            public void setMaterialPurchaseItems(List<MaterialPurchaseItemsBean> materialPurchaseItems) {
                this.materialPurchaseItems = materialPurchaseItems;
            }

            public static class MaterialPurchaseItemsBean {
                /**
                 * id : {"materialPurchaseId":1,"itemId":1}
                 * qty : 1
                 * price : 1.0
                 * materialPurchaseId : null
                 * itemId : null
                 */

                private IdBean id;
                private int qty;
                private double price;
                private Object materialPurchaseId;
                private Object itemId;
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

                public int getQty() {
                    return qty;
                }

                public void setQty(int qty) {
                    this.qty = qty;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public Object getMaterialPurchaseId() {
                    return materialPurchaseId;
                }

                public void setMaterialPurchaseId(Object materialPurchaseId) {
                    this.materialPurchaseId = materialPurchaseId;
                }

                public Object getItemId() {
                    return itemId;
                }

                public void setItemId(Object itemId) {
                    this.itemId = itemId;
                }

                public static class IdBean {
                    /**
                     * materialPurchaseId : 1
                     * itemId : 1
                     */

                    private int materialPurchaseId;
                    private int itemId;

                    public int getMaterialPurchaseId() {
                        return materialPurchaseId;
                    }

                    public void setMaterialPurchaseId(int materialPurchaseId) {
                        this.materialPurchaseId = materialPurchaseId;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }
                }
            }
        }
    }
}
