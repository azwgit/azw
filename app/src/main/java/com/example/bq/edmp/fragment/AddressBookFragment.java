package com.example.bq.edmp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 * 通讯录
 */
public class AddressBookFragment extends Fragment {

    // 这个数组是用来存储一级item的点击次数的，根据点击次数设置一级标签的选中、为选中状态
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;


    // 一级标签上的logo图片数据源
    int[] group_logo_array = new int[]{R.drawable.aboutus, R.drawable.aboutus, R.drawable.aboutus, R.drawable.aboutus, R.drawable.aboutus, R.drawable.aboutus, R.drawable.aboutus};
    // 一级标签上的标题数据源
    private String[] group_title_arry = new String[]{"人力资源部（5）", "行政部（3）", "财务部（2）", "行政部（3）", "市场部（9）", "行政部（3）", "业务部（25）"};
    // 子视图显示文字
    private String[][] child_text_array = new String[][]{
            {"1", "2", "3", "4", "5", "6"},
            {"0", "1", "2"},
            {"0", "1", "2", "3"},
            {"0", "1", "2", "3"},
            {"0", "1", "2", "3"},
            {"0", "1", "2", "3"},
            {"0", "1", "2", "3"}};
    // 一级标签上的状态图片数据源
    int[] group_state_array = new int[]{R.drawable.forward, R.drawable.down};

    public AddressBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // 隐藏标题
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View inflate = inflater.inflate(R.layout.fragment_addressbook, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {

        // 新建一个ExpandableListView
        ExpandableListView expandableListView = (ExpandableListView) inflate.findViewById(R.id.list);
        // 设置默认图标为不显示状态
        expandableListView.setGroupIndicator(null);
        // 为列表绑定数据源
        expandableListView.setAdapter(adapter);
        // 设置一级item点击的监听器
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {


            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                group_checked[groupPosition] = group_checked[groupPosition] + 1;
                // 刷新界面
                ((BaseExpandableListAdapter) adapter).notifyDataSetChanged();
                return false;
            }
        });


        // 设置二级item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {


            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // 将被点击的一丶二级标签的位置记录下来
                child_groupId = groupPosition;
                child_childId = childPosition;
                // 刷新界面
                ((BaseExpandableListAdapter) adapter).notifyDataSetChanged();
                ToastUtil.setToast(child_text_array[groupPosition][childPosition]);
                return false;
            }
        });

        String sign = MD5Util.encode("password=" + "1234" + "&username=" + "12346963651");
//        String sign = MD5Util.encode("username=" + "12346963651" + "&password=" + "1234");
        Log.d("nijao", "initView: ============"+sign);
    }

    final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

        // 重写ExpandableListAdapter中的各个方法

        /**
         * 获取一级标签总数
         */
        @Override
        public int getGroupCount() {
            return group_title_arry.length;
        }


        /**
         * 获取一级标签内容
         */
        @Override
        public Object getGroup(int groupPosition) {
            return group_title_arry[groupPosition];
        }


        /**
         * 获取一级标签的ID
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }


        /**
         * 获取一级标签下二级标签的总数
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return child_text_array[groupPosition].length;
        }


        /**
         * 获取一级标签下二级标签的内容
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child_text_array[groupPosition][childPosition];
        }


        /**
         * 获取二级标签的ID
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         * 指定位置相应的组视图
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }


        /**
         * 对一级标签进行设置
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            // 为视图对象指定布局
            convertView = (RelativeLayout) RelativeLayout.inflate(ProApplication.getmContext(), R.layout.group, null);
            /**
             * 声明视图上要显示的控件
             */
            // 新建一个ImageView对象，用来显示一级标签上的logo图片
            ImageView group_logo = (ImageView) convertView
                    .findViewById(R.id.group_logo);
            // 新建一个TextView对象，用来显示一级标签上的标题信息
            TextView group_title = (TextView) convertView
                    .findViewById(R.id.group_title);
            // 新建一个ImageView对象，根据用户点击来标识一级标签的选中状态
            ImageView group_state = (ImageView) convertView
                    .findViewById(R.id.group_state);
            /**
             * 设置相应控件的内容
             */
            // 设置要显示的图片
            group_logo.setBackgroundResource(group_logo_array[groupPosition]);
            // 设置标题上的文本信息
            group_title.setText(group_title_arry[groupPosition]);

            if (group_checked[groupPosition] % 2 == 1) {
                // 设置默认的图片是选中状态
                group_state.setBackgroundResource(group_state_array[1]);
            } else {
                for (int test : group_checked) {
                    if (test == 0 || test % 2 == 0) {
                        // 设置默认的图片是未选中状态
                        group_state.setBackgroundResource(group_state_array[0]);
                    }
                }
            }
            // 返回一个布局对象
            return convertView;
        }


        /**
         * 对一级标签下的二级标签进行设置
         */
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            // 为视图对象指定布局
            convertView = (RelativeLayout) RelativeLayout.inflate(ProApplication.getmContext(), R.layout.child, null);
            /**
             * 声明视图上要显示的控件
             */
            // 新建一个TextView对象，用来显示具体内容
            TextView child_text = (TextView) convertView.findViewById(R.id.child_text);
            /**
             * 设置相应控件的内容
             */
            // 设置要显示的文本信息
            child_text.setText(child_text_array[groupPosition][childPosition]);
//            // 判断item的位置是否相同，如相同，则表示为选中状态，更改其背景颜色，如不相同，则设置背景色为白色
//            if (child_groupId == groupPosition && child_childId == childPosition) {
//                // 设置背景色为绿色
//                convertView.setBackgroundColor(Color.GREEN);
//            } else {
//                // 设置背景色为白色
//                convertView.setBackgroundColor(Color.WHITE);
//            }
            // 返回一个布局对象
            return convertView;
        }


        /**
         * 当选择子节点的时候，调用该方法
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }


    };
}
