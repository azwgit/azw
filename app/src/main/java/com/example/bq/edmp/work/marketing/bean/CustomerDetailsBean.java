package com.example.bq.edmp.work.marketing.bean;

import java.io.Serializable;

public class CustomerDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":1,"owner":1,"saleId":16,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":"123","businessLicenseNumber":"231","businessLicense":"231","remark":"123","status":1,"saleName":"李四","balance":1,"deposit":null,"businessLicenseImg":null}
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
         * id : 1
         * owner : 1
         * saleId : 16
         * name : 123
         * region : 河北省 石家庄市 赞皇县
         * contacts : 21
         * mobTel : 123
         * contactAddress : 123
         * businessLicenseNumber : 231
         * businessLicense : 231
         * remark : 123
         * status : 1
         * saleName : 李四
         * balance : 1.0
         * deposit : null
         * businessLicenseImg : null
         */

        private int id;
        private int owner;
        private int saleId;
        private String name;
        private String region;
        private String contacts;
        private String mobTel;
        private String contactAddress;
        private String businessLicenseNumber;
        private String businessLicense;
        private String remark;
        private int status;
        private String saleName;
        private double balance;
        private Object deposit;
        private Object businessLicenseImg;

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

        public int getSaleId() {
            return saleId;
        }

        public void setSaleId(int saleId) {
            this.saleId = saleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getMobTel() {
            return mobTel;
        }

        public void setMobTel(String mobTel) {
            this.mobTel = mobTel;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public String getBusinessLicenseNumber() {
            return businessLicenseNumber;
        }

        public void setBusinessLicenseNumber(String businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Object getDeposit() {
            return deposit;
        }

        public void setDeposit(Object deposit) {
            this.deposit = deposit;
        }

        public Object getBusinessLicenseImg() {
            return businessLicenseImg;
        }

        public void setBusinessLicenseImg(Object businessLicenseImg) {
            this.businessLicenseImg = businessLicenseImg;
        }
    }
}
