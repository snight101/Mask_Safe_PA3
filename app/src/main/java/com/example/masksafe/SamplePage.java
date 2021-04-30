package com.example.masksafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;

public class SamplePage extends AppCompatActivity {

    //Flag and key related
    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    private static final String KEY_PAGE = "page number";
    private int mPageNum;

    //views and review related
    private int reviewScore = 0;
    private TextView mReviewScore;
    private TextView mCompanyTitle;
    private TextView mAddress;
    private ImageView mReviewImage;
    ReviewRecyclerViewAdapter myAdapter;
    private VideoView mVideo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_page);

        //Recieve Key from map page
        Bundle mydata = getIntent().getExtras();
        mPageNum = mydata.getInt(KEY_PAGE);

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

        //Set views for page

        mCompanyTitle = (TextView) findViewById(R.id.companyTitleTextView);

        mAddress = (TextView) findViewById(R.id.addressTextView);

        mReviewScore = (TextView)findViewById(R.id.percentageTextView);

        mReviewImage = (ImageView) findViewById(R.id.scoreImageView);

        ReviewDBHandler review = new ReviewDBHandler(this);

        //get review list
        List<Review> reviews = review.getReviews(mPageNum);

        //get Business list
        List<Business> businesses = review.getBusinesses();

        //Set company title and address
        for(int i = 1; i < businesses.size(); i++){
            if(businesses.get(i).getmID() == mPageNum){
                mCompanyTitle.setText(businesses.get(i).getmName());
                mAddress.setText(businesses.get(i).getmAddress());
            }
        }


        //Calculate review score for page

        for(int i = 0; i < reviews.size(); i++){
            int score = reviews.get(i).getmScore();
            reviewScore += (score * 100);
        }
        int realScore = reviewScore/(reviews.size());

        mReviewScore.setText( realScore + "%");

        //Set score image
        if(realScore > 50){
            mReviewImage.setImageResource(R.drawable.covid_icon);
        }
        else{
            mReviewImage.setImageResource(R.drawable.ic_logo);
        }


        //Create recycler view

        RecyclerView rView = (RecyclerView)findViewById(R.id.recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReviewRecyclerViewAdapter(reviews);
        try {
            rView.setAdapter(myAdapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }


        //Add video to page
        //Set video view
        mVideo = (VideoView) findViewById(R.id.videoView);
        mVideo.setVideoPath(businesses.get(0).getmVideo());

        //Set media Controller
        MediaController myMediaController = new MediaController(this);
        myMediaController.setAnchorView(mVideo);
        mVideo.setMediaController(myMediaController);

        mVideo.start();

        //SMS permissions
        int SMS_PERMISSION_REQ_CODE_SUBMIT = 101;
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SamplePage.this, new String[] {Manifest.permission.RECEIVE_SMS},
                    SMS_PERMISSION_REQ_CODE_SUBMIT);
            ActivityCompat.requestPermissions(SamplePage.this, new String[] {Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_REQ_CODE_SUBMIT);
        }


    }




    @Override
    public void onResume() {
        super.onResume();

        //Recieve Key from map page
        Bundle mydata = getIntent().getExtras();
        mPageNum = mydata.getInt(KEY_PAGE);

        ReviewDBHandler review = new ReviewDBHandler(this);

        List<Review> reviews = review.getReviews(mPageNum);


        RecyclerView rView = (RecyclerView)findViewById(R.id.recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReviewRecyclerViewAdapter(reviews);
        try {
            rView.setAdapter(myAdapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        reviewScore = 0;


        //Calculate review score for page

        for(int i = 0; i < reviews.size(); i++){
            int score = reviews.get(i).getmScore();
            reviewScore += (score * 100);
        }
        int realScore = reviewScore/(reviews.size());


        mReviewScore.setText( realScore + "%");

        //Set score image

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
                Intent myIntent2 = new Intent(this, MapsActivity.class);
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
            myIntent.putExtra(KEY_PAGE, mPageNum);
        }
        startActivity(myIntent);
    }

    //Command to send SMS
    //PA6 Send intent instead
    public void getInfoButtonClick(View v){

        ReviewDBHandler review = new ReviewDBHandler(this);

        //get Business list
        List<Business> businesses = review.getBusinesses();

        if(businesses.get(mPageNum -1).getmWebsite() != null) {


            String message = businesses.get(mPageNum - 1).getmWebsite();
            String phoneNumber = "5554";
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);



            try {
                sendIntent.putExtra("sms_body", message);
                sendIntent.putExtra("address", phoneNumber);

                sendIntent.setData(Uri.parse("sms:"));

                startActivity(sendIntent);
            }
            catch(Exception e){
                e.printStackTrace();
                Toast.makeText(this,"ERROR.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this,"This establishment does not have a website.", Toast.LENGTH_LONG).show();
        }
    }


}