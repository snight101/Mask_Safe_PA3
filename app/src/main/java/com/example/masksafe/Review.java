package com.example.masksafe;

public class Review {
    private int mID;
    private String mContent;
    private String mImage;
    private int mScore;
    private int mUserID;
    private int mBusinessID;


    public Review(int d, String c, String i, int e, int f, int g){
        mID = d;
        mContent = c;
        mImage = i;
        mScore = e;
        mUserID = f;
        mBusinessID = g;
    }



    public Review(String c, String i, int e, int f, int g){
        mContent = c;
        mImage = i;
        mScore = e;
        mUserID = f;
        mBusinessID = g;
    }

    //Getters and Setters

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public int getmBusinessID() {
        return mBusinessID;
    }

    public void setmBusinessID(int mBusinessID) {
        this.mBusinessID = mBusinessID;
    }




}
