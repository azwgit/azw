package com.example.bq.edmp.work.order.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"saleId":16,"name":"","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"businessLicenseImg":null},"rows":[{"id":1,"owner":null,"saleId":null,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四","balance":1,"deposit":1,"businessLicenseImg":null},{"id":2,"owner":null,"saleId":null,"name":"456","region":"河北省 石家庄市 平山县","contacts":"34","mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四12","balance":1,"deposit":2,"businessLicenseImg":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":2,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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

    public static class DataBean implements Serializable{
        private String id;
        private String name;
        private double price;
        private boolean select;
        private String varietyPackagingName;
        private double customerPrice;

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getVarietyPackagingName() {
            return varietyPackagingName;
        }

        public void setVarietyPackagingName(String varietyPackagingName) {
            this.varietyPackagingName = varietyPackagingName;
        }

        public double getCustomerPrice() {
            return customerPrice;
        }

        public void setCustomerPrice(double customerPrice) {
            this.customerPrice = customerPrice;
        }

    }
}
