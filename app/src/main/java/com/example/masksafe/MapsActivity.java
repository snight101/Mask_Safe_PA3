package com.example.masksafe;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final boolean USE_FLAG = true;
    private static final int mFlag = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    private static final String KEY_PAGE = "page number";
    private int mPageNum;

    //All markers
    private Marker Marker1; private Marker Marker2; private Marker Marker3; private Marker Marker4; private Marker Marker5; private Marker Marker6; private Marker Marker7; private Marker Marker8; private Marker Marker9; private Marker Marker10;

    //Booleans to make map work right
    boolean pressed = false; boolean pressedTwo = false; boolean pressedThree = false; boolean pressedFour = false; boolean pressedFive = false; boolean pressedSix = false; boolean pressedSeven = false; boolean pressedEight = false; boolean pressedNine = false; boolean pressedTen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMarkerClickListener(this);

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //Display map controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);


        // Add a marker in Luddy and move the camera
        LatLng bloomCafe = new LatLng(39.15337127562186, -86.52960918190149);
        Marker1 = mMap.addMarker(new MarkerOptions().position(bloomCafe).title("Bloomington Café"));


        //Add another marker
        LatLng bubsBur= new LatLng(39.170459382403756, -86.53591908005048);
        Marker2 = mMap.addMarker(new MarkerOptions().position(bubsBur).title("Bub's Burgers"));

        //Add another marker
        LatLng owl = new LatLng(39.167820845495136, -86.53456315403817);
        Marker3 = mMap.addMarker(new MarkerOptions().position(owl).title("Owlery"));

        //Add another marker
        LatLng malibu = new LatLng(39.16702230726966, -86.53317913422094);
        Marker4 = mMap.addMarker(new MarkerOptions().position(malibu).title("Malibu Grill"));

        //Add another marker
        LatLng juan = new LatLng(39.166972398329555, -86.54023870817224);
        Marker5 = mMap.addMarker(new MarkerOptions().position(juan).title("Juannita's"));

        //Add another marker
        LatLng threeAmigos = new LatLng(39.17197354436211, -86.53507880680144);
        Marker6 = mMap.addMarker(new MarkerOptions().position(threeAmigos).title("The 3 Amigos"));

        //Add another marker
        LatLng upland = new LatLng(39.17364535573402, -86.5371602009361);
        Marker7 = mMap.addMarker(new MarkerOptions().position(upland).title("Upland Brewery"));

        //Add another marker
        LatLng dom = new LatLng(39.17501770845756, -86.5333836507372);
        Marker8 = mMap.addMarker(new MarkerOptions().position(dom).title("Domino's Pizza"));

        //Add another marker
        LatLng starbucks = new LatLng(39.16636727990499, -86.526989264451);
        Marker9 = mMap.addMarker(new MarkerOptions().position(starbucks).title("Starbucks"));

        //Add another marker
        LatLng taste = new LatLng(39.165527133535896, -86.52997188080126);
        Marker10 = mMap.addMarker(new MarkerOptions().position(taste).title("Taste of India"));

        //Create default camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(bloomCafe)      // Sets the center of the map to Luddy schools
                .zoom(14)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    public boolean onMarkerClick(final Marker marker) {
        //If statement for marker 1
        if (marker.equals(Marker1)){
            if(pressed){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 1;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressed = false;
            }
            else{
                Marker1.showInfoWindow();

                pressed = true;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 2
        if (marker.equals(Marker2)){
            if(pressedTwo){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 2;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedTwo = false;
            }
            else{
                Marker2.showInfoWindow();

                pressed = false;
                pressedTwo = true;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }

        //If statement for marker 3
        if (marker.equals(Marker3)){
            if(pressedThree){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 3;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedThree = false;
            }
            else{
                Marker3.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = true;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 4
        if (marker.equals(Marker4)){
            if(pressedFour){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 4;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedFour = false;
            }
            else{
                Marker4.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = true;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 5
        if (marker.equals(Marker5)){
            if(pressedFive){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 5;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedFive = false;
            }
            else{
                Marker5.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = true;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 6
        if (marker.equals(Marker6)){
            if(pressedSix){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 6;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedSix = false;
            }
            else{
                Marker6.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = true;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 7
        if (marker.equals(Marker7)){
            if(pressedSeven){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 7;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedSeven = false;
            }
            else{
                Marker7.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = true;
                pressedEight = false;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 8
        if (marker.equals(Marker8)){
            if(pressedEight){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 8;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedEight = false;
            }
            else{
                Marker8.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = true;
                pressedNine = false;
                pressedTen = false;
            }
        }
        //If statement for marker 9
        if (marker.equals(Marker9)){
            if(pressedNine){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 9;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedNine = false;
            }
            else{
                Marker9.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = true;
                pressedTen = false;
            }
        }
        //If statement for marker 10
        if (marker.equals(Marker10)){
            if(pressedTen){
                Intent myIntent = new Intent(this, SamplePage.class);
                //using flags to make sure im not spamming intents
                if(USE_FLAG){
                    myIntent.addFlags(mFlag);
                    mPageNum = 10;
                    myIntent.putExtra(KEY_PAGE, mPageNum);
                }
                startActivity(myIntent);
                pressedTen = false;
            }
            else{
                Marker10.showInfoWindow();

                pressed = false;
                pressedTwo = false;
                pressedThree = false;
                pressedFour = false;
                pressedFive = false;
                pressedSix = false;
                pressedSeven = false;
                pressedEight = false;
                pressedNine = false;
                pressedTen = true;
            }
        }
        return true;
    }
}