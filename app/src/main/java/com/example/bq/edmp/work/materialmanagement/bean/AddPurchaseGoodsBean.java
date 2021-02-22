package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;

public class AddPurchaseGoodsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":{"materialPurchaseId":39,"itemId":3},"qty":11,"price":11,"materialPurchaseId":null,"itemId":null,"itemName":"66666"}
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
         * id : {"materialPurchaseId":39,"itemId":3}
         * qty : 11
         * price : 11.0
         * materialPurchaseId : null
         * itemId : null
         * itemName : 66666
         */

        private IdBean id;
        private int qty;
        private double price;
        private Object materialPurchaseId;
        private Object itemId;
        private String itemName;

        public IdBean getId() {
            return id;
        }

        public void setId(IdBean id) {
            this.id = id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getMaterialPurchaseId() {
            return materialPurchaseId;
        }

        public void setMaterialPurchaseId(Object materialPurchaseId) {
            this.materialPurchaseId = materialPurchaseId;
        }

        public Object getItemId() {
            return itemId;
        }

        public void setItemId(Object itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public static class IdBean {
            /**
             * materialPurchaseId : 39
             * itemId : 3
             */

            private int materialPurchaseId;
            private int itemId;

            public int getMaterialPurchaseId() {
                return materialPurchaseId;
            }

            public void setMaterialPurchaseId(int materialPurchaseId) {
                this.materialPurchaseId = materialPurchaseId;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }
        }
    }
}
