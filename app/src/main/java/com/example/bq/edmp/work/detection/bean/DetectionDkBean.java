package com.example.bq.edmp.work.detection.bean;

import java.util.List;

public class DetectionDkBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":1,"farmId":1,"code":"1","position":null,"areas":0,"farmName":"1号农场"},{"id":2,"farmId":3,"code":"3","position":null,"areas":0,"farmName":"3号农场"}]
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

    public static class DataBean {
        /**
         * id : 1
         * farmId : 1
         * code : 1
         * position : null
         * areas : 0
         * farmName : 1号农场
         */

        private String id;
        private String farmId;
        private String code;
        private String position;
        private String areas;
        private String farmName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAreas() {
            return areas;
        }

        public void setAreas(String areas) {
            this.areas = areas;
        }

        public String getFarmName() {
            return farmName;
        }

        public void setFarmName(String farmName) {
            this.farmName = farmName;
        }
    }
}
