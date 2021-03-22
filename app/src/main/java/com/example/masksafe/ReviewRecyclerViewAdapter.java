package com.example.masksafe;

import android.media.Image;
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
        //Will reference Review class once Google Maps Api key is installed and I can select multiple businesses on the main activity
        if(mReviewData.get(position).getmBusinessID() == 1){
            viewHolder.getmBusinessTitle().setText("Bloomington Cafe");
        }
        else{
            viewHolder.getmBusinessTitle().setText("Bub's Bugers");
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
        viewHolder.getmReviewImage().setImageResource(R.drawable.picupload);

    }

    //How many items are you showing
    @Override public int getItemCount(){
        return mReviewData.size();
    }
}
