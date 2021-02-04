package com.example.bq.edmp.work.messagenotification.bean;

import java.io.Serializable;
import java.util.List;

public class MessageManagementListBean implements Serializable {


    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"employeeId":16,"types":null,"title":null,"content":null,"relationInfo":null,"status":null,"addedTime":null,"readTime":null},"rows":[{"id":2,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知1","content":null,"relationInfo":"approvalflow/show/1","status":1,"addedTime":"2020-11-10 14:17:03","readTime":null},{"id":4,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知","content":null,"relationInfo":"approvalflow/show/3","status":2,"addedTime":"2020-11-11 09:27:41","readTime":null},{"id":3,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知2","content":null,"relationInfo":"approvalflow/show/2","status":2,"addedTime":"2020-11-10 14:18:12","readTime":null},{"id":1,"owner":null,"employeeId":null,"types":1,"title":"系统通知","content":"审批1","relationInfo":null,"status":2,"addedTime":"2020-11-10 14:08:33","readTime":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":4,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"employeeId":16,"types":null,"title":null,"content":null,"relationInfo":null,"status":null,"addedTime":null,"readTime":null}
         * rows : [{"id":2,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知1","content":null,"relationInfo":"approvalflow/show/1","status":1,"addedTime":"2020-11-10 14:17:03","readTime":null},{"id":4,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知","content":null,"relationInfo":"approvalflow/show/3","status":2,"addedTime":"2020-11-11 09:27:41","readTime":null},{"id":3,"owner":null,"employeeId":null,"types":2,"title":"审批进度通知2","content":null,"relationInfo":"approvalflow/show/2","status":2,"addedTime":"2020-11-10 14:18:12","readTime":null},{"id":1,"owner":null,"employeeId":null,"types":1,"title":"系统通知","content":"审批1","relationInfo":null,"status":2,"addedTime":"2020-11-10 14:08:33","readTime":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 4
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
             * employeeId : 16
             * types : null
             * title : null
             * content : null
             * relationInfo : null
             * status : null
             * addedTime : null
             * readTime : null
             */

            private Object id;
            private int owner;
            private int employeeId;
            private Object types;
            private Object title;
            private Object content;
            private Object relationInfo;
            private Object status;
            private Object addedTime;
            private Object readTime;

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

            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public Object getRelationInfo() {
                return relationInfo;
            }

            public void setRelationInfo(Object relationInfo) {
                this.relationInfo = relationInfo;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(Object addedTime) {
                this.addedTime = addedTime;
            }

            public Object getReadTime() {
                return readTime;
            }

            public void setReadTime(Object readTime) {
                this.readTime = readTime;
            }
        }

        public static class RowsBean {
            /**
             * id : 2
             * owner : null
             * employeeId : null
             * types : 2
             * title : 审批进度通知1
             * content : null
             * relationInfo : approvalflow/show/1
             * status : 1
             * addedTime : 2020-11-10 14:17:03
             * readTime : null
             */

            private int id;
            private Object owner;
            private Object employeeId;
            private int types;
            private String title;
            private Object content;
            private String relationInfo;
            private int status;
            private String addedTime;
            private Object readTime;

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

            public Object getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(Object employeeId) {
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

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getRelationInfo() {
                return relationInfo;
            }

            public void setRelationInfo(String relationInfo) {
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

            public Object getReadTime() {
                return readTime;
            }

            public void setReadTime(Object readTime) {
                this.readTime = readTime;
            }
        }
    }
}
