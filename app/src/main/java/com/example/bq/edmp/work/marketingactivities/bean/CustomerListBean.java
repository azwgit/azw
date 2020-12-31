package com.example.bq.edmp.work.marketingactivities.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":24,"owner":null,"saleId":null,"name":"雪碧vs可乐","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":26,"owner":null,"saleId":null,"name":"七喜vs冰红茶2","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":28,"owner":null,"saleId":null,"name":"爱种网330","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":31,"owner":null,"saleId":null,"name":"李三","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":32,"owner":null,"saleId":null,"name":"爱种网3","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":33,"owner":null,"saleId":null,"name":"爱种网3","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":34,"owner":null,"saleId":null,"name":"爱种网3","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":35,"owner":null,"saleId":null,"name":"爱种网3","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":37,"owner":null,"saleId":null,"name":"芬达vs北冰洋","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":39,"owner":null,"saleId":null,"name":"京东","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":40,"owner":null,"saleId":null,"name":"客户名称","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null},{"id":41,"owner":null,"saleId":null,"name":"皖垦","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"provinceId":null,"cityId":null,"countyId":null,"businessLicenseImg":null,"regionId":null}]
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
         * id : 24
         * owner : null
         * saleId : null
         * name : 雪碧vs可乐
         * region : null
         * contacts : null
         * mobTel : null
         * contactAddress : null
         * businessLicenseNumber : null
         * businessLicense : null
         * remark : null
         * status : null
         * saleName : null
         * balance : null
         * deposit : null
         * provinceId : null
         * cityId : null
         * countyId : null
         * businessLicenseImg : null
         * regionId : null
         */

        private int id;
        private Object owner;
        private Object saleId;
        private String name;
        private Object region;
        private Object contacts;
        private Object mobTel;
        private Object contactAddress;
        private Object businessLicenseNumber;
        private Object businessLicense;
        private Object remark;
        private Object status;
        private Object saleName;
        private Object balance;
        private Object deposit;
        private Object provinceId;
        private Object cityId;
        private Object countyId;
        private Object businessLicenseImg;
        private Object regionId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public Object getSaleId() {
            return saleId;
        }

        public void setSaleId(Object saleId) {
            this.saleId = saleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public Object getContacts() {
            return contacts;
        }

        public void setContacts(Object contacts) {
            this.contacts = contacts;
        }

        public Object getMobTel() {
            return mobTel;
        }

        public void setMobTel(Object mobTel) {
            this.mobTel = mobTel;
        }

        public Object getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(Object contactAddress) {
            this.contactAddress = contactAddress;
        }

        public Object getBusinessLicenseNumber() {
            return businessLicenseNumber;
        }

        public void setBusinessLicenseNumber(Object businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
        }

        public Object getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(Object businessLicense) {
            this.businessLicense = businessLicense;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getSaleName() {
            return saleName;
        }

        public void setSaleName(Object saleName) {
            this.saleName = saleName;
        }

        public Object getBalance() {
            return balance;
        }

        public void setBalance(Object balance) {
            this.balance = balance;
        }

        public Object getDeposit() {
            return deposit;
        }

        public void setDeposit(Object deposit) {
            this.deposit = deposit;
        }

        public Object getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Object provinceId) {
            this.provinceId = provinceId;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public Object getCountyId() {
            return countyId;
        }

        public void setCountyId(Object countyId) {
            this.countyId = countyId;
        }

        public Object getBusinessLicenseImg() {
            return businessLicenseImg;
        }

        public void setBusinessLicenseImg(Object businessLicenseImg) {
            this.businessLicenseImg = businessLicenseImg;
        }

        public Object getRegionId() {
            return regionId;
        }

        public void setRegionId(Object regionId) {
            this.regionId = regionId;
        }
    }
}
