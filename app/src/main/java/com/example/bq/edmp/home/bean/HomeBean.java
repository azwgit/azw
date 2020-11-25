package com.example.bq.edmp.home.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/18.
 */

public class HomeBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"backlogCount":6,"messageCount":1,"todayCalendar":5,"articles":[{"id":67,"owner":null,"columnId":null,"title":null,"summary":"title","contentKey":null,"imgUri":null,"source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-13T01:26:41.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":44,"owner":null,"columnId":null,"title":null,"summary":"广告","contentKey":null,"imgUri":"\\2020\\1602847018428-5468.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:08:44.000+0000","publishedOperator":null,"showCount":0,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":41,"owner":null,"columnId":null,"title":null,"summary":"广告","contentKey":null,"imgUri":"\\2020\\1602844884384-8672.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:08:24.000+0000","publishedOperator":null,"showCount":0,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":51,"owner":null,"columnId":null,"title":null,"summary":"广告000","contentKey":null,"imgUri":"\\2020\\1602849820637-5319.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:00:52.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":56,"owner":null,"columnId":null,"title":null,"summary":"废弃物品引起的重视123456","contentKey":null,"imgUri":"\\2020\\1602849820637-5319.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-10T08:25:39.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null}],"functions":[{"id":"01","owner":null,"parentId":null,"systemId":null,"name":"基础管理","levels":null,"displayType":null,"accessUri":"kjjh","icon":"1.jpg","status":null,"subt":null},{"id":"02","owner":null,"parentId":null,"systemId":null,"name":"生产管理","levels":null,"displayType":null,"accessUri":"er","icon":"2.jpg","status":null,"subt":null},{"id":"03","owner":null,"parentId":null,"systemId":null,"name":"审批管理","levels":null,"displayType":null,"accessUri":"approvalflow/staylist","icon":"3.jpg","status":null,"subt":null},{"id":"04","owner":null,"parentId":null,"systemId":null,"name":"报账管理","levels":null,"displayType":null,"accessUri":"reimburser/list","icon":"4.jpg","status":null,"subt":null},{"id":"05","owner":null,"parentId":null,"systemId":null,"name":"订单管理","levels":null,"displayType":null,"accessUri":"tqwe","icon":"5.jpg","status":null,"subt":null},{"id":"06","owner":null,"parentId":null,"systemId":null,"name":"营销分析","levels":null,"displayType":null,"accessUri":"ewrq","icon":"6.jpg","status":null,"subt":null},{"id":"07","owner":null,"parentId":null,"systemId":null,"name":"日常申请","levels":null,"displayType":null,"accessUri":"fsa","icon":"7.jpg","status":null,"subt":null}],"banners":[{"id":9,"owner":1,"title":"cess1233","imgUri":"\\2020\\1602844766846-8301.jpg","addedTime":"2020-10-16T10:39:27.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":10,"owner":1,"title":"cess1233","imgUri":"\\2020\\1602844926360-9238.jpg","addedTime":"2020-10-16T10:42:06.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":14,"owner":1,"title":"00","imgUri":"\\2020\\1603074867198-5562.jpg","addedTime":"2020-10-19T02:34:27.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":15,"owner":1,"title":"00","imgUri":"\\2020\\1603075277400-3892.jpg","addedTime":null,"addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":16,"owner":1,"title":"00","imgUri":"\\2020\\1603075323720-5096.jpg","addedTime":"2020-10-19T02:42:04.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":17,"owner":1,"title":"1109","imgUri":"\\2020\\1604885489454-694.jpg","addedTime":"2020-11-09T01:31:29.000+0000","addedOperator":"管理员","startTime":"2020-10-16T10:39:00.000+0000","endTime":"2020-11-16T10:39:00.000+0000","seq":4,"status":1,"bannerImg":null},{"id":18,"owner":1,"title":"1109","imgUri":"\\2020\\1604885596773-9212.jpg","addedTime":"2020-11-09T01:33:16.000+0000","addedOperator":"管理员","startTime":"2020-10-16T10:39:00.000+0000","endTime":"2020-11-16T10:39:00.000+0000","seq":4,"status":1,"bannerImg":null}]}
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
         * backlogCount : 6
         * messageCount : 1
         * todayCalendar : 5
         * articles : [{"id":67,"owner":null,"columnId":null,"title":null,"summary":"title","contentKey":null,"imgUri":null,"source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-13T01:26:41.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":44,"owner":null,"columnId":null,"title":null,"summary":"广告","contentKey":null,"imgUri":"\\2020\\1602847018428-5468.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:08:44.000+0000","publishedOperator":null,"showCount":0,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":41,"owner":null,"columnId":null,"title":null,"summary":"广告","contentKey":null,"imgUri":"\\2020\\1602844884384-8672.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:08:24.000+0000","publishedOperator":null,"showCount":0,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":51,"owner":null,"columnId":null,"title":null,"summary":"广告000","contentKey":null,"imgUri":"\\2020\\1602849820637-5319.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-11T09:00:52.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null},{"id":56,"owner":null,"columnId":null,"title":null,"summary":"废弃物品引起的重视123456","contentKey":null,"imgUri":"\\2020\\1602849820637-5319.jpg","source":null,"status":null,"addedTime":null,"addedOperator":null,"publishedTime":"2020-11-10T08:25:39.000+0000","publishedOperator":null,"showCount":null,"fullName":null,"parentId":null,"beginTime":null,"endTime":null,"articleImg":null,"annexFile":null,"content":null,"articleType":null,"articleAannex":null}]
         * functions : [{"id":"01","owner":null,"parentId":null,"systemId":null,"name":"基础管理","levels":null,"displayType":null,"accessUri":"kjjh","icon":"1.jpg","status":null,"subt":null},{"id":"02","owner":null,"parentId":null,"systemId":null,"name":"生产管理","levels":null,"displayType":null,"accessUri":"er","icon":"2.jpg","status":null,"subt":null},{"id":"03","owner":null,"parentId":null,"systemId":null,"name":"审批管理","levels":null,"displayType":null,"accessUri":"approvalflow/staylist","icon":"3.jpg","status":null,"subt":null},{"id":"04","owner":null,"parentId":null,"systemId":null,"name":"报账管理","levels":null,"displayType":null,"accessUri":"reimburser/list","icon":"4.jpg","status":null,"subt":null},{"id":"05","owner":null,"parentId":null,"systemId":null,"name":"订单管理","levels":null,"displayType":null,"accessUri":"tqwe","icon":"5.jpg","status":null,"subt":null},{"id":"06","owner":null,"parentId":null,"systemId":null,"name":"营销分析","levels":null,"displayType":null,"accessUri":"ewrq","icon":"6.jpg","status":null,"subt":null},{"id":"07","owner":null,"parentId":null,"systemId":null,"name":"日常申请","levels":null,"displayType":null,"accessUri":"fsa","icon":"7.jpg","status":null,"subt":null}]
         * banners : [{"id":9,"owner":1,"title":"cess1233","imgUri":"\\2020\\1602844766846-8301.jpg","addedTime":"2020-10-16T10:39:27.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":10,"owner":1,"title":"cess1233","imgUri":"\\2020\\1602844926360-9238.jpg","addedTime":"2020-10-16T10:42:06.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":14,"owner":1,"title":"00","imgUri":"\\2020\\1603074867198-5562.jpg","addedTime":"2020-10-19T02:34:27.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":15,"owner":1,"title":"00","imgUri":"\\2020\\1603075277400-3892.jpg","addedTime":null,"addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":16,"owner":1,"title":"00","imgUri":"\\2020\\1603075323720-5096.jpg","addedTime":"2020-10-19T02:42:04.000+0000","addedOperator":null,"startTime":null,"endTime":null,"seq":null,"status":1,"bannerImg":null},{"id":17,"owner":1,"title":"1109","imgUri":"\\2020\\1604885489454-694.jpg","addedTime":"2020-11-09T01:31:29.000+0000","addedOperator":"管理员","startTime":"2020-10-16T10:39:00.000+0000","endTime":"2020-11-16T10:39:00.000+0000","seq":4,"status":1,"bannerImg":null},{"id":18,"owner":1,"title":"1109","imgUri":"\\2020\\1604885596773-9212.jpg","addedTime":"2020-11-09T01:33:16.000+0000","addedOperator":"管理员","startTime":"2020-10-16T10:39:00.000+0000","endTime":"2020-11-16T10:39:00.000+0000","seq":4,"status":1,"bannerImg":null}]
         */

        private String backlogCount;
        private String messageCount;
        private String todayCalendar;
        private List<ArticlesBean> articles;
        private List<FunctionsBean> functions;
        private List<BannersBean> banners;

        public String getBacklogCount() {
            return backlogCount;
        }

        public void setBacklogCount(String backlogCount) {
            this.backlogCount = backlogCount;
        }

        public String getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(String messageCount) {
            this.messageCount = messageCount;
        }

        public String getTodayCalendar() {
            return todayCalendar;
        }

        public void setTodayCalendar(String todayCalendar) {
            this.todayCalendar = todayCalendar;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public List<FunctionsBean> getFunctions() {
            return functions;
        }

        public void setFunctions(List<FunctionsBean> functions) {
            this.functions = functions;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class ArticlesBean {
            /**
             * id : 67
             * owner : null
             * columnId : null
             * title : null
             * summary : title
             * contentKey : null
             * imgUri : null
             * source : null
             * status : null
             * addedTime : null
             * addedOperator : null
             * publishedTime : 2020-11-13T01:26:41.000+0000
             * publishedOperator : null
             * showCount : null
             * fullName : null
             * parentId : null
             * beginTime : null
             * endTime : null
             * articleImg : null
             * annexFile : null
             * content : null
             * articleType : null
             * articleAannex : null
             */

            private int id;
            private Object owner;
            private Object columnId;
            private String title;
            private String summary;
            private Object contentKey;
            private String imgUri;
            private Object source;
            private Object status;
            private Object addedTime;
            private Object addedOperator;
            private String publishedTime;
            private Object publishedOperator;
            private String showCount;
            private Object fullName;
            private Object parentId;
            private Object beginTime;
            private Object endTime;
            private Object articleImg;
            private Object annexFile;
            private Object content;
            private Object articleType;
            private Object articleAannex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public Object getColumnId() {
                return columnId;
            }

            public void setColumnId(Object columnId) {
                this.columnId = columnId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public Object getContentKey() {
                return contentKey;
            }

            public void setContentKey(Object contentKey) {
                this.contentKey = contentKey;
            }

            public String getImgUri() {
                return imgUri;
            }

            public void setImgUri(String imgUri) {
                this.imgUri = imgUri;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
                this.source = source;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
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

            public String getPublishedTime() {
                return publishedTime;
            }

            public void setPublishedTime(String publishedTime) {
                this.publishedTime = publishedTime;
            }

            public Object getPublishedOperator() {
                return publishedOperator;
            }

            public void setPublishedOperator(Object publishedOperator) {
                this.publishedOperator = publishedOperator;
            }

            public String getShowCount() {
                return showCount;
            }

            public void setShowCount(String showCount) {
                this.showCount = showCount;
            }

            public Object getFullName() {
                return fullName;
            }

            public void setFullName(Object fullName) {
                this.fullName = fullName;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
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

            public Object getArticleImg() {
                return articleImg;
            }

            public void setArticleImg(Object articleImg) {
                this.articleImg = articleImg;
            }

            public Object getAnnexFile() {
                return annexFile;
            }

            public void setAnnexFile(Object annexFile) {
                this.annexFile = annexFile;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public Object getArticleType() {
                return articleType;
            }

            public void setArticleType(Object articleType) {
                this.articleType = articleType;
            }

            public Object getArticleAannex() {
                return articleAannex;
            }

            public void setArticleAannex(Object articleAannex) {
                this.articleAannex = articleAannex;
            }
        }

        public static class FunctionsBean {
            /**
             * id : 01
             * owner : null
             * parentId : null
             * systemId : null
             * name : 基础管理
             * levels : null
             * displayType : null
             * accessUri : kjjh
             * icon : 1.jpg
             * status : null
             * subt : null
             */

            private String id;
            private Object owner;
            private Object parentId;
            private Object systemId;
            private String name;
            private Object levels;
            private Object displayType;
            private String accessUri;
            private String icon;
            private Object status;
            private Object subt;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getSystemId() {
                return systemId;
            }

            public void setSystemId(Object systemId) {
                this.systemId = systemId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getLevels() {
                return levels;
            }

            public void setLevels(Object levels) {
                this.levels = levels;
            }

            public Object getDisplayType() {
                return displayType;
            }

            public void setDisplayType(Object displayType) {
                this.displayType = displayType;
            }

            public String getAccessUri() {
                return accessUri;
            }

            public void setAccessUri(String accessUri) {
                this.accessUri = accessUri;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getSubt() {
                return subt;
            }

            public void setSubt(Object subt) {
                this.subt = subt;
            }
        }

        public static class BannersBean {
            /**
             * id : 9
             * owner : 1
             * title : cess1233
             * imgUri : \2020\1602844766846-8301.jpg
             * addedTime : 2020-10-16T10:39:27.000+0000
             * addedOperator : null
             * startTime : null
             * endTime : null
             * seq : null
             * status : 1
             * bannerImg : null
             */

            private int id;
            private int owner;
            private String title;
            private String imgUri;
            private String addedTime;
            private Object addedOperator;
            private Object startTime;
            private Object endTime;
            private Object seq;
            private int status;
            private Object bannerImg;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgUri() {
                return imgUri;
            }

            public void setImgUri(String imgUri) {
                this.imgUri = imgUri;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public Object getAddedOperator() {
                return addedOperator;
            }

            public void setAddedOperator(Object addedOperator) {
                this.addedOperator = addedOperator;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getSeq() {
                return seq;
            }

            public void setSeq(Object seq) {
                this.seq = seq;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getBannerImg() {
                return bannerImg;
            }

            public void setBannerImg(Object bannerImg) {
                this.bannerImg = bannerImg;
            }
        }
    }
}
