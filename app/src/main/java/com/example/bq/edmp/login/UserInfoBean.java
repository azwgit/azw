package com.example.bq.edmp.login;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

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

        private int id;
        private Object owner;
        private String name;
        private Object orgId;
        private Object title;
        private String headImg;
        private Object isManager;
        private Object mobTel;
        private Object officeTel;
        private Object email;
        private Object password;
        private Object lastLoginTime;
        private Object status;
        private String orgName;
        private Object addedTime;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public Object getIsManager() {
            return isManager;
        }

        public void setIsManager(Object isManager) {
            this.isManager = isManager;
        }

        public Object getMobTel() {
            return mobTel;
        }

        public void setMobTel(Object mobTel) {
            this.mobTel = mobTel;
        }

        public Object getOfficeTel() {
            return officeTel;
        }

        public void setOfficeTel(Object officeTel) {
            this.officeTel = officeTel;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public Object getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(Object addedTime) {
            this.addedTime = addedTime;
        }
    }
}
