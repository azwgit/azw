package com.example.bq.edmp.word.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.SecondResult;

import java.util.List;

/**
 * Created by bq on 2020/11/10.
 */

public class RightChildAdapter extends RecyclerView.Adapter<RightChildAdapter.MyViewHolder> {

    private Context mcontext;
    private List<SecondResult.DataBean.SubtBean> listChild;

    public RightChildAdapter(Context mcontext, List<SecondResult.DataBean.SubtBean> listChild) {
        this.mcontext = mcontext;
        this.listChild = listChild;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.rigth_item_child, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.mtitle.setText(listChild.get(i).getName());
//        Picasso.with(mcontext).load(listChild.get(i).getIcon()).into(myViewHolder.mIamge);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkeface(listChild.get(i), i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChild.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mtitle;
        ImageView mIamge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mIamge = (ImageView) itemView.findViewById(R.id.image);
            mtitle = (TextView) itemView.findViewById(R.id.title);
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
