package com.example.bq.edmp.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.home.bean.MessageBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * @author hyc
 * @date 2017/8/24 0024
 * Description：工作页面中的企业公告处的完成效果的展示适配器
 */

public class MessageListAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {

    public MessageListAdapter(List<MessageBean> data) {
        super(R.layout.item_message_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        AutoUtils.autoSize(helper.itemView);
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_time, item.getContent());
    }
}
