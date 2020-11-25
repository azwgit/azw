package com.example.bq.edmp.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.home.bean.HomeBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.TurnImgStringUtils;

import java.util.List;


/**
 * Created by bq on 2020/11/18.
 */

public class HomeJournalismAdapter extends RecyclerView.Adapter<HomeJournalismAdapter.Holder> {
    private List<HomeBean.DataBean.ArticlesBean> list;


    public HomeJournalismAdapter(List<HomeBean.DataBean.ArticlesBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.home_journalism_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final HomeBean.DataBean.ArticlesBean articlesBean = list.get(position);

        Glide.with(ProApplication.getmContext()).load(BaseApi.article_img_url + TurnImgStringUtils.isImgPath(articlesBean.getImgUri())).apply(RequestOptions.bitmapTransform(new CenterCrop()).error(R.drawable.untitled)).into(holder.img);

        if (articlesBean.getPublishedTime() != null && !articlesBean.getPublishedTime().equals("")) {
            holder.time.setText(articlesBean.getPublishedTime());
        } else {
            holder.time.setText("----------");
        }
        if (articlesBean.getShowCount() != null && !articlesBean.getShowCount().equals("")) {
            holder.liulan.setText("浏览次数：" + articlesBean.getShowCount());
        } else {
            holder.liulan.setText("浏览次数：-----");
        }
        if (articlesBean.getTitle() != null && !articlesBean.getTitle().equals("")) {
            holder.titles.setText(articlesBean.getTitle());
        } else {
            holder.titles.setText("皖垦种业-----------------------------");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(articlesBean, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView titles;
        private final TextView time;
        private final TextView liulan;
        private final ImageView img;

        public Holder(View itemView) {
            super(itemView);

            titles = itemView.findViewById(R.id.titles);
            time = itemView.findViewById(R.id.time);
            liulan = itemView.findViewById(R.id.liulan);
            img = itemView.findViewById(R.id.img);
        }
    }

    //接口回调
    public interface OnItemLeftClckListener {
        void onItemLeftClck(HomeBean.DataBean.ArticlesBean articlesBean, int mPosition);
    }

    private OnItemLeftClckListener onItemLeftClckListener;

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }
}
