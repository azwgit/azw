package com.example.bq.edmp.work.order.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"saleId":16,"name":"","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"businessLicenseImg":null},"rows":[{"id":1,"owner":null,"saleId":null,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四","balance":1,"deposit":1,"businessLicenseImg":null},{"id":2,"owner":null,"saleId":null,"name":"456","region":"河北省 石家庄市 平山县","contacts":"34","mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四12","balance":1,"deposit":2,"businessLicenseImg":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":2,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * filter : {"id":null,"owner":1,"saleId":16,"name":"","region":null,"contacts":null,"mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":null,"balance":null,"deposit":null,"businessLicenseImg":null}
         * rows : [{"id":1,"owner":null,"saleId":null,"name":"123","region":"河北省 石家庄市 赞皇县","contacts":"21","mobTel":"123","contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四","balance":1,"deposit":1,"businessLicenseImg":null},{"id":2,"owner":null,"saleId":null,"name":"456","region":"河北省 石家庄市 平山县","contacts":"34","mobTel":null,"contactAddress":null,"businessLicenseNumber":null,"businessLicense":null,"remark":null,"status":null,"saleName":"李四12","balance":1,"deposit":2,"businessLicenseImg":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 2
         * sumtotal : 0
         * sortname : null
         * sortorder : ASC
         */

        private FilterBean filter;
        private String page;
        private String pagerow;
        private String totalpages;
        private String totalrows;
        private String sumtotal;
        private String sortname;
        private String sortorder;
        private List<RowsBean> rows;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPagerow() {
            return pagerow;
        }

        public void setPagerow(String pagerow) {
            this.pagerow = pagerow;
        }

        public String getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(String totalpages) {
            this.totalpages = totalpages;
        }

        public String getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(String totalrows) {
            this.totalrows = totalrows;
        }

        public String getSumtotal() {
            return sumtotal;
        }

        public void setSumtotal(String sumtotal) {
            this.sumtotal = sumtotal;
        }

        public String getSortname() {
            return sortname;
        }

        public void setSortname(String sortname) {
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

        public static class FilterBean implements Serializable{
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

            private String id;
            private String owner;
            private String saleId;
            private String name;
            private String region;
            private String contacts;
            private String mobTel;
            private String contactAddress;
            private String businessLicenseNumber;
            private String businessLicense;
            private String remark;
            private String status;
            private String saleName;
            private String balance;
            private String deposit;
            private String businessLicenseImg;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getSaleId() {
                return saleId;
            }

            public void setSaleId(String saleId) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public String getBusinessLicenseImg() {
                return businessLicenseImg;
            }

            public void setBusinessLicenseImg(String businessLicenseImg) {
                this.businessLicenseImg = businessLicenseImg;
            }
        }

        public static class RowsBean implements Serializable{
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
             * balance : 1
             * deposit : 1
             * businessLicenseImg : null
             */

            private String id;
            private String owner;
            private String saleId;
            private String name;
            private String region;
            private String contacts;
            private String mobTel;
            private String contactAddress;
            private String businessLicenseNumber;
            private String businessLicense;
            private String remark;
            private String status;
            private String saleName;
            private String balance;
            private String deposit;
            private String businessLicenseImg;
            private String regionId;
            private boolean select;

            public String getRegionId() {
                return regionId;
            }

            public void setRegionId(String regionId) {
                this.regionId = regionId;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getSaleId() {
                return saleId;
            }

            public void setSaleId(String saleId) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public String getBusinessLicenseImg() {
                return businessLicenseImg;
            }

            public void setBusinessLicenseImg(String businessLicenseImg) {
                this.businessLicenseImg = businessLicenseImg;
            }
        }
    }
}
