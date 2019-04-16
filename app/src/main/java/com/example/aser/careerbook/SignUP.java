package com.example.aser.careerbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUP extends AppCompatActivity {
    EditText EdittextEmail,EdittextPassword,Edittextrepassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EdittextEmail = (EditText) findViewById(R.id.email);
        EdittextPassword = (EditText) findViewById(R.id.Mypassword);
        Edittextrepassword = (EditText) findViewById(R.id.MypasswordRe);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

    }
    public void opensignIn(View view)
    {
     startActivity(new Intent(this,login.class));

    }


    public void registerUser(View view)
    {
        String email = EdittextEmail.getText().toString().trim();
        String pass = EdittextPassword.getText().toString().trim();
        String repass = Edittextrepassword.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //password is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pass.equals(repass))
        {
            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("Registring User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignUP.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(SignUP.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }
}
