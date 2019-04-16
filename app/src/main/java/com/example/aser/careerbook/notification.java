package com.example.aser.careerbook;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class notification extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Notification_adapter adapter;
    private List<Notification_Model> notiModelList;
    private ProgressDialog progressDialog;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notiModelList = new ArrayList<>();
        adapter = new Notification_adapter(this, notiModelList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);
        mref= FirebaseDatabase.getInstance().getReference("Notification");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("UOL");
        get_notification();

    }
    public void get_notification(){
        progressDialog.setMessage("Loding ...");
        progressDialog.show();
        Query query=FirebaseDatabase.getInstance().getReference("Notification").orderByChild("title");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notiModelList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Notification_Model notificationModel = snapshot.getValue(Notification_Model.class);
                        notiModelList.add(notificationModel);
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
