package com.bao.wec.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.adapter.MemberAdapter;
import com.bao.wec.adapter.ProgramAdapter;
import com.bao.wec.adapter.RoomAdapter;
import com.bao.wec.app.Constant;
import com.bao.wec.entity.User;
import com.bao.wec.ui.activity.RoomActivity;
import com.bao.wec.ui.activity.WebViewActivity;
import com.bao.wec.ui.base.BaseActivity;
import com.bao.wec.ui.base.BaseRefreshFragment;
import com.bao.wec.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;


public class ListFgm extends BaseRefreshFragment {
    private List<BmobObject> items = new ArrayList<>();


    boolean isLoading = false;
    //    //默认为商品列表
    int type;

    public static ListFgm newInstance(int type) {
        ListFgm fragment = new ListFgm();
        fragment.setType(type);
        return fragment;
    }

    public void clearListAndReload() {
        items.clear();
        showLoadingBar();
        updateListData();
    }


    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void initRefreshView() {

        setLayout_id(R.layout.fragment_pull_list);
        setRefresh_layout_id(R.id.pulltorefresh);
        setListview_id(R.id.listview);

        setmEnablePull(true);

    }


    @Override
    protected void updateListData() {

        if (!isLoading) {
            isLoading = true;
            switch (type) {
                case Constant.CODE.LIST_HOME_ROOM:
                    initHomeRoomList();
                    break;
                case Constant.CODE.LIST_SBC_ROOM:
                    initSbcRoomList();
                    break;
                case Constant.CODE.LIST_MEMBER:
                    initMemberList();
                    break;
                case Constant.CODE.LIST_PROGRAM_PRIVATE:
                case Constant.CODE.LIST_PROGRAM_PUBLIC:
                    initProgramList();
                    break;
            }

        }
    }

    private void initProgramList() {
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());

        hideLoadingBarAndCheckResult();

        if (getmBaseAdapter() == null) {
            setmBaseAdapter(new ProgramAdapter(getActivity(), items, type));
        } else {
            getmBaseAdapter().notifyDataSetChanged();
        }
    }

    private void initHomeRoomList() {
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());

        hideLoadingBarAndCheckResult();

        if (getmBaseAdapter() == null) {
            setmBaseAdapter(new RoomAdapter(getActivity(), items));
        } else {
            getmBaseAdapter().notifyDataSetChanged();
        }

    }


    private void initSbcRoomList() {
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());

        hideLoadingBarAndCheckResult();

        if (getmBaseAdapter() == null) {
            setmBaseAdapter(new RoomAdapter(getActivity(), items));
        } else {
            getmBaseAdapter().notifyDataSetChanged();
        }

    }


    private void initMemberList() {
        setmEnablePull(false);

        items.add(new User().setType(2));
        items.add(new User().setType(1));
        items.add(new User().setType(1));
        items.add(new User().setType(1));
        items.add(new User().setType(0));
        items.add(new User().setType(0));
        items.add(new User().setType(0));

        hideLoadingBarAndCheckResult();

        if (getmBaseAdapter() == null) {
            setmBaseAdapter(new MemberAdapter(getActivity(), items));
        } else {
            getmBaseAdapter().notifyDataSetChanged();
        }

    }

    @Override
    protected void onRefreshData() {
        clearListAndReload();
    }

    @Override
    protected void onLoadMoreData() {
        //LogUtils.i("onLoadMoreData");
        try {
            updateListData();
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }

    @Override
    protected void clickItem(int position, View view) {
        Bundle bundle = new Bundle();

        //LogUtils.toast(getActivity(),"click item position = " + position);

        switch (type) {
            case Constant.CODE.LIST_HOME_ROOM:
                ShowToast("click LIST_HOME_ROOM ->" + position);
                mActivity.redirectToActivity(mActivity, RoomActivity.class);
//                bundle.putSerializable(Constant.KeyValue.DATA_KEY,items.get(position));
//                mActivity.redirectToActivity(mActivity, ServantActivity.class ,bundle);
                break;
            case Constant.CODE.LIST_SBC_ROOM:
                ShowToast("click LIST_SBC_ROOM ->" + position);
                mActivity.redirectToActivity(mActivity, RoomActivity.class);
//                bundle.putSerializable(Constant.KeyValue.DATA_KEY,items.get(position));
//                mActivity.redirectToActivity(mActivity, ServantActivity.class ,bundle);
                break;
            case Constant.CODE.LIST_PROGRAM_PRIVATE:
            case Constant.CODE.LIST_PROGRAM_PUBLIC:
                bundle.putString(Constant.KeyValue.DATA_KEY,"http://www.bing.com");
                mActivity.redirectToActivity(mActivity,WebViewActivity.class,bundle);
                break;
        }

    }


    private void onErrorDo(String string) {
        isLoading = false;
        LogUtils.toastError(getActivity(), string);
        hideLoadingBarAndCheckResult();
    }

    /**
     * 隐藏加载条 并 检查空提示
     */

    private void hideLoadingBarAndCheckResult() {
        try {
            if (items.size() != 0) {
                new AQuery(getView()).id(R.id.empty_tips).gone();
                ((BaseActivity) getActivity()).hideEmptyTips();
            } else {
                new AQuery(getView()).id(R.id.empty_tips).visible();
                ((BaseActivity) getActivity()).showEmptyTips();
            }
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        mAq.id(R.id.loading).gone();
        isLoading = false;

    }

    /**
     * 隐藏加载条 并 检查空提示
     */

    private void showLoadingBar() {
        mAq.id(R.id.loading).visible();
    }

}
