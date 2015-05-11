package com.bao.hnbs.ui.base;

import android.os.Bundle;

/**
 * @author kingofglory
 *         email: kingofglory@yeah.net
 *         blog:  http:www.google.com
 * @date 2014-2-21
 * TODO 
 */

public abstract class BasePageActivity extends BaseActivity {
    protected Bundle mBundle;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
        mBundle = getIntent().getExtras();
		setLayoutView();
		init(bundle);
	}
	private void init(Bundle bundle) {
		findViews();
		setupViews(bundle);
		setListener();
		fetchData();
	}


	protected abstract void setLayoutView();
	
	protected abstract void findViews();

	protected abstract void setupViews(Bundle bundle);

	protected abstract void setListener();

	protected abstract void fetchData();

	
}
