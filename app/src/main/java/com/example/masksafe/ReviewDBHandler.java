package com.example.masksafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReviewDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "reviewDB.db";
    private static final String TABLE_REVIEW = "Reviews";
    private static final String COLUMN_REVIEWID = "review_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_CONTENT = "content";

    public ReviewDBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_REVIEW_TABLE = "CREATE TABLE " +
                TABLE_REVIEW + "(" +
                COLUMN_REVIEWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(CREATE_REVIEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        onCreate(db);
    }

    public void addReview(Review review){
        ContentValues myValues = new ContentValues();
        myValues.put(COLUMN_USERNAME, review.getmUserName());
        myValues.put(COLUMN_CONTENT, review.getmContent());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_REVIEW, null, myValues);
        db.close();
    }

    public Review findReview(String username){
        String sqlQuery = "SELECT  * FROM " + TABLE_REVIEW +
                " WHERE " + COLUMN_USERNAME + " =\"" +
                username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor myCursor = db.rawQuery(sqlQuery, null);

        Review myReview = null;

        if(myCursor.moveToFirst()){
            int tmpID = myCursor.getInt(0);
            String tmpUserName = myCursor.getString(1);
            String tmpContext = myCursor.getString(2);
            myCursor.close();
            myReview = new Review(tmpID, tmpUserName, tmpContext);
        }
        db.close();
        return  myReview;
    }

    public boolean deleteReview(String userName){
        boolean result = false;

        String sqlQuery = "SELECT  * FROM " + TABLE_REVIEW +
                " WHERE " + COLUMN_USERNAME + " =\"" +
                userName + "\"";
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
