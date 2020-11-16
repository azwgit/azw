package com.example.bq.edmp.word.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/9.
 */

public class FirstResult {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":"01","owner":null,"parentId":null,"systemId":null,"name":"基础管理","levels":1,"displayType":null,"accessUri":null,"icon":null,"status":null,"subt":null},{"id":"02","owner":null,"parentId":null,"systemId":null,"name":"生产管理","levels":1,"displayType":null,"accessUri":null,"icon":null,"status":null,"subt":null},{"id":"1","owner":null,"parentId":null,"systemId":null,"name":"name1","levels":1,"displayType":null,"accessUri":null,"icon":null,"status":null,"subt":null}]
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
         * id : 01
         * owner : null
         * parentId : null
         * systemId : null
         * name : 基础管理
         * levels : 1
         * displayType : null
         * accessUri : null
         * icon : null
         * status : null
         * subt : null
         */

        private String id;
        private String name;
        private int levels;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

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

        public int getLevels() {
            return levels;
        }

        public void setLevels(int levels) {
            this.levels = levels;
        }
    }
}
