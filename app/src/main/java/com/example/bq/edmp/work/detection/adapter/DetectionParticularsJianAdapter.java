package com.example.bq.edmp.work.detection.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.detection.bean.DetectionParticularsBean;

import java.util.List;

public class DetectionParticularsJianAdapter extends RecyclerView.Adapter<DetectionParticularsJianAdapter.ViewHolder> {
    private List<DetectionParticularsBean.DataBean.TestPlanItemBean> list;

    public DetectionParticularsJianAdapter(List<DetectionParticularsBean.DataBean.TestPlanItemBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.d_p_jian_adapter_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetectionParticularsBean.DataBean.TestPlanItemBean testPlanItemBean = list.get(position);

        holder.xm_name_tv.setText(testPlanItemBean.getName()+"("+testPlanItemBean.getUnit()+")");
        holder.zhi_tv.setText(testPlanItemBean.getValue());

        //	1合格 -1不合格 0—
        String results = testPlanItemBean.getResults();
        if (results!=null) {
            if (results.equals("1")) {
                holder.zhi_tv.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.text_black));
            } else if (results.equals("-1")) {
                holder.zhi_tv.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.colorf9));
            }
        }

        if(testPlanItemBean.getUpperLimit()!=null&&testPlanItemBean.getLowerLimit()!=null){
            holder.fan_tv.setText("范围"+testPlanItemBean.getLowerLimit()+"-"+testPlanItemBean.getUpperLimit());
        }else{
            if(testPlanItemBean.getLowerLimit()!=null){
                holder.fan_tv.setText("范围"+"≥"+testPlanItemBean.getLowerLimit()+"("+testPlanItemBean.getUnit()+")");
            }else{
                holder.fan_tv.setText("范围"+"≤"+testPlanItemBean.getUpperLimit()+"("+testPlanItemBean.getUnit()+")");
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView xm_name_tv;
        private final TextView zhi_tv;
        private final TextView fan_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            xm_name_tv = itemView.findViewById(R.id.xm_name_tv);
            zhi_tv = itemView.findViewById(R.id.zhi_tv);
            fan_tv = itemView.findViewById(R.id.fan_tv);
        }
    }
}
