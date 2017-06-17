package com.org.ptr.loadmore;

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
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setFillAfter(true);
        setVisibility(INVISIBLE);
    }


    @Override
    public void onLoading(LoadMoreContainer container) {
        mTextView.setText("正在加载数据...");
        setVisibility(VISIBLE);
        mImgLoad.setAnimation(mAnimation);
        mImgLoad.setVisibility(VISIBLE);
        mAnimation.startNow();

    }

    @Override
    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);
            mAnimation.cancel();
            mImgLoad.clearAnimation();
            mImgLoad.invalidate();
            mImgLoad.setVisibility(GONE);
            if (empty) {
                mTextView.setText("没有加载到数据");
            } else {
                mTextView.setText("没有更多数据了");
            }
        } else {
            setVisibility(INVISIBLE);
        }


    }

    @Override
    public void onWaitToLoadMore(LoadMoreContainer container) {

        mTextView.setText("点击加载更多...");

        setVisibility(VISIBLE);
        mAnimation.cancel();
        mImgLoad.clearAnimation();
        mImgLoad.invalidate();
        mImgLoad.setVisibility(INVISIBLE);
    }
}
