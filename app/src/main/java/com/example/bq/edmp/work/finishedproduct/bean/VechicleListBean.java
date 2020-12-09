package com.example.bq.edmp.work.finishedproduct.bean;

import java.io.Serializable;
import java.util.List;

public class VechicleListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":3,"owner":null,"orgId":null,"license":"京D4321","contacts":"小五","mobTel":null,"carryingCapacity":null,"status":null}]
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
         * id : 3
         * owner : null
         * orgId : null
         * license : 京D4321
         * contacts : 小五
         * mobTel : null
         * carryingCapacity : null
         * status : null
         */

        private int id;
        private Object owner;
        private Object orgId;
        private String license;
        private String contacts;
        private Object mobTel;
        private Object carryingCapacity;
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

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public Object getMobTel() {
            return mobTel;
        }

        public void setMobTel(Object mobTel) {
            this.mobTel = mobTel;
        }

        public Object getCarryingCapacity() {
            return carryingCapacity;
        }

        public void setCarryingCapacity(Object carryingCapacity) {
            this.carryingCapacity = carryingCapacity;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
