package com.example.bq.edmp.addressbook.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/23.
 */

public class AddressBook_Bean_Data {


    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"parentId":0,"types":1,"name":"总公司","managerId":null,"bossId":null,"status":1,"orgChildren":[{"id":2,"owner":1,"parentId":1,"types":1,"name":"北京分公司","managerId":null,"bossId":null,"status":1,"orgChildren":null,"employees":[]},{"id":8,"owner":1,"parentId":1,"types":1,"name":"南京分公司","managerId":null,"bossId":null,"status":1,"orgChildren":null,"employees":[]}],"employees":[{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"哈哈","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null}]}
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
         * id : 1
         * owner : 1
         * parentId : 0
         * types : 1
         * name : 总公司
         * managerId : null
         * bossId : null
         * status : 1
         * orgChildren : [{"id":2,"owner":1,"parentId":1,"types":1,"name":"北京分公司","managerId":null,"bossId":null,"status":1,"orgChildren":null,"employees":[]},{"id":8,"owner":1,"parentId":1,"types":1,"name":"南京分公司","managerId":null,"bossId":null,"status":1,"orgChildren":null,"employees":[]}]
         * employees : [{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"哈哈","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null},{"id":null,"owner":null,"name":"呵123","orgId":1,"title":null,"headImg":null,"isManager":null,"mobTel":"12345611","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"signImg":null,"roles":[],"deptName":null,"companyName":null,"firstcompanyName":null}]
         */

        private int id;
        private int owner;
        private int parentId;
        private int types;
        private String name;
        private Object managerId;
        private Object bossId;
        private int status;
        private List<OrgChildrenBean> orgChildren;
        private List<EmployeesBean> employees;

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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getManagerId() {
            return managerId;
        }

        public void setManagerId(Object managerId) {
            this.managerId = managerId;
        }

        public Object getBossId() {
            return bossId;
        }

        public void setBossId(Object bossId) {
            this.bossId = bossId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<OrgChildrenBean> getOrgChildren() {
            return orgChildren;
        }

        public void setOrgChildren(List<OrgChildrenBean> orgChildren) {
            this.orgChildren = orgChildren;
        }

        public List<EmployeesBean> getEmployees() {
            return employees;
        }

        public void setEmployees(List<EmployeesBean> employees) {
            this.employees = employees;
        }

        public static class OrgChildrenBean {
            /**
             * id : 2
             * owner : 1
             * parentId : 1
             * types : 1
             * name : 北京分公司
             * managerId : null
             * bossId : null
             * status : 1
             * orgChildren : null
             * employees : []
             */

            private int id;
            private int owner;
            private int parentId;
            private int types;
            private String name;
            private Object managerId;
            private Object bossId;
            private int status;
            private Object orgChildren;
            private List<?> employees;

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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getTypes() {
                return types;
            }

            public void setTypes(int types) {
                this.types = types;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getManagerId() {
                return managerId;
            }

            public void setManagerId(Object managerId) {
                this.managerId = managerId;
            }

            public Object getBossId() {
                return bossId;
            }

            public void setBossId(Object bossId) {
                this.bossId = bossId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getOrgChildren() {
                return orgChildren;
            }

            public void setOrgChildren(Object orgChildren) {
                this.orgChildren = orgChildren;
            }

            public List<?> getEmployees() {
                return employees;
            }

            public void setEmployees(List<?> employees) {
                this.employees = employees;
            }
        }

        public static class EmployeesBean {
            /**
             * id : null
             * owner : null
             * name : 呵
             * orgId : 1
             * title : null
             * headImg : null
             * isManager : null
             * mobTel : 12345611
             * officeTel : null
             * email : null
             * password : null
             * lastLoginTime : null
             * status : null
             * addedTime : null
             * signImg : null
             * roles : []
             * deptName : null
             * companyName : null
             * firstcompanyName : null
             */

            private Object id;
            private Object owner;
            private String name;
            private int orgId;
            private Object title;
            private Object headImg;
            private Object isManager;
            private String mobTel;
            private Object officeTel;
            private Object email;
            private Object password;
            private Object lastLoginTime;
            private Object status;
            private Object addedTime;
            private Object signImg;
            private Object deptName;
            private Object companyName;
            private Object firstcompanyName;
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

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getHeadImg() {
                return headImg;
            }

            public void setHeadImg(Object headImg) {
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

            public Object getDeptName() {
                return deptName;
            }

            public void setDeptName(Object deptName) {
                this.deptName = deptName;
            }

            public Object getCompanyName() {
                return companyName;
            }

            public void setCompanyName(Object companyName) {
                this.companyName = companyName;
            }

            public Object getFirstcompanyName() {
                return firstcompanyName;
            }

            public void setFirstcompanyName(Object firstcompanyName) {
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
}
