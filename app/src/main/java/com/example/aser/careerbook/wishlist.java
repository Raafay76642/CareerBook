package com.example.aser.careerbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wishlist extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Query query;
    private RecyclerView wishRecycler;
    private Wish_Adapter adapter;
    private List<Wishlist_Model> wishList;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        wishRecycler = findViewById(R.id.wishlistRecycler);
        wishRecycler.setHasFixedSize(true);
        wishRecycler.setLayoutManager(new LinearLayoutManager(this));
        wishList = new ArrayList<>();
        adapter = new Wish_Adapter(this, wishList);
        wishRecycler.setAdapter(adapter);
        id=firebaseAuth.getInstance().getCurrentUser().getUid();
        query= FirebaseDatabase.getInstance().getReference("WishList").orderByChild("UID").equalTo(id);
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            wishList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Wishlist_Model wishlist_model = snapshot.getValue(Wishlist_Model.class);
                    wishList.add(wishlist_model);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
