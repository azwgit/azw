package com.example.bq.edmp.work.finished.bean;

import java.util.List;

public class MachineListBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"orgId":2,"code":null,"types":null,"businessFormId":null,"planFinishTime":null,"status":1,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":null,"varietyName":null,"planQty":null,"finishedQty":null,"orgName":null,"packagingId":null,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},"rows":[{"id":15,"owner":null,"orgId":null,"code":"JG20201209155744","types":1,"businessFormId":null,"planFinishTime":"2020-12-10","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"100公斤/袋","varietyName":"一号小麦","planQty":1650,"finishedQty":0,"orgName":"北京分公司","packagingId":1,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":14,"owner":null,"orgId":null,"code":"JG20201209155000","types":1,"businessFormId":null,"planFinishTime":"2020-12-09","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"200公斤/袋","varietyName":"一号小麦","planQty":1500,"finishedQty":0,"orgName":"北京分公司","packagingId":2,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":9,"owner":null,"orgId":null,"code":"JG20201209154843","types":2,"businessFormId":null,"planFinishTime":"2020-12-09","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"160公斤/袋","varietyName":"一号小麦","planQty":3000,"finishedQty":0,"orgName":"北京分公司","packagingId":3,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":7,"owner":null,"orgId":null,"code":"JG20201209100230","types":2,"businessFormId":null,"planFinishTime":"2020-12-08","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"100公斤/袋","varietyName":"一号小麦","planQty":1250,"finishedQty":0,"orgName":"北京分公司","packagingId":1,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":4,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * filter : {"id":null,"owner":1,"orgId":2,"code":null,"types":null,"businessFormId":null,"planFinishTime":null,"status":1,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":null,"varietyName":null,"planQty":null,"finishedQty":null,"orgName":null,"packagingId":null,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null}
         * rows : [{"id":15,"owner":null,"orgId":null,"code":"JG20201209155744","types":1,"businessFormId":null,"planFinishTime":"2020-12-10","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"100公斤/袋","varietyName":"一号小麦","planQty":1650,"finishedQty":0,"orgName":"北京分公司","packagingId":1,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":14,"owner":null,"orgId":null,"code":"JG20201209155000","types":1,"businessFormId":null,"planFinishTime":"2020-12-09","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"200公斤/袋","varietyName":"一号小麦","planQty":1500,"finishedQty":0,"orgName":"北京分公司","packagingId":2,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":9,"owner":null,"orgId":null,"code":"JG20201209154843","types":2,"businessFormId":null,"planFinishTime":"2020-12-09","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"160公斤/袋","varietyName":"一号小麦","planQty":3000,"finishedQty":0,"orgName":"北京分公司","packagingId":3,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null},{"id":7,"owner":null,"orgId":null,"code":"JG20201209100230","types":2,"businessFormId":null,"planFinishTime":"2020-12-08","status":null,"addedOperator":null,"addedTime":null,"confirmOperator":null,"confirmTime":null,"finishedTime":null,"finishedOperator":null,"acceptedTime":null,"acceptedOperator":null,"startTime":null,"endTime":null,"varietyPackagingId":null,"varietyPackagingName":"100公斤/袋","varietyName":"一号小麦","planQty":1250,"finishedQty":0,"orgName":"北京分公司","packagingId":1,"cpwarehouseId":null,"fswarehouseId":null,"ylwarehouseId":null,"stockAdds":null,"productWeight":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 4
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private String page;
        private String pagerow;
        private String totalpages;
        private String totalrows;
        private String sumtotal;
        private String sortname;
        private String sortorder;
        private List<RowsBean> rows;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPagerow() {
            return pagerow;
        }

        public void setPagerow(String pagerow) {
            this.pagerow = pagerow;
        }

        public String getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(String totalpages) {
            this.totalpages = totalpages;
        }

        public String getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(String totalrows) {
            this.totalrows = totalrows;
        }

        public String getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(String sumtotal) {
            this.sumtotal = sumtotal;
        }

        public String getSortname() {
            return sortname;
        }

        public void setSortname(String sortname) {
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
             * orgId : 2
             * code : null
             * types : null
             * businessFormId : null
             * planFinishTime : null
             * status : 1
             * addedOperator : null
             * addedTime : null
             * confirmOperator : null
             * confirmTime : null
             * finishedTime : null
             * finishedOperator : null
             * acceptedTime : null
             * acceptedOperator : null
             * startTime : null
             * endTime : null
             * varietyPackagingId : null
             * varietyPackagingName : null
             * varietyName : null
             * planQty : null
             * finishedQty : null
             * orgName : null
             * packagingId : null
             * cpwarehouseId : null
             * fswarehouseId : null
             * ylwarehouseId : null
             * stockAdds : null
             * productWeight : null
             */

            private String id;
            private String owner;
            private String orgId;
            private String code;
            private String types;
            private String businessFormId;
            private String planFinishTime;
            private String status;
            private String addedOperator;
            private String addedTime;
            private String confirmOperator;
            private String confirmTime;
            private String finishedTime;
            private String finishedOperator;
            private String acceptedTime;
            private String acceptedOperator;
            private String startTime;
            private String endTime;
            private String varietyPackagingId;
            private String varietyPackagingName;
            private String varietyName;
            private String planQty;
            private String finishedQty;
            private String orgName;
            private String packagingId;
            private String cpwarehouseId;
            private String fswarehouseId;
            private String ylwarehouseId;
            private String stockAdds;
            private String productWeight;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getOrgId() {
                return orgId;
            }

            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public String getBusinessFormId() {
                return businessFormId;
            }

            public void setBusinessFormId(String businessFormId) {
                this.businessFormId = businessFormId;
            }

            public String getPlanFinishTime() {
                return planFinishTime;
            }

            public void setPlanFinishTime(String planFinishTime) {
                this.planFinishTime = planFinishTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(String addedOperator) {
                this.addedOperator = addedOperator;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public String getConfirmOperator() {
                return confirmOperator;
            }

            public void setConfirmOperator(String confirmOperator) {
                this.confirmOperator = confirmOperator;
            }

            public String getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(String confirmTime) {
                this.confirmTime = confirmTime;
            }

            public String getFinishedTime() {
                return finishedTime;
            }

            public void setFinishedTime(String finishedTime) {
                this.finishedTime = finishedTime;
            }

            public String getFinishedOperator() {
                return finishedOperator;
            }

            public void setFinishedOperator(String finishedOperator) {
                this.finishedOperator = finishedOperator;
            }

            public String getAcceptedTime() {
                return acceptedTime;
            }

            public void setAcceptedTime(String acceptedTime) {
                this.acceptedTime = acceptedTime;
            }

            public String getAcceptedOperator() {
                return acceptedOperator;
            }

            public void setAcceptedOperator(String acceptedOperator) {
                this.acceptedOperator = acceptedOperator;
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

            public String getVarietyPackagingId() {
                return varietyPackagingId;
            }

            public void setVarietyPackagingId(String varietyPackagingId) {
                this.varietyPackagingId = varietyPackagingId;
            }

            public String getVarietyPackagingName() {
                return varietyPackagingName;
            }

            public void setVarietyPackagingName(String varietyPackagingName) {
                this.varietyPackagingName = varietyPackagingName;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public String getPlanQty() {
                return planQty;
            }

            public void setPlanQty(String planQty) {
                this.planQty = planQty;
            }

            public String getFinishedQty() {
                return finishedQty;
            }

            public void setFinishedQty(String finishedQty) {
                this.finishedQty = finishedQty;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
            }

            public String getCpwarehouseId() {
                return cpwarehouseId;
            }

            public void setCpwarehouseId(String cpwarehouseId) {
                this.cpwarehouseId = cpwarehouseId;
            }

            public String getFswarehouseId() {
                return fswarehouseId;
            }

            public void setFswarehouseId(String fswarehouseId) {
                this.fswarehouseId = fswarehouseId;
            }

            public String getYlwarehouseId() {
                return ylwarehouseId;
            }

            public void setYlwarehouseId(String ylwarehouseId) {
                this.ylwarehouseId = ylwarehouseId;
            }

            public String getStockAdds() {
                return stockAdds;
            }

            public void setStockAdds(String stockAdds) {
                this.stockAdds = stockAdds;
            }

            public String getProductWeight() {
                return productWeight;
            }

            public void setProductWeight(String productWeight) {
                this.productWeight = productWeight;
            }
        }

        public static class RowsBean {
            /**
             * id : 15
             * owner : null
             * orgId : null
             * code : JG20201209155744
             * types : 1
             * businessFormId : null
             * planFinishTime : 2020-12-10
             * status : null
             * addedOperator : null
             * addedTime : null
             * confirmOperator : null
             * confirmTime : null
             * finishedTime : null
             * finishedOperator : null
             * acceptedTime : null
             * acceptedOperator : null
             * startTime : null
             * endTime : null
             * varietyPackagingId : null
             * varietyPackagingName : 100公斤/袋
             * varietyName : 一号小麦
             * planQty : 1650
             * finishedQty : 0
             * orgName : 北京分公司
             * packagingId : 1
             * cpwarehouseId : null
             * fswarehouseId : null
             * ylwarehouseId : null
             * stockAdds : null
             * productWeight : null
             */

            private String id;
            private String owner;
            private String orgId;
            private String code;
            private String types;
            private String businessFormId;
            private String planFinishTime;
            private String status;
            private String addedOperator;
            private String addedTime;
            private String confirmOperator;
            private String confirmTime;
            private String finishedTime;
            private String finishedOperator;
            private String acceptedTime;
            private String acceptedOperator;
            private String startTime;
            private String endTime;
            private String varietyPackagingId;
            private String varietyPackagingName;
            private String varietyName;
            private String planQty;
            private String finishedQty;
            private String orgName;
            private String packagingId;
            private String cpwarehouseId;
            private String fswarehouseId;
            private String ylwarehouseId;
            private String stockAdds;
            private String productWeight;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getOrgId() {
                return orgId;
            }

            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public String getBusinessFormId() {
                return businessFormId;
            }

            public void setBusinessFormId(String businessFormId) {
                this.businessFormId = businessFormId;
            }

            public String getPlanFinishTime() {
                return planFinishTime;
            }

            public void setPlanFinishTime(String planFinishTime) {
                this.planFinishTime = planFinishTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(String addedOperator) {
                this.addedOperator = addedOperator;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public String getConfirmOperator() {
                return confirmOperator;
            }

            public void setConfirmOperator(String confirmOperator) {
                this.confirmOperator = confirmOperator;
            }

            public String getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(String confirmTime) {
                this.confirmTime = confirmTime;
            }

            public String getFinishedTime() {
                return finishedTime;
            }

            public void setFinishedTime(String finishedTime) {
                this.finishedTime = finishedTime;
            }

            public String getFinishedOperator() {
                return finishedOperator;
            }

            public void setFinishedOperator(String finishedOperator) {
                this.finishedOperator = finishedOperator;
            }

            public String getAcceptedTime() {
                return acceptedTime;
            }

            public void setAcceptedTime(String acceptedTime) {
                this.acceptedTime = acceptedTime;
            }

            public String getAcceptedOperator() {
                return acceptedOperator;
            }

            public void setAcceptedOperator(String acceptedOperator) {
                this.acceptedOperator = acceptedOperator;
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

            public String getVarietyPackagingId() {
                return varietyPackagingId;
            }

            public void setVarietyPackagingId(String varietyPackagingId) {
                this.varietyPackagingId = varietyPackagingId;
            }

            public String getVarietyPackagingName() {
                return varietyPackagingName;
            }

            public void setVarietyPackagingName(String varietyPackagingName) {
                this.varietyPackagingName = varietyPackagingName;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public String getPlanQty() {
                return planQty;
            }

            public void setPlanQty(String planQty) {
                this.planQty = planQty;
            }

            public String getFinishedQty() {
                return finishedQty;
            }

            public void setFinishedQty(String finishedQty) {
                this.finishedQty = finishedQty;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
            }

            public String getCpwarehouseId() {
                return cpwarehouseId;
            }

            public void setCpwarehouseId(String cpwarehouseId) {
                this.cpwarehouseId = cpwarehouseId;
            }

            public String getFswarehouseId() {
                return fswarehouseId;
            }

            public void setFswarehouseId(String fswarehouseId) {
                this.fswarehouseId = fswarehouseId;
            }

            public String getYlwarehouseId() {
                return ylwarehouseId;
            }

            public void setYlwarehouseId(String ylwarehouseId) {
                this.ylwarehouseId = ylwarehouseId;
            }

            public String getStockAdds() {
                return stockAdds;
            }

            public void setStockAdds(String stockAdds) {
                this.stockAdds = stockAdds;
            }

            public String getProductWeight() {
                return productWeight;
            }

            public void setProductWeight(String productWeight) {
                this.productWeight = productWeight;
            }
        }
    }
}
