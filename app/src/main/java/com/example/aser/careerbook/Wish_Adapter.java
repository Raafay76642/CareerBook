package com.example.aser.careerbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Wish_Adapter extends RecyclerView.Adapter<Wish_Adapter.WishlistViewHolder> {
    private Context mCtx;
    private List<Wishlist_Model> wishList;

    public Wish_Adapter(Context mCtx, List<Wishlist_Model> wishList) {
        this.mCtx = mCtx;
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.wishlist_design, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Wishlist_Model wishlist_model = wishList.get(position);
        holder.textViewUni.setText("University "+ wishlist_model.university);
        holder.textViewDep.setText("Program: " + wishlist_model.department);

    }


    @Override
    public int getItemCount() {
        return wishList.size();
    }
    class WishlistViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUni, textViewDep;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUni = itemView.findViewById(R.id.Wuniversty);
            textViewDep = itemView.findViewById(R.id.WdegreeName);

        }
    }
}
