package com.bao.wec.entity;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private int type;


    public int getType() {
        return type;
    }

    public User setType(int type) {
        this.type = type;
        return this;
    }
}
