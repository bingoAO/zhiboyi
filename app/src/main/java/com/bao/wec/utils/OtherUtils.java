package com.bao.wec.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bao.wec.app.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * scroll view 嵌套 ListView 解决只显示第一行问题
 */
public class OtherUtils {
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String getTimeStringByDate(String date) {
        String result;
        Date commentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            commentTime = sdf.parse(date);
        } catch (ParseException e) {
            LogUtils.e("解析日期出错:"+date);
            e.printStackTrace();
        }
        long dayBefore =  (System.currentTimeMillis() - commentTime.getTime())/(1000*60*60*24);
        long hourBefore =  (System.currentTimeMillis() - commentTime.getTime())/(1000*60*60);
        long minBefore =  (System.currentTimeMillis() - commentTime.getTime())/(1000*60) + 4;

        if(minBefore < 60){
            result = minBefore + "分钟前";
        }else if(hourBefore < 24){
            result = hourBefore + "小时前";
        }else{
            result = dayBefore + "天前";
        }

        return result;

    }


    /**
     * 产生一个四位随机数
     * @return
     */
    public static int getRandomVerifyCode(){
        return Integer.parseInt(((Math.floor(Math.random()*10000)+1000)+"").substring(0,4));
    }

    /**
     * 获取接下来3天的日期
     * @return
     */
    public static List<String> get5DatesAfterToday(){
        List<String> result = new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        result.add(df.format(calendar.getTime()));

        for (int i = 0; i < 2; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);//让日期加1
            result.add(df.format(calendar.getTime()));
            //LogUtils.i(result.get(i));
        }
        return result;

    }

    /**
     * 组装获取验证码
     * @param phone
     * @param verifyCode
     * @return
     */
    public static String getVerifyUrl(String phone,String verifyCode){
        return Constant.Data.VERIFY_INTERFACE_URL+phone+"/"+verifyCode;
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}