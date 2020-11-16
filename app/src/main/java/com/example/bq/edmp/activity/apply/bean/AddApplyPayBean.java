package com.example.bq.edmp.activity.apply.bean;

import java.io.Serializable;

public class AddApplyPayBean implements Serializable {

    /**
     * code : 200
     * msg : 开支报销添加成功
     * data : {"id":{"idx":3,"reimburserId":104,"billFile":null},"name":"李四","billCount":"0","amount":30,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":null}
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
         * id : {"idx":3,"reimburserId":104,"billFile":null}
         * name : 李四
         * billCount : 0
         * amount : 30.0
         * approvedAmount : null
         * idx : null
         * reimburserId : null
         * employeeId : null
         * billFile : null
         * reimburserItemBills : null
         */

        private IdBean id;
        private String name;
        private String billCount;
        private double amount;
        private Object approvedAmount;
        private Object idx;
        private Object reimburserId;
        private Object employeeId;
        private Object billFile;
        private Object reimburserItemBills;

        public IdBean getId() {
            return id;
        }

        public void setId(IdBean id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBillCount() {
            return billCount;
        }

        public void setBillCount(String billCount) {
            this.billCount = billCount;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getApprovedAmount() {
            return approvedAmount;
        }

        public void setApprovedAmount(Object approvedAmount) {
            this.approvedAmount = approvedAmount;
        }

        public Object getIdx() {
            return idx;
        }

        public void setIdx(Object idx) {
            this.idx = idx;
        }

        public Object getReimburserId() {
            return reimburserId;
        }

        public void setReimburserId(Object reimburserId) {
            this.reimburserId = reimburserId;
        }

        public Object getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Object employeeId) {
            this.employeeId = employeeId;
        }

        public Object getBillFile() {
            return billFile;
        }

        public void setBillFile(Object billFile) {
            this.billFile = billFile;
        }

        public Object getReimburserItemBills() {
            return reimburserItemBills;
        }

        public void setReimburserItemBills(Object reimburserItemBills) {
            this.reimburserItemBills = reimburserItemBills;
        }

        public static class IdBean {
            /**
             * idx : 3
             * reimburserId : 104
             * billFile : null
             */

            private int idx;
            private int reimburserId;
            private Object billFile;

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public int getReimburserId() {
                return reimburserId;
            }

            public void setReimburserId(int reimburserId) {
                this.reimburserId = reimburserId;
            }

            public Object getBillFile() {
                return billFile;
            }

            public void setBillFile(Object billFile) {
                this.billFile = billFile;
            }
        }
    }
}
