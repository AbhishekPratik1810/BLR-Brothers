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

import java.util.List;

public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ArticleViewHolder> {

    private List<Article> filteredList;
    private NewsFragment newsFragment;
    private Context context;

    public ArticlesRecyclerViewAdapter(List<Article> filteredList, NewsFragment newsFragment, Context context) {
        this.filteredList = filteredList;
        this.newsFragment = newsFragment;
        this.context = context;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_news,viewGroup,false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder articleViewHolder, int i) {
        final Article article = filteredList.get(i);
        articleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)context).showBody(position,article.getImageLink(),article.getTitle());
                newsFragment.showBody(article.Body,article.ImageLink,article.Title);
            }
        });
        articleViewHolder.tv_title.setText(article.Title);
        Picasso.get().load(article.ImageLink).fit().centerCrop().into(articleViewHolder.iv_article, new Callback() {
            @Override
            public void onSuccess() {
                articleViewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_category;
        ImageView iv_article;
        ProgressBar progressBar;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_category = itemView.findViewById(R.id.tv_category);
            iv_article = itemView.findViewById(R.id.iv_article);
            progressBar = itemView.findViewById(R.id.pb_article);
        }
    }
}
