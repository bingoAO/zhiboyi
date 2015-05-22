package com.bao.wec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bao.wec.R;

import java.util.List;

import cn.bmob.v3.BmobObject;


public class RoomAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<BmobObject> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器

    private final class ListItemView {                //自定义控件集合
        public TextView tag;
        public ImageView check;
    }


    /**
     * 实际操作时可以传入 某类BombObject，现在是死数据
     *
     * @param context
     * @param listItems
     */
    public RoomAdapter(Context context, List<BmobObject> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(this.context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    //为什么return0呢
    public long getItemId(int arg0) {
        return 0;
    }

    /**
     * ListView Item设置
     * 终于找到了
     */
    //视图的内容在哪里初始化，本来布局就已经初始化好了的，现在还不能动动态跟换
    public View getView(final int position, View convertView, ViewGroup parent) {
        //自定义视图
        ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            //listContainer容器上下文
            convertView = listContainer.inflate(R.layout.list_item_room, null);
            //获取控件对象
            //listItemView.tag = (TextView) convertView.findViewById(R.id.grid_item_tag_text);
            //listItemView.check = (ImageView) convertView.findViewById(R.id.grid_item_tag_check);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }




        return convertView;
    }

}
