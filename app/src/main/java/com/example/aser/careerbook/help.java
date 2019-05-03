package com.example.aser.careerbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        BottomNavigationView bottomnav = (BottomNavigationView) findViewById(R.id.nav_menu);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
    }

    public void makecall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:00923157809850"));
        startActivity(intent);
    }
    public void downloadmanual(View view)
    {
        String url = "http://www.example.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void makeaemail(View view)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ITSeries@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "Type Your Message here");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(help.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            finish();
                            Intent intent = new Intent (help.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.notification:
                            finish();
                            Intent in = new Intent (help.this, notification.class);
                            startActivity(in);
                            break;
                        case R.id.news:
                            finish();
                            Intent ine = new Intent (help.this, News.class);
                            startActivity(ine);
                            break;
                    }
                    return true;
                }

            };
}
