package com.example.masksafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLExample extends AppCompatActivity {
    private TextView mIDTextView;
    private EditText mUserIDEditText;
    private EditText mContentEditText;



    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_l_example);

        mIDTextView = (EditText)findViewById(R.id.reviewEditText);
        mUserIDEditText = (EditText)findViewById(R.id.userNameEditText);
        mContentEditText = (EditText)findViewById(R.id.contentEditText);

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
    }

    /*

      Adding menu to page

   */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_mask_safe, menu);
        return true;
    }


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







    // Find review by username and put their review ID and Content in appropriate fields
    public void findButtonClick(View v){
        String reviewID = mIDTextView.getText().toString();
        ReviewDBHandler handler = new ReviewDBHandler(this);

        Review review = handler.findReview(Integer.parseInt(reviewID));

        if(review != null){
            mIDTextView.setText(String.valueOf(review.getmID()));
            mContentEditText.setText(review.getmContent());
            mUserIDEditText.setText(String.valueOf(review.getmUserID()));
        }
        else{
            mIDTextView.setText("User was not found.");
            mContentEditText.setText("");
            mUserIDEditText.setText("");
        }
    }


    //find and delete review by user name

    public void deleteButtonClick(View v){
        String username = mIDTextView.getText().toString();
        ReviewDBHandler handler = new ReviewDBHandler(this);

        boolean result = handler.deleteReview(username);

        if(result){
            mUserIDEditText.setText("");
            mContentEditText.setText("");
            mIDTextView.setText("Record Deleted");
        }
        else
        {
            mIDTextView.setText("Review not found");
        }

    }





}