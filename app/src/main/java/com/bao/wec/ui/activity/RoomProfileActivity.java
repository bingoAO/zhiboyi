package com.bao.wec.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;

public class RoomProfileActivity extends BasePageActivity{
    AQuery aq;
    int userType;
    int haveSbc;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_room_profile);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("频道信息");
        aq.id(R.id.btn_back).visible().clicked(this, "aq_back");

    }

    @Override
    protected void setListener() {
        aq.id(R.id.layout_room_sbc).gone();
        aq.id(R.id.room_profile_member_btn).clicked(this,"aq_member");



        //TODO 用户类型
        userType = (int)Math.round(Math.random()*2);

        if(userType == Constant.CODE.USER_NORMAL){
            //如果是普通用户
            aq.id(R.id.room_profile_program_btn).gone();
            aq.id(R.id.room_profile_addr_btn).clicked(this,"aq_share");
        }else {
            //如果是群主或管理员
            aq.id(R.id.room_profile_bottom_btn).gone();
            aq.id(R.id.btn_right_img).visible().image(R.drawable.icon_more).clicked(this, "aq_menu");
            aq.id(R.id.room_profile_addr_btn).clicked(this,"aq_menu");
            aq.id(R.id.room_profile_program_btn).clicked(this,"aq_program");
        }

        //TODO 是否订阅   注：管理员与群主默认订阅
        haveSbc = (int)Math.round(Math.random());
        if(userType != Constant.CODE.USER_NORMAL) haveSbc = 1;

        if(haveSbc == 0){
            //如果未订阅
            aq.id(R.id.btn_right_img).invisible();
            aq.id(R.id.room_profile_bottom_btn).visible().clicked(this,"aq_sbc");

        }else {
            //如果已订阅
            aq.id(R.id.btn_right_img).visible().image(R.drawable.icon_more).clicked(this, "aq_menu");
            aq.id(R.id.room_profile_bottom_btn).gone();

        }


        ShowToast("用户类型 " + userType   + "是否订阅 " + haveSbc);


    }

    @Override
    protected void fetchData() {


    }


    public void aq_back(){
        finish();
    }

    public void aq_member(){
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KeyValue.TITLE_KEY,"成员");
        bundle.putInt(Constant.KeyValue.TYPE_KEY, Constant.CODE.LIST_MEMBER);
        redirectToActivity(this, ListFgmActivity.class ,bundle);
    }

    public void aq_menu(){
        ShowToast("aq_menu");
        showMenu();
    }
    public void aq_program(){
        redirectToActivity(this, ProgramActivity.class);
    }

    public void aq_share(){
        ShowToast("aq_share");
    }
    public void aq_sbc(){
        ShowToast("aq_sbc");
    }


    /**
     * 显示选择图片对话框
     */
    protected void showMenu() {
        View addImageView = getLayoutInflater().inflate(
                R.layout.dialog_menu, null);

        switch (userType){
            case Constant.CODE.USER_NORMAL:
                //普通用户
                addImageView.findViewById(R.id.menu_delete).setVisibility(View.GONE);
                addImageView.findViewById(R.id.menu_edit).setVisibility(View.GONE);
                break;
            case Constant.CODE.USER_MANAGER:
                //管理员
                addImageView.findViewById(R.id.menu_delete).setVisibility(View.GONE);
                break;
            case Constant.CODE.USER_ADMIN:
                //群主
                addImageView.findViewById(R.id.menu_cancel_sbc).setVisibility(View.GONE);
                break;
        }


        final PopupWindow popupWindow = new PopupWindow(addImageView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.AnimationPopup);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(aq.getView(), Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM, 0, 0);
        popupWindow.update();


        // 设置分享点击事件
        TextView menu_share = (TextView) addImageView
                .findViewById(R.id.menu_share);
        menu_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                aq_share();
            }
        });

        // 设置编辑点击事件
        TextView menu_edit = (TextView) addImageView
                .findViewById(R.id.menu_edit);
        menu_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                redirectToActivity(mContext,RoomMdfActivity.class);
            }
        });

        // 设置取消订阅点击事件
        TextView menu_cancel_sbc = (TextView) addImageView
                .findViewById(R.id.menu_cancel_sbc);
        menu_cancel_sbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ShowToast("menu_cancel_sbc");
            }
        });

        // 设置注销频道点击事件
        TextView menu_delete = (TextView) addImageView
                .findViewById(R.id.menu_delete);
        menu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ShowToast("menu_delete");
            }
        });

        // 设置注销频道点击事件
        TextView menu_cancel = (TextView) addImageView
                .findViewById(R.id.menu_cancel);
        menu_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
}
