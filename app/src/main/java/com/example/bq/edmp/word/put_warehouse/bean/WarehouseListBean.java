package com.example.bq.edmp.word.put_warehouse.bean;

import java.util.List;

public class WarehouseListBean {

    /**
     * filter : {"id":null,"code":null,"warehouseId":null,"type1":null,"type2":null,"businessFormId":null,"addedTime":null,"addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":2,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":null,"warehouseName":null,"orgName":null,"addQty":null,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null}
     * rows : [{"id":1,"code":"143","warehouseId":null,"type1":null,"type2":3,"businessFormId":null,"addedTime":"2020-12-02","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":12,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":7,"code":"SG20201203000001","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":1,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":18,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":-9,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":19,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":-9,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":20,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":-9,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":21,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":-9,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":22,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":40,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":23,"code":"SG202012313423","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":49,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":24,"code":"SG20201203150823","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":90,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":29,"code":"SG20201203150709","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":49990,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":30,"code":"SG20201203150709","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":49990,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null},{"id":31,"code":"SG20201203150709","warehouseId":null,"type1":null,"type2":1,"businessFormId":null,"addedTime":"2020-12-03","addedOperator":null,"finishedTime":null,"finishedOperator":null,"remark":null,"orgId":null,"orgIds":null,"beginTime":null,"endTime":null,"varietyId":null,"varietyName":"一号小麦","warehouseName":"1号仓库","orgName":"北京分公司","addQty":49990,"grainPurchaseCode":null,"testingItems":null,"stockAllots":null}]
     * page : 1
     * pagerow : 15
     * totalpages : 0
     * totalrows : 12
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
    private String sortname;
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
         * code : null
         * warehouseId : null
         * type1 : null
         * type2 : null
         * businessFormId : null
         * addedTime : null
         * addedOperator : null
         * finishedTime : null
         * finishedOperator : null
         * remark : null
         * orgId : 2
         * orgIds : null
         * beginTime : null
         * endTime : null
         * varietyId : null
         * varietyName : null
         * warehouseName : null
         * orgName : null
         * addQty : null
         * grainPurchaseCode : null
         * testingItems : null
         * stockAllots : null
         */

        private String id;
        private String code;
        private String warehouseId;
        private String type1;
        private String type2;
        private String businessFormId;
        private String addedTime;
        private String addedOperator;
        private String finishedTime;
        private String finishedOperator;
        private String remark;
        private int orgId;
        private String orgIds;
        private String beginTime;
        private String endTime;
        private String varietyId;
        private String varietyName;
        private String warehouseName;
        private String orgName;
        private String addQty;
        private String grainPurchaseCode;
        private String testingItems;
        private String stockAllots;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getBusinessFormId() {
            return businessFormId;
        }

        public void setBusinessFormId(String businessFormId) {
            this.businessFormId = businessFormId;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(String orgIds) {
            this.orgIds = orgIds;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getAddQty() {
            return addQty;
        }

        public void setAddQty(String addQty) {
            this.addQty = addQty;
        }

        public String getGrainPurchaseCode() {
            return grainPurchaseCode;
        }

        public void setGrainPurchaseCode(String grainPurchaseCode) {
            this.grainPurchaseCode = grainPurchaseCode;
        }

        public String getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(String testingItems) {
            this.testingItems = testingItems;
        }

        public String getStockAllots() {
            return stockAllots;
        }

        public void setStockAllots(String stockAllots) {
            this.stockAllots = stockAllots;
        }
    }

    public static class RowsBean {
        /**
         * id : 1
         * code : 143
         * warehouseId : null
         * type1 : null
         * type2 : 3
         * businessFormId : null
         * addedTime : 2020-12-02
         * addedOperator : null
         * finishedTime : null
         * finishedOperator : null
         * remark : null
         * orgId : null
         * orgIds : null
         * beginTime : null
         * endTime : null
         * varietyId : null
         * varietyName : 一号小麦
         * warehouseName : 1号仓库
         * orgName : 北京分公司
         * addQty : 12
         * grainPurchaseCode : null
         * testingItems : null
         * stockAllots : null
         */

        private int id;
        private String code;
        private String warehouseId;
        private String type1;
        private int type2;
        private String businessFormId;
        private String addedTime;
        private String addedOperator;
        private String finishedTime;
        private String finishedOperator;
        private String remark;
        private String orgId;
        private String orgIds;
        private String beginTime;
        private String endTime;
        private String varietyId;
        private String varietyName;
        private String warehouseName;
        private String orgName;
        private String addQty;
        private String grainPurchaseCode;
        private String testingItems;
        private String stockAllots;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public int getType2() {
            return type2;
        }

        public void setType2(int type2) {
            this.type2 = type2;
        }

        public String getBusinessFormId() {
            return businessFormId;
        }

        public void setBusinessFormId(String businessFormId) {
            this.businessFormId = businessFormId;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(String orgIds) {
            this.orgIds = orgIds;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getAddQty() {
            return addQty;
        }

        public void setAddQty(String addQty) {
            this.addQty = addQty;
        }

        public String getGrainPurchaseCode() {
            return grainPurchaseCode;
        }

        public void setGrainPurchaseCode(String grainPurchaseCode) {
            this.grainPurchaseCode = grainPurchaseCode;
        }

        public String getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(String testingItems) {
            this.testingItems = testingItems;
        }

        public String getStockAllots() {
            return stockAllots;
        }

        public void setStockAllots(String stockAllots) {
            this.stockAllots = stockAllots;
        }
    }
}
