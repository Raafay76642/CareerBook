package com.example.aser.careerbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

public class MyProfile extends AppCompatActivity {

    EditText matricTm,matricOm,interTm,interOm,testOm,BTm,BOm,fname,emailtv;
    Spinner testName,spinnerMatric,spinnerInter;
    FirebaseAuth firebaseAuth;
    DatabaseReference profileRefrence;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        id = firebaseAuth.getInstance().getCurrentUser().getUid();
        BottomNavigationView bottomnav = (BottomNavigationView)findViewById(R.id.nav_menu);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        fname = findViewById(R.id.fullName);
        emailtv=findViewById(R.id.EmailET);
        matricTm=findViewById(R.id.matricTM);
        matricOm = findViewById(R.id.matricOM);
        interTm=findViewById(R.id.interTM);
        interOm=findViewById(R.id.interOM);
        BTm = findViewById(R.id.btm);
        BOm = findViewById(R.id.bom);
        testOm = findViewById(R.id.testMarks);
        testName=findViewById(R.id.SpinnerTestType);
        spinnerInter = findViewById(R.id.SpinnerInter);
        spinnerMatric=findViewById(R.id.SpinnerMatric);
        firebaseAuth = FirebaseAuth.getInstance();
        profileRefrence = FirebaseDatabase.getInstance().getReference("Profile");
       getProfile();
        unEditabe();
    }
    public void openhome(View View)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            finish();
                            Intent intent = new Intent (MyProfile.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.notification:
                            finish();
                            Intent in = new Intent (MyProfile.this, notification.class);
                            startActivity(in);
                            break;
                        case R.id.news:
                            finish();
                            Intent ine = new Intent (MyProfile.this, News.class);
                            startActivity(ine);
                            break;
                    }
                    return true;
                }

            };

public void saveProfile(View view)
{

    double MatricTm = Double.parseDouble(matricTm.getText().toString());
    double MatricOm = Double.parseDouble(matricOm.getText().toString());
    double InterTm = Double.parseDouble(interTm.getText().toString());
    double InterOm = Double.parseDouble(interOm.getText().toString());
    double TestOm = Double.parseDouble(testOm.getText().toString());
    String TestName = testName.getSelectedItem().toString();
   double Agrigate = findagrigate();
    String MatricType = spinnerMatric.getSelectedItem().toString();
    String InterType = spinnerInter.getSelectedItem().toString();
    String Sfname = fname.getText().toString();
    String Semail = emailtv.getText().toString();
    ProfileModel profileModel = new ProfileModel(MatricTm,MatricOm,InterTm,InterOm,TestOm,TestName,Sfname,Semail,MatricType,InterType,Agrigate);
        profileRefrence.child(id).setValue(profileModel);
        final Toast toast = Toast.makeText(MyProfile.this, "Data is Saved", Toast.LENGTH_LONG);
        toast.show();
        unEditabe();
    }
    public void getProfile()
    {
        id = firebaseAuth.getInstance().getCurrentUser().getUid();
       profileRefrence.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists()){

                   ProfileModel getDataModel = dataSnapshot.getValue(ProfileModel.class);
                   fname.setText(getDataModel.fullName);
                   emailtv.setText(getDataModel.email);
                   matricTm.setText(Double.toString(getDataModel.matricTm));
                   matricOm.setText(Double.toString(getDataModel.matricOm));
                   interTm.setText(Double.toString(getDataModel.interTm));
                   interOm.setText(Double.toString(getDataModel.interOm));
                   testOm.setText(Double.toString(getDataModel.testOm));
               }


           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
    public double findagrigate()
    {
        double agrigate;
        double InterOm = Double.parseDouble(interOm.getText().toString());
        double TestOm = Double.parseDouble(testOm.getText().toString());
        if (testName.getSelectedItem().toString().equals("Ecat"))
        {

            agrigate= ((InterOm * 70)/1100) + ((TestOm * 30)/400);
        }
        else {
            agrigate = 0;

        }

        return agrigate;
    }

    public void unEditabe()
    {
        matricOm.setEnabled(false);
        matricTm.setEnabled(false);
        interTm.setEnabled(false);
        interOm.setEnabled(false);
        testOm.setEnabled(false);
        BTm.setEnabled(false);
        BOm.setEnabled(false);
        testName.setEnabled(false);
        fname.setEnabled(false);
        emailtv.setEnabled(false);
    }
    public void editable(View view)
    {
        matricOm.setEnabled(true);
        matricTm.setEnabled(true);
        interTm.setEnabled(true);
        interOm.setEnabled(true);
        testOm.setEnabled(true);
        BTm.setEnabled(true);
        BOm.setEnabled(true);
        testName.setEnabled(true);
        fname.setEnabled(true);
        emailtv.setEnabled(true);

    }






}
