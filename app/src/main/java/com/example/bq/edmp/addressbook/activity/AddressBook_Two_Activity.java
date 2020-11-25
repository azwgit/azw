package com.example.bq.edmp.addressbook.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.addressbook.adapter.DepartmentAdapter;
import com.example.bq.edmp.addressbook.adapter.Navigation_Adapter;
import com.example.bq.edmp.addressbook.adapter.StaffAdapter;
import com.example.bq.edmp.addressbook.api.AddressBookApi;
import com.example.bq.edmp.addressbook.bean.AddressBook_Bean_Data;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
* 通讯录 第二面
* */
public class AddressBook_Two_Activity extends BaseActivity {

    @BindView(R.id.department_rv)
    RecyclerView department_rv;
    @BindView(R.id.staff_rv)
    RecyclerView staff_rv;
    @BindView(R.id.navigation_rv)
    RecyclerView navigation_rv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.sousuo_rl)
    RelativeLayout sousuo_rl;


    private ArrayList<AddressBook_Bean_Data.DataBean.OrgChildrenBean> orgChildrenBeanXXES;
    private ArrayList<AddressBook_Bean_Data.DataBean.EmployeesBean> employeesBeanXXXES;
    private List<String> dataBeansName;
    private ArrayList<Integer> idList;

    private DepartmentAdapter departmentAdapter;
    private StaffAdapter staffAdapter;


    private int mID = 0;
    private String mTitles = "";
    private Navigation_Adapter navigationAdapter;
    private String titles;
    private LoadingDialog loadingDialog;

    @Override
    protected void initData() {
        initAddressData();
    }

    private void initAddressData() {

        String sign = MD5Util.encode("id=" + mID);

        RxHttpUtils.createApi(AddressBookApi.class)
                .getaddressbook(mID, sign)
                .compose(Transformer.<AddressBook_Bean_Data>switchSchedulers(loadingDialog))
                .subscribe(new CommonObserver<AddressBook_Bean_Data>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AddressBook_Bean_Data addressBookBeanData) {
                        if (addressBookBeanData.getCode() == 200) {
                            AddressBook_Bean_Data.DataBean data = addressBookBeanData.getData();
                            List<AddressBook_Bean_Data.DataBean.OrgChildrenBean> orgChildren = data.getOrgChildren();
                            List<AddressBook_Bean_Data.DataBean.EmployeesBean> employees = data.getEmployees();

                            title_tv.setText(data.getName());
                            for (int i = 0; i < idList.size(); i++) {
                                if (!idList.contains(data.getId()))
                                    idList.add(data.getId());
                            }
                            for (int i = 0; i < dataBeansName.size(); i++) {
                                if (!dataBeansName.contains(data.getName()))
                                    dataBeansName.add(data.getName());
                            }
                            navigationAdapter.notifyDataSetChanged();

                            if (orgChildren.size() > 0) {
                                department_rv.setVisibility(ViewGroup.VISIBLE);
                                orgChildrenBeanXXES.clear();
                                orgChildrenBeanXXES.addAll(orgChildren);
                                departmentAdapter.notifyDataSetChanged();
                            } else if (orgChildren.size() == 0) {
                                department_rv.setVisibility(ViewGroup.GONE);
                            }
                            if (employees.size() > 0) {
                                staff_rv.setVisibility(ViewGroup.VISIBLE);
                                employeesBeanXXXES.clear();
                                employeesBeanXXXES.addAll(employees);
                                staffAdapter.notifyDataSetChanged();
                            } else if (employees.size() == 0) {
                                staff_rv.setVisibility(ViewGroup.GONE);
                            }

                        } else {
                            ToastUtil.setToast(addressBookBeanData.getMsg());
                        }
                    }
                });

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        sousuo_rl.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        titles = intent.getStringExtra("titles");

        dataBeansName = new ArrayList<>();
        idList = new ArrayList<>();
        idList.add(0);
        dataBeansName.add(titles);

        mID = id;

        loadingDialog = new LoadingDialog(AddressBook_Two_Activity.this);

        /*
        * 导航数据适配器
        * */
        navigationAdapter = new Navigation_Adapter(dataBeansName);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressBook_Two_Activity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        navigation_rv.setLayoutManager(linearLayoutManager);
        navigation_rv.setAdapter(navigationAdapter);
        navigationAdapter.setOnItemLeftClckListener(new Navigation_Adapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(String s, int mPosition) {
                title_tv.setText(s);
                int i = dataBeansName.indexOf(s);
                if (i != 0) {
                    int integer = idList.get(i);
                    for (int j = 0; j < dataBeansName.size(); j++) {
                        if (j > i) {
                            dataBeansName.remove(j);
                            j--;
                        }
                    }
                    for (int y = 0; y < idList.size(); y++) {
                        if (y > i) {
                            idList.remove(y);
                            y--;
                        }
                    }
                    navigationAdapter.notifyDataSetChanged();

                    mID = integer;
                    initAddressData();
                } else {
                    fund();
                }
            }
        });


        //上面数据（分公司，部门）
        orgChildrenBeanXXES = new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(orgChildrenBeanXXES);
        department_rv.setLayoutManager(new LinearLayoutManager(AddressBook_Two_Activity.this));
        department_rv.setAdapter(departmentAdapter);


        //下面数据（员工）
        employeesBeanXXXES = new ArrayList<>();
        staffAdapter = new StaffAdapter(employeesBeanXXXES);
        staff_rv.setLayoutManager(new LinearLayoutManager(AddressBook_Two_Activity.this));
        staff_rv.setAdapter(staffAdapter);


        //监听适配器
        departmentAdapter.setOnItemLeftClckListener(new DepartmentAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AddressBook_Bean_Data.DataBean.OrgChildrenBean orgChildrenBean, int mPosition) {
                title_tv.setText(orgChildrenBean.getName());
                mID = orgChildrenBean.getId();
                initAddressData();
            }
        });

        staffAdapter.setOnItemLeftClckListener(new StaffAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AddressBook_Bean_Data.DataBean.EmployeesBean employeesBean, int mPosition) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + employeesBean.getMobTel());
                intent.setData(data);
                startActivity(intent);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addressbook_two;
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.sousuo_rl:
                intent = new Intent(AddressBook_Two_Activity.this, SearchAddressBookActivity.class);
                intent.putExtra("officetext", titles);
                startActivityForResult(intent, 250);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            initAddressData();
        }
    }
}
