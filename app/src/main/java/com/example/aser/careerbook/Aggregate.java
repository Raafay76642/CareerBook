 package com.example.aser.careerbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

 public class Aggregate extends AppCompatActivity {

     DatabaseReference databaseReference;TextView test1;
     RecyclerView recyclerView;
     Aggri_Adapter aggri_adapter;
     List <Aggri_Model> aggri_model_list;
     private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregate);
        Intent intent = getIntent();
        String Selected = intent.getStringExtra("selected");
        databaseReference = FirebaseDatabase.getInstance().getReference("universities");
        recyclerView = findViewById(R.id.aggriRecycler);
        aggri_model_list = new ArrayList<>();
        test1=findViewById(R.id.testview);
        aggri_adapter = new Aggri_Adapter(this,aggri_model_list);
        recyclerView.setAdapter(aggri_adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.child(Selected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot Snapshot:dataSnapshot.getChildren()){
                        Aggri_Model aggri_model1 = Snapshot.getValue(Aggri_Model.class);
                        aggri_model_list.add(aggri_model1);
                    }
                    aggri_adapter.notifyDataSetChanged();

                }
                else { Toast.makeText(Aggregate.this,"No data found",Toast.LENGTH_LONG).show();}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

test();
    }
     public void test(){
         databaseReference = FirebaseDatabase.getInstance().getReference("universities").child("CSIT").child("-Lca3MC8MUZ60oIDxg3O").child("previousMerit");
         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (dataSnapshot.exists())
                 {

                     test1.setText(String.valueOf(dataSnapshot.getValue(Double.class)));
                 }
                 else
                     Toast.makeText(Aggregate.this,"empty",Toast.LENGTH_LONG).show();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

     }
}


