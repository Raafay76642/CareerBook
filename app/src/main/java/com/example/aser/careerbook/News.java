package com.example.aser.careerbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {
    private RecyclerView recyclerView;
    private news_adapter adapter;
    private List<News_model> newsModelList;
    private ProgressDialog progressDialog;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsModelList = new ArrayList<>();
       adapter= new news_adapter(this,newsModelList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);
        mref= FirebaseDatabase.getInstance().getReference("News");
        get_news();


    }

    public void openhome(View View)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void get_news(){
        progressDialog.setMessage("Loding ...");
        progressDialog.show();
      Query query=FirebaseDatabase.getInstance().getReference("News").orderByChild("tittle");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsModelList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        News_model news_model = snapshot.getValue(News_model.class);
                        newsModelList.add(news_model);
                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
