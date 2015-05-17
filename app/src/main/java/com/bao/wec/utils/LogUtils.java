package com.bao.wec.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bao.wec.app.Constant;


/**
 * @author kingofglory
 *         email: kingofglory@yeah.net
 *         blog:  http:www.google.com
 * @date 2014-2-21
 * TODO Log工具类，设置开关，防止发布版本时log信息泄露
 */

public class LogUtils {

        public static void println(Object object){
            if (Constant.Config.ISDEBUG) {
                System.out.println(object);
            }
        }

		public static void v(String tag, String msg) {
			if (Constant.Config.ISDEBUG) {
				Log.v(tag, msg);
			}

		}

		public static void d(String tag, String msg) {
			if (Constant.Config.ISDEBUG) {
				Log.d(tag, msg);
			}

		}

		public static void i(String tag, String msg) {
			if (Constant.Config.ISDEBUG) {
				Log.i(tag, msg);
			}

		}

		public static void w(String tag, String msg) {
			if (Constant.Config.ISDEBUG) {
				Log.w(tag, msg);
			}

		}

		public static void e(String tag, String msg) {
			if (Constant.Config.ISDEBUG) {
				Log.e(tag, msg);
			}
		}

    public static void v(String msg) {
        if (Constant.Config.ISDEBUG) {
            Log.v(Constant.Config.TAG, msg);
        }

    }

    public static void d(String msg) {
        if (Constant.Config.ISDEBUG) {
            Log.d(Constant.Config.TAG, msg);
        }

    }

    public static void i(String msg) {
        if (Constant.Config.ISDEBUG) {
            Log.i(Constant.Config.TAG, msg);
        }

    }

    public static void w(String msg) {
        if (Constant.Config.ISDEBUG) {
            Log.w(Constant.Config.TAG, msg);
        }

    }

    public static void e(String msg) {
        if (Constant.Config.ISDEBUG) {
            Log.e(Constant.Config.TAG, msg);
        }
    }

    public static void toastError(Context context, String msg){
        if(Constant.Config.ISSHOWTOAST){
            Toast.makeText(context , "操作失败 :" + msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(Context context, String msg){
        if(Constant.Config.ISSHOWTOAST){
            Toast.makeText(context , msg, Toast.LENGTH_SHORT).show();
        }
    }

}
