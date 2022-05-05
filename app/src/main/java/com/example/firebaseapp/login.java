package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText lemail,lpass;
    Button login,lsignup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lemail=findViewById(R.id.email);
        lpass=findViewById(R.id.pass);
        login=findViewById(R.id.submit);
        lsignup=findViewById(R.id.register);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(view -> {
            loginUSer();
        });
        lsignup.setOnClickListener(view -> {
            startActivity(new Intent(login.this,register.class));
        });
    }
    private void loginUSer(){
        String lem=lemail.getText().toString();
        String lpa=lpass.getText().toString();
        if(TextUtils.isEmpty(lem)){
            lemail.setError("Cannot be Empty");
            lemail.requestFocus();
        }
        else if(TextUtils.isEmpty(lpa)){
            lpass.setError("Cannot be Empty");
            lpass.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(lem,lpa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this,"Registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(login.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}