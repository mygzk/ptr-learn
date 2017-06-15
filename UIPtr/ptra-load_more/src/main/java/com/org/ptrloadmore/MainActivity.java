package com.org.ptrloadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.org.ptrloadmore.adapter.HomeAdapter;
import com.org.ptrloadmore.bean.HomeBean;
import com.org.ptrloadmore.util.LocalDisplay;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MainActivity extends AppCompatActivity {


    private ListView lsHome;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        lsHome = (ListView) findViewById(R.id.pt_home_ls);
        initdata();

        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.fragment_ptr_home_ptr_frame);


     /*   StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("Ultra PTR");*/
        StoreHouseHeader Sheader = new StoreHouseHeader(this);
        Sheader.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        Sheader.initWithString("Ultra PTR");
        ptrFrameLayout.setFooterView(Sheader);
        ptrFrameLayout.addPtrUIHandler(Sheader);


        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });

    }

    private void initdata() {
        List<HomeBean> datas = new ArrayList<>();
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        adapter = new HomeAdapter(this, datas);
        lsHome.setAdapter(adapter);

        lsHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toSkip(position);
            }
        });
    }
}
