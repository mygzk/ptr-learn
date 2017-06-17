package com.org.ptr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.org.ptr.R;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;

/**
 * Created by guozhk on 2017/6/17.
 */

public class LoadMoreFooterView extends RelativeLayout implements LoadMoreUIHandler {
    private String TAG = LoadMoreFooterView.class.getSimpleName();

    private TextView mTextView;
    private ImageView mImgLoad;
    private RotateAnimation mAnimation;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.load_more_footer, this);
        mTextView = (TextView) findViewById(R.id.load_more_list_footer_tv);
        mImgLoad = (ImageView) findViewById(R.id.load_more_list_footer_img);
        mAnimation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setDuration(1000);//设置动画持续时间
        mAnimation.setRepeatCount(Animation.INFINITE);//设置重复次数
        mAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        // mAnimation.setStartOffset(0);//执行前的等待时间
        setVisibility(INVISIBLE);
    }


    @Override
    public void onLoading(LoadMoreContainer container) {
        Log.e(TAG, "onLoading:---------");
        mTextView.setText("正在加载数据...");
        setVisibility(VISIBLE);
        mImgLoad.setAnimation(mAnimation);
        mImgLoad.setVisibility(VISIBLE);
        mAnimation.startNow();

    }

    @Override
    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
        Log.e(TAG, "onLoadFinish:---------");
        mAnimation.cancel();
        mTextView.setText("点击加载数据");
        setVisibility(INVISIBLE);
        mImgLoad.clearAnimation();
        mImgLoad.invalidate();
        mImgLoad.setVisibility(GONE);
    }

    @Override
    public void onWaitToLoadMore(LoadMoreContainer container) {
        mAnimation.cancel();
        Log.e(TAG, "onWaitToLoadMore:---------");
        mTextView.setText("点击加载更多...");

        setVisibility(INVISIBLE);
        mImgLoad.clearAnimation();
        mImgLoad.invalidate();
        mImgLoad.setVisibility(INVISIBLE);
    }
}
