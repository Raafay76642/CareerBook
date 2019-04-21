package com.example.aser.careerbook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Aggri_Adapter extends RecyclerView.Adapter<Aggri_Adapter.aggri_view_holder> {
   private Context ctx;
    private List <Aggri_Model> aggri_model_list ;





    public Aggri_Adapter(Context ctx, List<Aggri_Model> aggri_model_list) {
        this.ctx = ctx;
        this.aggri_model_list = aggri_model_list;
    }

    @NonNull
    @Override
    public aggri_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.aggri_layout,parent,false);
        return new aggri_view_holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull aggri_view_holder holder, int position) {
        final Aggri_Model aggri_model = aggri_model_list.get(position);
        holder.aggri_uni.setText(aggri_model.univesity);
        holder.aggri_province.setText(aggri_model.province);
        holder.aggri_sector.setText(aggri_model.sector);
        holder.aggri_name.setText(aggri_model.degreeName);
        holder.aggri_previous.setText( Float.toString(aggri_model.previousMerit));
        holder.aggri_expected.setText(Float .toString(aggri_model.expectedMerit));


    }

    @Override
    public int getItemCount() {
        return aggri_model_list.size();
    }




    class aggri_view_holder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView aggri_uni,aggri_sector,aggri_name,aggri_province,aggri_previous,aggri_expected;
        Button add_W_list;
        public aggri_view_holder(View itemView ) {
            super(itemView);
            aggri_name = itemView.findViewById(R.id.degreeName);
            aggri_uni = itemView.findViewById(R.id.universty);
            aggri_sector = itemView.findViewById(R.id.sector);
            aggri_province = itemView.findViewById(R.id.province);
            aggri_previous = itemView.findViewById(R.id.previousYear);
            aggri_expected = itemView.findViewById(R.id.expectedMerit);
            add_W_list = itemView.findViewById(R.id.add_WishList);

        }

        @Override
        public void onClick(View view) {

        }
    }

}
