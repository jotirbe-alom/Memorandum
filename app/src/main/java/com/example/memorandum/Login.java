package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button createbtn, loginbtn;
    EditText lemail,lpass;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        createbtn = findViewById(R.id.btn02);
        loginbtn = findViewById(R.id.btnLogin);
        lemail = findViewById(R.id.logEmail);
        lpass = findViewById(R.id.logPassword);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = lemail.getText().toString();
                String spass = lpass.getText().toString();

                auth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())  {
                            try{
                                Intent intent = new Intent(Login.this,Menu.class);
                                startActivity(intent);
                                finish();
                            }catch(Exception e){
                                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


            }
        });


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }
}