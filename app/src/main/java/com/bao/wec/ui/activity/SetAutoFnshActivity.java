package com.bao.wec.ui.activity;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.ui.base.BasePageActivity;

public class SetAutoFnshActivity extends BasePageActivity {
    AQuery aq;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_settings_autofinish);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("自动退出频道");
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");

    }

    @Override
    protected void setListener() {
        aq.id(R.id.settings_autofinish_never_btn).clicked(this,"aq_never");
        aq.id(R.id.settings_autofinish_5mins_btn).clicked(this,"aq_5mins");

    }

    @Override
    protected void fetchData() {

    }


    public void aq_back(){
        finish();
    }

    public void aq_never(){
        aq.id(R.id.settings_autofinish_5mins_image).invisible();
        aq.id(R.id.settings_autofinish_never_image).visible();
    }


    public void aq_5mins(){
        aq.id(R.id.settings_autofinish_5mins_image).visible();
        aq.id(R.id.settings_autofinish_never_image).invisible();
    }

}
