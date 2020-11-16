package com.example.bq.edmp.activity.apply.travel.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.List;

public class TravelDetailsInfo implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":364,"owner":1,"orgId":"6","datas":"2020-11-11T16:00:00.000+0000","employeeId":16,"types":2,"amount":15086,"tdReason":"HOHO","advanceLoan":565,"addedTime":"2020-11-12T06:52:03.000+0000","addedOperator":"李四","status":1,"remark":"12345","submitTime":null,"approvedTime":null,"finishedTime":null,"dates":null,"firstTime":null,"lastTime":null,"disparity":14521,"reimburserItems":[{"id":{"idx":1,"reimburserId":364,"billFile":null},"name":"市内车费","billCount":"2","amount":545,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":848,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015157-6521.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":849,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015219-9285.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":2,"reimburserId":364,"billFile":null},"name":"邮电费","billCount":"2","amount":131,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":850,"reimburserId":364,"itemIdx":2,"itemType":1,"uri":"\\2020\\1605164025092-3381.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":851,"reimburserId":364,"itemIdx":2,"itemType":1,"uri":"\\2020\\1605164025137-4621.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":3,"reimburserId":364,"billFile":null},"name":"住宿费","billCount":"2","amount":4949,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":852,"reimburserId":364,"itemIdx":3,"itemType":1,"uri":"\\2020\\1605164033415-238.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":853,"reimburserId":364,"itemIdx":3,"itemType":1,"uri":"\\2020\\1605164033456-7020.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":4,"reimburserId":364,"billFile":null},"name":"办公用品费","billCount":"1","amount":4546,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":854,"reimburserId":364,"itemIdx":4,"itemType":1,"uri":"\\2020\\1605164041944-4404.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":5,"reimburserId":364,"billFile":null},"name":"其他费用","billCount":"4","amount":4515,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":855,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054020-926.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":856,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054070-9874.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":857,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054098-931.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":858,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054123-189.png","checkedStatus":1,"employeeId":null,"billFile":null}]}],"reimburserTravelingItems":[{"id":{"idx":1,"reimburserId":364},"setOutTime":"2020-11-12","setOutRegion":"北京","arriveTime":"2020-11-13","arriveRegion":"成都","transport":"火车","transportFee":100,"days":5,"subsidy":500,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":842,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964009-4345.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":843,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964043-2810.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":844,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964070-726.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":2,"reimburserId":364},"setOutTime":"2020-11-12","setOutRegion":"北京","arriveTime":"2020-11-13","arriveRegion":"上海","transport":"私家车","transportFee":300,"days":5,"subsidy":200,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":845,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997396-309.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":846,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997442-566.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":847,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997467-5865.png","checkedStatus":1,"employeeId":null,"billFile":null}]}],"empName":"李四","deptName":"销售00","companyName":"安徽皖垦种业股份有限公司"}
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
         * id : 364
         * owner : 1
         * orgId : 6
         * datas : 2020-11-11T16:00:00.000+0000
         * employeeId : 16
         * types : 2
         * amount : 15086.0
         * tdReason : HOHO
         * advanceLoan : 565.0
         * addedTime : 2020-11-12T06:52:03.000+0000
         * addedOperator : 李四
         * status : 1
         * remark : 12345
         * submitTime : null
         * approvedTime : null
         * finishedTime : null
         * dates : null
         * firstTime : null
         * lastTime : null
         * disparity : 14521.0
         * reimburserItems : [{"id":{"idx":1,"reimburserId":364,"billFile":null},"name":"市内车费","billCount":"2","amount":545,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":848,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015157-6521.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":849,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015219-9285.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":2,"reimburserId":364,"billFile":null},"name":"邮电费","billCount":"2","amount":131,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":850,"reimburserId":364,"itemIdx":2,"itemType":1,"uri":"\\2020\\1605164025092-3381.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":851,"reimburserId":364,"itemIdx":2,"itemType":1,"uri":"\\2020\\1605164025137-4621.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":3,"reimburserId":364,"billFile":null},"name":"住宿费","billCount":"2","amount":4949,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":852,"reimburserId":364,"itemIdx":3,"itemType":1,"uri":"\\2020\\1605164033415-238.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":853,"reimburserId":364,"itemIdx":3,"itemType":1,"uri":"\\2020\\1605164033456-7020.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":4,"reimburserId":364,"billFile":null},"name":"办公用品费","billCount":"1","amount":4546,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":854,"reimburserId":364,"itemIdx":4,"itemType":1,"uri":"\\2020\\1605164041944-4404.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":5,"reimburserId":364,"billFile":null},"name":"其他费用","billCount":"4","amount":4515,"approvedAmount":null,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":855,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054020-926.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":856,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054070-9874.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":857,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054098-931.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":858,"reimburserId":364,"itemIdx":5,"itemType":1,"uri":"\\2020\\1605164054123-189.png","checkedStatus":1,"employeeId":null,"billFile":null}]}]
         * reimburserTravelingItems : [{"id":{"idx":1,"reimburserId":364},"setOutTime":"2020-11-12","setOutRegion":"北京","arriveTime":"2020-11-13","arriveRegion":"成都","transport":"火车","transportFee":100,"days":5,"subsidy":500,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":842,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964009-4345.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":843,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964043-2810.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":844,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964070-726.png","checkedStatus":1,"employeeId":null,"billFile":null}]},{"id":{"idx":2,"reimburserId":364},"setOutTime":"2020-11-12","setOutRegion":"北京","arriveTime":"2020-11-13","arriveRegion":"上海","transport":"私家车","transportFee":300,"days":5,"subsidy":200,"idx":null,"reimburserId":null,"employeeId":null,"billFile":null,"reimburserItemBills":[{"id":845,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997396-309.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":846,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997442-566.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":847,"reimburserId":364,"itemIdx":2,"itemType":2,"uri":"\\2020\\1605163997467-5865.png","checkedStatus":1,"employeeId":null,"billFile":null}]}]
         * empName : 李四
         * deptName : 销售00
         * companyName : 安徽皖垦种业股份有限公司
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
        private Object submitTime;
        private Object approvedTime;
        private Object finishedTime;
        private Object dates;
        private Object firstTime;
        private Object lastTime;
        private double disparity;
        private String empName;
        private String deptName;
        private String companyName;
        private List<ReimburserItemsBean> reimburserItems;
        private List<ReimburserTravelingItemsBean> reimburserTravelingItems;

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

        public Object getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(Object submitTime) {
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

        public Object getDates() {
            return dates;
        }

        public void setDates(Object dates) {
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

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public List<ReimburserItemsBean> getReimburserItems() {
            return reimburserItems;
        }

        public void setReimburserItems(List<ReimburserItemsBean> reimburserItems) {
            this.reimburserItems = reimburserItems;
        }

        public List<ReimburserTravelingItemsBean> getReimburserTravelingItems() {
            return reimburserTravelingItems;
        }

        public void setReimburserTravelingItems(List<ReimburserTravelingItemsBean> reimburserTravelingItems) {
            this.reimburserTravelingItems = reimburserTravelingItems;
        }

        public static class ReimburserItemsBean {
            /**
             * id : {"idx":1,"reimburserId":364,"billFile":null}
             * name : 市内车费
             * billCount : 2
             * amount : 545.0
             * approvedAmount : null
             * idx : null
             * reimburserId : null
             * employeeId : null
             * billFile : null
             * reimburserItemBills : [{"id":848,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015157-6521.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":849,"reimburserId":364,"itemIdx":1,"itemType":1,"uri":"\\2020\\1605164015219-9285.png","checkedStatus":1,"employeeId":null,"billFile":null}]
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
            private List<ReimburserItemBillsBean> reimburserItemBills;

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

            public List<ReimburserItemBillsBean> getReimburserItemBills() {
                return reimburserItemBills;
            }

            public void setReimburserItemBills(List<ReimburserItemBillsBean> reimburserItemBills) {
                this.reimburserItemBills = reimburserItemBills;
            }

            public static class IdBean {
                /**
                 * idx : 1
                 * reimburserId : 364
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

            public static class ReimburserItemBillsBean {
                /**
                 * id : 848
                 * reimburserId : 364
                 * itemIdx : 1
                 * itemType : 1
                 * uri : \2020\1605164015157-6521.png
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

        public static class ReimburserTravelingItemsBean {
            /**
             * id : {"idx":1,"reimburserId":364}
             * setOutTime : 2020-11-12
             * setOutRegion : 北京
             * arriveTime : 2020-11-13
             * arriveRegion : 成都
             * transport : 火车
             * transportFee : 100.0
             * days : 5
             * subsidy : 500.0
             * idx : null
             * reimburserId : null
             * employeeId : null
             * billFile : null
             * reimburserItemBills : [{"id":842,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964009-4345.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":843,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964043-2810.png","checkedStatus":1,"employeeId":null,"billFile":null},{"id":844,"reimburserId":364,"itemIdx":1,"itemType":2,"uri":"\\2020\\1605163964070-726.png","checkedStatus":1,"employeeId":null,"billFile":null}]
             */

            private IdBeanX id;
            private String setOutTime;
            private String setOutRegion;
            private String arriveTime;
            private String arriveRegion;
            private String transport;
            private double transportFee;
            private int days;
            private double subsidy;
            private Object idx;
            private Object reimburserId;
            private Object employeeId;
            private Object billFile;
            private List<ReimburserItemBillsBeanX> reimburserItemBills;
            private List<LocalMedia> picList;

            public List<LocalMedia> getPicList() {
                return picList;
            }

            public void setPicList(List<LocalMedia> picList) {
                this.picList = picList;
            }

            public IdBeanX getId() {
                return id;
            }

            public void setId(IdBeanX id) {
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

            public List<ReimburserItemBillsBeanX> getReimburserItemBills() {
                return reimburserItemBills;
            }

            public void setReimburserItemBills(List<ReimburserItemBillsBeanX> reimburserItemBills) {
                this.reimburserItemBills = reimburserItemBills;
            }

            public static class IdBeanX {
                /**
                 * idx : 1
                 * reimburserId : 364
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

            public static class ReimburserItemBillsBeanX {
                /**
                 * id : 842
                 * reimburserId : 364
                 * itemIdx : 1
                 * itemType : 2
                 * uri : \2020\1605163964009-4345.png
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
}
