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

import java.util.List;

public class AccountPage extends AppCompatActivity {

    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    ReviewRecyclerViewAdapter myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

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


        //Get data
        ReviewDBHandler review = new ReviewDBHandler(this);

        List<Review> reviews = review.getReviews();

        //Add recycler view for page

        //For some reason this is glitched and won't remove certain reviews
        for(int i = 0; i < reviews.size(); i++){
            if(reviews.get(i).getmUserID() != 1){
                reviews.remove(i);
            }
        }

        //Create recycler view

        RecyclerView rView = (RecyclerView)findViewById(R.id.accountRecyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter2 = new ReviewRecyclerViewAdapter(reviews);
        rView.setAdapter(myAdapter2);
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

    /*
        Button Commands
     */

    public void accountSettingsButtonClick(View v){
       //nothing in here as of yet. I don't know of any account settings I will be able to implement yet.
    }



    public void editProfileButtonClick(View v){
        /*
            I think I will be adding the ability to change both your user name and profile name
         */
    }
}