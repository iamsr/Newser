package com.example.shubhamr.newser.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhamr.newser.Interface.ClickListenerInterface;
import com.example.shubhamr.newser.ModelClass.News;
import com.example.shubhamr.newser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private List<News> newsList;
    private ClickListenerInterface clickListeners = null;

    public NewsRecyclerViewAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView newsImage;
        public TextView newsHeading, newsDescription;

        public NewsViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            newsImage = view.findViewById(R.id.newsImage);
            newsHeading = view.findViewById(R.id.newsHeading);
            newsDescription = view.findViewById(R.id.newsDescription);
        }

        @Override
        public void onClick(View view) {
            if (clickListeners != null) {
                clickListeners.itemClicked(view, getAdapterPosition());
            }


        }

    }


    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_recyclerview_layout, parent, false);
        return new NewsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        // Retrieving news object from list and setting it in respected textView and imageView
        News news = newsList.get(position);

        // If description present then only change

        if(!news.getDescription().equals("null")){

            holder.newsDescription.setText(news.getDescription());

        }

        // If title present then only change

        if(!news.getTitle().equals("null")) {

            holder.newsHeading.setText(news.getTitle());

        }

        // If image present then only load image

        if(news.getImageURL()!=null||!news.getImageURL().equals("null")||!(news.getImageURL().length()<3)){

            Picasso.get().load(news.getImageURL()).fit().into(holder.newsImage);
        }



    }



    // Return number of sources in list
    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public News getNewsFromList(int index){
        return newsList.get(index);
    }

    // Setting listener
    public void setClickListeners(ClickListenerInterface clickListeners){
        this.clickListeners = clickListeners;
    }


}