package com.bao.wec.ui.activity;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;
import com.bao.wec.ui.fragment.ListFgm;

public class ListFgmActivity extends BasePageActivity{
    AQuery aq;
    ListFgm listFgm;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_memberlist);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text(mBundle.getString(Constant.KeyValue.TITLE_KEY, "标题"));
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");

        listFgm = ListFgm.newInstance(mBundle.getInt(Constant.KeyValue.TYPE_KEY,Constant.CODE.LIST_HOME_ROOM));
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFgm).commit();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void fetchData() {

    }


    public void aq_back(){
        finish();
    }


}
