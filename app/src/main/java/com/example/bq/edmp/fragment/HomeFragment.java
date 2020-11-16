package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bq.edmp.R;
import com.example.bq.edmp.home.activity.MessageNotificationListActivity;
import com.example.bq.edmp.home.activity.UploadImageActivity;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class HomeFragment extends Fragment {
    private LinearLayout mLyMessage;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mLyMessage = inflate.findViewById(R.id.ly_message);
        mLyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UploadImageActivity.class);
                startActivity(intent);
                //MessageNotificationListActivity.start(getActivity());
            }
        });
        return inflate;
    }

}
