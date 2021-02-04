package com.example.bq.edmp.work.messagenotification.bean;

import java.io.Serializable;

public class MessageDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":7,"owner":1,"employeeId":16,"types":1,"title":"审批通知","content":"审批通过","relationInfo":null,"status":2,"addedTime":"2021-02-02 16:28:13","readTime":"2021-02-03T05:20:36.000+0000"}
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
         * id : 7
         * owner : 1
         * employeeId : 16
         * types : 1
         * title : 审批通知
         * content : 审批通过
         * relationInfo : null
         * status : 2
         * addedTime : 2021-02-02 16:28:13
         * readTime : 2021-02-03T05:20:36.000+0000
         */

        private int id;
        private int owner;
        private int employeeId;
        private int types;
        private String title;
        private String content;
        private Object relationInfo;
        private int status;
        private String addedTime;
        private String readTime;

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

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getRelationInfo() {
            return relationInfo;
        }

        public void setRelationInfo(Object relationInfo) {
            this.relationInfo = relationInfo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public String getReadTime() {
            return readTime;
        }

        public void setReadTime(String readTime) {
            this.readTime = readTime;
        }
    }
}
