package com.example.bq.edmp.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/5.
 */

public class Address_StaffBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":null,"owner":null,"name":"123","orgId":2,"title":null,"isManager":null,"mobTel":"1230","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"呵","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"哈哈","orgId":1,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"hah","orgId":2,"title":null,"isManager":null,"mobTel":"13462919789","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"张三","orgId":2,"title":null,"isManager":null,"mobTel":"13696532123","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"12346963651","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"123456","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"李四","orgId":6,"title":null,"isManager":null,"mobTel":"10326325631","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]},{"id":null,"owner":null,"name":"张三123","orgId":6,"title":null,"isManager":null,"mobTel":"13696532123","officeTel":null,"email":null,"password":null,"lastLoginTime":null,"status":null,"addedTime":null,"roles":[]}]
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
         * id : null
         * owner : null
         * name : 123
         * orgId : 2
         * title : null
         * isManager : null
         * mobTel : 1230
         * officeTel : null
         * email : null
         * password : null
         * lastLoginTime : null
         * status : null
         * addedTime : null
         * roles : []
         */

        private Object id;
        private String name;
        private int orgId;
        private String mobTel;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
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

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }
    }
}
