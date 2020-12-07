package com.example.bq.edmp.word.purchase.bean;

import java.util.List;

public class PurchaseListBean {

    /**
     * filter : {"id":null,"owner":1,"orgId":2,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":null,"addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":null,"stockAddId":null,"testingId":null,"beginTime":null,"endTime":null,"farmName":null,"farmerName":null,"orgName":null,"varietyName":null,"warehouseName":null,"orgIds":null,"status":null,"testingItems":null}
     * rows : [{"id":1,"owner":null,"orgId":null,"code":"144332","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-11-27","addedOperator":null,"grossWeight":null,"tareWeight":1,"netWeight":0,"stockAddId":null,"testingId":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"orgIds":null,"status":2,"testingItems":null},{"id":3,"owner":null,"orgId":null,"code":"321","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2018-11-20","addedOperator":null,"grossWeight":null,"tareWeight":0,"netWeight":0,"stockAddId":null,"testingId":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"orgIds":null,"status":1,"testingItems":null},{"id":4,"owner":null,"orgId":null,"code":"21341","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-01","addedOperator":null,"grossWeight":null,"tareWeight":0,"netWeight":0,"stockAddId":null,"testingId":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"李四","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"orgIds":null,"status":1,"testingItems":null}]
     * page : 1
     * pagerow : 15
     * totalpages : 0
     * totalrows : 3
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
         * orgId : 2
         * code : null
         * farmId : null
         * farmerId : null
         * varietyId : null
         * warehouseId : null
         * addedTime : null
         * addedOperator : null
         * grossWeight : null
         * tareWeight : null
         * netWeight : null
         * stockAddId : null
         * testingId : null
         * beginTime : null
         * endTime : null
         * farmName : null
         * farmerName : null
         * orgName : null
         * varietyName : null
         * warehouseName : null
         * orgIds : null
         * status : null
         * testingItems : null
         */

        private Object id;
        private int owner;
        private int orgId;
        private Object code;
        private Object farmId;
        private Object farmerId;
        private Object varietyId;
        private Object warehouseId;
        private Object addedTime;
        private Object addedOperator;
        private Object grossWeight;
        private Object tareWeight;
        private Object netWeight;
        private Object stockAddId;
        private Object testingId;
        private Object beginTime;
        private Object endTime;
        private Object farmName;
        private Object farmerName;
        private Object orgName;
        private Object varietyName;
        private Object warehouseName;
        private Object orgIds;
        private Object status;
        private Object testingItems;

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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getFarmId() {
            return farmId;
        }

        public void setFarmId(Object farmId) {
            this.farmId = farmId;
        }

        public Object getFarmerId() {
            return farmerId;
        }

        public void setFarmerId(Object farmerId) {
            this.farmerId = farmerId;
        }

        public Object getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(Object varietyId) {
            this.varietyId = varietyId;
        }

        public Object getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Object warehouseId) {
            this.warehouseId = warehouseId;
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

        public Object getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(Object grossWeight) {
            this.grossWeight = grossWeight;
        }

        public Object getTareWeight() {
            return tareWeight;
        }

        public void setTareWeight(Object tareWeight) {
            this.tareWeight = tareWeight;
        }

        public Object getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Object netWeight) {
            this.netWeight = netWeight;
        }

        public Object getStockAddId() {
            return stockAddId;
        }

        public void setStockAddId(Object stockAddId) {
            this.stockAddId = stockAddId;
        }

        public Object getTestingId() {
            return testingId;
        }

        public void setTestingId(Object testingId) {
            this.testingId = testingId;
        }

        public Object getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Object beginTime) {
            this.beginTime = beginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getFarmName() {
            return farmName;
        }

        public void setFarmName(Object farmName) {
            this.farmName = farmName;
        }

        public Object getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(Object farmerName) {
            this.farmerName = farmerName;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public Object getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(Object varietyName) {
            this.varietyName = varietyName;
        }

        public Object getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(Object warehouseName) {
            this.warehouseName = warehouseName;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(Object testingItems) {
            this.testingItems = testingItems;
        }
    }

    public static class RowsBean {
        /**
         * id : 1
         * owner : null
         * orgId : null
         * code : 144332
         * farmId : null
         * farmerId : null
         * varietyId : null
         * warehouseId : null
         * addedTime : 2020-11-27
         * addedOperator : null
         * grossWeight : null
         * tareWeight : 1
         * netWeight : 0
         * stockAddId : null
         * testingId : null
         * beginTime : null
         * endTime : null
         * farmName : 1号农场
         * farmerName : 张三
         * orgName : 北京分公司
         * varietyName : 一号小麦
         * warehouseName : null
         * orgIds : null
         * status : 2
         * testingItems : null
         */

        private int id;
        private String owner;
        private String orgId;
        private String code;
        private String farmId;
        private String farmerId;
        private String varietyId;
        private String warehouseId;
        private String addedTime;
        private String addedOperator;
        private String grossWeight;
        private String tareWeight;
        private String netWeight;
        private String stockAddId;
        private String testingId;
        private String beginTime;
        private String endTime;
        private String farmName;
        private String farmerName;
        private String orgName;
        private String varietyName;
        private String warehouseName;
        private String orgIds;
        private int status;
        private String testingItems;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getFarmerId() {
            return farmerId;
        }

        public void setFarmerId(String farmerId) {
            this.farmerId = farmerId;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
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

        public String getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(String grossWeight) {
            this.grossWeight = grossWeight;
        }

        public String getTareWeight() {
            return tareWeight;
        }

        public void setTareWeight(String tareWeight) {
            this.tareWeight = tareWeight;
        }

        public String getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(String netWeight) {
            this.netWeight = netWeight;
        }

        public String getStockAddId() {
            return stockAddId;
        }

        public void setStockAddId(String stockAddId) {
            this.stockAddId = stockAddId;
        }

        public String getTestingId() {
            return testingId;
        }

        public void setTestingId(String testingId) {
            this.testingId = testingId;
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

        public String getFarmName() {
            return farmName;
        }

        public void setFarmName(String farmName) {
            this.farmName = farmName;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
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

        public String getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(String orgIds) {
            this.orgIds = orgIds;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTestingItems() {
            return testingItems;
        }

        public void setTestingItems(String testingItems) {
            this.testingItems = testingItems;
        }
    }
}
