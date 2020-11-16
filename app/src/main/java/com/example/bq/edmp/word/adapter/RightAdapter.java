package com.example.bq.edmp.word.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.SecondResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bq on 2020/11/10.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    private Context mcontext;
    private List<SecondResult.DataBean> list = new ArrayList<>();

    public RightAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setList(List<SecondResult.DataBean> result) {
        this.list = result;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.right_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mname.setText(list.get(i).getName());
        //写下面recycler视图
        List<SecondResult.DataBean.SubtBean> listchild = this.list.get(i).getSubt();
        if (listchild != null && listchild.size() != 0) {
            myViewHolder.mRecyclerView.setVisibility(ViewGroup.VISIBLE);
            myViewHolder.rl.setVisibility(ViewGroup.GONE);
            RightChildAdapter rightChildAdapter = new RightChildAdapter(mcontext, listchild);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 3);
            myViewHolder.mRecyclerView.setLayoutManager(gridLayoutManager);
            myViewHolder.mRecyclerView.setAdapter(rightChildAdapter);

            rightChildAdapter.setOnInterface(new RightChildAdapter.OnInterface() {
                @Override
                public void OnCilkeface(SecondResult.DataBean.SubtBean subtBean, int position) {
                    onInterface.OnCilkeface(subtBean, position);
                }
            });
        } else {
            myViewHolder.mRecyclerView.setVisibility(ViewGroup.GONE);
            myViewHolder.rl.setVisibility(ViewGroup.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        RelativeLayout rl;
        TextView mname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.name);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycleview);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl);
        }
    }

    public interface OnInterface {
        void OnCilkeface(SecondResult.DataBean.SubtBean subtBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
