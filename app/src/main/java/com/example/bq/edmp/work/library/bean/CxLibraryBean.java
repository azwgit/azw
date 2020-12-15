package com.example.bq.edmp.work.library.bean;

import java.util.List;

public class CxLibraryBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"qty":null,"lockedQty":null,"warehouseId":null,"itemId":null,"varietyId":null,"packagingId":null,"cropId":null,"orgId":2,"orgName":null,"varietyName":null,"warehouseName":null,"packagingName":null,"stockRecords":null},"rows":[{"id":null,"qty":6.9,"lockedQty":null,"warehouseId":5,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"1号辣椒仓库","packagingName":null,"stockRecords":null},{"id":null,"qty":9.73,"lockedQty":null,"warehouseId":6,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"2号辣椒仓库","packagingName":null,"stockRecords":null},{"id":null,"qty":-7.63,"lockedQty":null,"warehouseId":7,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"3号辣椒仓库33","packagingName":null,"stockRecords":null},{"id":null,"qty":-0.43,"lockedQty":null,"warehouseId":8,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"1号仓库","packagingName":null,"stockRecords":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":4,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"qty":null,"lockedQty":null,"warehouseId":null,"itemId":null,"varietyId":null,"packagingId":null,"cropId":null,"orgId":2,"orgName":null,"varietyName":null,"warehouseName":null,"packagingName":null,"stockRecords":null}
         * rows : [{"id":null,"qty":6.9,"lockedQty":null,"warehouseId":5,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"1号辣椒仓库","packagingName":null,"stockRecords":null},{"id":null,"qty":9.73,"lockedQty":null,"warehouseId":6,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"2号辣椒仓库","packagingName":null,"stockRecords":null},{"id":null,"qty":-7.63,"lockedQty":null,"warehouseId":7,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"3号辣椒仓库33","packagingName":null,"stockRecords":null},{"id":null,"qty":-0.43,"lockedQty":null,"warehouseId":8,"itemId":1,"varietyId":null,"packagingId":null,"cropId":null,"orgId":null,"orgName":"北京分公司","varietyName":"一号小麦 100公斤/袋","warehouseName":"1号仓库","packagingName":null,"stockRecords":null}]
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
             * qty : null
             * lockedQty : null
             * warehouseId : null
             * itemId : null
             * varietyId : null
             * packagingId : null
             * cropId : null
             * orgId : 2
             * orgName : null
             * varietyName : null
             * warehouseName : null
             * packagingName : null
             * stockRecords : null
             */

            private String id;
            private String qty;
            private String lockedQty;
            private String warehouseId;
            private String itemId;
            private String varietyId;
            private String packagingId;
            private String cropId;
            private String orgId;
            private String orgName;
            private String varietyName;
            private String warehouseName;
            private String packagingName;
            private String stockRecords;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(String warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }

            public String getVarietyId() {
                return varietyId;
            }

            public void setVarietyId(String varietyId) {
                this.varietyId = varietyId;
            }

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
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

            public String getPackagingName() {
                return packagingName;
            }

            public void setPackagingName(String packagingName) {
                this.packagingName = packagingName;
            }

            public String getStockRecords() {
                return stockRecords;
            }

            public void setStockRecords(String stockRecords) {
                this.stockRecords = stockRecords;
            }
        }

        public static class RowsBean {
            /**
             * id : null
             * qty : 6.9
             * lockedQty : null
             * warehouseId : 5
             * itemId : 1
             * varietyId : null
             * packagingId : null
             * cropId : null
             * orgId : null
             * orgName : 北京分公司
             * varietyName : 一号小麦 100公斤/袋
             * warehouseName : 1号辣椒仓库
             * packagingName : null
             * stockRecords : null
             */

            private String id;
            private String qty;
            private String lockedQty;
            private String warehouseId;
            private String itemId;
            private String varietyId;
            private String packagingId;
            private String cropId;
            private String orgId;
            private String orgName;
            private String varietyName;
            private String warehouseName;
            private String packagingName;
            private String stockRecords;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(String warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }

            public String getVarietyId() {
                return varietyId;
            }

            public void setVarietyId(String varietyId) {
                this.varietyId = varietyId;
            }

            public String getPackagingId() {
                return packagingId;
            }

            public void setPackagingId(String packagingId) {
                this.packagingId = packagingId;
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

            public String getPackagingName() {
                return packagingName;
            }

            public void setPackagingName(String packagingName) {
                this.packagingName = packagingName;
            }

            public String getStockRecords() {
                return stockRecords;
            }

            public void setStockRecords(String stockRecords) {
                this.stockRecords = stockRecords;
            }
        }
    }
}
