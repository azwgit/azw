package com.example.bq.edmp.work.marketing.bean;

import java.io.Serializable;
import java.util.List;

public class ProvinceAndCityListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":"11","parentId":"0","name":"北京市","fullName":"北京市","status":1,"children":null},{"id":"12","parentId":"0","name":"天津市","fullName":"天津市","status":1,"children":null},{"id":"13","parentId":"0","name":"河北省","fullName":"河北省","status":1,"children":null},{"id":"14","parentId":"0","name":"山西省","fullName":"山西省","status":1,"children":null},{"id":"15","parentId":"0","name":"内蒙古","fullName":"内蒙古","status":1,"children":null},{"id":"21","parentId":"0","name":"辽宁省","fullName":"辽宁省","status":1,"children":null},{"id":"22","parentId":"0","name":"吉林省","fullName":"吉林省","status":1,"children":null},{"id":"23","parentId":"0","name":"黑龙江省","fullName":"黑龙江省","status":1,"children":null},{"id":"31","parentId":"0","name":"上海市","fullName":"上海市","status":1,"children":null},{"id":"32","parentId":"0","name":"江苏省","fullName":"江苏省","status":1,"children":null},{"id":"33","parentId":"0","name":"浙江省","fullName":"浙江省","status":1,"children":null},{"id":"34","parentId":"0","name":"安徽省","fullName":"安徽省","status":1,"children":null},{"id":"35","parentId":"0","name":"福建省","fullName":"福建省","status":1,"children":null},{"id":"36","parentId":"0","name":"江西省","fullName":"江西省","status":1,"children":null},{"id":"37","parentId":"0","name":"山东省","fullName":"山东省","status":1,"children":null},{"id":"41","parentId":"0","name":"河南省","fullName":"河南省","status":1,"children":null},{"id":"42","parentId":"0","name":"湖北省","fullName":"湖北省","status":1,"children":null},{"id":"43","parentId":"0","name":"湖南省","fullName":"湖南省","status":1,"children":null},{"id":"44","parentId":"0","name":"广东省","fullName":"广东省","status":1,"children":null},{"id":"45","parentId":"0","name":"广西","fullName":"广西","status":1,"children":null},{"id":"46","parentId":"0","name":"海南省","fullName":"海南省","status":1,"children":null},{"id":"50","parentId":"0","name":"重庆市","fullName":"重庆市","status":1,"children":null},{"id":"51","parentId":"0","name":"四川省","fullName":"四川省","status":1,"children":null},{"id":"52","parentId":"0","name":"贵州省","fullName":"贵州省","status":1,"children":null},{"id":"53","parentId":"0","name":"云南省","fullName":"云南省","status":1,"children":null},{"id":"54","parentId":"0","name":"西藏","fullName":"西藏","status":1,"children":null},{"id":"61","parentId":"0","name":"陕西省","fullName":"陕西省","status":1,"children":null},{"id":"62","parentId":"0","name":"甘肃省","fullName":"甘肃省","status":1,"children":null},{"id":"63","parentId":"0","name":"青海省","fullName":"青海省","status":1,"children":null},{"id":"64","parentId":"0","name":"宁夏","fullName":"宁夏","status":1,"children":null},{"id":"65","parentId":"0","name":"新疆省","fullName":"新疆","status":1,"children":null}]
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
         * id : 11
         * parentId : 0
         * name : 北京市
         * fullName : 北京市
         * status : 1
         * children : null
         */

        private String id;
        private String parentId;
        private String name;
        private String fullName;
        private int status;
        private Object children;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }
    }
}
