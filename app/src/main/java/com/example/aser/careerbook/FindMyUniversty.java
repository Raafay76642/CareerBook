package com.example.aser.careerbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FindMyUniversty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_universty);
    }

    public void openhome(View View)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void opennews(View View)
    {
        Intent i = new Intent(this, News.class);
        startActivity(i);
    }

    public void open_engineering(View view)
    {
        Intent i = new Intent(this, Aggregate.class);
        i.putExtra("selected","Engineering");
        startActivity(i);
    }
    public void open_business(View view)
    {
        Intent i = new Intent(this, Aggregate.class);
        i.putExtra("selected","Bussnies");
        startActivity(i);
    }
    public void open_gs(View view)
    {
        Intent i = new Intent(this, Aggregate.class);
        i.putExtra("selected","GScience");
        startActivity(i);

    }
    public void open_it(View view)
    {
        Intent i = new Intent(this, Aggregate.class);
        i.putExtra("selected","CSIT");
        startActivity(i);
    }
}
