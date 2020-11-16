package com.example.bq.edmp.word.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.AuditListBean;
import com.example.bq.edmp.word.bean.SecondResult;
import com.example.bq.edmp.word.bean.SubmitListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bq on 2020/11/11.
 */

public class AuditListAdapter extends RecyclerView.Adapter<AuditListAdapter.Holder> {
    private ArrayList<AuditListBean.RowsBean> list;

    public AuditListAdapter(ArrayList<AuditListBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<AuditListBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.audit_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final AuditListBean.RowsBean rowsBean = list.get(position);
        //	1审批中 2通过 3拒绝
        if (rowsBean.getApproveStatus() == 1) {
            holder.zt_img.setVisibility(ViewGroup.GONE);
            holder.zt_tv.setVisibility(ViewGroup.VISIBLE);
            holder.spsj_ll.setVisibility(ViewGroup.GONE);
        } else if (rowsBean.getApproveStatus() == 2) {
            holder.zt_img.setVisibility(ViewGroup.VISIBLE);
            holder.zt_tv.setVisibility(ViewGroup.GONE);
            holder.spsj_ll.setVisibility(ViewGroup.VISIBLE);
            holder.spsj_tv.setText(rowsBean.getApprovedTime());
//            Glide.with(ProApplication.getmContext()).load(R.drawable.yitongyi).into(holder.zt_img);
        } else if (rowsBean.getApproveStatus() == 3) {
            holder.zt_img.setVisibility(ViewGroup.VISIBLE);
            holder.zt_tv.setVisibility(ViewGroup.GONE);
            holder.spsj_ll.setVisibility(ViewGroup.VISIBLE);
            holder.spsj_tv.setText(rowsBean.getApprovedTime());
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1yijujue).into(holder.zt_img);
        }

        holder.sqsj_tv.setText(rowsBean.getAddedTime());
        holder.name_lx_tv.setText(rowsBean.getPromoter() + "提交的" + "--申请");

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkeface(rowsBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView fl_tv;
        private final TextView sp_tv;
        private final TextView zt_tv;
        private final TextView name_lx_tv;
        private final TextView sqlx_tv;
        private final TextView bxze_tv;
        private final TextView spsj_tv;
        private final TextView sqsj_tv;
        private final ImageView zt_img;
        private final LinearLayout spsj_ll;

        public Holder(View itemView) {
            super(itemView);
            fl_tv = itemView.findViewById(R.id.fl_tv);
            sp_tv = itemView.findViewById(R.id.sp_tv);
            zt_tv = itemView.findViewById(R.id.zt_tv);
            name_lx_tv = itemView.findViewById(R.id.name_lx_tv);
            sqlx_tv = itemView.findViewById(R.id.sqlx_tv);
            bxze_tv = itemView.findViewById(R.id.bxze_tv);
            spsj_tv = itemView.findViewById(R.id.spsj_tv);
            sqsj_tv = itemView.findViewById(R.id.sqsj_tv);
            zt_img = itemView.findViewById(R.id.zt_img);
            spsj_ll = itemView.findViewById(R.id.spsj_ll);

        }
    }


    public interface OnInterface {
        void OnCilkeface(AuditListBean.RowsBean rowsBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
