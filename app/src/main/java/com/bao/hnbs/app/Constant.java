package com.bao.hnbs.app;

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

    }

    public static class Data {

    }



    public static class Sp {
        //应用总的 SharedPreference key
        public static final String PRE_NAME = "zengli_pre";
    }

    public static class Path {
        public static final String SDCardRoot = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator;
        //默认的文件夹名称
        public static final String DEFAULT_DIR_NAME = "zengli";
        public static final String DIR_WITH_SEPARATE = DEFAULT_DIR_NAME + File.separator;
        public static final String DIR_WITHOUT_SEPARATE = DEFAULT_DIR_NAME;
        public static final String COMPLETE_PATH = SDCardRoot+ DIR_WITH_SEPARATE;


    }

    public static class KeyValue {

        public static final String TYPE_KEY ="type_key";
        public static final String DATA_KEY ="data_key";
    }


    public static  class  CODE{


    }

}
