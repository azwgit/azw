package com.example.bq.edmp.work.marketingactivities.bean;

import java.io.Serializable;
import java.util.List;

public class ActivitySiteBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":9,"owner":1,"orgId":2,"deptId":23,"region":"110102","customerId":24,"name":"北京活动","responsiblePeople":"可乐","address":"北京","purpose":"活动开始","advanceLoan":5000,"effect":"放假开心","startTime":"2020-12-31","endTime":"2020-12-31","finishedTime":"2021-01-04T03:08:57.000+0000","addedTime":"2020-12-31","addedOperator":"李四","addedOperatorId":16,"status":5,"activityAnnex":null,"deptName":"运营部","customerName":"雪碧vs可乐","orgName":"北京爱种网络科技有限公司 - 北京分公司","regionName":"北京市 西城区","activityItems":[{"id":16,"activityId":9,"type":2,"uri":"/2021/1609729736986-145.jpg"},{"id":17,"activityId":9,"type":2,"uri":"/2021/1609729737423-5492.jpg"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * id : 9
         * owner : 1
         * orgId : 2
         * deptId : 23
         * region : 110102
         * customerId : 24
         * name : 北京活动
         * responsiblePeople : 可乐
         * address : 北京
         * purpose : 活动开始
         * advanceLoan : 5000.0
         * effect : 放假开心
         * startTime : 2020-12-31
         * endTime : 2020-12-31
         * finishedTime : 2021-01-04T03:08:57.000+0000
         * addedTime : 2020-12-31
         * addedOperator : 李四
         * addedOperatorId : 16
         * status : 5
         * activityAnnex : null
         * deptName : 运营部
         * customerName : 雪碧vs可乐
         * orgName : 北京爱种网络科技有限公司 - 北京分公司
         * regionName : 北京市 西城区
         * activityItems : [{"id":16,"activityId":9,"type":2,"uri":"/2021/1609729736986-145.jpg"},{"id":17,"activityId":9,"type":2,"uri":"/2021/1609729737423-5492.jpg"}]
         */

        private int id;
        private int owner;
        private int orgId;
        private int deptId;
        private String region;
        private int customerId;
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
        private int addedOperatorId;
        private int status;
        private Object activityAnnex;
        private String deptName;
        private String customerName;
        private String orgName;
        private String regionName;
        private List<ActivityItemsBean> activityItems;

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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
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

        public int getAddedOperatorId() {
            return addedOperatorId;
        }

        public void setAddedOperatorId(int addedOperatorId) {
            this.addedOperatorId = addedOperatorId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getActivityAnnex() {
            return activityAnnex;
        }

        public void setActivityAnnex(Object activityAnnex) {
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

        public List<ActivityItemsBean> getActivityItems() {
            return activityItems;
        }

        public void setActivityItems(List<ActivityItemsBean> activityItems) {
            this.activityItems = activityItems;
        }

        public static class ActivityItemsBean {
            /**
             * id : 16
             * activityId : 9
             * type : 2
             * uri : /2021/1609729736986-145.jpg
             */

            private int id;
            private int activityId;
            private int type;
            private String uri;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
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
