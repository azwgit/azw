package com.example.bq.edmp.mine.bean;

/**
 * Created by bq on 2020/11/17.
 */

public class MineUserBean {

    /**
     * code : 200
     * msg : success
     * data : {"id":16,"owner":null,"name":"李四","orgId":null,"title":null,"headImg":"234234","isManager":null,"mobTel":null,"officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"orgName":"销售00","addedTime":null}
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
         * id : 16
         * owner : null
         * name : 李四
         * orgId : null
         * title : null
         * headImg : 234234
         * isManager : null
         * mobTel : null
         * officeTel : null
         * email : null
         * password : null
         * lastLoginTime : null
         * status : null
         * orgName : 销售00
         * addedTime : null
         */

        private String id;
        private String name;
        private String title;
        private String headImg;
        private String mobTel;
        private String orgName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }
    }
}
