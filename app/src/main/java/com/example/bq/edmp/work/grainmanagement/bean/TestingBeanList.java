package com.example.bq.edmp.work.grainmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class TestingBeanList implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":2,"owner":1,"cropId":2,"types":null,"name":null,"remark":null,"testPlanItems":[{"id":1,"testPlanId":2,"name":"水分","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1},{"id":2,"testPlanId":2,"name":"杂质","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":2},{"id":3,"testPlanId":2,"name":"颗粒饱满","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1}]}]
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
         * id : 2
         * owner : 1
         * cropId : 2
         * types : null
         * name : null
         * remark : null
         * testPlanItems : [{"id":1,"testPlanId":2,"name":"水分","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1},{"id":2,"testPlanId":2,"name":"杂质","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":2},{"id":3,"testPlanId":2,"name":"颗粒饱满","unit":null,"lowerLimit":null,"upperLimit":null,"valueType":1}]
         */

        private int id;
        private int owner;
        private int cropId;
        private Object types;
        private Object name;
        private Object remark;
        private List<TestPlanItemsBean> testPlanItems;

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

        public int getCropId() {
            return cropId;
        }

        public void setCropId(int cropId) {
            this.cropId = cropId;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public List<TestPlanItemsBean> getTestPlanItems() {
            return testPlanItems;
        }

        public void setTestPlanItems(List<TestPlanItemsBean> testPlanItems) {
            this.testPlanItems = testPlanItems;
        }

        public static class TestPlanItemsBean {
            /**
             * id : 1
             * testPlanId : 2
             * name : 水分
             * unit : null
             * lowerLimit : null
             * upperLimit : null
             * valueType : 1
             */

            private int id;
            private int testPlanId;
            private String name;
            private Object unit;
            private Object lowerLimit;
            private Object upperLimit;
            private int valueType;
            private String content;

            public String getContent() {
                if(content==null){
                    return  "";
                }
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTestPlanId() {
                return testPlanId;
            }

            public void setTestPlanId(int testPlanId) {
                this.testPlanId = testPlanId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getUnit() {
                return unit;
            }

            public void setUnit(Object unit) {
                this.unit = unit;
            }

            public Object getLowerLimit() {
                return lowerLimit;
            }

            public void setLowerLimit(Object lowerLimit) {
                this.lowerLimit = lowerLimit;
            }

            public Object getUpperLimit() {
                return upperLimit;
            }

            public void setUpperLimit(Object upperLimit) {
                this.upperLimit = upperLimit;
            }

            public int getValueType() {
                return valueType;
            }

            public void setValueType(int valueType) {
                this.valueType = valueType;
            }
        }
    }
}
