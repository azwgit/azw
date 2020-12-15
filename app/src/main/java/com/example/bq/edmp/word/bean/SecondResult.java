package com.example.bq.edmp.word.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/9.
 */

public class SecondResult {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":"0101","owner":1,"parentId":"01","systemId":6,"name":"文章管理","levels":1,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":[{"id":"010101","owner":1,"parentId":"0101","systemId":6,"name":"文章添加","levels":1,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":null},{"id":"010102","owner":1,"parentId":"0101","systemId":6,"name":"文章修改","levels":2,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":null}]},{"id":"0102","owner":1,"parentId":"01","systemId":1,"name":"宣传图管理","levels":1,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":[{"id":"010201","owner":1,"parentId":"0102","systemId":1,"name":"宣传图添加","levels":1,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":null}]}]
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
         * id : 0101
         * owner : 1
         * parentId : 01
         * systemId : 6
         * name : 文章管理
         * levels : 1
         * displayType : 1
         * accessUri : null
         * icon : null
         * status : 1
         * subt : [{"id":"010101","owner":1,"parentId":"0101","systemId":6,"name":"文章添加","levels":1,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":null},{"id":"010102","owner":1,"parentId":"0101","systemId":6,"name":"文章修改","levels":2,"displayType":1,"accessUri":null,"icon":null,"status":1,"subt":null}]
         */

        private String id;
        private String name;
        private int levels;
        private Object icon;
        private List<SubtBean> subt;

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

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public List<SubtBean> getSubt() {
            return subt;
        }

        public void setSubt(List<SubtBean> subt) {
            this.subt = subt;
        }

        public static class SubtBean {
            /**
             * id : 010101
             * owner : 1
             * parentId : 0101
             * systemId : 6
             * name : 文章添加
             * levels : 1
             * displayType : 1
             * accessUri : null
             * icon : null
             * status : 1
             * subt : null
             */

            private String id;
            private String name;
            private int levels;
            private Object icon;
            private Object subt;
            private String accessUri;

            public String getAccessUri() {
                return accessUri;
            }

            public void setAccessUri(String accessUri) {
                this.accessUri = accessUri;
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

            public Object getIcon() {
                return icon;
            }

            public void setIcon(Object icon) {
                this.icon = icon;
            }

            public Object getSubt() {
                return subt;
            }

            public void setSubt(Object subt) {
                this.subt = subt;
            }
        }
    }
}
