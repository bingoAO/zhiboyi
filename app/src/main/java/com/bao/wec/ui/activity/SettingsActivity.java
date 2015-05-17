package com.bao.wec.ui.activity;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.ui.base.BasePageActivity;

public class SettingsActivity extends BasePageActivity {
    AQuery aq;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_settings);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("应用设置");
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");

    }

    @Override
    protected void setListener() {
        aq.id(R.id.settings_autofinish_btn).clicked(this,"aq_autofinish");
        aq.id(R.id.settings_aboutus_btn).clicked(this,"aq_aboutus");
        aq.id(R.id.settings_logout).clicked(this,"aq_logout");

    }

    @Override
    protected void fetchData() {

    }


    public void aq_back(){
        finish();
    }

    public void aq_autofinish(){
        redirectToActivity(this,SetAutoFnshActivity.class);
    }

    public void aq_aboutus(){
        ShowToast("aq_aboutus");
    }

    public void aq_logout(){
        ShowToast("aq_logout");
    }

}
