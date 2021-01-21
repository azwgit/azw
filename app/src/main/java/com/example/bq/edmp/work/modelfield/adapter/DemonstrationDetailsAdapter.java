package com.example.bq.edmp.work.modelfield.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.bq.edmp.work.modelfield.activity.DemonstrationDetailsActivity;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class DemonstrationDetailsAdapter extends RecyclerView.Adapter<DemonstrationDetailsAdapter.ViewHolder> {

    private ArrayList<DemonstrationDetailsBean.DataBean.DemonstrationItemBean> list;
    private Activity context;

    public DemonstrationDetailsAdapter(ArrayList<DemonstrationDetailsBean.DataBean.DemonstrationItemBean> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.demonstrationdetails, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DemonstrationDetailsBean.DataBean.DemonstrationItemBean demonstrationItemBean = list.get(position);
        holder.user_name_tv.setText(demonstrationItemBean.getAddedOperator());
        holder.put_time_tv.setText(demonstrationItemBean.getAddedTime());
        holder.title_tv.setText(demonstrationItemBean.getTitle());


        List<DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean> demonstrationItemAnnexs = demonstrationItemBean.getDemonstrationItemAnnexs();
        if (demonstrationItemAnnexs != null && demonstrationItemAnnexs.size() != 0) {
            ArrayList<DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean> demonstrationItemAnnexsBeans = new ArrayList<>();
            demonstrationItemAnnexsBeans.addAll(demonstrationItemAnnexs);
            DemonstrationDetailsNewAdapter demonstrationDetailsNewAdapter = new DemonstrationDetailsNewAdapter(demonstrationItemAnnexsBeans);
            holder.rv.setLayoutManager(new GridLayoutManager(ProApplication.getmContext(), 3));
            holder.rv.setAdapter(demonstrationDetailsNewAdapter);
            demonstrationDetailsNewAdapter.notifyDataSetChanged();

            demonstrationDetailsNewAdapter.setOnItemClickListener(new DemonstrationDetailsNewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int type, int pos, DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean demonstrationItemAnnexsBean, List<LocalMedia> selectList) {
                   int themeId = R.style.picture_QQ_style;
                    PictureSelector.create(context).themeStyle(themeId).openExternalPreview(pos, selectList);
                }
            });

        }

        holder.delet_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(1, position, demonstrationItemBean);
            }
        });
        holder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(2, position, demonstrationItemBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rv;
        private final ImageView edit_img;
        private final TextView user_name_tv;
        private final TextView title_tv;
        private final TextView put_time_tv;
        private final ImageView delet_img;

        public ViewHolder(View itemView) {
            super(itemView);
            user_name_tv = itemView.findViewById(R.id.user_name_tv);
            title_tv = itemView.findViewById(R.id.title_tv);
            put_time_tv = itemView.findViewById(R.id.put_time_tv);
            delet_img = itemView.findViewById(R.id.delet_img);
            edit_img = itemView.findViewById(R.id.edit_img);
            rv = itemView.findViewById(R.id.rv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int type, int pos, DemonstrationDetailsBean.DataBean.DemonstrationItemBean demonstrationItemAnnexsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
