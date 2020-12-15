package com.example.bq.edmp.word.purchase.bean;

import java.util.List;

public class PurchaseListBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"orgId":2,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":null,"addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":null,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":null,"farmerName":null,"orgName":null,"varietyName":null,"warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},"rows":[{"id":1,"owner":null,"orgId":null,"code":"144332","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-11-27","addedOperator":null,"grossWeight":null,"tareWeight":1,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":3,"owner":null,"orgId":null,"code":"321","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2018-11-20","addedOperator":null,"grossWeight":null,"tareWeight":0,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":5,"owner":null,"orgId":null,"code":"1","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":2,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":6,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":7,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":8,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":9,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":10,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":11,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":12,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":13,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":14,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":15,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":16,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":17,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"beginTime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null}],"page":1,"pagerow":15,"totalpages":5,"totalrows":79,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"orgId":2,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":null,"addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":null,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":null,"farmerName":null,"orgName":null,"varietyName":null,"warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null}
         * rows : [{"id":1,"owner":null,"orgId":null,"code":"144332","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-11-27","addedOperator":null,"grossWeight":null,"tareWeight":1,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":3,"owner":null,"orgId":null,"code":"321","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2018-11-20","addedOperator":null,"grossWeight":null,"tareWeight":0,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":5,"owner":null,"orgId":null,"code":"1","farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":2,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":6,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":7,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":8,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":9,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":10,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":11,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":12,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":13,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":14,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":15,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":16,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":null,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null},{"id":17,"owner":null,"orgId":null,"code":null,"farmId":null,"farmerId":null,"varietyId":null,"warehouseId":null,"addedTime":"2020-12-02","addedOperator":null,"grossWeight":null,"tareWeight":null,"netWeight":0,"stockAddId":null,"testingId":null,"status":1,"begStringime":null,"endTime":null,"farmName":"1号农场","farmerName":"张三","orgName":"北京分公司","varietyName":"一号小麦","warehouseName":null,"testingItems":null,"testPlanItemId":null,"testingItemValue":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 5
         * totalrows : 79
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
             * status : null
             * begStringime : null
             * endTime : null
             * farmName : null
             * farmerName : null
             * orgName : null
             * varietyName : null
             * warehouseName : null
             * testingItems : null
             * testPlanItemId : null
             * testingItemValue : null
             */

            private String id;
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
            private String status;
            private String begStringime;
            private String endTime;
            private String farmName;
            private String farmerName;
            private String orgName;
            private String varietyName;
            private String warehouseName;
            private String testingItems;
            private String testPlanItemId;
            private String testingItemValue;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getBegStringime() {
                return begStringime;
            }

            public void setBegStringime(String begStringime) {
                this.begStringime = begStringime;
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

            public String getTestingItems() {
                return testingItems;
            }

            public void setTestingItems(String testingItems) {
                this.testingItems = testingItems;
            }

            public String getTestPlanItemId() {
                return testPlanItemId;
            }

            public void setTestPlanItemId(String testPlanItemId) {
                this.testPlanItemId = testPlanItemId;
            }

            public String getTestingItemValue() {
                return testingItemValue;
            }

            public void setTestingItemValue(String testingItemValue) {
                this.testingItemValue = testingItemValue;
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
             * status : 1
             * begStringime : null
             * endTime : null
             * farmName : 1号农场
             * farmerName : 张三
             * orgName : 北京分公司
             * varietyName : 一号小麦
             * warehouseName : null
             * testingItems : null
             * testPlanItemId : null
             * testingItemValue : null
             */

            private String id;
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
            private String status;
            private String begStringime;
            private String endTime;
            private String farmName;
            private String farmerName;
            private String orgName;
            private String varietyName;
            private String warehouseName;
            private String testingItems;
            private String testPlanItemId;
            private String testingItemValue;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getBegStringime() {
                return begStringime;
            }

            public void setBegStringime(String begStringime) {
                this.begStringime = begStringime;
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

            public String getTestingItems() {
                return testingItems;
            }

            public void setTestingItems(String testingItems) {
                this.testingItems = testingItems;
            }

            public String getTestPlanItemId() {
                return testPlanItemId;
            }

            public void setTestPlanItemId(String testPlanItemId) {
                this.testPlanItemId = testPlanItemId;
            }

            public String getTestingItemValue() {
                return testingItemValue;
            }

            public void setTestingItemValue(String testingItemValue) {
                this.testingItemValue = testingItemValue;
            }
        }
    }
}
