package com.bao.wec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.entity.User;
import com.bao.wec.utils.LogUtils;

import java.util.List;

import cn.bmob.v3.BmobObject;


public class MemberAdapter extends BaseAdapter{

    private final Context context;
    private LayoutInflater mInflater;
    private List<BmobObject> listItems;    //商品信息集合

    public MemberAdapter(Context context,List<BmobObject> items) {
        this.context = context;
        listItems = items;
        mInflater = LayoutInflater.from(context);
    }


    private final class  ListItemView {
        TextView header_tv;
        TextView user_type_tv;
        LinearLayout header_ll;
        LinearLayout content_btn;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListItemView holder;

        if (convertView == null) {
            holder = new ListItemView();
            convertView = mInflater.inflate(R.layout.list_item_member, parent, false);
            holder.header_ll = (LinearLayout) convertView.findViewById(R.id.item_member_header_ll);
            holder.content_btn = (LinearLayout) convertView.findViewById(R.id.item_member_ll_btn);
            holder.header_tv = (TextView) convertView.findViewById(R.id.item_member_header_tv);
            holder.user_type_tv = (TextView) convertView.findViewById(R.id.item_member_usertype_tv);
            convertView.setTag(holder);
        } else {
            holder = (ListItemView) convertView.getTag();
        }

        User user = (User)listItems.get(position);

        switch (user.getType()){
            case Constant.CODE.USER_ADMIN:
                holder.header_tv.setText("频道主");
                holder.user_type_tv.setText("频道主");
                break;
            case Constant.CODE.USER_MANAGER:
                holder.header_tv.setText("管理员");
                holder.user_type_tv.setText("管理员");

                break;
            case Constant.CODE.USER_NORMAL:
                holder.header_tv.setText("成员");
                holder.user_type_tv.setText("成员");
                break;
        }
//社么情况隐藏header
        if(position != 0 && ((User)listItems.get(position - 1)).getType() == user.getType()){
            //隐藏header
            holder.header_ll.setVisibility(View.GONE);
        }else {
            holder.header_ll.setVisibility(View.VISIBLE);
        }

        holder.content_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.toast(context,"position =" + position);
            }
        });


        return convertView;
    }


}
