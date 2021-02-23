package com.example.bq.edmp.work.materialmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class ProcurementDetailsBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"id":58,"owner":1,"code":"WL20210223134546","orgId":1,"deptId":1,"remark":"采购","amount":55000,"warehouseId":null,"status":2,"addedTime":"2021-02-23 13:45","addedOperator":"超级管理员","addedOperatorId":20,"submitTime":null,"submitOperator":null,"approvedTime":null,"finishedOperator":null,"finishedTime":null,"materialPurchaseItems":[{"id":{"materialPurchaseId":58,"itemId":78},"qty":1000,"price":50,"materialPurchaseId":null,"itemId":null,"itemName":"极致玉米"},{"id":{"materialPurchaseId":58,"itemId":80},"qty":100,"price":50,"materialPurchaseId":null,"itemId":null,"itemName":"2323"}],"materialPurchaseAnnexs":[{"id":28,"materialPurchaseId":58,"uri":"/202102/1614059176773-7717.docx","annexFile":null},{"id":29,"materialPurchaseId":58,"uri":"/202102/1614059188860-5806.jpg","annexFile":null}],"deptName":"北京爱种网络科技有限公司","username":null,"itemId":null,"approvalFlow":{"id":202,"owner":1,"approvalsId":27,"businessId":7,"businessBillId":58,"promoterId":20,"remark":"审批流编号:27,业务单号:WL20210223134546","title":"超级管理员采购物料审批","addedTime":"2021-02-23 13:46:31","approveStatus":null,"approvedTime":null,"headImg":null,"promoternName":"超级管理员","approvalFlowLevels":[{"id":{"levels":1,"flowId":202},"approverId":17,"approverName":"李四12","approvedStatus":1,"approvedTime":null,"remark":null,"flowId":null,"approverImg":"34"}],"companyName":"北京爱种网络科技有限公司"},"warehouseName":null}
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
         * id : 58
         * owner : 1
         * code : WL20210223134546
         * orgId : 1
         * deptId : 1
         * remark : 采购
         * amount : 55000.0
         * warehouseId : null
         * status : 2
         * addedTime : 2021-02-23 13:45
         * addedOperator : 超级管理员
         * addedOperatorId : 20
         * submitTime : null
         * submitOperator : null
         * approvedTime : null
         * finishedOperator : null
         * finishedTime : null
         * materialPurchaseItems : [{"id":{"materialPurchaseId":58,"itemId":78},"qty":1000,"price":50,"materialPurchaseId":null,"itemId":null,"itemName":"极致玉米"},{"id":{"materialPurchaseId":58,"itemId":80},"qty":100,"price":50,"materialPurchaseId":null,"itemId":null,"itemName":"2323"}]
         * materialPurchaseAnnexs : [{"id":28,"materialPurchaseId":58,"uri":"/202102/1614059176773-7717.docx","annexFile":null},{"id":29,"materialPurchaseId":58,"uri":"/202102/1614059188860-5806.jpg","annexFile":null}]
         * deptName : 北京爱种网络科技有限公司
         * username : null
         * itemId : null
         * approvalFlow : {"id":202,"owner":1,"approvalsId":27,"businessId":7,"businessBillId":58,"promoterId":20,"remark":"审批流编号:27,业务单号:WL20210223134546","title":"超级管理员采购物料审批","addedTime":"2021-02-23 13:46:31","approveStatus":null,"approvedTime":null,"headImg":null,"promoternName":"超级管理员","approvalFlowLevels":[{"id":{"levels":1,"flowId":202},"approverId":17,"approverName":"李四12","approvedStatus":1,"approvedTime":null,"remark":null,"flowId":null,"approverImg":"34"}],"companyName":"北京爱种网络科技有限公司"}
         * warehouseName : null
         */

        private int id;
        private int owner;
        private String code;
        private int orgId;
        private int deptId;
        private String remark;
        private double amount;
        private Object warehouseId;
        private int status;
        private String addedTime;
        private String addedOperator;
        private int addedOperatorId;
        private Object submitTime;
        private Object submitOperator;
        private Object approvedTime;
        private String finishedOperator;
        private String finishedTime;
        private String deptName;
        private Object username;
        private Object itemId;
        private ApprovalFlowBean approvalFlow;
        private String warehouseName;
        private List<MaterialPurchaseItemsBean> materialPurchaseItems;
        private List<MaterialPurchaseAnnexsBean> materialPurchaseAnnexs;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Object warehouseId) {
            this.warehouseId = warehouseId;
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

        public String getAddedOperator() {
            return addedOperator;
        }

        public void setAddedOperator(String addedOperator) {
            this.addedOperator = addedOperator;
        }

        public int getAddedOperatorId() {
            return addedOperatorId;
        }

        public void setAddedOperatorId(int addedOperatorId) {
            this.addedOperatorId = addedOperatorId;
        }

        public Object getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(Object submitTime) {
            this.submitTime = submitTime;
        }

        public Object getSubmitOperator() {
            return submitOperator;
        }

        public void setSubmitOperator(Object submitOperator) {
            this.submitOperator = submitOperator;
        }

        public Object getApprovedTime() {
            return approvedTime;
        }

        public void setApprovedTime(Object approvedTime) {
            this.approvedTime = approvedTime;
        }

        public String getFinishedOperator() {
            return finishedOperator;
        }

        public void setFinishedOperator(String finishedOperator) {
            this.finishedOperator = finishedOperator;
        }

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getItemId() {
            return itemId;
        }

        public void setItemId(Object itemId) {
            this.itemId = itemId;
        }

        public ApprovalFlowBean getApprovalFlow() {
            return approvalFlow;
        }

        public void setApprovalFlow(ApprovalFlowBean approvalFlow) {
            this.approvalFlow = approvalFlow;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public List<MaterialPurchaseItemsBean> getMaterialPurchaseItems() {
            return materialPurchaseItems;
        }

        public void setMaterialPurchaseItems(List<MaterialPurchaseItemsBean> materialPurchaseItems) {
            this.materialPurchaseItems = materialPurchaseItems;
        }

        public List<MaterialPurchaseAnnexsBean> getMaterialPurchaseAnnexs() {
            return materialPurchaseAnnexs;
        }

        public void setMaterialPurchaseAnnexs(List<MaterialPurchaseAnnexsBean> materialPurchaseAnnexs) {
            this.materialPurchaseAnnexs = materialPurchaseAnnexs;
        }

        public static class ApprovalFlowBean {
            /**
             * id : 202
             * owner : 1
             * approvalsId : 27
             * businessId : 7
             * businessBillId : 58
             * promoterId : 20
             * remark : 审批流编号:27,业务单号:WL20210223134546
             * title : 超级管理员采购物料审批
             * addedTime : 2021-02-23 13:46:31
             * approveStatus : null
             * approvedTime : null
             * headImg : null
             * promoternName : 超级管理员
             * approvalFlowLevels : [{"id":{"levels":1,"flowId":202},"approverId":17,"approverName":"李四12","approvedStatus":1,"approvedTime":null,"remark":null,"flowId":null,"approverImg":"34"}]
             * companyName : 北京爱种网络科技有限公司
             */

            private int id;
            private int owner;
            private int approvalsId;
            private int businessId;
            private int businessBillId;
            private int promoterId;
            private String remark;
            private String title;
            private String addedTime;
            private int approveStatus;
            private Object approvedTime;
            private Object headImg;
            private String promoternName;
            private String companyName;
            private List<ApprovalFlowLevelsBean> approvalFlowLevels;

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

            public int getApprovalsId() {
                return approvalsId;
            }

            public void setApprovalsId(int approvalsId) {
                this.approvalsId = approvalsId;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public int getBusinessBillId() {
                return businessBillId;
            }

            public void setBusinessBillId(int businessBillId) {
                this.businessBillId = businessBillId;
            }

            public int getPromoterId() {
                return promoterId;
            }

            public void setPromoterId(int promoterId) {
                this.promoterId = promoterId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(String addedTime) {
                this.addedTime = addedTime;
            }

            public int getApproveStatus() {
                return approveStatus;
            }

            public void setApproveStatus(int approveStatus) {
                this.approveStatus = approveStatus;
            }

            public Object getApprovedTime() {
                return approvedTime;
            }

            public void setApprovedTime(Object approvedTime) {
                this.approvedTime = approvedTime;
            }

            public Object getHeadImg() {
                return headImg;
            }

            public void setHeadImg(Object headImg) {
                this.headImg = headImg;
            }

            public String getPromoternName() {
                return promoternName;
            }

            public void setPromoternName(String promoternName) {
                this.promoternName = promoternName;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public List<ApprovalFlowLevelsBean> getApprovalFlowLevels() {
                return approvalFlowLevels;
            }

            public void setApprovalFlowLevels(List<ApprovalFlowLevelsBean> approvalFlowLevels) {
                this.approvalFlowLevels = approvalFlowLevels;
            }

            public static class ApprovalFlowLevelsBean {
                /**
                 * id : {"levels":1,"flowId":202}
                 * approverId : 17
                 * approverName : 李四12
                 * approvedStatus : 1
                 * approvedTime : null
                 * remark : null
                 * flowId : null
                 * approverImg : 34
                 */

                private IdBean id;
                private int approverId;
                private String approverName;
                private int approvedStatus;
                private Object approvedTime;
                private Object remark;
                private Object flowId;
                private String approverImg;

                public IdBean getId() {
                    return id;
                }

                public void setId(IdBean id) {
                    this.id = id;
                }

                public int getApproverId() {
                    return approverId;
                }

                public void setApproverId(int approverId) {
                    this.approverId = approverId;
                }

                public String getApproverName() {
                    return approverName;
                }

                public void setApproverName(String approverName) {
                    this.approverName = approverName;
                }

                public int getApprovedStatus() {
                    return approvedStatus;
                }

                public void setApprovedStatus(int approvedStatus) {
                    this.approvedStatus = approvedStatus;
                }

                public Object getApprovedTime() {
                    return approvedTime;
                }

                public void setApprovedTime(Object approvedTime) {
                    this.approvedTime = approvedTime;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public Object getFlowId() {
                    return flowId;
                }

                public void setFlowId(Object flowId) {
                    this.flowId = flowId;
                }

                public String getApproverImg() {
                    return approverImg;
                }

                public void setApproverImg(String approverImg) {
                    this.approverImg = approverImg;
                }

                public static class IdBean {
                    /**
                     * levels : 1
                     * flowId : 202
                     */

                    private int levels;
                    private int flowId;

                    public int getLevels() {
                        return levels;
                    }

                    public void setLevels(int levels) {
                        this.levels = levels;
                    }

                    public int getFlowId() {
                        return flowId;
                    }

                    public void setFlowId(int flowId) {
                        this.flowId = flowId;
                    }
                }
            }
        }

        public static class MaterialPurchaseItemsBean {
            /**
             * id : {"materialPurchaseId":58,"itemId":78}
             * qty : 1000
             * price : 50.0
             * materialPurchaseId : null
             * itemId : null
             * itemName : 极致玉米
             */

            private IdBeanX id;
            private int qty;
            private double price;
            private Object materialPurchaseId;
            private Object itemId;
            private String itemName;

            public IdBeanX getId() {
                return id;
            }

            public void setId(IdBeanX id) {
                this.id = id;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public Object getMaterialPurchaseId() {
                return materialPurchaseId;
            }

            public void setMaterialPurchaseId(Object materialPurchaseId) {
                this.materialPurchaseId = materialPurchaseId;
            }

            public Object getItemId() {
                return itemId;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public static class IdBeanX {
                /**
                 * materialPurchaseId : 58
                 * itemId : 78
                 */

                private int materialPurchaseId;
                private int itemId;

                public int getMaterialPurchaseId() {
                    return materialPurchaseId;
                }

                public void setMaterialPurchaseId(int materialPurchaseId) {
                    this.materialPurchaseId = materialPurchaseId;
                }

                public int getItemId() {
                    return itemId;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }
            }
        }

        public static class MaterialPurchaseAnnexsBean {
            /**
             * id : 28
             * materialPurchaseId : 58
             * uri : /202102/1614059176773-7717.docx
             * annexFile : null
             */

            private int id;
            private int materialPurchaseId;
            private String uri;
            private Object annexFile;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMaterialPurchaseId() {
                return materialPurchaseId;
            }

            public void setMaterialPurchaseId(int materialPurchaseId) {
                this.materialPurchaseId = materialPurchaseId;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public Object getAnnexFile() {
                return annexFile;
            }

            public void setAnnexFile(Object annexFile) {
                this.annexFile = annexFile;
            }
        }
    }
}
