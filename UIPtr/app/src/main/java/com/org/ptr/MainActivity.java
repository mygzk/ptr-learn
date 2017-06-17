package com.org.ptr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.org.ptr.adapter.HomeAdapter;
import com.org.ptr.bean.HomeBean;
import com.org.ptr.widget.LoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends Activity {

    private ListView lsHome;
    private HomeAdapter adapter;
    private LoadMoreListViewContainer mLoadMoreListViewContainer;
    private List<HomeBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        lsHome = (ListView) findViewById(R.id.pt_home_ls);
        datas = new ArrayList<>();
        adapter = new HomeAdapter(this, datas);
        lsHome.setAdapter(adapter);
        initdata(true);

        lsHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toSkip(position);
            }
        });
        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.fragment_ptr_home_ptr_frame);


     /*   StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("Ultra PTR");*/

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
                        initdata(true);
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });


        mLoadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        mLoadMoreListViewContainer.setAutoLoadMore(true);//设置是否自动加载更多
        // mLoadMoreListViewContainer.useDefaultHeader();
        LoadMoreFooterView loadMoreView = new LoadMoreFooterView(this);
        mLoadMoreListViewContainer.setLoadMoreView(loadMoreView);
        mLoadMoreListViewContainer.setLoadMoreUIHandler(loadMoreView);
        //5.添加加载更多的事件监听
        mLoadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                //模拟加载更多的业务处理
                mLoadMoreListViewContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       /* start++;
                        mockStrList.addAll(getMockData(start * 10, count));
                        if (start * 10 > 30) {
//                          mLoadMoreListViewContainer.loadMoreFinish(true, false);
                            //以下是加载失败的情节
                            int errorCode = 0;
                            String errormessage = "加载失败，点击加载更多";
                            mLoadMoreListViewContainer.loadMoreError(errorCode, errorMessage);
                        } else {
                            mLoadMoreListViewContainer.loadMoreFinish(false, true);
                        }
                        adapter.notifyDataSetChanged();*/
                        initdata(false);
                        mLoadMoreListViewContainer.loadMoreFinish(false, true);
                    }
                }, 3000);
            }
        });

    }

    private void initdata(boolean update) {
        ArrayList datas = new ArrayList<>();
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        if (update) {
            adapter.updateData(datas);
        } else {
            adapter.addData(datas);
        }
       /* datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));
        datas.add(new HomeBean(GrideActivity.class, "grideView"));*/

    }
}
