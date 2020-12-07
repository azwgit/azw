package com.example.bq.edmp.word.inventory.bean;

import java.util.List;

public class InventoryBean {

    /**
     * filter : {"warehouseId":null,"varietyId":null,"qty":null,"lockedQty":null,"orgIds":null,"cropId":0,"orgId":2,"orgName":null,"varietyName":null,"warehouseName":null,"stockRecords":null}
     * rows : [{"warehouseId":1,"varietyId":null,"qty":123,"lockedQty":null,"orgIds":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦","warehouseName":"1号仓库","stockRecords":null},{"warehouseId":4,"varietyId":null,"qty":182,"lockedQty":null,"orgIds":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号玉米","warehouseName":"1号玉米仓库","stockRecords":null}]
     * page : 1
     * pagerow : 15
     * totalpages : 0
     * totalrows : 2
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
         * warehouseId : null
         * varietyId : null
         * qty : null
         * lockedQty : null
         * orgIds : null
         * cropId : 0
         * orgId : 2
         * orgName : null
         * varietyName : null
         * warehouseName : null
         * stockRecords : null
         */

        private Object warehouseId;
        private Object varietyId;
        private Object qty;
        private Object lockedQty;
        private Object orgIds;
        private int cropId;
        private int orgId;
        private Object orgName;
        private Object varietyName;
        private Object warehouseName;
        private Object stockRecords;

        public Object getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Object warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Object getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(Object varietyId) {
            this.varietyId = varietyId;
        }

        public Object getQty() {
            return qty;
        }

        public void setQty(Object qty) {
            this.qty = qty;
        }

        public Object getLockedQty() {
            return lockedQty;
        }

        public void setLockedQty(Object lockedQty) {
            this.lockedQty = lockedQty;
        }

        public Object getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(Object orgIds) {
            this.orgIds = orgIds;
        }

        public int getCropId() {
            return cropId;
        }

        public void setCropId(int cropId) {
            this.cropId = cropId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
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

        public Object getStockRecords() {
            return stockRecords;
        }

        public void setStockRecords(Object stockRecords) {
            this.stockRecords = stockRecords;
        }
    }

    public static class RowsBean {
        /**
         * warehouseId : 1
         * varietyId : null
         * qty : 123
         * lockedQty : null
         * orgIds : null
         * cropId : null
         * orgId : null
         * orgName : 北京分公司
         * varietyName : 一号小麦
         * warehouseName : 1号仓库
         * stockRecords : null
         */

        private int warehouseId;
        private String varietyId;
        private String qty;
        private String lockedQty;
        private String orgIds;
        private String cropId;
        private String orgId;
        private String orgName;
        private String varietyName;
        private String warehouseName;
        private String stockRecords;

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getLockedQty() {
            return lockedQty;
        }

        public void setLockedQty(String lockedQty) {
            this.lockedQty = lockedQty;
        }

        public String getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(String orgIds) {
            this.orgIds = orgIds;
        }

        public String getCropId() {
            return cropId;
        }

        public void setCropId(String cropId) {
            this.cropId = cropId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
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

        public String getStockRecords() {
            return stockRecords;
        }

        public void setStockRecords(String stockRecords) {
            this.stockRecords = stockRecords;
        }
    }
}
