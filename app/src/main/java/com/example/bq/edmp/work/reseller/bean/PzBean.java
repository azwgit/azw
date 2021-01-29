package com.example.bq.edmp.work.reseller.bean;

import java.util.List;

public class PzBean {

    /**
     * code : 200
     * msg : 品种查询成功
     * data : [{"id":1,"owner":null,"cropId":null,"name":"一号小麦","types":null,"yield":null,"remark":null,"status":null},{"id":2,"owner":null,"cropId":null,"name":"二号小麦","types":null,"yield":null,"remark":null,"status":null},{"id":3,"owner":null,"cropId":null,"name":"一号玉米","types":null,"yield":null,"remark":null,"status":null},{"id":4,"owner":null,"cropId":null,"name":"一号辣椒","types":null,"yield":null,"remark":null,"status":null}]
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
         * cropId : null
         * name : 一号小麦
         * types : null
         * yield : null
         * remark : null
         * status : null
         */

        private String varietyId;

        public String getVarietyId() {
            return varietyId;
        }

        public void setVarietyId(String varietyId) {
            this.varietyId = varietyId;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        private String varietyName;


        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }


    }
}
