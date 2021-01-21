package com.example.bq.edmp.work.tracking.bean;

import java.util.List;

public class TrackingDereBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":21,"owner":1,"orgId":1,"deptId":22,"region":"110101","customerId":41,"name":"放假000","responsiblePeople":"张三","address":"四季青1","purpose":"过节日000","advanceLoan":283,"effect":null,"startTime":"2020-12-30","endTime":"2020-12-28","finishedTime":null,"addedTime":"2021-01-04","addedOperator":"管理员","addedOperatorId":16,"status":2,"activityAnnex":null,"deptName":"技术部","customerName":"皖垦","orgName":"北京爱种网络科技有限公司","regionName":"北京市 东城区","types":null,"activityItems":[{"id":32,"activityId":21,"type":1,"uri":"/2021/1609740281296-7753.doc"},{"id":33,"activityId":21,"type":1,"uri":"/2021/1609740281378-1823.docx"}]}
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
         * id : 21
         * owner : 1
         * orgId : 1
         * deptId : 22
         * region : 110101
         * customerId : 41
         * name : 放假000
         * responsiblePeople : 张三
         * address : 四季青1
         * purpose : 过节日000
         * advanceLoan : 283
         * effect : null
         * startTime : 2020-12-30
         * endTime : 2020-12-28
         * finishedTime : null
         * addedTime : 2021-01-04
         * addedOperator : 管理员
         * addedOperatorId : 16
         * status : 2
         * activityAnnex : null
         * deptName : 技术部
         * customerName : 皖垦
         * orgName : 北京爱种网络科技有限公司
         * regionName : 北京市 东城区
         * types : null
         * activityItems : [{"id":32,"activityId":21,"type":1,"uri":"/2021/1609740281296-7753.doc"},{"id":33,"activityId":21,"type":1,"uri":"/2021/1609740281378-1823.docx"}]
         */

        private String id;
        private String owner;
        private String orgId;
        private String deptId;
        private String region;
        private String customerId;
        private String name;
        private String responsiblePeople;
        private String address;
        private String purpose;
        private double advanceLoan;
        private String effect;
        private String startTime;
        private String endTime;
        private String finishedTime;
        private String addedTime;
        private String addedOperator;
        private String addedOperatorId;
        private String status;
        private String activityAnnex;
        private String deptName;
        private String customerName;
        private String orgName;
        private String regionName;
        private String types;
        private List<ActivityItemsBean> activityItems;

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

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public double getAdvanceLoan() {
            return advanceLoan;
        }

        public void setAdvanceLoan(double advanceLoan) {
            this.advanceLoan = advanceLoan;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
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

        public String getActivityAnnex() {
            return activityAnnex;
        }

        public void setActivityAnnex(String activityAnnex) {
            this.activityAnnex = activityAnnex;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public List<ActivityItemsBean> getActivityItems() {
            return activityItems;
        }

        public void setActivityItems(List<ActivityItemsBean> activityItems) {
            this.activityItems = activityItems;
        }

        public static class ActivityItemsBean {
            /**
             * id : 32
             * activityId : 21
             * type : 1
             * uri : /2021/1609740281296-7753.doc
             */

            private String id;
            private String activityId;
            private String type;
            private String uri;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getActivityId() {
                return activityId;
            }

            public void setActivityId(String activityId) {
                this.activityId = activityId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }
        }
    }
}
