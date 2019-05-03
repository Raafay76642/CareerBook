 package com.example.aser.careerbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

 public class Aggregate extends AppCompatActivity {

     DatabaseReference databaseReference;TextView test1;
     RecyclerView recyclerView;
     Aggri_Adapter aggri_adapter;
     List <Aggri_Model> aggri_model_list;
     FirebaseAuth firebaseAuth;
     String key;
     Button bapply,filterbtn;
     String filterCategory,filterValue;
     Spinner filter,value;
     Query query1;
   double user_agg;
   String Selected;
     private RecyclerView.LayoutManager mLayoutMaanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregate);
        firebaseAuth=FirebaseAuth.getInstance();
        Intent intent = getIntent();
         Selected = intent.getStringExtra("selected");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.aggriRecycler);
        aggri_model_list = new ArrayList<>();
        filterbtn = findViewById(R.id.opendialouge);
        test1=findViewById(R.id.testview);
        aggri_adapter = new Aggri_Adapter(this,aggri_model_list);
        recyclerView.setAdapter(aggri_adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Aggregate.this);
                dialog.setContentView(R.layout.filterdialouge);
                Button dialogButton = (Button) dialog.findViewById(R.id.applydioloudefilter);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // filter=(Spinner)dialog.findViewById(R.id.spinnerFilter);
                        filter =  (Spinner)dialog.findViewById(R.id.spinnerValue);
                     //   filterCategory = filter.getSelectedItem().toString();
                        filterValue = filter.getSelectedItem().toString();
                        test();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }


     public void test(){
        key=firebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile").child(key).child("uetAgrigate");
         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists())
                 {
                    user_agg = dataSnapshot.getValue(double.class);
                 }
                 else
                     Toast.makeText(Aggregate.this,"empty",Toast.LENGTH_LONG).show();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

         switch (filter.getSelectedItem().toString()){
             case ("Previous Merit"):
             {
                 query1=FirebaseDatabase.getInstance().getReference("universities").child(Selected).orderByChild("previousMerit").startAt(user_agg);
                 break;

             }
             case ("Expected Merit"):
             {
                 query1=FirebaseDatabase.getInstance().getReference("universities").child(Selected).orderByChild("expectedMerit").startAt(user_agg);
                 break;
             }
             default:
             {
                 query1=FirebaseDatabase.getInstance().getReference("universities").child(Selected).orderByChild("previousMerit");
                 break;
             }
         }
         query1.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if(dataSnapshot.exists())
                 {
                     aggri_model_list.clear();
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


     }
//public void getProfile()
//{
//    key = firebaseAuth.getInstance().getCurrentUser().getUid();
//    databaseReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if(dataSnapshot.exists()){
//
//                ProfileModel getDataModel = dataSnapshot.getValue(ProfileModel.class);
//                test1.setText(Double.toString(getDataModel.uetAgrigate));
////                String str = String.valueOf(getDataModel.uetAgrigate);
////                test1.setText(str);
//
//            }
//
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    });
//
//}
     public void setfilterValue()
     {

     }

}

