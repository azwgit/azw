package com.example.bq.edmp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.addressbook.activity.AddressBook_Two_Activity;
import com.example.bq.edmp.addressbook.activity.SearchAddressBookActivity;
import com.example.bq.edmp.addressbook.adapter.DepartmentAdapter;
import com.example.bq.edmp.addressbook.adapter.StaffAdapter;
import com.example.bq.edmp.addressbook.api.AddressBookApi;
import com.example.bq.edmp.addressbook.bean.AddressBook_Bean_Data;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 通讯录
 */
public class AddressBookFragment extends BaseFragment {

    @BindView(R.id.sousuo_rl)
    RelativeLayout sousuo_rl;
    @BindView(R.id.head_office_tv)
    TextView head_office_tv;
    @BindView(R.id.department_rv)
    RecyclerView department_rv;
    @BindView(R.id.staff_rv)
    RecyclerView staff_rv;

    private ArrayList<AddressBook_Bean_Data.DataBean.OrgChildrenBean> orgChildrenBeanXXES;
    private ArrayList<AddressBook_Bean_Data.DataBean.EmployeesBean> employeesBeanXXXES;
    private DepartmentAdapter departmentAdapter;
    private StaffAdapter staffAdapter;
    private int mID = 0;
    private AddressBook_Bean_Data.DataBean data;

    public AddressBookFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_addressbook;
    }

    @Override
    protected void initView() {
        orgChildrenBeanXXES = new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(orgChildrenBeanXXES);
        department_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        department_rv.setAdapter(departmentAdapter);

        employeesBeanXXXES = new ArrayList<>();
        staffAdapter = new StaffAdapter(employeesBeanXXXES);
        staff_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        staff_rv.setAdapter(staffAdapter);


        //监听适配器
        departmentAdapter.setOnItemLeftClckListener(new DepartmentAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AddressBook_Bean_Data.DataBean.OrgChildrenBean orgChildrenBean, int mPosition) {
                Intent intent = new Intent(getActivity(), AddressBook_Two_Activity.class);
                intent.putExtra("id", orgChildrenBean.getId());
                intent.putExtra("titles", data.getName());
                startActivityForResult(intent, 250);

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
    protected void initListener() {
        sousuo_rl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initAddressData();
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.sousuo_rl:
                intent = new Intent(getActivity(), SearchAddressBookActivity.class);
                intent.putExtra("officetext", data.getName());
                startActivityForResult(intent, 250);
                break;
        }
    }

    private void initAddressData() {

        String sign = MD5Util.encode("id=" + mID);

        RxHttpUtils.createApi(AddressBookApi.class)
                .getaddressbook(mID, sign)
                .compose(Transformer.<AddressBook_Bean_Data>switchSchedulers())
                .subscribe(new CommonObserver<AddressBook_Bean_Data>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AddressBook_Bean_Data addressBookBeanData) {
                        if (addressBookBeanData.getCode() == 200) {
                            data = addressBookBeanData.getData();
                            List<AddressBook_Bean_Data.DataBean.OrgChildrenBean> orgChildren = data.getOrgChildren();
                            List<AddressBook_Bean_Data.DataBean.EmployeesBean> employees = data.getEmployees();

                            //总公司名称
                            if (data.getName() != null && !data.getName().equals("")) {
                                head_office_tv.setText(data.getName());
                            }

                            if (orgChildren.size() > 0) {
                                orgChildrenBeanXXES.clear();
                                orgChildrenBeanXXES.addAll(orgChildren);
                                departmentAdapter.notifyDataSetChanged();
                            }
                            if (employees.size() > 0) {
                                employeesBeanXXXES.clear();
                                employeesBeanXXXES.addAll(employees);
                                staffAdapter.notifyDataSetChanged();
                            }

                        } else {
                            ToastUtil.setToast(addressBookBeanData.getMsg());
                        }
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            initAddressData();
        }
    }
}
