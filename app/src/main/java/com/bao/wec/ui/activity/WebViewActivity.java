package com.bao.wec.ui.activity;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;
import com.bao.wec.ui.customview.MyWebView;

public class WebViewActivity extends BasePageActivity {
    AQuery aq;
    String url;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_webview);
        aq = new AQuery(this);
        url = mBundle.getString(Constant.KeyValue.DATA_KEY);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("以往节目");
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void fetchData() {
        MyWebView webView = (MyWebView) aq.id(R.id.webview).getView();
        webView.loadUrl(url);
    }

    public void aq_back(){
        finish();
    }


}
