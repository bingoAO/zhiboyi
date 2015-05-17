package com.bao.wec.app;

import android.os.Environment;

import java.io.File;

//test branch

public interface Constant {

    public static class Config {
        public static final boolean ISDEBUG = Boolean.parseBoolean("true");
        public static final boolean ISSHOWTOAST = Boolean.parseBoolean("true");
        public static final String TAG = "tag";


        // 高德地图   以及   友盟appkey 在 mainifest 文件

        public static  final String BMOB_APP_ID = "9c907c0cafc58cd896369d86a4ef795f";

        public static  final String MOB_APP_KEY = "643e81c90822";
        public static  final String MOB_APP_SECRET = "53a40a2247f7e6409451697eeecfefef";


    }

    public static class Count {

        //封面尺寸
        public  static  final int UPLOAD_AVATAR_SIZE = 128;
    }

    public static class Data {
        //短信接口地址
        public static final String VERIFY_INTERFACE_URL = "http://120.24.158.159:8090/sendSMS/";
        //再次发送短信间隔
        public static final int VERIFY_INTERVAL = 70;
        //用户默认密码
        public static final String DEFAULT_PASS = "wec";
    }



    public static class Sp {
        //应用总的 SharedPreference key
        public static final String PRE_NAME = "wecourse_pre";
    }

    public static class Path {
        public static final String SDCardRoot = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator;
        //默认的文件夹名称
        public static final String DEFAULT_DIR_NAME = "wecourse";
        public static final String DIR_WITH_SEPARATE = DEFAULT_DIR_NAME + File.separator;
        public static final String DIR_WITHOUT_SEPARATE = DEFAULT_DIR_NAME;
        public static final String COMPLETE_PATH = SDCardRoot+ DIR_WITH_SEPARATE;


    }

    public static class KeyValue {

        public static final String TYPE_KEY ="type_key";
        public static final String DATA_KEY ="data_key";
        public static final String TITLE_KEY ="title_key";
    }


    public static  class  CODE{
        //gender type
        public static final int MALE = 0;
        public static final int FEMALE = 1;

        //intent action
        public static final int PICK_FROM_CAMERA = 0;
        public static final int PICK_FROM_FILE = 1;
        public static final int ACTION_CROP = 2;

        //list type
        public static final int LIST_HOME_ROOM = 0;
        public static final int LIST_SBC_ROOM = 1;
        public static final int LIST_MEMBER = 2;
        public static final int LIST_PROGRAM_PRIVATE = 3;
        public static final int LIST_PROGRAM_PUBLIC = 4;

        //user_type
        public static final int USER_NORMAL = 0;
        public static final int USER_MANAGER = 1;
        public static final int USER_ADMIN = 2;



    }

}
