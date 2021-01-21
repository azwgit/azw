package com.example.bq.edmp.work.reseller.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.modelfield.adapter.DemonstrationDetailsNewAdapter;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResellerEdtAdapter extends RecyclerView.Adapter<ResellerEdtAdapter.ViewHolder> {

    private ArrayList<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> list;
    private Activity context;

    public ResellerEdtAdapter(ArrayList<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.reseller_edt, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean stockAllotItemsBean = list.get(position);

        holder.umber_tv.setText("数量 " + new DecimalFormat("#0.00").format(stockAllotItemsBean.getQty()) + " 公斤");
        holder.zhuanchu_tv.setText(stockAllotItemsBean.getOutItemName());
        holder.zhuanru_tv.setText(stockAllotItemsBean.getInItemName());

        holder.delet_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(1, position, stockAllotItemsBean);
            }
        });
        holder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(2, position, stockAllotItemsBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView edit_img;
        private final TextView zhuanru_tv;
        private final TextView umber_tv;
        private final TextView zhuanchu_tv;
        private final ImageView delet_img;

        public ViewHolder(View itemView) {
            super(itemView);
            zhuanru_tv = itemView.findViewById(R.id.zhuanru_tv);
            umber_tv = itemView.findViewById(R.id.umber_tv);
            zhuanchu_tv = itemView.findViewById(R.id.zhuanchu_tv);
            delet_img = itemView.findViewById(R.id.delet_img);
            edit_img = itemView.findViewById(R.id.edit_img);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int type, int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean stockAllotItemsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
