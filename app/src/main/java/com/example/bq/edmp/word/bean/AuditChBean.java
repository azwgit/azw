package com.example.bq.edmp.word.bean;

import java.util.List;

/**
 * Created by bq on 2020/11/12.
 */

public class AuditChBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 01
         * owner : null
         * parentId : null
         * systemId : null
         * name : 基础管理
         * levels : 1
         * displayType : null
         * accessUri : null
         * icon : null
         * status : null
         * subt : null
         */

        private int id;
        private String name;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
