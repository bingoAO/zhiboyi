package com.bao.hnbs.ui.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bao.hnbs.R;


public class NoticeDialog extends Dialog{

    public static NoticeDialog newInstanceShow(Context viewHolderContext,String msg){

        NoticeDialog dialog = new NoticeDialog(viewHolderContext, R.style.ThemeDialogCustom);
        dialog.setMsg(msg);
        dialog.show();

        return dialog;
    }


    public NoticeDialog(Context context, int layout_res_id) {
        super(context, layout_res_id);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_sure);
        findViewById(R.id.dialog_sure_cancel).setVisibility(View.GONE);
        findViewById(R.id.dialog_div).setVisibility(View.GONE);
        findViewById(R.id.dialog_sure_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setMsg(String msg) {
        ((TextView)findViewById(R.id.dialog_sure_content_text)).setText(msg);
    }



}
