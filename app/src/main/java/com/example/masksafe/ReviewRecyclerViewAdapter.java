package com.example.masksafe;

import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {


    private List<Review> mReviewData;


    public ReviewRecyclerViewAdapter(List<Review> data){
        mReviewData = data;
    }
    //View Holder constructor
    public static class ViewHolder extends RecyclerView.ViewHolder{


        private final TextView mUserName;
        private final ImageView mReviewScore;
        private final ImageView mReviewImage;
        private final TextView mReviewContent;
        private final TextView mBusinessTitle;


        public ViewHolder(View view){
            super(view);
            mUserName = (TextView)view.findViewById(R.id.userNameRecyclerTextView);
            mReviewScore = (ImageView)view.findViewById(R.id.userScoreImage);
            mReviewImage = (ImageView)view.findViewById(R.id.userImageInput);
            mReviewContent = (TextView)view.findViewById(R.id.reviewContent);
            mBusinessTitle = (TextView)view.findViewById(R.id.businessNameRecyclerTextView);
        }

        //Getters for all recycler view content

        public ImageView getmReviewImage() {
            return mReviewImage;
        }

        public ImageView getmReviewScore() {
            return mReviewScore;
        }

        public TextView getmBusinessTitle() {
            return mBusinessTitle;
        }

        public TextView getmReviewContent() {
            return mReviewContent;
        }

        public TextView getmUserName() {
            return mUserName;
        }
    }

    //Override Methods

    //Create view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reviewrecycler_row, viewGroup,
                false);

        return new ViewHolder(view);
    }

    //Binds data to review holder created above
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.getmReviewContent().setText(mReviewData.get(position).getmContent());

        //set business name according to BusineesID number
        if(mReviewData.get(position).getmBusinessID() == 1){
            viewHolder.getmBusinessTitle().setText("Bloomington Cafe");
        }
        else if(mReviewData.get(position).getmBusinessID() == 2){
            viewHolder.getmBusinessTitle().setText("Bub's Burgers");
        }
        else if(mReviewData.get(position).getmBusinessID() == 3){
            viewHolder.getmBusinessTitle().setText("The Owlery Restaraunt");
        }
        else if(mReviewData.get(position).getmBusinessID() == 4){
            viewHolder.getmBusinessTitle().setText("Malibu Grill");
        }
        else if(mReviewData.get(position).getmBusinessID() == 5){
            viewHolder.getmBusinessTitle().setText("Juannita's");
        }
        else if(mReviewData.get(position).getmBusinessID() == 6){
            viewHolder.getmBusinessTitle().setText("The 3 Amigos");
        }
        else if(mReviewData.get(position).getmBusinessID() == 7){
            viewHolder.getmBusinessTitle().setText("Upland Brewery");
        }
        else if(mReviewData.get(position).getmBusinessID() == 8){
            viewHolder.getmBusinessTitle().setText("Domino's Pizza");
        }
        else if(mReviewData.get(position).getmBusinessID() == 9){
            viewHolder.getmBusinessTitle().setText("Starbucks");
        }
        else if(mReviewData.get(position).getmBusinessID() == 10){
            viewHolder.getmBusinessTitle().setText("Taste of India");
        }

        //I would have selected this by referencing a foreign key but that is not currently possible with how the database is set up
        if(mReviewData.get(position).getmUserID() == 1){
            viewHolder.getmUserName().setText("Sam Night");
        }
        else if(mReviewData.get(position).getmUserID() == 2){
            viewHolder.getmUserName().setText("Michael Jordan");
        }
        else if(mReviewData.get(position).getmUserID() == 3){
            viewHolder.getmUserName().setText("James Bond");
        }
        else if(mReviewData.get(position).getmUserID() == 4){
            viewHolder.getmUserName().setText("Bruce Willis");
        }
        else{
            viewHolder.getmUserName().setText("Jackie Chan");
        }



        //Set reviewImage based on the score given.
        if (mReviewData.get(position).getmScore() == 1){
            viewHolder.getmReviewScore().setImageResource(R.drawable.covid_icon);
        }
        else{
            viewHolder.getmReviewScore().setImageResource(R.drawable.ic_logo);
        }
        //Once we can take pictures using the app this will change
        if(mReviewData.get(position).getmImage() == null){
            viewHolder.getmReviewImage().setImageResource(R.drawable.picupload);
        }
        else if(mReviewData.get(position).getmImage() == "null"){
            viewHolder.getmReviewImage().setImageResource(R.drawable.picupload);
        }
        else{
            try {
                viewHolder.getmReviewImage().setImageURI(Uri.parse(mReviewData.get(position).getmImage()));
            }
            catch(Exception e){
                viewHolder.getmReviewImage().setImageResource(R.drawable.picupload);
                e.printStackTrace();
            }
        }





    }

    //How many items are you showing
    @Override public int getItemCount(){
        return mReviewData.size();
    }
}
