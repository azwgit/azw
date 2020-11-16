package com.example.bq.edmp.activity.apply.bean;

import java.io.Serializable;
import java.util.List;

public class SelectReimbursementDetailsBean implements Serializable {


    /**
     * code : 200
     * msg : 查询成功
     * data : {"name":"he123","billCount":"2","amount":10,"approvedAmount":null,"idx":0,"reimburserId":87,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":174,"reimburserId":87,"itemIdx":0,"itemType":1,"uri":"\\2020\\1604466004519-3063.jpg","checkedStatus":1,"employeeId":null,"billFile":null}]}
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
         * name : he123
         * billCount : 2
         * amount : 10.0
         * approvedAmount : null
         * idx : 0
         * reimburserId : 87
         * employeeId : null
         * billFile : null
         * reimburserItemBills : [{"id":174,"reimburserId":87,"itemIdx":0,"itemType":1,"uri":"\\2020\\1604466004519-3063.jpg","checkedStatus":1,"employeeId":null,"billFile":null}]
         */

        private String name;
        private String billCount;
        private double amount;
        private Object approvedAmount;
        private int idx;
        private int reimburserId;
        private Object employeeId;
        private Object billFile;
        private List<ReimburserItemBillsBean> reimburserItemBills;

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

        public List<ReimburserItemBillsBean> getReimburserItemBills() {
            return reimburserItemBills;
        }

        public void setReimburserItemBills(List<ReimburserItemBillsBean> reimburserItemBills) {
            this.reimburserItemBills = reimburserItemBills;
        }

        public static class ReimburserItemBillsBean {
            /**
             * id : 174
             * reimburserId : 87
             * itemIdx : 0
             * itemType : 1
             * uri : \2020\1604466004519-3063.jpg
             * checkedStatus : 1
             * employeeId : null
             * billFile : null
             */

            private int id;
            private int reimburserId;
            private int itemIdx;
            private int itemType;
            private String uri;
            private int checkedStatus;
            private Object employeeId;
            private Object billFile;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getReimburserId() {
                return reimburserId;
            }

            public void setReimburserId(int reimburserId) {
                this.reimburserId = reimburserId;
            }

            public int getItemIdx() {
                return itemIdx;
            }

            public void setItemIdx(int itemIdx) {
                this.itemIdx = itemIdx;
            }

            public int getItemType() {
                return itemType;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public int getCheckedStatus() {
                return checkedStatus;
            }

            public void setCheckedStatus(int checkedStatus) {
                this.checkedStatus = checkedStatus;
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
        }
    }
}
