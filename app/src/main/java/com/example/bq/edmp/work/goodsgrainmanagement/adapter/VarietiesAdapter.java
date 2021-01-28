package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;

import java.util.ArrayList;

/**
 * Created by bq on 2020/11/12.
 */

public class VarietiesAdapter extends RecyclerView.Adapter<VarietiesAdapter.Holder> {
    private ArrayList<VarietiesBean.DataBean> list;

    public VarietiesAdapter(ArrayList<VarietiesBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.pz_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final VarietiesBean.DataBean dataBean = list.get(position);

        holder.tv.setText(dataBean.getVarietyName());


        //判断点击与不点击的背景
        if (list.get(position).isSelected()) {
            holder.tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.tv_text_red_shape));
            holder.tv.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.colorf9));
        } else {
            holder.tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.tv_text_grey_shape));
            holder.tv.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.color_66));
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
        void onItemLeftClck(VarietiesBean.DataBean dataBean, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
