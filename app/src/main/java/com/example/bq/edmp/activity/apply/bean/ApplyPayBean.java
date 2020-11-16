package com.example.bq.edmp.activity.apply.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bq on 2020/11/9.
 */

public class ApplyPayBean implements Serializable {

    /**
     * code : 200
     * msg : 报账修改保存成功
     * data : {"id":122,"owner":1,"orgId":"6","datas":"2020-11-08T16:00:00.000+0000","employeeId":16,"types":1,"amount":20,"tdReason":"调休123","advanceLoan":200,"addedTime":"2020-11-09T07:28:59.000+0000","addedOperator":"管理员","status":2,"remark":"hah","submitTime":"2020-11-09T07:53:07.000+0000","approvedTime":null,"finishedTime":null,"dates":null,"firstTime":null,"lastTime":null,"disparity":-180,"reimburserItems":[{"id":{"idx":1,"reimburserId":122,"billFile":null},"name":"开支报销1","billCount":"0","amount":10,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[]},{"id":{"idx":2,"reimburserId":122,"billFile":null},"name":"开支报销1","billCount":"2","amount":10,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":201,"reimburserId":122,"itemIdx":2,"itemType":1,"uri":"\\2020\\1604909608650-2731.jpg","checkedStatus":1,"employeeId":null,"billFile":null},{"id":202,"reimburserId":122,"itemIdx":2,"itemType":1,"uri":"\\2020\\1604909609151-645.pdf","checkedStatus":1,"employeeId":null,"billFile":null}]}],"reimburserTravelingItems":[],"empName":null,"deptName":null}
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

    public static class DataBean implements Serializable{
        /**
         * id : 122
         * owner : 1
         * orgId : 6
         * datas : 2020-11-08T16:00:00.000+0000
         * employeeId : 16
         * types : 1
         * amount : 20.0
         * tdReason : 调休123
         * advanceLoan : 200.0
         * addedTime : 2020-11-09T07:28:59.000+0000
         * addedOperator : 管理员
         * status : 2
         * remark : hah
         * submitTime : 2020-11-09T07:53:07.000+0000
         * approvedTime : null
         * finishedTime : null
         * dates : null
         * firstTime : null
         * lastTime : null
         * disparity : -180.0
         * reimburserItems : [{"id":{"idx":1,"reimburserId":122,"billFile":null},"name":"开支报销1","billCount":"0","amount":10,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[]},{"id":{"idx":2,"reimburserId":122,"billFile":null},"name":"开支报销1","billCount":"2","amount":10,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":201,"reimburserId":122,"itemIdx":2,"itemType":1,"uri":"\\2020\\1604909608650-2731.jpg","checkedStatus":1,"employeeId":null,"billFile":null},{"id":202,"reimburserId":122,"itemIdx":2,"itemType":1,"uri":"\\2020\\1604909609151-645.pdf","checkedStatus":1,"employeeId":null,"billFile":null}]}]
         * reimburserTravelingItems : []
         * empName : null
         * deptName : null
         */

        private int id;
        private int owner;
        private String orgId;
        private String datas;
        private int employeeId;
        private int types;
        private double amount;
        private String tdReason;
        private double advanceLoan;
        private String addedTime;
        private String addedOperator;
        private int status;
        private String remark;
        private String submitTime;
        private Object approvedTime;
        private Object finishedTime;
        private String dates;
        private Object firstTime;
        private Object lastTime;
        private double disparity;
        private String empName;
        private String deptName;
        private List<ReimburserItemsBean> reimburserItems;
        private List<?> reimburserTravelingItems;

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

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getDatas() {
            return datas;
        }

        public void setDatas(String datas) {
            this.datas = datas;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getTdReason() {
            return tdReason;
        }

        public void setTdReason(String tdReason) {
            this.tdReason = tdReason;
        }

        public double getAdvanceLoan() {
            return advanceLoan;
        }

        public void setAdvanceLoan(double advanceLoan) {
            this.advanceLoan = advanceLoan;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public Object getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(Object approvedTime) {
            this.approvedTime = approvedTime;
        }

        public Object getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(Object finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getDates() {
            return dates;
        }

        public void setDates(String dates) {
            this.dates = dates;
        }

        public Object getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(Object firstTime) {
            this.firstTime = firstTime;
        }

        public Object getLastTime() {
            return lastTime;
        }

        public void setLastTime(Object lastTime) {
            this.lastTime = lastTime;
        }

        public double getDisparity() {
            return disparity;
        }

        public void setDisparity(double disparity) {
            this.disparity = disparity;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public List<ReimburserItemsBean> getReimburserItems() {
            return reimburserItems;
        }

        public void setReimburserItems(List<ReimburserItemsBean> reimburserItems) {
            this.reimburserItems = reimburserItems;
        }

        public List<?> getReimburserTravelingItems() {
            return reimburserTravelingItems;
        }

        public void setReimburserTravelingItems(List<?> reimburserTravelingItems) {
            this.reimburserTravelingItems = reimburserTravelingItems;
        }

        public static class ReimburserItemsBean implements Serializable {
            /**
             * id : {"idx":1,"reimburserId":122,"billFile":null}
             * name : 开支报销1
             * billCount : 0
             * amount : 10.0
             * approvedAmount : null
             * idx : null
             * reimburserId : null
             * employeeId : null
             * billFile : null
             * reimburserItemBills : []
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
            private List<?> reimburserItemBills;

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

            public List<?> getReimburserItemBills() {
                return reimburserItemBills;
            }

            public void setReimburserItemBills(List<?> reimburserItemBills) {
                this.reimburserItemBills = reimburserItemBills;
            }

            public static class IdBean implements  Serializable {
                /**
                 * idx : 1
                 * reimburserId : 122
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
}
