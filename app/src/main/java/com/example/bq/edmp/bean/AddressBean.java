package com.example.bq.edmp.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/5.
 */

public class AddressBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":null,"parentId":null,"types":null,"name":"1","managerId":null,"bossId":null,"status":null,"orgChildren":null,"employees":[{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"哈哈","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]}]},{"id":2,"owner":null,"parentId":null,"types":null,"name":"财务","managerId":null,"bossId":null,"status":null,"orgChildren":null,"employees":[{"id":null,"owner":null,"name":"123","orgId":2,"title":null,"isManager":null,"mobTel":"1230","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"hah","orgId":2,"title":null,"isManager":null,"mobTel":"13462919789","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"张三","orgId":2,"title":null,"isManager":null,"mobTel":"13696532123","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]}]},{"id":6,"owner":null,"parentId":null,"types":null,"name":"销售00","managerId":null,"bossId":null,"status":null,"orgChildren":null,"employees":[{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"12346963651","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"10326325631","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"张三123","orgId":6,"title":null,"isManager":null,"mobTel":"13696532123","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]}]},{"id":7,"owner":null,"parentId":null,"types":null,"name":"销售02","managerId":null,"bossId":null,"status":null,"orgChildren":null,"employees":[]},{"id":8,"owner":null,"parentId":null,"types":null,"name":"222","managerId":null,"bossId":null,"status":null,"orgChildren":null,"employees":[]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * owner : null
         * parentId : null
         * types : null
         * name : 1
         * managerId : null
         * bossId : null
         * status : null
         * orgChildren : null
         * employees : [{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"哈哈","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]}]
         */

        private int id;
        private Object owner;
        private Object parentId;
        private Object types;
        private String name;
        private Object managerId;
        private Object bossId;
        private Object status;
        private Object orgChildren;
        private List<EmployeesBean> employees;

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

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getOrgChildren() {
            return orgChildren;
        }

        public void setOrgChildren(Object orgChildren) {
            this.orgChildren = orgChildren;
        }

        public List<EmployeesBean> getEmployees() {
            return employees;
        }

        public void setEmployees(List<EmployeesBean> employees) {
            this.employees = employees;
        }

        public static class EmployeesBean {
            /**
             * id : null
             * owner : null
             * name : 呵
             * orgId : 1
             * title : null
             * isManager : null
             * mobTel : 123456
             * officeTel : null
             * email : null
             * password : null
             * lastLoginTime : null
             * status : null
             * addedTime : null
             * roles : []
             */

            private Object id;
            private Object owner;
            private String name;
            private int orgId;
            private Object title;
            private Object isManager;
            private String mobTel;
            private Object officeTel;
            private Object email;
            private Object password;
            private Object lastLoginTime;
            private Object status;
            private Object addedTime;
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

            public List<?> getRoles() {
                return roles;
            }

            public void setRoles(List<?> roles) {
                this.roles = roles;
            }
        }
    }
}
