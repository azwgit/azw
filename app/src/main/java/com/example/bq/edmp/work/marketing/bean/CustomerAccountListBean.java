package com.example.bq.edmp.work.marketing.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerAccountListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"customerId":null,"balance":null,"deposit":null,"name":"","accountRecords":null,"types":null},"rows":[{"customerId":6,"balance":367,"deposit":32,"name":"爱种网","accountRecords":null,"types":null},{"customerId":8,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":9,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":10,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":11,"balance":0,"deposit":0,"name":"431","accountRecords":null,"types":null},{"customerId":12,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":13,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":14,"balance":0,"deposit":0,"name":"142","accountRecords":null,"types":null},{"customerId":15,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":16,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":17,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":18,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":19,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null},{"customerId":20,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null},{"customerId":21,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null}],"page":1,"pagerow":15,"totalpages":1,"totalrows":23,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"customerId":null,"balance":null,"deposit":null,"name":"","accountRecords":null,"types":null}
         * rows : [{"customerId":6,"balance":367,"deposit":32,"name":"爱种网","accountRecords":null,"types":null},{"customerId":8,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":9,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":10,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":11,"balance":0,"deposit":0,"name":"431","accountRecords":null,"types":null},{"customerId":12,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":13,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":14,"balance":0,"deposit":0,"name":"142","accountRecords":null,"types":null},{"customerId":15,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":16,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":17,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":18,"balance":0,"deposit":0,"name":"爱种网","accountRecords":null,"types":null},{"customerId":19,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null},{"customerId":20,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null},{"customerId":21,"balance":0,"deposit":0,"name":"王二麻子","accountRecords":null,"types":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 1
         * totalrows : 23
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
             * customerId : null
             * balance : null
             * deposit : null
             * name :
             * accountRecords : null
             * types : null
             */

            private Object customerId;
            private Object balance;
            private Object deposit;
            private String name;
            private Object accountRecords;
            private Object types;

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getAccountRecords() {
                return accountRecords;
            }

            public void setAccountRecords(Object accountRecords) {
                this.accountRecords = accountRecords;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }
        }

        public static class RowsBean {
            /**
             * customerId : 6
             * balance : 367.0
             * deposit : 32.0
             * name : 爱种网
             * accountRecords : null
             * types : null
             */

            private int customerId;
            private double balance;
            private double deposit;
            private String name;
            private Object accountRecords;
            private Object types;

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getAccountRecords() {
                return accountRecords;
            }

            public void setAccountRecords(Object accountRecords) {
                this.accountRecords = accountRecords;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }
        }
    }
}
