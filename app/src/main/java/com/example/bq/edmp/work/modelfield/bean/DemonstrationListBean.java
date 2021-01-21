package com.example.bq.edmp.work.modelfield.bean;

import java.util.List;

public class DemonstrationListBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":null,"cropId":0,"varietyId":null,"regionId":null,"years":null,"companyName":null,"address":null,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":null,"demonstrationItem":null,"region":null},"rows":[{"id":1,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"爱种网","address":"北京","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号小麦","demonstrationItem":null,"region":"北京市 东城区"},{"id":2,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"","address":"","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号小麦","demonstrationItem":null,"region":"北京市 东城区"},{"id":3,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"单位","address":"","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号玉米","demonstrationItem":null,"region":"北京市 东城区"}],"page":1,"pagerow":15,"totalpages":0,"totalrows":3,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":null,"cropId":0,"varietyId":null,"regionId":null,"years":null,"companyName":null,"address":null,"addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":null,"demonstrationItem":null,"region":null}
         * rows : [{"id":1,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"爱种网","address":"北京","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号小麦","demonstrationItem":null,"region":"北京市 东城区"},{"id":2,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"","address":"","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号小麦","demonstrationItem":null,"region":"北京市 东城区"},{"id":3,"owner":null,"cropId":null,"varietyId":null,"regionId":null,"years":2020,"companyName":"单位","address":"","addedTime":null,"addedOperator":null,"addedOperatorId":null,"status":null,"varietyName":"一号玉米","demonstrationItem":null,"region":"北京市 东城区"}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 3
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
             * owner : null
             * cropId : 0
             * varietyId : null
             * regionId : null
             * years : null
             * companyName : null
             * address : null
             * addedTime : null
             * addedOperator : null
             * addedOperatorId : null
             * status : null
             * varietyName : null
             * demonstrationItem : null
             * region : null
             */

            private String id;
            private String owner;
            private String cropId;
            private String varietyId;
            private String regionId;
            private String years;
            private String companyName;
            private String address;
            private String addedTime;
            private String addedOperator;
            private String addedOperatorId;
            private String status;
            private String varietyName;
            private String demonstrationItem;
            private String region;

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

            public String getCropId() {
                return cropId;
            }

            public void setCropId(String cropId) {
                this.cropId = cropId;
            }

            public String getVarietyId() {
                return varietyId;
            }

            public void setVarietyId(String varietyId) {
                this.varietyId = varietyId;
            }

            public String getRegionId() {
                return regionId;
            }

            public void setRegionId(String regionId) {
                this.regionId = regionId;
            }

            public String getYears() {
                return years;
            }

            public void setYears(String years) {
                this.years = years;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(String addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public String getDemonstrationItem() {
                return demonstrationItem;
            }

            public void setDemonstrationItem(String demonstrationItem) {
                this.demonstrationItem = demonstrationItem;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }

        public static class RowsBean {
            /**
             * id : 1
             * owner : null
             * cropId : null
             * varietyId : null
             * regionId : null
             * years : 2020
             * companyName : 爱种网
             * address : 北京
             * addedTime : null
             * addedOperator : null
             * addedOperatorId : null
             * status : null
             * varietyName : 一号小麦
             * demonstrationItem : null
             * region : 北京市 东城区
             */

            private String id;
            private String owner;
            private String cropId;
            private String varietyId;
            private String regionId;
            private String years;
            private String companyName;
            private String address;
            private String addedTime;
            private String addedOperator;
            private String addedOperatorId;
            private String status;
            private String varietyName;
            private String demonstrationItem;
            private String region;

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

            public String getCropId() {
                return cropId;
            }

            public void setCropId(String cropId) {
                this.cropId = cropId;
            }

            public String getVarietyId() {
                return varietyId;
            }

            public void setVarietyId(String varietyId) {
                this.varietyId = varietyId;
            }

            public String getRegionId() {
                return regionId;
            }

            public void setRegionId(String regionId) {
                this.regionId = regionId;
            }

            public String getYears() {
                return years;
            }

            public void setYears(String years) {
                this.years = years;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getAddedOperatorId() {
                return addedOperatorId;
            }

            public void setAddedOperatorId(String addedOperatorId) {
                this.addedOperatorId = addedOperatorId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public String getDemonstrationItem() {
                return demonstrationItem;
            }

            public void setDemonstrationItem(String demonstrationItem) {
                this.demonstrationItem = demonstrationItem;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }
}