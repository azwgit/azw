package com.example.bq.edmp.work.marketingactivities.bean;

import java.io.Serializable;
import java.util.List;

public class HistoricalListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"orgId":null,"deptId":null,"region":null,"customerId":null,"name":"","responsiblePeople":null,"address":null,"purpose":null,"advanceLoan":null,"effect":null,"startTime":null,"endTime":null,"finishedTime":null,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":5,"activityAnnex":null,"deptName":null,"customerName":null,"orgName":null,"regionName":null,"activityItems":null},"rows":[{"id":9,"owner":null,"orgId":null,"deptId":null,"region":null,"customerId":null,"name":"北京活动","responsiblePeople":"可乐","address":null,"purpose":null,"advanceLoan":5000,"effect":null,"startTime":"2020-12-31","endTime":"2020-12-31","finishedTime":"2021-01-04T03:08:57.000+0000","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":5,"activityAnnex":null,"deptName":"运营部","customerName":null,"orgName":null,"regionName":null,"activityItems":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":1,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"orgId":null,"deptId":null,"region":null,"customerId":null,"name":"","responsiblePeople":null,"address":null,"purpose":null,"advanceLoan":null,"effect":null,"startTime":null,"endTime":null,"finishedTime":null,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":5,"activityAnnex":null,"deptName":null,"customerName":null,"orgName":null,"regionName":null,"activityItems":null}
         * rows : [{"id":9,"owner":null,"orgId":null,"deptId":null,"region":null,"customerId":null,"name":"北京活动","responsiblePeople":"可乐","address":null,"purpose":null,"advanceLoan":5000,"effect":null,"startTime":"2020-12-31","endTime":"2020-12-31","finishedTime":"2021-01-04T03:08:57.000+0000","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":5,"activityAnnex":null,"deptName":"运营部","customerName":null,"orgName":null,"regionName":null,"activityItems":null}]
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
             * orgId : null
             * deptId : null
             * region : null
             * customerId : null
             * name :
             * responsiblePeople : null
             * address : null
             * purpose : null
             * advanceLoan : null
             * effect : null
             * startTime : null
             * endTime : null
             * finishedTime : null
             * addedTime : null
             * addedOperator : null
             * addedOperatorId : null
             * status : 5
             * activityAnnex : null
             * deptName : null
             * customerName : null
             * orgName : null
             * regionName : null
             * activityItems : null
             */

            private Object id;
            private int owner;
            private Object orgId;
            private Object deptId;
            private Object region;
            private Object customerId;
            private String name;
            private Object responsiblePeople;
            private Object address;
            private Object purpose;
            private Object advanceLoan;
            private Object effect;
            private Object startTime;
            private Object endTime;
            private Object finishedTime;
            private Object addedTime;
            private Object addedOperator;
            private Object addedOperatorId;
            private int status;
            private Object activityAnnex;
            private Object deptName;
            private Object customerName;
            private Object orgName;
            private Object regionName;
            private Object activityItems;

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

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getResponsiblePeople() {
                return responsiblePeople;
            }

            public void setResponsiblePeople(Object responsiblePeople) {
                this.responsiblePeople = responsiblePeople;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getPurpose() {
                return purpose;
            }

            public void setPurpose(Object purpose) {
                this.purpose = purpose;
            }

            public Object getAdvanceLoan() {
                return advanceLoan;
            }

            public void setAdvanceLoan(Object advanceLoan) {
                this.advanceLoan = advanceLoan;
            }

            public Object getEffect() {
                return effect;
            }

            public void setEffect(Object effect) {
                this.effect = effect;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getFinishedTime() {
                return finishedTime;
            }

            public void setFinishedTime(Object finishedTime) {
                this.finishedTime = finishedTime;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getActivityAnnex() {
                return activityAnnex;
            }

            public void setActivityAnnex(Object activityAnnex) {
                this.activityAnnex = activityAnnex;
            }

            public Object getDeptName() {
                return deptName;
            }

            public void setDeptName(Object deptName) {
                this.deptName = deptName;
            }

            public Object getCustomerName() {
                return customerName;
            }

            public void setCustomerName(Object customerName) {
                this.customerName = customerName;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public Object getRegionName() {
                return regionName;
            }

            public void setRegionName(Object regionName) {
                this.regionName = regionName;
            }

            public Object getActivityItems() {
                return activityItems;
            }

            public void setActivityItems(Object activityItems) {
                this.activityItems = activityItems;
            }
        }

        public static class RowsBean {
            /**
             * id : 9
             * owner : null
             * orgId : null
             * deptId : null
             * region : null
             * customerId : null
             * name : 北京活动
             * responsiblePeople : 可乐
             * address : null
             * purpose : null
             * advanceLoan : 5000.0
             * effect : null
             * startTime : 2020-12-31
             * endTime : 2020-12-31
             * finishedTime : 2021-01-04T03:08:57.000+0000
             * addedTime : null
             * addedOperator : null
             * addedOperatorId : null
             * status : 5
             * activityAnnex : null
             * deptName : 运营部
             * customerName : null
             * orgName : null
             * regionName : null
             * activityItems : null
             */

            private int id;
            private Object owner;
            private Object orgId;
            private Object deptId;
            private Object region;
            private Object customerId;
            private String name;
            private String responsiblePeople;
            private Object address;
            private Object purpose;
            private double advanceLoan;
            private Object effect;
            private String startTime;
            private String endTime;
            private String finishedTime;
            private Object addedTime;
            private Object addedOperator;
            private Object addedOperatorId;
            private int status;
            private Object activityAnnex;
            private String deptName;
            private Object customerName;
            private Object orgName;
            private Object regionName;
            private Object activityItems;

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

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getResponsiblePeople() {
                return responsiblePeople;
            }

            public void setResponsiblePeople(String responsiblePeople) {
                this.responsiblePeople = responsiblePeople;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getPurpose() {
                return purpose;
            }

            public void setPurpose(Object purpose) {
                this.purpose = purpose;
            }

            public double getAdvanceLoan() {
                return advanceLoan;
            }

            public void setAdvanceLoan(double advanceLoan) {
                this.advanceLoan = advanceLoan;
            }

            public Object getEffect() {
                return effect;
            }

            public void setEffect(Object effect) {
                this.effect = effect;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getFinishedTime() {
                return finishedTime;
            }

            public void setFinishedTime(String finishedTime) {
                this.finishedTime = finishedTime;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getActivityAnnex() {
                return activityAnnex;
            }

            public void setActivityAnnex(Object activityAnnex) {
                this.activityAnnex = activityAnnex;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public Object getCustomerName() {
                return customerName;
            }

            public void setCustomerName(Object customerName) {
                this.customerName = customerName;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public Object getRegionName() {
                return regionName;
            }

            public void setRegionName(Object regionName) {
                this.regionName = regionName;
            }

            public Object getActivityItems() {
                return activityItems;
            }

            public void setActivityItems(Object activityItems) {
                this.activityItems = activityItems;
            }
        }
    }
}
