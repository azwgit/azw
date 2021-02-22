package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class EditMaterialBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":7,"owner":1,"code":"WL20210220094105","orgId":1,"deptId":1,"remark":"ingoing","amount":0,"warehouseId":null,"status":1,"addedTime":"2021-02-20 09:41","addedOperator":"超级管理员","addedOperatorId":20,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":7,"itemId":1},"qty":2,"price":2,"materialPurchaseId":null,"itemId":null,"itemName":"哈哈"}],"materialPurchaseAnnexs":[{"id":1,"materialPurchaseId":7,"uri":null}],"deptName":null,"username":null,"itemId":null}
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
         * id : 7
         * owner : 1
         * code : WL20210220094105
         * orgId : 1
         * deptId : 1
         * remark : ingoing
         * amount : 0.0
         * warehouseId : null
         * status : 1
         * addedTime : 2021-02-20 09:41
         * addedOperator : 超级管理员
         * addedOperatorId : 20
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * finishedOperator : null
         * finishedTime : null
         * materialPurchaseItems : [{"id":{"materialPurchaseId":7,"itemId":1},"qty":2,"price":2,"materialPurchaseId":null,"itemId":null,"itemName":"哈哈"}]
         * materialPurchaseAnnexs : [{"id":1,"materialPurchaseId":7,"uri":null}]
         * deptName : null
         * username : null
         * itemId : null
         */

        private int id;
        private int owner;
        private String code;
        private int orgId;
        private int deptId;
        private String remark;
        private double amount;
        private Object warehouseId;
        private int status;
        private String addedTime;
        private String addedOperator;
        private int addedOperatorId;
        private Object submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private Object finishedOperator;
        private Object finishedTime;
        private String deptName;
        private Object username;
        private Object itemId;
        private List<MaterialPurchaseItemsBean> materialPurchaseItems;
        private List<MaterialPurchaseAnnexsBean> materialPurchaseAnnexs;

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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public int getAddedOperatorId() {
            return addedOperatorId;
        }

        public void setAddedOperatorId(int addedOperatorId) {
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

        public List<MaterialPurchaseAnnexsBean> getMaterialPurchaseAnnexs() {
            return materialPurchaseAnnexs;
        }

        public void setMaterialPurchaseAnnexs(List<MaterialPurchaseAnnexsBean> materialPurchaseAnnexs) {
            this.materialPurchaseAnnexs = materialPurchaseAnnexs;
        }

        public static class MaterialPurchaseItemsBean {
            /**
             * id : {"materialPurchaseId":7,"itemId":1}
             * qty : 2
             * price : 2.0
             * materialPurchaseId : null
             * itemId : null
             * itemName : 哈哈
             */

            private IdBean id;
            private int qty;
            private double price;
            private Object materialPurchaseId;
            private Object itemId;
            private String itemName;

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

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public static class IdBean {
                /**
                 * materialPurchaseId : 7
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

        public static class MaterialPurchaseAnnexsBean {
            /**
             * id : 1
             * materialPurchaseId : 7
             * uri : null
             */

            private int id;
            private int materialPurchaseId;
            private String uri;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMaterialPurchaseId() {
                return materialPurchaseId;
            }

            public void setMaterialPurchaseId(int materialPurchaseId) {
                this.materialPurchaseId = materialPurchaseId;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }
        }
    }
}
