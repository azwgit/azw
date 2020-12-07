package com.example.bq.edmp.word.inventory.bean;

import java.util.List;

public class InventoryTabBean {

    /**
     * code : 200
     * msg : 消息统计成功
     * data : [{"id":1,"owner":null,"name":"小麦"},{"id":2,"owner":null,"name":"玉米"},{"id":3,"owner":null,"name":"辣椒"}]
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
         * id : 1
         * owner : null
         * name : 小麦
         */

        private String id;
        private String owner;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
