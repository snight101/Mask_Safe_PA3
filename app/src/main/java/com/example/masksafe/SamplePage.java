package com.example.masksafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SamplePage extends AppCompatActivity {

    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    private int reviewScore = 0;
    private TextView mReviewScore;
    private ImageView mReviewImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_page);

        ActionBar AB = getSupportActionBar();
        AB.setTitle("Mask Safe");
        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#2F9CCD"));

        // Set BackgroundDrawable
        AB.setBackgroundDrawable(colorDrawable);

        AB.setDisplayShowHomeEnabled(true);
        AB.setDisplayUseLogoEnabled(true);
        AB.setLogo(R.drawable.ic_logo);

        //Set review score for page

        mReviewScore = (TextView)findViewById(R.id.percentageTextView);

        mReviewImage = (ImageView) findViewById(R.id.scoreImageView);

        ReviewDBHandler review = new ReviewDBHandler(this);

        List<Review> reviews = review.getReviews();

        //Remove reviews that aren't related to the restaraunt
        for(int i = 0; i < reviews.size(); i++){
            if(reviews.get(i).getmBusinessID() != 1){
                reviews.remove(i);
            }
        }

        for(int i = 1; i < reviews.size(); i++){
            int score = reviews.get(i).getmScore();
            reviewScore += (score * 100);
        }
        int realScore = (reviewScore - 100)/reviews.size();


        mReviewScore.setText( realScore + "%");

        if(realScore > 50){
            mReviewImage.setImageResource(R.drawable.covid_icon);
        }
        else{
            mReviewImage.setImageResource(R.drawable.ic_logo);
        }


    }

    /*

        Adding menu to page

     */


    @Override
    public void onResume() {
        super.onResume();
        reviewScore = 0;

        ReviewDBHandler review = new ReviewDBHandler(this);

        List<Review> reviews = review.getReviews();

        //Remove reviews that aren't related to the restaraunt
        for(int i = 0; i < reviews.size(); i++){
            if(reviews.get(i).getmBusinessID() != 1){
                reviews.remove(i);
            }
        }

        for(int i = 1; i < reviews.size(); i++){
            int score = reviews.get(i).getmScore();
            reviewScore += (score * 100);
        }
        int realScore = (reviewScore - 100)/reviews.size();


        mReviewScore.setText( realScore + "%");

        if(realScore > 50){
            mReviewImage.setImageResource(R.drawable.covid_icon);
        }
        else{
            mReviewImage.setImageResource(R.drawable.ic_logo);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_mask_safe, menu);
        return true;
    }
    //Menu commands
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Menu Commands
        // works like an if else statement
        switch (id) {
            case R.id.profileButton:
                Intent myIntent = new Intent(this, AccountPage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                }
                startActivity(myIntent);
                return true;
            case R.id.mapButton:
                Intent myIntent2 = new Intent(this, MainActivity.class);
                if(USE_FLAG){
                    myIntent2.addFlags(mFlag);
                }
                startActivity(myIntent2);
                return true;
            case R.id.sqlExample:
                Intent myIntent3 = new Intent(this, SQLExample.class);
                if(USE_FLAG){
                    myIntent3.addFlags(mFlag);
                }
                startActivity(myIntent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Command to switch to submit review page
    public void submitReviewButtonClick(View v){
        Intent myIntent = new Intent(this, SubmitReview.class);
        if(USE_FLAG){
            myIntent.addFlags(mFlag);
        }
        startActivity(myIntent);
    }
    //Command to switch to submit review page
    public void moreReviewsButtonClick(View v){
        Intent myIntent = new Intent(this, reviewRecyclerView.class);
        if(USE_FLAG){
            myIntent.addFlags(mFlag);
        }
        startActivity(myIntent);
    }

}