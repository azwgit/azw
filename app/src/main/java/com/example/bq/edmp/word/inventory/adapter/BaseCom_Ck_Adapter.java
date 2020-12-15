package com.example.bq.edmp.word.inventory.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;

import java.util.List;

/**
 * Created by bq on 2020/11/12.
 */

public class BaseCom_Ck_Adapter extends RecyclerView.Adapter<BaseCom_Ck_Adapter.Holder> {
    private List<CompanyBean.DataBean> list;

    public BaseCom_Ck_Adapter(List<CompanyBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.base_com_ck_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final CompanyBean.DataBean dataBean = list.get(position);

        holder.tv.setText(dataBean.getName());

        //判断点击与不点击的背景
        if (list.get(position).isSelected()) {
            holder.tv.setBackgroundColor(Color.parseColor("#FFF9F8F8"));
//            holder.tv.setBackgroundResource(R.drawable.audit_ch_shape_yes);
            holder.tv.setTextColor(Color.parseColor("#B317171A"));//FFFEE8E8    FFF2F5F6
        } else {
            holder.tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.tv.setBackgroundResource(R.drawable.audit_ch_shape_no);
            holder.tv.setTextColor(Color.parseColor("#000000"));
        }

        //点击条目
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(dataBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(CompanyBean.DataBean dataBean, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
