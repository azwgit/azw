package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.ItemPackingListAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.materialmanagement.bean.MaterialListBean;

import java.util.ArrayList;
import java.util.List;

public class MaterialManagmentListAdapter extends RecyclerView.Adapter<MaterialManagmentListAdapter.ViewHolder> {
    private List<MaterialListBean.DataBean.RowsBean> list;

    public MaterialManagmentListAdapter(ArrayList<MaterialListBean.DataBean.RowsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<MaterialListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_material_managment_list, null));
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MaterialListBean.DataBean.RowsBean rowsBean = list.get(position);
        holder.tv_code.setText("物料单号: " + rowsBean.getCode());
//        holder.tv_compay_name.setText(rowsBean.getCustomerName());
        holder.tv_price.setText("¥ " + MoneyUtils.formatMoney(rowsBean.getAmount()));
        holder.tv_time.setText(rowsBean.getAddedTime());
//        holder.tv_subsidiary_company.setText(rowsBean.getOrgName());
//        holder.tv_warehouse.setText(rowsBean.getWarehouseName());
        holder.tv_name.setText(rowsBean.getDeptName()+" "+rowsBean.getAddedOperator());
        holder.tv_time.setText(rowsBean.getAddedTime());
        List<MaterialListBean.DataBean.RowsBean.MaterialPurchaseItemsBean> orderItem = rowsBean.getMaterialPurchaseItems();
        if (orderItem != null && orderItem.size() != 0) {
            holder.rv.setVisibility(View.VISIBLE);
            holder.wsj.setVisibility(View.GONE);
            ItemGoodsListAdapter orderNeiAdapter = new ItemGoodsListAdapter(orderItem);
            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.rv.setAdapter(orderNeiAdapter);
            orderNeiAdapter.notifyDataSetChanged();
        } else {
            holder.rv.setVisibility(View.GONE);
            holder.wsj.setVisibility(View.VISIBLE);
        }


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position, rowsBean);
            }
        });
        holder.rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return holder.ll.onTouchEvent(event);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_code;//订单号
        private final TextView tv_status;//订单状态
        private final TextView tv_compay_name;//公司名称
        private final TextView tv_price;//订单总价
        private final TextView tv_subsidiary_company;//分子公司名称
        private final TextView tv_warehouse;//仓库名称
        private final TextView tv_name;//操作人
        private final TextView tv_time;//添加时间
        private final TextView wsj;
        private final RecyclerView rv;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_code = itemView.findViewById(R.id.tv_code);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_compay_name = itemView.findViewById(R.id.tv_compay_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_subsidiary_company = itemView.findViewById(R.id.tv_subsidiary_company);
            tv_warehouse = itemView.findViewById(R.id.tv_warehouse);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            wsj = itemView.findViewById(R.id.wsj);
            rv = itemView.findViewById(R.id.rv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, MaterialListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
