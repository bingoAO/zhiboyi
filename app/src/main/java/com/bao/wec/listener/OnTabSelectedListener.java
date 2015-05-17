package com.bao.wec.listener;

import android.view.View;

import java.util.List;

/**
 * 该监听器用于监听选中Tab时View的变化
 */
public interface OnTabSelectedListener {

    void onSelected(List<View> tabViews, int position);

}
