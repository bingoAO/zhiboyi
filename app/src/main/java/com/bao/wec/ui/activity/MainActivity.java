package com.bao.wec.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.adapter.pager.TabPagerAdapter;
import com.bao.wec.listener.OnTabSelectedListener;
import com.bao.wec.ui.base.BaseActivity;
import com.bao.wec.ui.customview.PagerTabWidget;
import com.bao.wec.ui.customview.ScrollableViewPager;
import com.bao.wec.ui.fragment.TabHomeAndSbcFgm;
import com.bao.wec.ui.fragment.TabMeFgm;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    /**
     * 绑定viewpager和tab
     */
    private PagerTabWidget mTabWidget;
    private PagerAdapter mPagerAdapter;
    //设置viewpager是否允许滑动
    private ScrollableViewPager mViewPager;
    private AQuery aq;

    //三个fragment
    private TabHomeAndSbcFgm tabHomeFgm;
    private TabHomeAndSbcFgm tabSbcFgm;
    private TabMeFgm tabMeFgm;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        aq = new AQuery(this);

        //初始化Tab显示内容
        initTab();
    }


    /**
     * 初始化Tab的显示内容
     */
    private void initTab() {

        //pagertabwidget

        mTabWidget = (PagerTabWidget) findViewById(R.id.home_tabWidget);
        mTabWidget.setDividerInvisible();
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_home, null));
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_sbc, null));
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_me, null));

        mViewPager = (ScrollableViewPager) findViewById(R.id.main_viewPager);

        List<Fragment> fragments = new ArrayList<>();
//新建Fragment类，设置类型
        fragments.add(setTabHomeFgm(TabHomeAndSbcFgm.newInstance(TabHomeAndSbcFgm.FRAGMENT_HOME)));
        fragments.add(setTabSbcFgm(TabHomeAndSbcFgm.newInstance(TabHomeAndSbcFgm.FRAGMENT_SBC)));
        fragments.add(setTabMeFgm(TabMeFgm.newInstance()));
//        fragments.add(setTabMsgFgm(TabMsgFgm.newInstance()));
//        fragments.add(setTabMeFgm(TabOrderFgm.newInstance()));
//
        mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
//container
        mViewPager.setAdapter(mPagerAdapter);
        //设置ViewPager无法滑动交互
        mViewPager.setmScrollable(false);
        //预加载两页
        mViewPager.setOffscreenPageLimit(2);

        mTabWidget.setmViewPager(mViewPager);

        mTabWidget.setmOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(List<View> tabViews, int position) {
                //ShowToast("第" + position + "个Tab被选中");
                //实现换色功能
            switch (position){
                case 0:
                    aq.id(R.id.tab_icon_home_id).image(R.drawable.icon_tab_home_selected);
                    aq.id(R.id.tab_home_text).textColor(mResources.getColor(R.color.tab_selected));

                    aq.id(R.id.tab_icon_sbc_id).image(R.drawable.icon_tab_sbc_normal);
                    aq.id(R.id.tab_sbc_text).textColor(mResources.getColor(R.color.tab_normal));
                    aq.id(R.id.tab_icon_me_id).image(R.drawable.icon_tab_me_normal);
                    aq.id(R.id.tab_me_text).textColor(mResources.getColor(R.color.tab_normal));
                    break;
                case 1:
                    aq.id(R.id.tab_icon_sbc_id).image(R.drawable.icon_tab_sbc_selected);
                    aq.id(R.id.tab_sbc_text).textColor(mResources.getColor(R.color.tab_selected));

                    aq.id(R.id.tab_icon_home_id).image(R.drawable.icon_tab_home_normal);
                    aq.id(R.id.tab_home_text).textColor(mResources.getColor(R.color.tab_normal));
                    aq.id(R.id.tab_icon_me_id).image(R.drawable.icon_tab_me_normal);
                    aq.id(R.id.tab_me_text).textColor(mResources.getColor(R.color.tab_normal));
                    break;
                case 2:
                    aq.id(R.id.tab_icon_me_id).image(R.drawable.icon_tab_me_selected);
                    aq.id(R.id.tab_me_text).textColor(mResources.getColor(R.color.tab_selected));

                    aq.id(R.id.tab_icon_sbc_id).image(R.drawable.icon_tab_sbc_normal);
                    aq.id(R.id.tab_sbc_text).textColor(mResources.getColor(R.color.tab_normal));
                    aq.id(R.id.tab_icon_home_id).image(R.drawable.icon_tab_home_normal);
                    aq.id(R.id.tab_home_text).textColor(mResources.getColor(R.color.tab_normal));
                    break;

            }
            }
        });

    }

    public TabHomeAndSbcFgm getTabHomeFgm() {
        return tabHomeFgm;
    }

    public TabHomeAndSbcFgm setTabHomeFgm(TabHomeAndSbcFgm tabHomeFgm) {
        this.tabHomeFgm = tabHomeFgm;
        return tabHomeFgm;
    }

    public TabHomeAndSbcFgm getTabSbcFgm() {
        return tabSbcFgm;
    }

    public TabHomeAndSbcFgm setTabSbcFgm(TabHomeAndSbcFgm tabSbcFgm) {
        this.tabSbcFgm = tabSbcFgm;
        return tabSbcFgm;
    }

    public TabMeFgm getTabMeFgm() {
        return tabMeFgm;
    }

    public TabMeFgm setTabMeFgm(TabMeFgm tabMeFgm) {
        this.tabMeFgm = tabMeFgm;
        return tabMeFgm;
    }

    /**
     * 再按一次退出程序
     */
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                mMyApplication.exit();
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
