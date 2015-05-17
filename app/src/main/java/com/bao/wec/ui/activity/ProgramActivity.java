package com.bao.wec.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.adapter.pager.TabPagerAdapter;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;
import com.bao.wec.ui.customview.TwoPagerTabWidget;
import com.bao.wec.ui.fragment.ListFgm;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends BasePageActivity {
    AQuery aq;
    ViewPager mViewPager;
    TwoPagerTabWidget mTabWidget;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_program);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("节目单");
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void fetchData() {
        initTab();
    }


    public void aq_back(){
        finish();
    }

    /**
     * 初始化Tab的显示内容
     */
    public void initTab() {


        mTabWidget = (TwoPagerTabWidget) aq.id(R.id.topTab).getView();
        mTabWidget.setText_first_tab("未发布");
        mTabWidget.setText_second_tab("已发布");

        mViewPager = (ViewPager) aq.id(R.id.viewPager).getView();
        //mViewPager.setPageTransformer(true,new DepthPageTransformer());

        fragments.add(ListFgm.newInstance(Constant.CODE.LIST_PROGRAM_PRIVATE));
        fragments.add(ListFgm.newInstance(Constant.CODE.LIST_PROGRAM_PUBLIC));
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragments));

        mTabWidget.setmViewPager(mViewPager);
    }
}
