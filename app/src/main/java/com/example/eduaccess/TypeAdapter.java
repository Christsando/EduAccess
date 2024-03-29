package com.example.eduaccess;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.VideoTypeViewHolder> {
    Context context;
    private ArrayList<Video> videoList;

    // Constructor
    public TypeAdapter(Context context, ArrayList<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    public class VideoTypeViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textTeacher;
        ImageView imageThumbnail;

        public VideoTypeViewHolder(View itemView) {
            super(itemView);
            this.imageThumbnail = itemView.findViewById(R.id.videoThumbnail);
            this.textTitle = itemView.findViewById(R.id.videoTitle);
            this.textTeacher = itemView.findViewById(R.id.videoTeacher);

            // Set click listener for the item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle item click here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Video clickedVideo = videoList.get(position);

                        // Create an Intent to start the VideoActivity
                        Intent intent = new Intent(context, VideoActivity.class);

                        // Pass data to the VideoActivity
                        intent.putExtra("videoLink", clickedVideo.getVideoLink());
                        intent.putExtra("videoId", clickedVideo.getVideoId());
                        intent.putExtra("videoTitle", clickedVideo.getVideoTitle());
                        intent.putExtra("videoTeacher", clickedVideo.getVideoTeacher());

                        // Start the VideoActivity
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public VideoTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_type, parent, false);
        return new VideoTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoTypeViewHolder holder, int position) {
        Video video = videoList.get(position);

        // Load image using Glide from the URL in the Video object
        String thumbnailUrl = video.getImageLink();
        Glide.with(context).load(Uri.parse(thumbnailUrl)).into(holder.imageThumbnail);

        // Set data to views
        holder.textTitle.setText(video.getVideoTitle());
        holder.textTeacher.setText(video.getVideoTeacher());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
