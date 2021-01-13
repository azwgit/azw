package com.example.bq.edmp.work.returnsmanagement.bean;

import java.io.Serializable;
import java.util.List;

public class DeliverGoodsListBean implements Serializable {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"filter":{"id":null,"owner":1,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":null,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":24,"warehouseName":null,"varietyName":null,"code":null,"customerName":null,"orgName":null,"price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null},"rows":[{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":37,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"大豆仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201223150923","customerName":"雪碧vs可乐","orgName":"南京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null},{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":38,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"大豆仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201223151051","customerName":"雪碧vs可乐","orgName":"北京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null},{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":83,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"辣椒仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201224102719","customerName":"雪碧vs可乐","orgName":"北京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null}],"page":1,"pagerow":15,"totalpages":0,"totalrows":3,"sumtotal":0,"sortname":null,"sortorder":"ASC"}
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
         * filter : {"id":null,"owner":1,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":null,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":24,"warehouseName":null,"varietyName":null,"code":null,"customerName":null,"orgName":null,"price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null}
         * rows : [{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":37,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"大豆仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201223150923","customerName":"雪碧vs可乐","orgName":"南京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null},{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":38,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"大豆仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201223151051","customerName":"雪碧vs可乐","orgName":"北京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null},{"id":null,"owner":null,"qty":null,"orgId":null,"warehouseId":null,"addedTimes":null,"sendOutTimes":null,"sendOutOperator":null,"types":null,"truckId":null,"tplName":null,"tplNo":null,"remark":null,"signedTime":null,"signedOperator":null,"status":null,"ordersId":83,"itemIds":null,"qtys":null,"orgIds":null,"itemId":1,"customerId":null,"warehouseName":"辣椒仓库","varietyName":"一号小麦 100公斤/袋","code":"DD20201224102719","customerName":"雪碧vs可乐","orgName":"北京分公司","price":null,"salesId":null,"region":null,"contacts":null,"mobTel":null,"address":null,"warehouseIds":null}]
         * page : 1
         * pagerow : 15
         * totalpages : 0
         * totalrows : 3
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
             * qty : null
             * orgId : null
             * warehouseId : null
             * addedTimes : null
             * sendOutTimes : null
             * sendOutOperator : null
             * types : null
             * truckId : null
             * tplName : null
             * tplNo : null
             * remark : null
             * signedTime : null
             * signedOperator : null
             * status : null
             * ordersId : null
             * itemIds : null
             * qtys : null
             * orgIds : null
             * itemId : 1
             * customerId : 24
             * warehouseName : null
             * varietyName : null
             * code : null
             * customerName : null
             * orgName : null
             * price : null
             * salesId : null
             * region : null
             * contacts : null
             * mobTel : null
             * address : null
             * warehouseIds : null
             */

            private Object id;
            private int owner;
            private Object qty;
            private Object orgId;
            private Object warehouseId;
            private Object addedTimes;
            private Object sendOutTimes;
            private Object sendOutOperator;
            private Object types;
            private Object truckId;
            private Object tplName;
            private Object tplNo;
            private Object remark;
            private Object signedTime;
            private Object signedOperator;
            private Object status;
            private Object ordersId;
            private Object itemIds;
            private Object qtys;
            private Object orgIds;
            private int itemId;
            private int customerId;
            private Object warehouseName;
            private Object varietyName;
            private Object code;
            private Object customerName;
            private Object orgName;
            private Object price;
            private Object salesId;
            private Object region;
            private Object contacts;
            private Object mobTel;
            private Object address;
            private Object warehouseIds;

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

            public Object getQty() {
                return qty;
            }

            public void setQty(Object qty) {
                this.qty = qty;
            }

            public Object getOrgId() {
                return orgId;
            }

            public void setOrgId(Object orgId) {
                this.orgId = orgId;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public Object getAddedTimes() {
                return addedTimes;
            }

            public void setAddedTimes(Object addedTimes) {
                this.addedTimes = addedTimes;
            }

            public Object getSendOutTimes() {
                return sendOutTimes;
            }

            public void setSendOutTimes(Object sendOutTimes) {
                this.sendOutTimes = sendOutTimes;
            }

            public Object getSendOutOperator() {
                return sendOutOperator;
            }

            public void setSendOutOperator(Object sendOutOperator) {
                this.sendOutOperator = sendOutOperator;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }

            public Object getTruckId() {
                return truckId;
            }

            public void setTruckId(Object truckId) {
                this.truckId = truckId;
            }

            public Object getTplName() {
                return tplName;
            }

            public void setTplName(Object tplName) {
                this.tplName = tplName;
            }

            public Object getTplNo() {
                return tplNo;
            }

            public void setTplNo(Object tplNo) {
                this.tplNo = tplNo;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getSignedTime() {
                return signedTime;
            }

            public void setSignedTime(Object signedTime) {
                this.signedTime = signedTime;
            }

            public Object getSignedOperator() {
                return signedOperator;
            }

            public void setSignedOperator(Object signedOperator) {
                this.signedOperator = signedOperator;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(Object ordersId) {
                this.ordersId = ordersId;
            }

            public Object getItemIds() {
                return itemIds;
            }

            public void setItemIds(Object itemIds) {
                this.itemIds = itemIds;
            }

            public Object getQtys() {
                return qtys;
            }

            public void setQtys(Object qtys) {
                this.qtys = qtys;
            }

            public Object getOrgIds() {
                return orgIds;
            }

            public void setOrgIds(Object orgIds) {
                this.orgIds = orgIds;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public Object getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(Object warehouseName) {
                this.warehouseName = warehouseName;
            }

            public Object getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(Object varietyName) {
                this.varietyName = varietyName;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public Object getCustomerName() {
                return customerName;
            }

            public void setCustomerName(Object customerName) {
                this.customerName = customerName;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getSalesId() {
                return salesId;
            }

            public void setSalesId(Object salesId) {
                this.salesId = salesId;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getContacts() {
                return contacts;
            }

            public void setContacts(Object contacts) {
                this.contacts = contacts;
            }

            public Object getMobTel() {
                return mobTel;
            }

            public void setMobTel(Object mobTel) {
                this.mobTel = mobTel;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getWarehouseIds() {
                return warehouseIds;
            }

            public void setWarehouseIds(Object warehouseIds) {
                this.warehouseIds = warehouseIds;
            }
        }

        public static class RowsBean {
            /**
             * id : null
             * owner : null
             * qty : null
             * orgId : null
             * warehouseId : null
             * addedTimes : null
             * sendOutTimes : null
             * sendOutOperator : null
             * types : null
             * truckId : null
             * tplName : null
             * tplNo : null
             * remark : null
             * signedTime : null
             * signedOperator : null
             * status : null
             * ordersId : 37
             * itemIds : null
             * qtys : null
             * orgIds : null
             * itemId : 1
             * customerId : null
             * warehouseName : 大豆仓库
             * varietyName : 一号小麦 100公斤/袋
             * code : DD20201223150923
             * customerName : 雪碧vs可乐
             * orgName : 南京分公司
             * price : null
             * salesId : null
             * region : null
             * contacts : null
             * mobTel : null
             * address : null
             * warehouseIds : null
             */

            private Object id;
            private Object owner;
            private String qty;
            private Object orgId;
            private Object warehouseId;
            private Object addedTimes;
            private String sendOutTimes;
            private Object sendOutOperator;
            private Object types;
            private Object truckId;
            private Object tplName;
            private Object tplNo;
            private Object remark;
            private Object signedTime;
            private Object signedOperator;
            private Object status;
            private int ordersId;
            private Object itemIds;
            private String qtys;
            private Object orgIds;
            private int itemId;
            private Object customerId;
            private String warehouseName;
            private String varietyName;
            private String code;
            private String customerName;
            private String orgName;
            private Object price;
            private Object salesId;
            private Object region;
            private Object contacts;
            private Object mobTel;
            private Object address;
            private Object warehouseIds;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public Object getOrgId() {
                return orgId;
            }

            public void setOrgId(Object orgId) {
                this.orgId = orgId;
            }

            public Object getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(Object warehouseId) {
                this.warehouseId = warehouseId;
            }

            public Object getAddedTimes() {
                return addedTimes;
            }

            public void setAddedTimes(Object addedTimes) {
                this.addedTimes = addedTimes;
            }

            public String getSendOutTimes() {
                return sendOutTimes;
            }

            public void setSendOutTimes(String sendOutTimes) {
                this.sendOutTimes = sendOutTimes;
            }

            public Object getSendOutOperator() {
                return sendOutOperator;
            }

            public void setSendOutOperator(Object sendOutOperator) {
                this.sendOutOperator = sendOutOperator;
            }

            public Object getTypes() {
                return types;
            }

            public void setTypes(Object types) {
                this.types = types;
            }

            public Object getTruckId() {
                return truckId;
            }

            public void setTruckId(Object truckId) {
                this.truckId = truckId;
            }

            public Object getTplName() {
                return tplName;
            }

            public void setTplName(Object tplName) {
                this.tplName = tplName;
            }

            public Object getTplNo() {
                return tplNo;
            }

            public void setTplNo(Object tplNo) {
                this.tplNo = tplNo;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getSignedTime() {
                return signedTime;
            }

            public void setSignedTime(Object signedTime) {
                this.signedTime = signedTime;
            }

            public Object getSignedOperator() {
                return signedOperator;
            }

            public void setSignedOperator(Object signedOperator) {
                this.signedOperator = signedOperator;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public int getOrdersId() {
                return ordersId;
            }

            public void setOrdersId(int ordersId) {
                this.ordersId = ordersId;
            }

            public Object getItemIds() {
                return itemIds;
            }

            public void setItemIds(Object itemIds) {
                this.itemIds = itemIds;
            }

            public String getQtys() {
                return qtys;
            }

            public void setQtys(String qtys) {
                this.qtys = qtys;
            }

            public Object getOrgIds() {
                return orgIds;
            }

            public void setOrgIds(Object orgIds) {
                this.orgIds = orgIds;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(String warehouseName) {
                this.warehouseName = warehouseName;
            }

            public String getVarietyName() {
                return varietyName;
            }

            public void setVarietyName(String varietyName) {
                this.varietyName = varietyName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getSalesId() {
                return salesId;
            }

            public void setSalesId(Object salesId) {
                this.salesId = salesId;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getContacts() {
                return contacts;
            }

            public void setContacts(Object contacts) {
                this.contacts = contacts;
            }

            public Object getMobTel() {
                return mobTel;
            }

            public void setMobTel(Object mobTel) {
                this.mobTel = mobTel;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getWarehouseIds() {
                return warehouseIds;
            }

            public void setWarehouseIds(Object warehouseIds) {
                this.warehouseIds = warehouseIds;
            }
        }
    }
}
