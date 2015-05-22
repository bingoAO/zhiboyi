package com.bao.wec.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.bao.wec.listener.OnScroll2BottomListener;
import com.bao.wec.ui.customview.pulltorefresh.PullRefreshLayout;
import com.bao.wec.utils.LogUtils;


public abstract class BaseRefreshFragment extends BaseFragment {

    protected AbsListView mListView ;
    //设置是否可下拉
    protected boolean mEnablePull = true ;
    protected View mLayout;
    protected AQuery mAq;


    BaseAdapter mBaseAdapter ;

    PullRefreshLayout mRefreshLayout;

//
    int layout_id = 0,listview_id = 0,recycler_view_id = 0,refresh_layout_id = 0;
    int mRefreshStyle;

    public void setRefreshStyle(int refreshStyle) {
        this.mRefreshStyle = refreshStyle;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the mRefreshLayout for this fragment

        mActivity = (BaseActivity)getActivity();
        initRefreshView();

        mLayout = inflater.inflate(layout_id, container, false);
        mAq = new AQuery(mLayout);

        try {
            updateListData();
        }catch (Exception e){
            LogUtils.e(e.toString());
        }

        return mLayout;
    }

    /**
     * 初始化View
     */
    protected abstract void initRefreshView();

    /**
     * 初始化数据
     */
    protected abstract void updateListData();

    /**
     *刷新数据
     *成功后执行 setRefreshingComplete()
     */
    protected abstract void onRefreshData();

    /**
     *加载更多数据
     */
    protected abstract void onLoadMoreData();

    /**
     * 点击后回调
     */
    protected abstract void clickItem(int position,View view);


    public BaseAdapter getmBaseAdapter() {
        return mBaseAdapter;
    }

//这五个函数用来设置id

    public void setRefreshingComplete(){
        mRefreshLayout.setRefreshing(false);
    }

    public void setLayout_id(int layout_id) {
        this.layout_id = layout_id;
    }

    public void setListview_id(int listview_id) {
        this.listview_id = listview_id;
    }

    public void setRefresh_layout_id(int refresh_layout_id) {
        this.refresh_layout_id = refresh_layout_id;
    }

    public void setmEnablePull(boolean mEnablePull) {
        this.mEnablePull = mEnablePull;
    }
//

    public void setmBaseAdapter(BaseAdapter mBaseAdapter) {
        this.mBaseAdapter = mBaseAdapter;

        if(listview_id != 0){
            mListView = (AbsListView) mLayout.findViewById(listview_id);
            mListView.setAdapter(mBaseAdapter);
            mListView.setOnItemClickListener(itemListener);
        }

        mRefreshLayout = (PullRefreshLayout) mLayout.findViewById(refresh_layout_id);
        mRefreshLayout.setOnRefreshListener(onRefreshListener);
        mRefreshLayout.setOnScroll2BottomListener(onScroll2BottomListener);
        mRefreshLayout.setRefreshStyle(mRefreshStyle);
        if(!mEnablePull) mRefreshLayout.disablePullRefresh();

        //默认样式
        setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);

    }


    private AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(getActivity().getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
            clickItem(position,view);
        }
    };

    private PullRefreshLayout.OnRefreshListener onRefreshListener = new PullRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            onRefreshData();
            mRefreshLayout.setRefreshing(false);
//            mRefreshLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mRefreshLayout.setRefreshing(false);
//                }
//            }, 4000);

            //Toast.makeText(getActivity().getApplicationContext(), "refresh complete ", Toast.LENGTH_SHORT).show();
        }
    };


    private OnScroll2BottomListener onScroll2BottomListener = new OnScroll2BottomListener() {
        @Override
        public void onBottom() {
            onLoadMoreData();
        }
    };


}

