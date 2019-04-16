package com.example.aser.careerbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class news_adapter extends RecyclerView.Adapter<news_adapter.NewsViewHolder> {

    private Context mCtx;
    private List<News_model> newsModelList;

    public news_adapter(Context mCtx, List<News_model> newsModelList) {
        this.mCtx = mCtx;
        this.newsModelList = newsModelList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.news_design, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News_model newsModel = newsModelList.get(position);
        holder.textViewTitle.setText(newsModel.tittle);
        holder.textViewDate.setText(newsModel.publishDate);
        holder.textViewUni.setText(newsModel.universty);

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDate, textViewUni;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDate = itemView.findViewById(R.id.text_date);
            textViewUni = itemView.findViewById(R.id.text_view_uni);
        }
    }}
