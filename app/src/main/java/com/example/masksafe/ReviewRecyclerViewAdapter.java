package com.example.masksafe;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {
    //will become an of a data class with multiple data types
    private String[] mReviewData;

    public ReviewRecyclerViewAdapter(String [] data){
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


}
