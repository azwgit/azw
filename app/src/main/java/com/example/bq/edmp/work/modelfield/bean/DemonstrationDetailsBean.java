package com.example.bq.edmp.work.modelfield.bean;

import java.io.Serializable;
import java.util.List;

public class DemonstrationDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":0,"cropId":1,"varietyId":1,"regionId":"110101","years":2020,"companyName":"爱种网","address":"北京","addedTime":"2020-12-31","addedOperator":"管理员","addedOperatorId":16,"status":1,"varietyName":"一号小麦","demonstrationItem":[{"id":{"idx":1,"demonstrationId":1},"title":"222","addedTime":"2021-01-04 09:39","addedOperator":"222","status":1,"idx":null,"demonstrationId":null,"demonstrationItemAnnexs":[{"id":1,"demonstrationId":1,"itemIdx":1,"uri":"2222","demonstrationImg":null},{"id":2,"demonstrationId":1,"itemIdx":1,"uri":"1111","demonstrationImg":null},{"id":10,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730284821-4413.jpg","demonstrationImg":null},{"id":12,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310517-1363.jpg","demonstrationImg":null},{"id":13,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310796-8189.jpg","demonstrationImg":null},{"id":14,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350428-9797.jpg","demonstrationImg":null},{"id":15,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350752-637.jpg","demonstrationImg":null},{"id":16,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369629-6795.jpg","demonstrationImg":null},{"id":17,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369723-4216.jpg","demonstrationImg":null},{"id":20,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103211-8170.jpg","demonstrationImg":null},{"id":21,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103479-4617.jpg","demonstrationImg":null},{"id":22,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912272-5619.jpg","demonstrationImg":null},{"id":23,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912462-639.jpg","demonstrationImg":null},{"id":24,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901295-3528.jpg","demonstrationImg":null},{"id":25,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901538-858.jpg","demonstrationImg":null},{"id":27,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745348098-2627.jpg","demonstrationImg":null},{"id":28,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523078-4658.jpg","demonstrationImg":null},{"id":29,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523292-823.jpg","demonstrationImg":null},{"id":30,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780591-6236.jpg","demonstrationImg":null},{"id":31,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780779-2664.jpg","demonstrationImg":null}]}],"region":"北京市 东城区"}
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * owner : 0
         * cropId : 1
         * varietyId : 1
         * regionId : 110101
         * years : 2020
         * companyName : 爱种网
         * address : 北京
         * addedTime : 2020-12-31
         * addedOperator : 管理员
         * addedOperatorId : 16
         * status : 1
         * varietyName : 一号小麦
         * demonstrationItem : [{"id":{"idx":1,"demonstrationId":1},"title":"222","addedTime":"2021-01-04 09:39","addedOperator":"222","status":1,"idx":null,"demonstrationId":null,"demonstrationItemAnnexs":[{"id":1,"demonstrationId":1,"itemIdx":1,"uri":"2222","demonstrationImg":null},{"id":2,"demonstrationId":1,"itemIdx":1,"uri":"1111","demonstrationImg":null},{"id":10,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730284821-4413.jpg","demonstrationImg":null},{"id":12,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310517-1363.jpg","demonstrationImg":null},{"id":13,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310796-8189.jpg","demonstrationImg":null},{"id":14,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350428-9797.jpg","demonstrationImg":null},{"id":15,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350752-637.jpg","demonstrationImg":null},{"id":16,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369629-6795.jpg","demonstrationImg":null},{"id":17,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369723-4216.jpg","demonstrationImg":null},{"id":20,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103211-8170.jpg","demonstrationImg":null},{"id":21,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103479-4617.jpg","demonstrationImg":null},{"id":22,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912272-5619.jpg","demonstrationImg":null},{"id":23,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912462-639.jpg","demonstrationImg":null},{"id":24,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901295-3528.jpg","demonstrationImg":null},{"id":25,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901538-858.jpg","demonstrationImg":null},{"id":27,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745348098-2627.jpg","demonstrationImg":null},{"id":28,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523078-4658.jpg","demonstrationImg":null},{"id":29,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523292-823.jpg","demonstrationImg":null},{"id":30,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780591-6236.jpg","demonstrationImg":null},{"id":31,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780779-2664.jpg","demonstrationImg":null}]}]
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
        private String region;
        private List<DemonstrationItemBean> demonstrationItem;

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

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<DemonstrationItemBean> getDemonstrationItem() {
            return demonstrationItem;
        }

