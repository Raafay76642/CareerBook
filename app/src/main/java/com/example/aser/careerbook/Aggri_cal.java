package com.example.aser.careerbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Aggri_cal extends AppCompatActivity {
    DatabaseReference mref;
    FirebaseAuth firebaseAuth;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggri_cal);
        key=firebaseAuth.getInstance().getCurrentUser().getUid();
        mref=FirebaseDatabase.getInstance().getReference("Profile").child(key);

    }


}
