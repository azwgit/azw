package com.example.bq.edmp.work.marketing.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerManagementListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"saleId":16,"name":"","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"businessLicenseImg":null},"rows":[{"id":1,"owner":null,"saleId":null,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四","balance":1,"deposit":1,"businessLicenseImg":null},{"id":2,"owner":null,"saleId":null,"name":"456","region":"河北省 石家庄市 平山县","contacts":"34","mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四12","balance":1,"deposit":2,"businessLicenseImg":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":2,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"saleId":16,"name":"","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"businessLicenseImg":null}
         * rows : [{"id":1,"owner":null,"saleId":null,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四","balance":1,"deposit":1,"businessLicenseImg":null},{"id":2,"owner":null,"saleId":null,"name":"456","region":"河北省 石家庄市 平山县","contacts":"34","mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四12","balance":1,"deposit":2,"businessLicenseImg":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 2
         * sumtotal : 0.0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private int page;
        private int pagerow;
        private int totalpages;
        private int totalrows;
        private double sumtotal;
        private Object sortname;
        private String sortorder;
        private List<RowsBean> rows;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPagerow() {
            return pagerow;
        }

        public void setPagerow(int pagerow) {
            this.pagerow = pagerow;
        }

        public int getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(int totalpages) {
            this.totalpages = totalpages;
        }

        public int getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }

        public double getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(double sumtotal) {
            this.sumtotal = sumtotal;
        }

        public Object getSortname() {
            return sortname;
        }

        public void setSortname(Object sortname) {
            this.sortname = sortname;
        }

        public String getSortorder() {
            return sortorder;
        }

        public void setSortorder(String sortorder) {
            this.sortorder = sortorder;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class FilterBean {
            /**
             * id : null
             * owner : 1
             * saleId : 16
             * name :
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
             * businessLicenseImg : null
             */

            private Object id;
            private int owner;
            private int saleId;
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
            private Object businessLicenseImg;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
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

            public Object getBusinessLicenseImg() {
                return businessLicenseImg;
            }

            public void setBusinessLicenseImg(Object businessLicenseImg) {
                this.businessLicenseImg = businessLicenseImg;
            }
        }

        public static class RowsBean {
            /**
             * id : 1
             * owner : null
             * saleId : null
             * name : 123
             * region : 河北省 石家庄市 赞皇县
             * contacts : 21
             * mobTel : 123
             * contactAddress : null
             * businessLicenseNumber : null
             * businessLicense : null
             * remark : null
             * status : null
             * saleName : 李四
             * balance : 1.0
             * deposit : 1.0
             * businessLicenseImg : null
             */

            private int id;
            private Object owner;
            private Object saleId;
            private String name;
            private String region;
            private String contacts;
            private String mobTel;
            private Object contactAddress;
            private Object businessLicenseNumber;
            private Object businessLicense;
            private Object remark;
            private Object status;
            private String saleName;
            private double balance;
            private double deposit;
            private Object businessLicenseImg;

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

            public double getDeposit() {
                return deposit;
            }

            public void setDeposit(double deposit) {
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
}