        public void setDemonstrationItem(List<DemonstrationItemBean> demonstrationItem) {
            this.demonstrationItem = demonstrationItem;
        }

        public static class DemonstrationItemBean implements Serializable {
            /**
             * id : {"idx":1,"demonstrationId":1}
             * title : 222
             * addedTime : 2021-01-04 09:39
             * addedOperator : 222
             * status : 1
             * idx : null
             * demonstrationId : null
             * demonstrationItemAnnexs : [{"id":1,"demonstrationId":1,"itemIdx":1,"uri":"2222","demonstrationImg":null},{"id":2,"demonstrationId":1,"itemIdx":1,"uri":"1111","demonstrationImg":null},{"id":10,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730284821-4413.jpg","demonstrationImg":null},{"id":12,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310517-1363.jpg","demonstrationImg":null},{"id":13,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609730310796-8189.jpg","demonstrationImg":null},{"id":14,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350428-9797.jpg","demonstrationImg":null},{"id":15,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731350752-637.jpg","demonstrationImg":null},{"id":16,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369629-6795.jpg","demonstrationImg":null},{"id":17,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609731369723-4216.jpg","demonstrationImg":null},{"id":20,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103211-8170.jpg","demonstrationImg":null},{"id":21,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734103479-4617.jpg","demonstrationImg":null},{"id":22,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912272-5619.jpg","demonstrationImg":null},{"id":23,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609734912462-639.jpg","demonstrationImg":null},{"id":24,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901295-3528.jpg","demonstrationImg":null},{"id":25,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609735901538-858.jpg","demonstrationImg":null},{"id":27,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745348098-2627.jpg","demonstrationImg":null},{"id":28,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523078-4658.jpg","demonstrationImg":null},{"id":29,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745523292-823.jpg","demonstrationImg":null},{"id":30,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780591-6236.jpg","demonstrationImg":null},{"id":31,"demonstrationId":1,"itemIdx":1,"uri":"/2021/1609745780779-2664.jpg","demonstrationImg":null}]
             */

            private IdBean id;
            private String title;
            private String addedTime;
            private String addedOperator;
            private String status;
            private String idx;
            private String demonstrationId;
            private List<DemonstrationItemAnnexsBean> demonstrationItemAnnexs;

            public IdBean getId() {
                return id;
            }

            public void setId(IdBean id) {
                this.id = id;
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

            public String getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(String addedOperator) {
                this.addedOperator = addedOperator;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIdx() {
                return idx;
            }

            public void setIdx(String idx) {
                this.idx = idx;
            }

            public String getDemonstrationId() {
                return demonstrationId;
            }

            public void setDemonstrationId(String demonstrationId) {
                this.demonstrationId = demonstrationId;
            }

            public List<DemonstrationItemAnnexsBean> getDemonstrationItemAnnexs() {
                return demonstrationItemAnnexs;
            }

            public void setDemonstrationItemAnnexs(List<DemonstrationItemAnnexsBean> demonstrationItemAnnexs) {
                this.demonstrationItemAnnexs = demonstrationItemAnnexs;
            }

            public static class IdBean implements Serializable {
                /**
                 * idx : 1
                 * demonstrationId : 1
                 */

                private String idx;
                private String demonstrationId;

                public String getIdx() {
                    return idx;
                }

                public void setIdx(String idx) {
                    this.idx = idx;
                }

                public String getDemonstrationId() {
                    return demonstrationId;
                }

                public void setDemonstrationId(String demonstrationId) {
                    this.demonstrationId = demonstrationId;
                }
            }

            public static class DemonstrationItemAnnexsBean implements Serializable {
                /**
                 * id : 1
                 * demonstrationId : 1
                 * itemIdx : 1
                 * uri : 2222
                 * demonstrationImg : null
                 */

                private String id;
                private String demonstrationId;
                private String itemIdx;
                private String uri;
                private String demonstrationImg;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDemonstrationId() {
                    return demonstrationId;
                }

                public void setDemonstrationId(String demonstrationId) {
                    this.demonstrationId = demonstrationId;
                }

                public String getItemIdx() {
                    return itemIdx;
                }

                public void setItemIdx(String itemIdx) {
                    this.itemIdx = itemIdx;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getDemonstrationImg() {
                    return demonstrationImg;
                }

                public void setDemonstrationImg(String demonstrationImg) {
                    this.demonstrationImg = demonstrationImg;
                }
            }
        }
    }
}
