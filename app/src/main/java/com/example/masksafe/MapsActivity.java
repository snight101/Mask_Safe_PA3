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
        LatLng bloomCafe = new LatLng(39.17264696709298, -86.52253708064691);
        Marker1 = mMap.addMarker(new MarkerOptions().position(bloomCafe).title("Bloomington Café"));


        //Add another marker
        LatLng bubsBur= new LatLng(39.170459382403756, -86.53591908005048);
        Marker2 = mMap.addMarker(new MarkerOptions().position(bubsBur).title("Bub's Burgers"));

        //Add another marker
        LatLng art = new LatLng(39.16955279330485, -86.51871761486917);
        mMap.addMarker(new MarkerOptions().position(art).title("Department of Art History"));

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
                pressedTwo = true;
            }
        }

        return true;
    }
}