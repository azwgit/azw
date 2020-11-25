package com.example.bq.edmp.addressbook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.addressbook.bean.AddressBook_Bean_Data;

import java.util.List;

/**
 * Created by bq on 2020/11/23.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.Holder> {
    private List<AddressBook_Bean_Data.DataBean.OrgChildrenBean> list;

    public DepartmentAdapter(List<AddressBook_Bean_Data.DataBean.OrgChildrenBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.department_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {


        final AddressBook_Bean_Data.DataBean.OrgChildrenBean orgChildrenBean = list.get(position);
        holder.classify_tv.setText(orgChildrenBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(orgChildrenBean,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView classify_tv;
        private final RelativeLayout rl;

        public Holder(View itemView) {
            super(itemView);
            classify_tv = itemView.findViewById(R.id.classify_tv);
            rl = itemView.findViewById(R.id.rl);

        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(AddressBook_Bean_Data.DataBean.OrgChildrenBean orgChildrenBeanXX, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
