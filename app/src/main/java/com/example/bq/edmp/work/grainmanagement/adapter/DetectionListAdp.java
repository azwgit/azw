package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

import java.util.List;

public class DetectionListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DetectionListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
        EditText etResult = helper.getView(R.id.et_result);
        int pos = helper.getLayoutPosition();
        if(pos==0){
            etResult.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        etResult.setText(item.getDesc());
        //添加editText的监听事件
        etResult.addTextChangedListener(new TextSwitcher(helper));
//        helper.setText(R.id.tv_nick,"昵称");
//        helper.setText(R.id.tv_time,"2020-10-25 10:12");
    }
    //自定义EditText的监听类
    class TextSwitcher implements TextWatcher {
        private BaseViewHolder helper;
        public TextSwitcher(BaseViewHolder helper ) {
            this.helper = helper;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
            SaveEditListener listener= (SaveEditListener) mContext;
            if(s!=null){
                listener.SaveEdit(helper.getAdapterPosition(),s.toString());
            }

        }
    }
}
