package com.example.bq.edmp.utils;

/**
 * Created by tl on 2018/5/29.
 */

public class ActivityCode {
    public static final int ADDPLAN_GOODSLIST = 0001;//新建计划-->商品列表
    public static final int GOODSLIST_ADDPLAN = 0002;//新建计划<--商品列表
    public static final int NEWALLOCATION_ADDPLAN = 0003;//新建调拨<--商品列表
    public static final int ADDINDENT_GOODSLIST = 0004;//新建订单<--商品列表
    public static final int ADDCHARGEBACK_GOODSLIST = 0005;//新建订单<--商品列表
    public static final int LISTFRAGMENT_PLANDETAILSACTIVITY = 0006;//生产管理列表-->详情
    public static final int CUSTOMERLIST_CLIENTDETAILS = 0007;//客户列表-->详情
    public static final int PLAN_ADDPLAN = 0011;//计划详情-->修改计划
    public static final int PLAN_REFUSE = 0012;//计划详情-->拒绝
    public static final int ADDUNPACK_GOODSLIST = 0013;//添加拆包单-->商品详情
    public static final int ADDUNPACK_UNPACKDETAILS = 0014;//拆包单列表-->拆包详情
    public static final int UNPACKDETAILS_ADDUNPACK = 0015;//拆包详情-->修改(添加)
    public static final int SALESINFO_ADDINDENT = 0016;//订单详情 --> 修改(添加)
    public static final int SALESINFO_SINGLE_ADDINDENT = 0017;//退单详情 --> 修改(添加)
    public static final int ALLOCATIONDETAILS_ADDALLOCATION = 0020;//调拨详情 --> 修改(添加)
    public static final int ALLOCATIONDETAILS_JUJUE = 0021;//调拨详情 --> 修改(添加)
}
