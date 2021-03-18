package com.example.masksafe;

public class Review {
    private int mID;
    private String mUserName;
    private String mContent;

    public Review(int i, String u, String c){
        mID = i;
        mUserName = u;
        mContent = c;
    }

    public Review(String u, String c){
        mUserName = u;
        mContent = c;
    }

    //Getters and Setters

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }
}
