package com.example.bq.edmp.word.inventory.bean;

import java.util.List;

public class CkBean {

    /**
     * code : 200
     * msg : 消息统计成功
     * data : [{"id":1,"owner":null,"orgId":null,"name":"1号仓库","types":null,"wardenId":null},{"id":4,"owner":null,"orgId":null,"name":"1号玉米仓库","types":null,"wardenId":null},{"id":5,"owner":null,"orgId":null,"name":"1号辣椒仓库","types":null,"wardenId":null}]
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
         * name : 1号仓库
         * types : null
         * wardenId : null
         */

        private String id;
        private String name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
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
