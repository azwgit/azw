package com.example.bq.edmp.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/5.
 */

public class Address_DepartmentBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":null,"parentId":null,"types":null,"name":"1","managerId":null,"bossId":null,"status":null,"orgs":null},{"id":2,"owner":null,"parentId":null,"types":null,"name":"财务","managerId":null,"bossId":null,"status":null,"orgs":null},{"id":6,"owner":null,"parentId":null,"types":null,"name":"销售00","managerId":null,"bossId":null,"status":null,"orgs":null},{"id":7,"owner":null,"parentId":null,"types":null,"name":"销售02","managerId":null,"bossId":null,"status":null,"orgs":null}]
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
         * orgs : null
         */

        private int id;
        private String name;

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
    }
}
