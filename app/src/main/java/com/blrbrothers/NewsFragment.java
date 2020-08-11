package com.blrbrothers;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements IOnBackPressed {

    private List<Article> articleList;
    private DatabaseReference databaseArticle = FirebaseDatabase.getInstance().getReference("News");
    private RecyclerView recyclerViewArticle;
    private ArticlesRecyclerViewAdapter articlesRecyclerViewAdapter;
    private TextView article_body;
    private ImageView iv_collapsingToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Boolean onShowBody = false;
    private RelativeLayout r1_main,r1_body;
    private RelativeLayout toolbarLayout;
    private ImageView storiesBackButton;
    private ProgressBar progressBar;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initViews(view);
        initialiseList();
        r1_main.setVisibility(View.VISIBLE);
        r1_body.setVisibility(View.GONE);

        storiesBackButton = view.findViewById(R.id.iv_News_Back_Button);
        storiesBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //toolbar.setVisibility(View.VISIBLE);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void initViews(View view){
        recyclerViewArticle = view.findViewById(R.id.recyclerView_article);
        r1_main = view.findViewById(R.id.rl_main);
        r1_body = view.findViewById(R.id.rl_body);
        article_body = view.findViewById(R.id.tv_body);
        iv_collapsingToolbar = view.findViewById(R.id.iv_collapsingToolbar);
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbar);
        toolbarLayout = view.findViewById(R.id.RL_News_Header);
        progressBar = view.findViewById(R.id.pb_stories);
    }


    private void initialiseList(){
        articleList = new ArrayList<>();

        databaseArticle.orderByChild("TimeStamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articleList.clear();
                Log.i("PurohitStories","Fetching articles");
                for(DataSnapshot articleSnapshot : dataSnapshot.getChildren()){
                    Article article = articleSnapshot.getValue(Article.class);
                    articleList.add(article);
                }
                    populateRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("PurohitCheck","Error");
            }
        });

    }


    private void populateRecyclerView(){
        Log.i("PurohitStories","populateRV");
        Gson gson = new Gson();
        String json = gson.toJson(articleList);
        Log.i("PurohitStories FlterLst",json);
        recyclerViewArticle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewArticle.setLayoutManager(layoutManager);
        articlesRecyclerViewAdapter = new ArticlesRecyclerViewAdapter(articleList,NewsFragment.this,getActivity());
        recyclerViewArticle.setAdapter(articlesRecyclerViewAdapter);


    }

    public void showBody(String body, String imageLink, final String title){
//        toolbar.setVisibility(View.GONE);
        toolbarLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        onShowBody = true;
        r1_main.setVisibility(View.GONE);
        r1_body.setVisibility(View.VISIBLE);
        article_body.setText(body);
        Picasso.get().load(imageLink).fit().centerCrop().into(iv_collapsingToolbar, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Collapsed);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded);
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#194769"));

    }

    @Override
    public boolean onBackPressed() {
        if(onShowBody) {
            r1_main.setVisibility(View.VISIBLE);
            r1_body.setVisibility(View.GONE);
            toolbarLayout.setVisibility(View.VISIBLE);
            onShowBody = false;
            return true;
        }else{
            return false;
        }
    }

}
