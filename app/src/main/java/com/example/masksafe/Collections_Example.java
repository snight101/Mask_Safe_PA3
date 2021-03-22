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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Collections_Example extends AppCompatActivity {

    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

    private TextView mTextView;

    private ArrayList mArrayList;
    private HashSet mHashSet;
    private HashMap mHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections__example);

        mTextView = (TextView)findViewById(R.id.collectionsTextView);

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



    public void arrayListButtonClick(View v){
        mArrayList = new ArrayList();


        String tmpString = "Initialized Array. Size:" + mArrayList.size() + "\n\n";


        // add elements

        mArrayList.add("Ireland");
        mArrayList.add("Vietnam");
        mArrayList.add("France");
        mArrayList.add("Zimbabwe");
        mArrayList.add("Argentina");

        //display array
        tmpString += "The contents of the Array List is: \n\t" + mArrayList + "\n\n";


        //remove elements
        mArrayList.remove(0);
        mArrayList.remove("France");

        //display the array
        tmpString += "The contents of the Array List is: \n\t" + mArrayList + "\n\n";


        //set the text view
        mTextView.setText(tmpString);
    }

    public void hashSetButtonClick(View v){
        mHashSet = new HashSet();

        String tmpString = "Initialized Hash Set. Size:" + mHashSet.size() + "\n\n";

        // add elements

        mHashSet.add(0);
        mHashSet.add(1);
        mHashSet.add(2);
        mHashSet.add(3);
        mHashSet.add(3);

        //display hash Set
        tmpString += "The contents of the Hash Set is: \n\t" + mHashSet + "\n\n";

        //Check to see if Item is in has set
        tmpString += "Does the hash set contain 1: " + mHashSet.contains(1) + "\n";

        tmpString += "Does the hash set contain 4: " + mHashSet.contains(4) + "\n\n";

        //display hash set
        tmpString += "The contents of the Hash Set is: \n\t" + mHashSet + "\n\n";

        //Remove an item
        mHashSet.remove(0);

        //display hash set
        tmpString += "The contents of the Hash Set is: \n\t" + mHashSet + "\n\n";

        //set the text view
        mTextView.setText(tmpString);

    }
    public void hashMapButtonClick(View v){
        mHashMap = new HashMap();

        String tmpString = "Initialized Hash Map Size:" + mHashMap.size() + "\n\n";

        //Put in key/value pairs
        mHashMap.put(22,"Sam");
        mHashMap.put(21, "Kaiya");
        mHashMap.put(21, "Kaiya");
        mHashMap.put(23, "JD");
        mHashMap.put(58, "Dad");

        //display hash map
        tmpString += "The contents of the Hash Map is: \n\t" + mHashMap + "\n\n";

        //check to see if a key is in the hash map
        tmpString += "Does the hash map contain the key 22: " + mHashMap.containsKey(22) + "\n\n";

        tmpString += "Does the hash map contain the key 19: " + mHashMap.containsKey(19) + "\n\n";

        //Get all of the keys
        tmpString += "The keys of the Hash Map are: \n\t" + mHashMap.keySet() + "\n\n";

        //display Hash Map
        tmpString += "The contents of the Hash Map is: \n\t" + mHashMap + "\n\n";

        //remove item
        mHashMap.remove(23);

        //display Hash Map
        tmpString += "The contents of the Hash Map is: \n\t" + mHashMap + "\n\n";

        //set the text view
        mTextView.setText(tmpString);

    }

}