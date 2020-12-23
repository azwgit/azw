package com.example.bq.edmp.work.marketing.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerAccountDetails implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"customerId":6,"balance":367,"deposit":32,"name":"爱种网","accountRecords":[{"id":null,"types":null,"addedTime":"2020-12-22 16:12:00","status":1,"balance":234,"businessFormId":null,"remark":"DD20201222142253"},{"id":null,"types":null,"addedTime":"2020-12-22 16:12:00","status":2,"balance":142,"businessFormId":null,"remark":"DD20201222142215"}],"types":null}
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
         * customerId : 6
         * balance : 367.0
         * deposit : 32.0
         * name : 爱种网
         * accountRecords : [{"id":null,"types":null,"addedTime":"2020-12-22 16:12:00","status":1,"balance":234,"businessFormId":null,"remark":"DD20201222142253"},{"id":null,"types":null,"addedTime":"2020-12-22 16:12:00","status":2,"balance":142,"businessFormId":null,"remark":"DD20201222142215"}]
         * types : null
         */

        private int customerId;
        private double balance;
        private double deposit;
        private String name;
        private Object types;
        private List<AccountRecordsBean> accountRecords;

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

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public List<AccountRecordsBean> getAccountRecords() {
            return accountRecords;
        }

        public void setAccountRecords(List<AccountRecordsBean> accountRecords) {
            this.accountRecords = accountRecords;
        }

        public static class AccountRecordsBean {
            /**
             * id : null
             * types : null
             * addedTime : 2020-12-22 16:12:00
             * status : 1
             * balance : 234.0
             * businessFormId : null
             * remark : DD20201222142253
             */

            private Object id;
            private Object types;
            private String addedTime;
            private int status;
            private double balance;
            private Object businessFormId;
            private String remark;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public Object getBusinessFormId() {
                return businessFormId;
            }

            public void setBusinessFormId(Object businessFormId) {
                this.businessFormId = businessFormId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
