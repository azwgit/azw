package com.example.bq.edmp.word.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class SubmitListBean {

    /**
     * filter : {"id":null,"owner":1,"orgId":null,"datas":null,"employeeId":16,"types":null,"amount":null,"tdReason":null,"advanceLoan":null,"addedTime":null,"addedOperator":null,"status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":null,"reimburserItems":null,"reimburserTravelingItems":null}
     * rows : [{"id":109,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":1,"amount":90,"tdReason":"休息0","advanceLoan":10,"addedTime":"2020-11-03T10:06:57.000+0000","addedOperator":"李四","status":1,"submitTime":"2020-11-04T04:48:32.000+0000","approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":80,"reimburserItems":null,"reimburserTravelingItems":null},{"id":108,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":2,"amount":20,"tdReason":"月底报销(差旅报销提交)","advanceLoan":1000,"addedTime":"2020-11-03T08:52:46.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-980,"reimburserItems":null,"reimburserTravelingItems":null},{"id":106,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":1,"amount":0,"tdReason":"休息","advanceLoan":10,"addedTime":"2020-11-03T07:14:22.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-10,"reimburserItems":null,"reimburserTravelingItems":null},{"id":105,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":1,"amount":0,"tdReason":"加班1234","advanceLoan":10,"addedTime":"2020-11-03T07:07:10.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-10,"reimburserItems":null,"reimburserTravelingItems":null},{"id":103,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":1,"amount":0,"tdReason":"加班123","advanceLoan":10,"addedTime":"2020-11-03T06:59:37.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-10,"reimburserItems":null,"reimburserTravelingItems":null},{"id":102,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":1,"amount":0,"tdReason":"加班12","advanceLoan":10,"addedTime":"2020-11-03T06:56:44.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-10,"reimburserItems":null,"reimburserTravelingItems":null},{"id":98,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":null,"amount":0,"tdReason":"加班11","advanceLoan":10,"addedTime":"2020-11-03T06:48:38.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":-10,"reimburserItems":null,"reimburserTravelingItems":null},{"id":97,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":null,"amount":2,"tdReason":null,"advanceLoan":0,"addedTime":"2020-11-02T09:52:06.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":2,"reimburserItems":null,"reimburserTravelingItems":null},{"id":96,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":2,"amount":1500,"tdReason":"月底报销(差旅报销保存)","advanceLoan":1000,"addedTime":"2020-11-02T08:21:03.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":500,"reimburserItems":null,"reimburserTravelingItems":null},{"id":95,"owner":1,"orgId":"6","datas":null,"employeeId":16,"types":2,"amount":1500,"tdReason":"月底报销(差旅报销提交)","advanceLoan":1000,"addedTime":"2020-11-02T08:14:16.000+0000","addedOperator":"李四","status":1,"submitTime":null,"approvedTime":null,"finishedTime":null,"firstTime":null,"lastTime":null,"disparity":500,"reimburserItems":null,"reimburserTravelingItems":null}]
     * page : 1
     * pagerow : 10
     * totalpages : 4
     * totalrows : 40
     * sumtotal : 0
     * sortname : null
     * sortorder : ASC
     */

    private FilterBean filter;
    private int page;
    private int pagerow;
    private int totalpages;
    private int totalrows;
    private int sumtotal;
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

    public int getSumtotal() {
        return sumtotal;
    }

    public void setSumtotal(int sumtotal) {
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
         * datas : null
         * employeeId : 16
         * types : null
         * amount : null
         * tdReason : null
         * advanceLoan : null
         * addedTime : null
         * addedOperator : null
         * status : 1
         * submitTime : null
         * approvedTime : null
         * finishedTime : null
         * firstTime : null
         * lastTime : null
         * disparity : null
         * reimburserItems : null
         * reimburserTravelingItems : null
         */

        private Object id;
        private int owner;
        private Object orgId;
        private Object datas;
        private int employeeId;
        private Object types;
        private Object amount;
        private Object tdReason;
        private Object advanceLoan;
        private Object addedTime;
        private Object addedOperator;
        private int status;
        private Object submitTime;
        private Object approvedTime;
        private Object finishedTime;
        private Object firstTime;
        private Object lastTime;
        private Object disparity;
        private Object reimburserItems;
        private Object reimburserTravelingItems;

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

        public Object getDatas() {
            return datas;
        }

        public void setDatas(Object datas) {
            this.datas = datas;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getTdReason() {
            return tdReason;
        }

        public void setTdReason(Object tdReason) {
            this.tdReason = tdReason;
        }

        public Object getAdvanceLoan() {
            return advanceLoan;
        }

        public void setAdvanceLoan(Object advanceLoan) {
            this.advanceLoan = advanceLoan;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public Object getDisparity() {
            return disparity;
        }

        public void setDisparity(Object disparity) {
            this.disparity = disparity;
        }

        public Object getReimburserItems() {
            return reimburserItems;
        }

        public void setReimburserItems(Object reimburserItems) {
            this.reimburserItems = reimburserItems;
        }

        public Object getReimburserTravelingItems() {
            return reimburserTravelingItems;
        }

        public void setReimburserTravelingItems(Object reimburserTravelingItems) {
            this.reimburserTravelingItems = reimburserTravelingItems;
        }
    }

    public static class RowsBean {
        /**
         * id : 109
         * owner : 1
         * orgId : 6
         * datas : null
         * employeeId : 16
         * types : 1
         * amount : 90
         * tdReason : 休息0
         * advanceLoan : 10
         * addedTime : 2020-11-03T10:06:57.000+0000
         * addedOperator : 李四
         * status : 1
         * submitTime : 2020-11-04T04:48:32.000+0000
         * approvedTime : null
         * finishedTime : null
         * firstTime : null
         * lastTime : null
         * disparity : 80
         * reimburserItems : null
         * reimburserTravelingItems : null
         */

        private int id;
        private int owner;
        private String orgId;
        private Object datas;
        private int employeeId;
        private int types;
        private int amount;
        private String tdReason;
        private int advanceLoan;
        private String addedTime;
        private String addedOperator;
        private int status;
        private String submitTime;
        private Object approvedTime;
        private Object finishedTime;
        private Object firstTime;
        private Object lastTime;
        private int disparity;
        private Object reimburserItems;
        private Object reimburserTravelingItems;

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

        private String empName;
        private String deptName;


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

        public Object getDatas() {
            return datas;
        }

        public void setDatas(Object datas) {
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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getTdReason() {
            return tdReason;
        }

        public void setTdReason(String tdReason) {
            this.tdReason = tdReason;
        }

        public int getAdvanceLoan() {
            return advanceLoan;
        }

        public void setAdvanceLoan(int advanceLoan) {
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

        public int getDisparity() {
            return disparity;
        }

        public void setDisparity(int disparity) {
            this.disparity = disparity;
        }

        public Object getReimburserItems() {
            return reimburserItems;
        }

        public void setReimburserItems(Object reimburserItems) {
            this.reimburserItems = reimburserItems;
        }

        public Object getReimburserTravelingItems() {
            return reimburserTravelingItems;
        }

        public void setReimburserTravelingItems(Object reimburserTravelingItems) {
            this.reimburserTravelingItems = reimburserTravelingItems;
        }
    }
}
