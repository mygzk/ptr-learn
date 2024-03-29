package com.org.ptr.loadmore;


/**
 * Created by guozhk on 2017/6/17.
 */

public interface LoadMoreUIHandler {

    public static final int STATUS_LOADED_EMPTY = 1;
    public static final int STATUS_LOADED_NO_MORE = 1;

    public void onLoading(LoadMoreContainer container);

    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore);

    public void onWaitToLoadMore(LoadMoreContainer container);
}
