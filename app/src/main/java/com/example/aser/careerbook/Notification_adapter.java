package com.example.aser.careerbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class Notification_adapter extends RecyclerView.Adapter<Notification_adapter.NotiViewHolder> {

    private Context mCtx;
    private List<Notification_Model> notiModelList;

    public Notification_adapter(Context mCtx, List<Notification_Model> notiModelList) {
        this.mCtx = mCtx;
        this.notiModelList = notiModelList;
    }

    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.notifi_design, parent, false);
        return new NotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        Notification_Model notification_model= notiModelList.get(position);
        holder.textViewTitle.setText(notification_model.title);
        holder.textViewData.setText(notification_model.message);

    }

    @Override
    public int getItemCount() {
        return notiModelList.size();
    }

    class NotiViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewData;

        public NotiViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.not_title);
            textViewData = itemView.findViewById(R.id.not_data);
        }
    }}
