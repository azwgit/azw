package com.example.bq.edmp.word.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.FirstResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bq on 2020/11/10.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private Context mcontext;
    private List<FirstResult.DataBean> list = new ArrayList<>();

    public LeftAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setList(List<FirstResult.DataBean> result) {
        this.list = result;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.left_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        myViewHolder.left_name.setText(list.get(position).getName());


        //判断点击与不点击的背景
        if (list.get(position).isSelected()) {
            myViewHolder.mlayout.setBackgroundColor(Color.parseColor("#F2F5F6"));
            myViewHolder.left_name.setTextColor(Color.parseColor("#000000"));
            myViewHolder.vw.setVisibility(ViewGroup.VISIBLE);
        } else {
            myViewHolder.mlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            myViewHolder.left_name.setTextColor(Color.parseColor("#66000000"));
            myViewHolder.vw.setVisibility(ViewGroup.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout mlayout;
        private final TextView left_name;
        private final View vw;

        public MyViewHolder(View itemView) {
            super(itemView);
            mlayout = itemView.findViewById(R.id.layout);
            left_name = itemView.findViewById(R.id.left_name);
            vw = itemView.findViewById(R.id.vw);

            //点击条目
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemLeftClckListener.onItemLeftClck(getLayoutPosition());
                }
            });
        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(int position);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
