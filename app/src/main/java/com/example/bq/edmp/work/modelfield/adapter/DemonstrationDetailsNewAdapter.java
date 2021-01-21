package com.example.bq.edmp.work.modelfield.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class DemonstrationDetailsNewAdapter extends RecyclerView.Adapter<DemonstrationDetailsNewAdapter.ViewHolder> {

    private ArrayList<DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean> list;

    public DemonstrationDetailsNewAdapter(ArrayList<DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.demonstrationdetailsnew, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean demonstrationItemAnnexsBean = list.get(position);

        Glide.with(ProApplication.getmContext()).load("http://192.168.0.188:8010/demonstration_img" + demonstrationItemAnnexsBean.getUri()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<LocalMedia> selectList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath("http://192.168.0.188:8010/demonstration_img" + TurnImgStringUtils.isImgPath(list.get(i).getUri()));
                    selectList.add(localMedia);
                }

                mItemClickListener.onItemClick(1, position, demonstrationItemAnnexsBean, selectList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }


    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int type, int pos, DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean demonstrationItemAnnexsBean, List<LocalMedia> selectList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
