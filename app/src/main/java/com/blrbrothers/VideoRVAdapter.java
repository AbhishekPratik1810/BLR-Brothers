package com.blrbrothers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoRVAdapter extends RecyclerView.Adapter<VideoRVAdapter.VideoViewHolder> {

    ArrayList<VideoModel> videos;
    VideoFragment videoFragment;
    Context context;

    public VideoRVAdapter(ArrayList<VideoModel> videos, VideoFragment videoFragment, Context context) {
        this.videos = videos;
        this.videoFragment = videoFragment;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_video,parent,false);
        VideoViewHolder darshanViewHolder = new VideoViewHolder(view);
        return darshanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {
        final VideoModel darshan = videos.get(position);
        if(darshan!=null){
            holder.orgName.setText(darshan.title);
            Picasso.get().load(darshan.imageLink).fit().centerCrop().into(holder.orgLogo, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoFragment.openWebView(darshan.webViewLink, darshan.title, darshan.videoDetails);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{
        ImageView orgLogo;
        TextView orgName;
        ProgressBar progressBar;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            orgLogo = itemView.findViewById(R.id.iv_orgLogo_darshanRV);
            orgName = itemView.findViewById(R.id.tv_orgName_darshanRV);
            progressBar = itemView.findViewById(R.id.pb_darshanRV);
        }
    }
}
