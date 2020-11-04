package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.login.Control_Login_Activity;
import com.example.bq.edmp.activity.mine_activity.Message_Activity;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 我的baiquan白泉
 *
 * */
public class MineFragment extends Fragment implements View.OnClickListener {


    private ImageView mine_head_img;
    private TextView mEditTv;
    private LinearLayout mine_bb;//添加东西
    private String r = "r";//改的
    private String nnn = "250";
    private TextView mTextview;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        inttView(inflate);
        return inflate;
    }

    private void inttView(View inflate) {
        RelativeLayout dl_rl = inflate.findViewById(R.id.dl_rl);
        RelativeLayout gy_rl = inflate.findViewById(R.id.gy_rl);
        mEditTv = inflate.findViewById(R.id.edit_tv);
        mine_bb = inflate.findViewById(R.id.mine_bb);
        mEditTv.setOnClickListener(this);
        mine_head_img = inflate.findViewById(R.id.mine_head_img);
        dl_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Control_Login_Activity.class));
            }
        });
        gy_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProApplication.getmContext(), Message_Activity.class));
            }
        });
        TextView a=mEditTv;
        mEditTv.setOnClickListener(this);
    }

    private int CHOOSE_SMALL_PICTURE = 250;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //编辑功能
            case R.id.edit_tv:
                Message_Activity.start(ProApplication.getmContext());
                break;
        }
    }
}


