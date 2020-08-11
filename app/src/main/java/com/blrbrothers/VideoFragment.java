package com.blrbrothers;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    ImageView backButton;
    RecyclerView videoRV;
    ArrayList<VideoModel> videos;
    private DatabaseReference videoDBReference;
    private VideoRVAdapter videoRVAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initView(view);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        getVideos();
        return view;
    }

    private void initView(View view){
        backButton=view.findViewById(R.id.iv_Video_Back_Button);
        videoRV = view.findViewById(R.id.rv_video);
        videoDBReference = FirebaseDatabase.getInstance().getReference("Video List");
        videos=new ArrayList<>();
    }

    private void getVideos(){
        videoDBReference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videos.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    videos.add(dataSnapshot1.getValue(VideoModel.class));
                }

                Gson gson = new Gson();
                Log.i("PurohitDarshanCheck",gson.toJson(videos));
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setReverseLayout(true);
                layoutManager.setStackFromEnd(true);
                videoRV.setLayoutManager(layoutManager);
                videoRVAdapter = new VideoRVAdapter(videos,VideoFragment.this,getActivity());
                videoRV.setAdapter(videoRVAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void openWebView(String webViewLink, String templeName, String templeDetails){
        Intent intent = new Intent(getActivity(),YoutubeVideoActivity.class);
        intent.putExtra("yTCode",webViewLink);
        intent.putExtra("templeName",templeName);
        intent.putExtra("templeDetails",templeDetails);
        startActivity(intent);
        /*Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(webViewLink));
        startActivity(implicit);*/
    }

}
