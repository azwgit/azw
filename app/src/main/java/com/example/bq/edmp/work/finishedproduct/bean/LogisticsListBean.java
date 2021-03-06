package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class LogisticsListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"owner":null,"orgId":null,"name":"中通快递","status":null},{"id":2,"owner":null,"orgId":null,"name":"顺丰快递","status":null}]
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
         * orgId : null
         * name : 中通快递
         * status : null
         */

        private int id;
        private Object owner;
        private Object orgId;
        private String name;
        private Object status;

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

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
