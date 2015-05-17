package com.bao.wec.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity ;
    protected Bundle mBundle ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity)getActivity();
        mBundle = getArguments();
    }

    Toast mToast;
    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity().getApplicationContext(), text,
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

