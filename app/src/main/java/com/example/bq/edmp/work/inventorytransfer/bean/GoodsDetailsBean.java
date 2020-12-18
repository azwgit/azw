package com.example.bq.edmp.work.inventorytransfer.bean;

import java.io.Serializable;

public class GoodsDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":null,"qty":500,"varietyName":"一号小麦","inItemId":1,"outItemId":1,"stockAllotId":null}
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
         * id : null
         * qty : 500.0
         * varietyName : 一号小麦
         * inItemId : 1
         * outItemId : 1
         * stockAllotId : null
         */

        private Object id;
        private double qty;
        private String varietyName;
        private int inItemId;
        private int outItemId;
        private Object stockAllotId;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
        }

        public int getInItemId() {
            return inItemId;
        }

        public void setInItemId(int inItemId) {
            this.inItemId = inItemId;
        }

        public int getOutItemId() {
            return outItemId;
        }

        public void setOutItemId(int outItemId) {
            this.outItemId = outItemId;
        }

        public Object getStockAllotId() {
            return stockAllotId;
        }

        public void setStockAllotId(Object stockAllotId) {
            this.stockAllotId = stockAllotId;
        }
    }
}
