package com.example.masksafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.masksafe.R.drawable.picupload;

// I did not have time to finish the landscape view on this page as I could not figure it out


public class SubmitReview extends AppCompatActivity {

    //Flag and key related
    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    private static final String KEY_PAGE = "page number";
    private int mPageNum;

    //View and score related
    private EditText mEnterReview;
    private Boolean mScore;
    private int score;
    private ImageButton mImageButton;

    //Adding ability to take photos
    private static final int IMAGE_CAPTURE = 102;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static Uri fileUri;
    private static Context context;
    private TextView errorMSG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_review);

        //Recieve key
        Bundle mydata = getIntent().getExtras();
        mPageNum = mydata.getInt(KEY_PAGE);

        mEnterReview = (EditText) findViewById(R.id.enterReview);
        errorMSG = (TextView) findViewById(R.id.errorMSG);
        context = getApplicationContext();
        mImageButton = (ImageButton) findViewById(R.id.enterImage);

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


        errorMSG.setText("state: " + Environment.getExternalStorageState());

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

    // For some reason these functions do not work
    public void maskButtonClick(View v){
        Toast.makeText(this,"You have determined this establishment pandemic friendly.", Toast.LENGTH_LONG).show();
        mScore = true;
        mEnterReview.setText("");

    }

    public void virusButtonClick(View v){
        Toast.makeText(this,"You have determined this establishment disgusting.", Toast.LENGTH_LONG).show();
        mScore = false;
        mEnterReview.setText("");
    }



    public void submitReviewButtonClick(View v){
        String content = mEnterReview.getText().toString();

        if(mScore){
            score = 1;
        }
        else{
            score = 0;
        }

        //These will be more specific and customizeable once the google API is added
        Review review = new Review(content, fileUri.toString(), score, 1, mPageNum);

        ReviewDBHandler handler = new ReviewDBHandler(this);
        handler.addReview(review);
        mEnterReview.setText("");
        mImageButton.setImageResource(picupload);

    }

    private  static File getOutputMediaFile(int type){
        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if(!myDir.exists()){
            if(!myDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        String myTimeStamp = new SimpleDateFormat( "yyyMMdd_HHmmss").format(new Date());
        File myMediaFile;
        if(type == MEDIA_TYPE_IMAGE){
            myMediaFile = new File( myDir.getPath() + File.separator + "IMG_" + myTimeStamp + ".jpg");
        }
        else{
            return null;
        }
        return myMediaFile;
    }

    private static Uri getOutMediaFileUri(int type){
        Uri URI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getOutputMediaFile(type));
        return URI;
    }



    public void enterImageButtonClick(View v){
        try {
            Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutMediaFileUri(MEDIA_TYPE_IMAGE);
            errorMSG.setText(errorMSG.getText() + "\nfileURI: " + fileUri.getPath());
            myIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(myIntent, IMAGE_CAPTURE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == IMAGE_CAPTURE){
            if(resultCode == RESULT_OK){
                mImageButton.setImageURI(fileUri);
                Toast.makeText(this,"Image returned", Toast.LENGTH_LONG).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Capture Canceled.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Failed to capture image.", Toast.LENGTH_LONG).show();
            }
        }
    }


}