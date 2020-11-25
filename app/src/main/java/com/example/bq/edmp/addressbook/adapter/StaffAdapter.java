package com.example.bq.edmp.addressbook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.addressbook.bean.AddressBook_Bean_Data;

import java.util.List;

/**
 * Created by bq on 2020/11/23.
 */

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.Holder> {

    private List<AddressBook_Bean_Data.DataBean.EmployeesBean> list;

    public StaffAdapter(List<AddressBook_Bean_Data.DataBean.EmployeesBean> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.staff_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final AddressBook_Bean_Data.DataBean.EmployeesBean employeesBean = list.get(position);

        holder.employee_name_tv.setText(employeesBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(employeesBean,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView employee_name_tv;
        private final TextView post_tv;
        private final ImageView head_img;

        public Holder(View itemView) {
            super(itemView);
            employee_name_tv = itemView.findViewById(R.id.employee_name_tv);
            post_tv = itemView.findViewById(R.id.post_tv);
            head_img = itemView.findViewById(R.id.head_img);
        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(AddressBook_Bean_Data.DataBean.EmployeesBean employeesBean, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
