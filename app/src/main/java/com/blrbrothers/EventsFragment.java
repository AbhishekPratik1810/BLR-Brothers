package com.blrbrothers;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment  {



    RecyclerView recyclerView;
    EventsRVAdapter eventsRVAdapter;
    ArrayList<EventModel> events;
    DatabaseReference databaseReference;
    ImageView backButton;
    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        initView(view);
        databaseReference=FirebaseDatabase.getInstance().getReference("Events");
        databaseReference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    events.add(dataSnapshot1.getValue(EventModel.class));
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setReverseLayout(true);
                layoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(layoutManager);
                eventsRVAdapter=new EventsRVAdapter(events);
                recyclerView.setAdapter(eventsRVAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void initView(View view){
     recyclerView=view.findViewById(R.id.rv_Events);
     backButton=view.findViewById(R.id.iv_Events_Back_Button);
     events=new ArrayList<>();
    }

    /*private void initView(View view){
        storiesRecyclerView = view.findViewById(R.id.rv_Events);
        articles = new ArrayList<>();
        latestArticles = new ArrayList<>();
        home_body = view.findViewById(R.id.home_article_body);
        home_main = view.findViewById(R.id.home_main);
        article_body =  view.findViewById(R.id.tv_home_body);
        collapsingToolbarLayout = view.findViewById(R.id.home_collapsingToolbar);
        iv_collapsingToolbar = view.findViewById(R.id.iv_home_collapsingToolbar);
        home_BodyProgressBar = view.findViewById(R.id.pb_home_story_body);
    }


    private void getLatestStories(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("News");
        databaseReference.orderByChild("TimeStamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    articles.add(dataSnapshot1.getValue(Article.class));
                    Log.i("PurohitStoriesHome","ArticleName " +dataSnapshot1.getValue(Article.class).Title +"Time " + dataSnapshot1.getValue(Article.class).TimeStamp);

                }
                *//*for(int i=articles.size()-1;i>(articles.size()-3);i--) {
                    latestArticles.add(articles.get(i));
                    Log.i("PurohitStoriesHome ","LatestArticleName at"+i+" " +articles.get(i).Title +"Time " + articles.get(i).TimeStamp);
                }*//*
                latestArticles.addAll(articles);
                storiesRecyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                storiesRecyclerView.setLayoutManager(linearLayoutManager);
                //snapHelperStoriesRecyclerView.attachToRecyclerView(storiesRecyclerView);
                articlesRecyclerViewAdapter = new HomeArticlesRecyclerViewAdapter(latestArticles,getActivity(),EventsFragment.this);
                storiesRecyclerView.setAdapter(articlesRecyclerViewAdapter);
                //storiesRecyclerView.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, color, color));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void homeShowBody(Article article){
        home_BodyProgressBar.setVisibility(View.VISIBLE);
        onShowBody = true;
        home_main.setVisibility(View.GONE);
        home_body.setVisibility(View.VISIBLE);
        article_body.setText(article.Body);
        Picasso.get().load(article.ImageLink).fit().centerCrop().into(iv_collapsingToolbar, new Callback() {
            @Override
            public void onSuccess() {
                home_BodyProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        collapsingToolbarLayout.setTitle(article.Title);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Collapsed);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded);
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#194769"));
    }

    @Override
    public boolean onBackPressed() {
        if(onShowBody) {
            home_main.setVisibility(View.VISIBLE);
            home_body.setVisibility(View.GONE);
            onShowBody = false;
            return true;
        }else{
            return false;
        }
    }*/
}
