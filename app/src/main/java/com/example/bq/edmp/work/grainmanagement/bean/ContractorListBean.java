package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class ContractorListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":2,"owner":1,"farmId":2,"name":"李四","tel":null,"wxOpenid":null}]
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
         * id : 2
         * owner : 1
         * farmId : 2
         * name : 李四
         * tel : null
         * wxOpenid : null
         */

        private int id;
        private int owner;
        private int farmId;
        private String name;
        private Object tel;
        private Object wxOpenid;

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

        public int getFarmId() {
            return farmId;
        }

        public void setFarmId(int farmId) {
            this.farmId = farmId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public Object getWxOpenid() {
            return wxOpenid;
        }

        public void setWxOpenid(Object wxOpenid) {
            this.wxOpenid = wxOpenid;
        }
    }
}
