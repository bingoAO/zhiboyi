package com.bao.wec.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.activity.RoomCreateActivity;
import com.bao.wec.ui.base.BaseFragment;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class TabHomeAndSbcFgm extends BaseFragment {
    AQuery aq;
    ListFgm listFgm;
    Dialog enterDialog;

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_SBC = 1;
    int type = 0;


    public static TabHomeAndSbcFgm newInstance(int type) {
        TabHomeAndSbcFgm fragment = new TabHomeAndSbcFgm();
        fragment.setType(type);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        aq = new AQuery(view);

        initBtns();
        if(type == FRAGMENT_HOME){
            initHomeViews();
        }else if(type == FRAGMENT_SBC){
            initSbcViews();
        }

        return view;
    }


    private void initBtns(){
        aq.id(R.id.cur_room_finish_btn).clicked(this,"aq_finish");
        aq.id(R.id.cur_room_rl).clicked(this,"aq_cur_room");
    }

    private void initHomeViews(){
        aq.id(R.id.btn_right_img).visible().margin(5,5,5,5).image(R.drawable.icon_add).clicked(this,"aq_add");
        aq.id(R.id.title_bar_name).text("首页");
        listFgm = ListFgm.newInstance(Constant.CODE.LIST_HOME_ROOM);
        getChildFragmentManager().beginTransaction().add(R.id.fragment_container, listFgm).commit();

    }

    private void initSbcViews(){
        aq.id(R.id.title_bar_name).text("订阅");
        listFgm = ListFgm.newInstance(Constant.CODE.LIST_SBC_ROOM);
        getChildFragmentManager().beginTransaction().add(R.id.fragment_container, listFgm).commit();

    }

    public void aq_add(){
        showPopAddWindow();
    }

    public void aq_finish(){
        ShowToast("aq_finish!");
    }

    public void aq_cur_room(){
        ShowToast("aq_cur_room!");
    }

    private void showPopAddWindow(){
        View popAddView = getActivity().getLayoutInflater().inflate(
                R.layout.pop_add, null);
        final PopupWindow popupWindow = new PopupWindow(popAddView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(aq.getView(), Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM, 0, 0);
        popupWindow.update();
        //显示动画
        YoYo.with(Techniques.BounceInDown).duration(400).playOn(popAddView);

        // 设置取消点击事件
        View pop_outside =  popAddView
                .findViewById(R.id.pop_add_outside);
        pop_outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShowToast("pop_outside");
                popupWindow.dismiss();
            }
        });


        View pop_enter =  popAddView
                .findViewById(R.id.pop_add_enter_rl);
        pop_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShowToast("pop_enter");
                showEnterDialog();
                popupWindow.dismiss();
            }
        });

        // 与她联系
        View pop_create = popAddView
                .findViewById(R.id.pop_add_create_rl);
        pop_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShowToast("pop_create");

                mActivity.redirectToActivity(mActivity, RoomCreateActivity.class);
                popupWindow.dismiss();
            }
        });

    }

    private void showEnterDialog(){
        View outerView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_enter, null);
        enterDialog = new AlertDialog.Builder(mActivity)
                .setView(outerView)
                .show();
        final AQuery aQuery = new AQuery(outerView);
        aQuery.id(R.id.dialog_sure_ok).getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowToast(aQuery.id(R.id.dialog_enter_et).getText().toString());
                enterDialog.dismiss();
            }
        });

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
