package com.example.bq.edmp.addressbook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;

import java.util.List;

/**
 * Created by bq on 2020/11/23.
 */

public class Navigation_Adapter extends RecyclerView.Adapter<Navigation_Adapter.Holder> {

    private List<String> list;

    public Navigation_Adapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.navigation_attempt_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final String s = list.get(position);
        holder.child_text.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(s, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView child_text;

        public Holder(View itemView) {
            super(itemView);
            child_text = itemView.findViewById(R.id.child_text);
        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(String s, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
