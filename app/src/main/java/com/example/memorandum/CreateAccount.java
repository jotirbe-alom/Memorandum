package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    Button createbutton;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference reference;

    EditText fstName, lstName, cemail,cpass,rcpass,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        createbutton = findViewById(R.id.CreateBtn);
        fstName = findViewById(R.id.firstName);
        lstName = findViewById(R.id.lastName);
        userName = findViewById(R.id.userName);
        cemail = findViewById(R.id.regEmail);
        cpass = findViewById(R.id.regPassword);
        rcpass = findViewById(R.id.regReTyPass);

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();

                String s1 = fstName.getText().toString();
                String s2 = lstName.getText().toString();
                String s3 = userName.getText().toString();
                String s4 = cemail.getText().toString();
                String s5 = cpass.getText().toString();

                auth.createUserWithEmailAndPassword(s4,s5).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id = task.getResult().getUser().getUid();
                        reference = database.getReference().child("users").child(id);
                        Users users = new Users(id,s1,s2,s3,s4,s5);
                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(CreateAccount.this,Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                });

            }
        });








    }
}