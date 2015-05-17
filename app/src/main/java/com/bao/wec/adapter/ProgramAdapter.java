package com.bao.wec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.utils.LogUtils;

import java.util.List;

import cn.bmob.v3.BmobObject;


public class ProgramAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<BmobObject> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器

    private int type;

    private final class ListItemView {                //自定义控件集合
        public TextView submit_btn;
    }


    /**
     * 实际操作时可以传入 某类BombObject，现在是死数据
     *
     * @param context
     * @param listItems
     */
    public ProgramAdapter(Context context, List<BmobObject> listItems ,int type) {
        this.context = context;
        listContainer = LayoutInflater.from(this.context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        this.type = type;
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    /**
     * ListView Item设置
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_program, null);
            //获取控件对象
            listItemView.submit_btn = (TextView) convertView.findViewById(R.id.item_program_submit_btn);
            //listItemView.check = (ImageView) convertView.findViewById(R.id.grid_item_tag_check);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }

        if(type == Constant.CODE.LIST_PROGRAM_PRIVATE){
            listItemView.submit_btn.setVisibility(View.VISIBLE);
        }else {
            listItemView.submit_btn.setVisibility(View.INVISIBLE);
        }

        listItemView.submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.toast(context,"submit position = " + position);
            }
        });

        return convertView;
    }

}
