package com.example.aser.careerbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomnav = (BottomNavigationView)findViewById(R.id.nav_menu);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        firebaseAuth = FirebaseAuth.getInstance();

    }
    public void openwishList(View View)
    {
        Intent i = new Intent(this, wishlist.class);
        startActivity(i);
    }

    public void opennews(View View)
    {
        Intent i = new Intent(this, News.class);
        startActivity(i);
    }
    public void openprofile(View View)
    {
        Intent i = new Intent(this,MyProfile.class);
        startActivity(i);
    }
    public void openFindMyUniversty(View View)
    {
        Intent i = new Intent(this,FindMyUniversty.class);
        startActivity(i);
    }
    public void openNotification(View View)
    {
        Intent i = new Intent(this,notification.class);
        startActivity(i);
    }
    public void openHelp(View View)
    {
        Intent i = new Intent(this,help.class);
        startActivity(i);
    }
    public void opencalulator(View View)
    {
        Toast.makeText(this,"its woking",Toast.LENGTH_LONG);
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,login.class) );
    }
    public void logout(View view)
    {

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            finish();
                            Intent intent = new Intent (MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.notification:
                            finish();
                            Intent in = new Intent (MainActivity.this, notification.class);
                            startActivity(in);
                            break;
                        case R.id.news:
                            finish();
                            Intent ine = new Intent (MainActivity.this, News.class);
                            startActivity(ine);
                            break;
                    }
                    return true;
                }

            };



}

