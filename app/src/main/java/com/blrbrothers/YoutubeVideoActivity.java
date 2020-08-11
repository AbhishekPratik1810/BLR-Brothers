package com.blrbrothers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YoutubeVideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    ImageView darshanYTBackButton;
    private String youtubeVideoCode, templeNameReceived, templeDetailsReceived;
    private TextView templeDetails, templeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);
        Intent intent = getIntent();
        youtubeVideoCode = intent.getStringExtra("yTCode");
        templeNameReceived = intent.getStringExtra("templeName");
        templeDetailsReceived = intent.getStringExtra("templeDetails");
        youTubePlayerView = findViewById(R.id.youtubePlayerDarshan);
        darshanYTBackButton = findViewById(R.id.iv_YTVideo_Back_Button);
        templeName = findViewById(R.id.darshanTempleName);
        templeDetails = findViewById(R.id.darshanTempleDetails);

        templeName.setText(templeNameReceived);
        templeDetails.setText(templeDetailsReceived);

        Log.i("World",""+youtubeVideoCode);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.i("HelloSuccess",""+youtubeVideoCode);

                youTubePlayer.loadVideo(youtubeVideoCode);
                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        if(b){
                            //headerToolbar.setVisibility(View.INVISIBLE);
                        }else{
                            //headerToolbar.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.i("HelloFailure",""+youTubeInitializationResult);
            }
        };

        darshanYTBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        youTubePlayerView.initialize("AIzaSyC7UpHXQr0jftKCB_jSvIlqITpG4xZSct4",onInitializedListener);

    }

}
