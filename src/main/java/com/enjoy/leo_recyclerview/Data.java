package com.enjoy.leo_recyclerview;

public class Data {
    private String str;
    private int resId;
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        System.out.println("===" + str);
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "str='" + str + '\'' +
                ", resId=" + resId +
                ", uid=" + uid +
                '}';
    }
}
