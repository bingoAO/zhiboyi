package com.bao.wec.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.ui.activity.MeMdfActivity;
import com.bao.wec.ui.activity.SettingsActivity;
import com.bao.wec.ui.base.BaseFragment;


public class TabMeFgm extends BaseFragment {
    AQuery aq;


    public static TabMeFgm newInstance() {
        TabMeFgm fragment = new TabMeFgm();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        aq = new AQuery(view);

        aq.id(R.id.title_bar_name).text("个人中心");

        initBtns();

        return view;
    }


    private void initBtns(){
        aq.id(R.id.me_mdf_btn).clicked(this,"aq_go_mdf");
        aq.id(R.id.me_settings_btn).clicked(this,"aq_go_settings");
    }


    public void aq_go_mdf(){
        mActivity.redirectToActivity(mActivity, MeMdfActivity.class);
    }

    public void aq_go_settings(){
        mActivity.redirectToActivity(mActivity, SettingsActivity.class);
    }


}
