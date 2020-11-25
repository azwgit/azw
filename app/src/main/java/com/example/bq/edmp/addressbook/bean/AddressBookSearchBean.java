package com.example.bq.edmp.addressbook.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/24.
 */

public class AddressBookSearchBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":null,"owner":null,"name":"李四","orgId":2,"title":"3","headImg":"/2020/1604885596773-9212.jpg","isManager":null,"mobTel":"17800000000","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":"北京分公司","companyName":"北京分公司","firstcompanyName":"总公司"},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"headImg":"34","isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":"财务部","companyName":"北京分公司","firstcompanyName":"总公司"},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"headImg":"2342","isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":"财务部","companyName":"北京分公司","firstcompanyName":"总公司"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * owner : null
         * name : 李四
         * orgId : 2
         * title : 3
         * headImg : /2020/1604885596773-9212.jpg
         * isManager : null
         * mobTel : 17800000000
         * officeTel : null
         * email : null
         * password : null
         * lastLoginTime : null
         * status : null
         * addedTime : null
         * signImg : null
         * roles : []
         * deptName : 北京分公司
         * companyName : 北京分公司
         * firstcompanyName : 总公司
         */

        private Object id;
        private Object owner;
        private String name;
        private int orgId;
        private String title;
        private String headImg;
        private Object isManager;
        private String mobTel;
        private Object officeTel;
        private Object email;
        private Object password;
        private Object lastLoginTime;
        private Object status;
        private Object addedTime;
        private Object signImg;
        private String deptName;
        private String companyName;
        private String firstcompanyName;
        private List<?> roles;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
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

        public Object getIsManager() {
            return isManager;
        }

        public void setIsManager(Object isManager) {
            this.isManager = isManager;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
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

        public Object getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(Object addedTime) {
            this.addedTime = addedTime;
        }

        public Object getSignImg() {
            return signImg;
        }

        public void setSignImg(Object signImg) {
            this.signImg = signImg;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getFirstcompanyName() {
            return firstcompanyName;
        }

        public void setFirstcompanyName(String firstcompanyName) {
            this.firstcompanyName = firstcompanyName;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }
    }
}
