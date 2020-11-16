package com.example.bq.edmp.word.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/11.
 */

public class AuditListBean {

    /**
     * filter : {"id":null,"owner":1,"approvalsId":null,"businessId":null,"businessBillId":null,"promoterId":null,"remark":null,"title":null,"addedTime":null,"approveStatus":null,"approvedTime":null,"empId":16,"bussinessName":null,"approvals":null,"approvalFlowLevels":null}
     * rows : [{"id":1,"owner":null,"approvalsId":null,"businessId":null,"businessBillId":null,"promoterId":null,"remark":"1","title":"1","addedTime":"2020-11-10T02:26:02.000+0000","approveStatus":null,"approvedTime":"2020-11-10T02:26:00.000+0000","empId":null,"bussinessName":null,"approvals":null,"approvalFlowLevels":null},{"id":1,"owner":null,"approvalsId":null,"businessId":null,"businessBillId":null,"promoterId":null,"remark":"1","title":"1","addedTime":"2020-11-10T02:26:02.000+0000","approveStatus":null,"approvedTime":"2020-11-10T02:26:00.000+0000","empId":null,"bussinessName":null,"approvals":null,"approvalFlowLevels":null}]
     * page : 1
     * pagerow : 10
     * totalpages : 0
     * totalrows : 2
     * sumtotal : 0
     * sortname : null
     * sortorder : ASC
     */

    private int page;
    private int pagerow;
    private int totalrows;
    private List<RowsBean> rows;

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

    public int getTotalrows() {
        return totalrows;
    }

    public void setTotalrows(int totalrows) {
        this.totalrows = totalrows;
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
         * approvalsId : null
         * businessId : null
         * businessBillId : null
         * promoterId : null
         * remark : null
         * title : null
         * addedTime : null
         * approveStatus : null
         * approvedTime : null
         * empId : 16
         * bussinessName : null
         * approvals : null
         * approvalFlowLevels : null
         */

        private Object id;
        private Object businessBillId;
        private Object promoterId;
        private Object remark;
        private Object title;
        private Object addedTime;
        private Object approveStatus;
        private Object approvedTime;
        private int empId;
        private Object bussinessName;
        private Object approvals;
        private Object approvalFlowLevels;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getBusinessBillId() {
            return businessBillId;
        }

        public void setBusinessBillId(Object businessBillId) {
            this.businessBillId = businessBillId;
        }

        public Object getPromoterId() {
            return promoterId;
        }

        public void setPromoterId(Object promoterId) {
            this.promoterId = promoterId;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(Object addedTime) {
            this.addedTime = addedTime;
        }

        public Object getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(Object approveStatus) {
            this.approveStatus = approveStatus;
        }

        public Object getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(Object approvedTime) {
            this.approvedTime = approvedTime;
        }

        public int getEmpId() {
            return empId;
        }

        public void setEmpId(int empId) {
            this.empId = empId;
        }

        public Object getBussinessName() {
            return bussinessName;
        }

        public void setBussinessName(Object bussinessName) {
            this.bussinessName = bussinessName;
        }

        public Object getApprovals() {
            return approvals;
        }

        public void setApprovals(Object approvals) {
            this.approvals = approvals;
        }

        public Object getApprovalFlowLevels() {
            return approvalFlowLevels;
        }

        public void setApprovalFlowLevels(Object approvalFlowLevels) {
            this.approvalFlowLevels = approvalFlowLevels;
        }
    }

    public static class RowsBean {
        /**
         * id : 1
         * owner : null
         * approvalsId : null
         * businessId : null
         * businessBillId : null
         * promoter : null
         * remark : 1
         * title : 1
         * addedTime : 2020-11-10T02:26:02.000+0000
         * approveStatus : null
         * approvedTime : 2020-11-10T02:26:00.000+0000
         * empId : null
         * bussinessName : null
         * approvals : null
         * approvalFlowLevels : null
         */

        private int id;
        private Object owner;
        private Object businessId;
        private Object businessBillId;
        private String promoter;
        private String remark;
        private String title;
        private String addedTime;
        private int approveStatus;
        private String approvedTime;
        private Object empId;
        private Object bussinessName;
        private Object approvals;
        private Object approvalFlowLevels;

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

        public Object getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Object businessId) {
            this.businessId = businessId;
        }

        public Object getBusinessBillId() {
            return businessBillId;
        }

        public void setBusinessBillId(Object businessBillId) {
            this.businessBillId = businessBillId;
        }

        public String getPromoter() {
            return promoter;
        }

        public void setPromoterId(String promoterId) {
            this.promoter = promoter;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
            this.approveStatus = approveStatus;
        }

        public String getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(String approvedTime) {
            this.approvedTime = approvedTime;
        }

        public Object getEmpId() {
            return empId;
        }

        public void setEmpId(Object empId) {
            this.empId = empId;
        }

        public Object getBussinessName() {
            return bussinessName;
        }

        public void setBussinessName(Object bussinessName) {
            this.bussinessName = bussinessName;
        }

        public Object getApprovals() {
            return approvals;
        }

        public void setApprovals(Object approvals) {
            this.approvals = approvals;
        }

        public Object getApprovalFlowLevels() {
            return approvalFlowLevels;
        }

        public void setApprovalFlowLevels(Object approvalFlowLevels) {
            this.approvalFlowLevels = approvalFlowLevels;
        }
    }
}
