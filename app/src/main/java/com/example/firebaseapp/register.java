package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class register extends AppCompatActivity {
    EditText email,pass;
    Button reg;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.emailsignup);
        pass=findViewById(R.id.passsignup);
        reg=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();
        reg.setOnClickListener(view -> {
            createUser();
        });

    }
    private void createUser() {
        String em = email.getText().toString();
        String pa = pass.getText().toString();
        if (TextUtils.isEmpty(em)) {
            email.setError("Cannot be Empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pa)) {
            pass.setError("Cannot be Empty");
            pass.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this,"Succesful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this,login.class));
                    }
                    else{
                        Toast.makeText(register.this,"UnSuccesful"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}