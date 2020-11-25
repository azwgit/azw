package com.example.bq.edmp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
//import com.othershe.calendarview.bean.DateBean;
//import com.othershe.calendarview.listener.OnPagerChangeListener;
//import com.othershe.calendarview.listener.OnSingleChooseListener;
//import com.othershe.calendarview.utils.CalendarUtil;
//import com.othershe.calendarview.weiget.CalendarView;

import butterknife.BindView;

public class ScheduleInformationActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
//    @BindView(R.id.calendar)
//    CalendarView calendar;
//    private int[] cDate = CalendarUtil.getCurrentDate();
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
//        title.setText(cDate[0] + "年" + cDate[1] + "月");
//        calendar.setStartEndDate("2020.1", "2049.12")
//                .setInitDate(cDate[0] + "." + cDate[1])
//                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
//                .init();
//        calendar.setOnPagerChangeListener(new OnPagerChangeListener() {
//            @Override
//            public void onPagerChanged(int[] date) {
//                title.setText(date[0] + "年" + date[1] + "月");
//            }
//        });
//        calendar.setOnSingleChooseListener(new OnSingleChooseListener() {
//            @Override
//            public void onSingleChoose(View view, DateBean date) {
//                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
//                if (date.getType() == 1) { }
//            }
//        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule_information;
    }

    @Override
    protected void otherViewClick(View view) {

    }
}