package com.example.masksafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ReviewDBHandler extends SQLiteOpenHelper  {
    private static final int DATABASE_VERSION = 2;
    public List<Review> review_list;
    public List<Business> business_list;

    //
    private static final String DATABASE_NAME = "reviewDB2.db";
    //Tables
    private static final String TABLE_REVIEW = "Reviews";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_BUSINESSES = "Businesses";

    //Reviews rows
    private static final String COLUMN_REVIEWID = "review_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_SCORE = "score";

    //primary keys for other tables referenced in Reviews
    private static final String COLUMN_USERID = "user_id";
    private static final String COLUMN_BUSINESSID = "business_id";

    //Users rows
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USERFULLNAME = "username_fullname";


    //Businesses rows
    private static final String COLUMN_BUSINESSNAME = "business_name";
    private static final String COLUMN_VIDEO = "video";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_API = "api_key";
    private static final String COLUMN_WEBSITE = "website";


    public ReviewDBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        //Create Reviews table

        String CREATE_REVIEW_TABLE = "CREATE TABLE " +
                TABLE_REVIEW + "(" +
                COLUMN_REVIEWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_SCORE + " INTEGER, " +
                COLUMN_USERID + " INTEGER, " +
                COLUMN_BUSINESSID + " INTEGER)";



        //Create Users table
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" +
                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERFULLNAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT)";


        //Create Business table
        String CREATE_BUSINESSES_TABLE = "CREATE TABLE " +
                TABLE_BUSINESSES + "(" +
                COLUMN_BUSINESSID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BUSINESSNAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_VIDEO + " TEXT, " +
                COLUMN_RATING + " FLOAT, " +
                COLUMN_API + " TEXT, " +
                COLUMN_WEBSITE + " TEXT)";

        //Create Tables in sql
        db.execSQL(CREATE_BUSINESSES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_REVIEW_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESSES);
        onCreate(db);
    }

    //Once I have the google API set up I will use a KEY to allow me to add reviews to multiple businesses
    public void addReview(Review review){
        ContentValues myValues = new ContentValues();

        //myValues.put(COLUMN_REVIEWID, review.getmID());
        myValues.put(COLUMN_CONTENT, review.getmContent());
        myValues.put(COLUMN_IMAGE, review.getmImage());
        myValues.put(COLUMN_SCORE, review.getmScore());
        myValues.put(COLUMN_USERID, review.getmUserID());
        myValues.put(COLUMN_BUSINESSID, review.getmBusinessID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_REVIEW, null, myValues);
        db.close();

    }

    //Method that returns a list of all the Reviews
    //I tried using the code from https://stackoverflow.com/questions/44656025/getting-data-from-sqlite-using-custom-object-arraylist
    public List<Review> getReviews(int i){

        review_list = new ArrayList<Review>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] field = {COLUMN_REVIEWID, COLUMN_CONTENT, COLUMN_IMAGE, COLUMN_SCORE, COLUMN_USERID, COLUMN_BUSINESSID};
        Cursor c = db.query(TABLE_REVIEW, field, null, null, null, null, null);

        int iReviewID = c.getColumnIndex(COLUMN_REVIEWID);
        int iContent = c.getColumnIndex(COLUMN_CONTENT);
        int iImage = c.getColumnIndex(COLUMN_IMAGE);
        int iScore = c.getColumnIndex(COLUMN_SCORE);
        int iUserID = c.getColumnIndex(COLUMN_USERID);
        int iBusinessID = c.getColumnIndex(COLUMN_BUSINESSID);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            int reviewID = c.getInt(iReviewID);
            String content = c.getString(iContent);
            String image = c.getString(iImage);
            int score = c.getInt(iScore);
            int userID = c.getInt(iUserID);
            int businessID = c.getInt(iBusinessID);
            if( businessID == i) {
                review_list.add(new Review(reviewID, content, image, score, userID, businessID));
            }

        }

        return review_list;
    }

    public List<Review> getMyReviews(){

        review_list = new ArrayList<Review>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] field = {COLUMN_REVIEWID, COLUMN_CONTENT, COLUMN_IMAGE, COLUMN_SCORE, COLUMN_USERID, COLUMN_BUSINESSID};
        Cursor c = db.query(TABLE_REVIEW, field, null, null, null, null, null);

        int iReviewID = c.getColumnIndex(COLUMN_REVIEWID);
        int iContent = c.getColumnIndex(COLUMN_CONTENT);
        int iImage = c.getColumnIndex(COLUMN_IMAGE);
        int iScore = c.getColumnIndex(COLUMN_SCORE);
        int iUserID = c.getColumnIndex(COLUMN_USERID);
        int iBusinessID = c.getColumnIndex(COLUMN_BUSINESSID);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            int reviewID = c.getInt(iReviewID);
            String content = c.getString(iContent);
            String image = c.getString(iImage);
            int score = c.getInt(iScore);
            int userID = c.getInt(iUserID);
            int businessID = c.getInt(iBusinessID);
            if( userID == 1) {
                review_list.add(new Review(reviewID, content, image, score, userID, businessID));
            }

        }

        return review_list;
    }


    //Method that returns a list of all the Businesses
    public List<Business> getBusinesses(){

        business_list = new ArrayList<Business>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] field = {COLUMN_BUSINESSID, COLUMN_BUSINESSNAME, COLUMN_ADDRESS, COLUMN_VIDEO, COLUMN_RATING, COLUMN_API, COLUMN_WEBSITE};
        Cursor c = db.query(TABLE_BUSINESSES, field, null, null, null, null, null);

        int iBusinessID = c.getColumnIndex(COLUMN_BUSINESSID);
        int iBusinessName = c.getColumnIndex(COLUMN_BUSINESSNAME);
        int iAddress = c.getColumnIndex(COLUMN_ADDRESS);
        int iVideo = c.getColumnIndex(COLUMN_VIDEO);
        int iRating = c.getColumnIndex(COLUMN_RATING);
        int iApi = c.getColumnIndex(COLUMN_API);
        int iWebsite = c.getColumnIndex(COLUMN_WEBSITE);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            int businessID = c.getInt(iBusinessID);
            String businessName = c.getString(iBusinessName);
            String address = c.getString(iAddress);
            String video = c.getString(iVideo);
            Float rating = c.getFloat(iRating);
            String api = c.getString(iApi);
            String website = c.getString(iWebsite);
            business_list.add(new Business(businessID, businessName, address, video, rating, api, website));

        }

        return business_list;
    }










    public Review findReview(int userid){
        String sqlQuery = "SELECT  * FROM " + TABLE_REVIEW +
                " WHERE " + COLUMN_USERID + " =\"" +
                userid + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor myCursor = db.rawQuery(sqlQuery, null);

        Review myReview = null;

        if(myCursor.moveToFirst()){
            int tmpReviewID = myCursor.getInt(0);
            String tmpContext = myCursor.getString(1);
            String tmpImage = myCursor.getString(2);
            int tmpScore = myCursor.getInt(3);
            int tmpUserID = myCursor.getInt(4);
            int tmpBusinessID = myCursor.getInt(5);
            myCursor.close();
            myReview = new Review(tmpReviewID, tmpContext, tmpImage, tmpScore, tmpUserID, tmpBusinessID);
        }
        db.close();
        return  myReview;
    }
     /*
    public Review(int d, String c, String i, int e, int f, int g){
        mID = d;
        mContent = c;
        mImage = i;
        mScore = e;
        mUserID = f;
        mBusinessID = g;
    }

     */

    public boolean deleteReview(String reviewID){
        boolean result = false;

        String sqlQuery = "SELECT  * FROM " + TABLE_REVIEW +
                " WHERE " + COLUMN_REVIEWID + " =\"" +
                reviewID + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor myCursor = db.rawQuery(sqlQuery, null);

        if(myCursor.moveToFirst()){
            int tmpID = myCursor.getInt(0);

            String where = COLUMN_REVIEWID + "=?";
            String[] whereArgs = {String.valueOf(tmpID)};
            db.delete(TABLE_REVIEW, where, whereArgs);
            myCursor.close();

            result = true;
        }


        db.close();
        return result;

    }


}