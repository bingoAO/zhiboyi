package com.bao.wec.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.app.MyApplication;
import com.bao.wec.utils.SpUtils;


/**
 * 用于监视Shared Preference的变化
 * OnSharedPreferenceChangeListener
 *继承接口
 * registerOnSharedPreferenceChangeListener
 * 注册
 * onSharedPreferenceChanged
 * 事件处理器
 */

public class BaseActivity extends FragmentActivity implements OnSharedPreferenceChangeListener{

    protected static String mTAG ;


    protected MyApplication mMyApplication;
    protected SpUtils mSpUtils;
    protected Resources mResources;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle bundle) {
        // TODO Auto-generated method stub
        super.onCreate(bundle);
        mTAG = this.getClass().getSimpleName();
        initConfigure();
        //PushAgent.getInstance(mContext).onAppStart();

    }


    private void initConfigure() {
        mContext = this;
        if(null == mMyApplication){
            mMyApplication = MyApplication.getInstance();
        }
        mMyApplication.addActivity(this);
        if(null == mSpUtils){
            mSpUtils = new SpUtils(this, Constant.Sp.PRE_NAME);
        }
        mSpUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
        mResources = getResources();

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        // TODO Auto-generated method stub
        //可用于监听设置参数，然后作出响应
    }

    /**
     * Activity跳转
     * @param context
     * @param targetActivity
     * @param bundle
     */
    public void redirectToActivity(Context context, Class<?> targetActivity, Bundle bundle){
        Intent intent = new Intent(context, targetActivity);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Activity跳转
     * @param context
     * @param targetActivity
     */
    public void redirectToActivity(Context context, Class<?> targetActivity){
        Intent intent = new Intent(context, targetActivity);
        startActivity(intent);
    }

    /**
     * 加载完毕隐藏progressbar
     */
    public void hideLoadingBar(){
        new AQuery(this).id(R.id.loading).gone();
    }

    public void showLoadingBar(){
        new AQuery(this).id(R.id.loading).visible();
    }

    /**
     * 有结果隐藏空提示
     */
    public void hideEmptyTips(){
        new AQuery(this).id(R.id.empty_tips).gone();
    }

    /**
     * 无结果显示空提示
     */
    public void showEmptyTips(){
        new AQuery(this).id(R.id.empty_tips).visible();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    Toast mToast;
    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

}
