package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":null,"owner":null,"code":null,"orgId":null,"deptId":2,"remark":null,"amount":null,"warehouseId":null,"status":null,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":null,"materialPurchaseAnnexs":null,"deptName":"北京分公司","username":"李四","itemId":null}
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
         * id : null
         * owner : null
         * code : null
         * orgId : null
         * deptId : 2
         * remark : null
         * amount : null
         * warehouseId : null
         * status : null
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
         * deptName : 北京分公司
         * username : 李四
         * itemId : null
         */

        private Object id;
        private Object owner;
        private Object code;
        private Object orgId;
        private int deptId;
        private Object remark;
        private Object amount;
        private Object warehouseId;
        private Object status;
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
        private String deptName;
        private String username;
        private Object itemId;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
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

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getItemId() {
            return itemId;
        }

        public void setItemId(Object itemId) {
            this.itemId = itemId;
        }
    }
}
