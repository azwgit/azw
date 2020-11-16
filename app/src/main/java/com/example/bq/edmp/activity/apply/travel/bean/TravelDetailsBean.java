package com.example.bq.edmp.activity.apply.travel.bean;

import java.io.Serializable;
import java.util.List;

public class TravelDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":{"idx":1,"reimburserId":342},"setOutTime":"2020-11-12","setOutRegion":"HOHO","arriveTime":"2020-11-17","arriveRegion":"ingoing","transport":"火车","transportFee":4548,"days":464,"subsidy":4949,"idx":1,"reimburserId":342,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":773,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408099-7791.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":774,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408142-1862.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":775,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408202-8640.png","checkedStatus":1,"employeeId":null,"billFile":null}]}
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
         * id : {"idx":1,"reimburserId":342}
         * setOutTime : 2020-11-12
         * setOutRegion : HOHO
         * arriveTime : 2020-11-17
         * arriveRegion : ingoing
         * transport : 火车
         * transportFee : 4548.0
         * days : 464
         * subsidy : 4949.0
         * idx : 1
         * reimburserId : 342
         * employeeId : null
         * billFile : null
         * reimburserItemBills : [{"id":773,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408099-7791.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":774,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408142-1862.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":775,"reimburserId":342,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605151408202-8640.png","checkedStatus":1,"employeeId":null,"billFile":null}]
         */

        private IdBean id;
        private String setOutTime;
        private String setOutRegion;
        private String arriveTime;
        private String arriveRegion;
        private String transport;
        private double transportFee;
        private int days;
        private double subsidy;
        private int idx;
        private int reimburserId;
        private Object employeeId;
        private Object billFile;
        private List<ReimburserItemBillsBean> reimburserItemBills;

        public IdBean getId() {
            return id;
        }

        public void setId(IdBean id) {
            this.id = id;
        }

        public String getSetOutTime() {
            return setOutTime;
        }

        public void setSetOutTime(String setOutTime) {
            this.setOutTime = setOutTime;
        }

        public String getSetOutRegion() {
            return setOutRegion;
        }

        public void setSetOutRegion(String setOutRegion) {
            this.setOutRegion = setOutRegion;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getArriveRegion() {
            return arriveRegion;
        }

        public void setArriveRegion(String arriveRegion) {
            this.arriveRegion = arriveRegion;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public double getTransportFee() {
            return transportFee;
        }

        public void setTransportFee(double transportFee) {
            this.transportFee = transportFee;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public double getSubsidy() {
            return subsidy;
        }

        public void setSubsidy(double subsidy) {
            this.subsidy = subsidy;
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

        public static class IdBean {
            /**
             * idx : 1
             * reimburserId : 342
             */

            private int idx;
            private int reimburserId;

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
        }

        public static class ReimburserItemBillsBean {
            /**
             * id : 773
             * reimburserId : 342
             * itemIdx : 1
             * itemType : 2
             * uri : \2020\1605151408099-7791.png
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
