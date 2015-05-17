package com.bao.wec.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.adapter.ProgramAdapter;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;
import com.bao.wec.utils.OtherUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

public class RoomActivity extends BasePageActivity implements AdapterView.OnItemClickListener{
    AQuery aq;
    List<BmobObject> items = new ArrayList<>();


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_room);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("频道");
        aq.id(R.id.btn_back).visible().clicked(this, "aq_back");
        aq.id(R.id.btn_right_img).visible().image(R.drawable.icon_menu).margin(5,5,5,5).clicked(this, "aq_go_room_profile");

    }

    @Override
    protected void setListener() {
        aq.id(R.id.layout_room_sbc).clicked(this,"aq_sbc");
        aq.id(R.id.layout_room_profile_ll_btn).clicked(this,"aq_go_room_profile");
    }

    @Override
    protected void fetchData() {
        initProgramList();

    }


    public void aq_back(){
        finish();
    }
    public void aq_sbc(){
        ShowToast("aq_sbc");
    }

    public void aq_go_room_profile(){

        redirectToActivity(this,RoomProfileActivity.class);

    }


    private void initProgramList(){
        ListView listView = aq.id(R.id.listview).getListView();
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        items.add(new BmobObject());
        listView.setAdapter(new ProgramAdapter(this, items , Constant.CODE.LIST_PROGRAM_PUBLIC));
        listView.setOnItemClickListener(this);
        OtherUtils.setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString(Constant.KeyValue.DATA_KEY,"http://www.bing.com");
        redirectToActivity(this,WebViewActivity.class,bundle);

    }
}
