package com.example.bq.edmp.work.order.bean;

import com.google.gson.annotations.SerializedName;

public class StatusBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"1":{"status":3,"name":"待分配","count":3}}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 1 : {"status":3,"name":"待分配","count":3}
         */

        @SerializedName("1")
        private _$1Bean _$1;

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public static class _$1Bean {
            /**
             * status : 3
             * name : 待分配
             * count : 3
             */

            private int status;
            private String name;
            private int count;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
