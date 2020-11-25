package com.example.bq.edmp.mine.bean;

/**
 * Created by bq on 2020/11/17.
 */

public class MineUserInfoBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":16,"owner":1,"name":"李四","orgId":6,"title":"3","headImg":"234234","isManager":null,"mobTel":"12346963651","officeTel":null,"email":"123@qq.com","password":"{bcrypt}$2a$10$X56ud5n0REw9BSUd5dKjg./VmTtgCj6QUYiwRRX8BLj4V8V7HVxWC","lastLoginTime":"2020-11-17T01:21:08.000+0000","status":1,"addedTime":"2020-10-28T03:02:43.000+0000","roles":[]}
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
         * owner : 1
         * name : 李四
         * orgId : 6
         * title : 3
         * headImg : 234234
         * isManager : null
         * mobTel : 12346963651
         * officeTel : null
         * email : 123@qq.com
         * password : {bcrypt}$2a$10$X56ud5n0REw9BSUd5dKjg./VmTtgCj6QUYiwRRX8BLj4V8V7HVxWC
         * lastLoginTime : 2020-11-17T01:21:08.000+0000
         * status : 1
         * addedTime : 2020-10-28T03:02:43.000+0000
         * roles : []
         */

        private int id;
        private String name;
        private String deptName;
        private String title;
        private String headImg;
        private String mobTel;
        private String officeTel;
        private String email;
        private String lastLoginTime;
        private int status;
        private String addedTime;
        private String signImg;

        public String getSignImg() {
            return signImg;
        }

        public void setSignImg(String signImg) {
            this.signImg = signImg;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getOfficeTel() {
            return officeTel;
        }

        public void setOfficeTel(String officeTel) {
            this.officeTel = officeTel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }
    }
}
