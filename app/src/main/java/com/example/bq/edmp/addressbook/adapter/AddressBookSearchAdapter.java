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
import com.example.bq.edmp.addressbook.bean.AddressBookSearchBean;

import java.util.List;

/**
 * Created by bq on 2020/11/24.
 */

public class AddressBookSearchAdapter extends RecyclerView.Adapter<AddressBookSearchAdapter.Holder> {
    private List<AddressBookSearchBean.DataBean> list;

    public AddressBookSearchAdapter(List<AddressBookSearchBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AddressBookSearchAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.addressbook_search_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressBookSearchAdapter.Holder holder, final int position) {
        final AddressBookSearchBean.DataBean dataBean = list.get(position);


        holder.child_text.setText(dataBean.getName());
        holder.child_text2.setText(dataBean.getTitle());
        if (dataBean.getCompanyName() != null && !dataBean.getCompanyName().equals("")) {
            if (dataBean.getDeptName() != null && !dataBean.getDeptName().equals("")) {
                holder.child_text3.setText(dataBean.getFirstcompanyName() + "-" + dataBean.getCompanyName() + "-" + dataBean.getDeptName());
            } else {
                holder.child_text3.setText(dataBean.getFirstcompanyName() + "-" + dataBean.getCompanyName());
            }
        } else {
            if (dataBean.getDeptName() != null && !dataBean.getDeptName().equals("")) {
                holder.child_text3.setText(dataBean.getFirstcompanyName() + "-" + dataBean.getDeptName());
            } else {
                holder.child_text3.setText(dataBean.getFirstcompanyName());
            }
        }

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

        private final ImageView img;
        private final ImageView phone_img;
        private final TextView child_text;
        private final TextView child_text2;
        private final TextView child_text3;

        public Holder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            phone_img = itemView.findViewById(R.id.phone_img);
            child_text = itemView.findViewById(R.id.child_text);
            child_text2 = itemView.findViewById(R.id.child_text2);
            child_text3 = itemView.findViewById(R.id.child_text3);


        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(AddressBookSearchBean.DataBean dataBean, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
