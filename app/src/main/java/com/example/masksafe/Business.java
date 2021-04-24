package com.example.masksafe;

public class Business {
    private int mID;
    private String mName;
    private String mAddress;
    private String mVideo;
    private Float mRating;
    private String mApi;
    private String mWebsite;



    public Business(int d, String n, String a, String v, Float r, String p, String w){
        mID = d;
        mName = n;
        mAddress = a;
        mVideo = v;
        mRating = r;
        mApi = p;
        mWebsite = w;
    }

    public Business(String n, String a, String v, Float r, String p, String w){
        mName = n;
        mAddress = a;
        mVideo = v;
        mRating = r;
        mApi = p;
        mWebsite = w;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmVideo() {
        return mVideo;
    }

    public void setmVideo(String mVideo) {
        this.mVideo = mVideo;
    }

    public Float getmRating() {
        return mRating;
    }

    public void setmRating(Float mRating) {
        this.mRating = mRating;
    }

    public String getmApi() {
        return mApi;
    }

    public void setmApi(String mApi) {
        this.mApi = mApi;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }
}
