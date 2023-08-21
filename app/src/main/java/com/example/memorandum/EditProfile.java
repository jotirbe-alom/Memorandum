package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    DatabaseReference reference;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        et1 = findViewById(R.id.editProfileName);
        et2 = findViewById(R.id.editProfileUsername);
        et3 = findViewById(R.id.editProfilePassword);
        et4 = findViewById(R.id.editProfileReTypePassword);


        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child("userId").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
              if(task.isSuccessful()){
                  DataSnapshot dataSnapshot = task.getResult();
                  String firstname = String.valueOf(dataSnapshot.child("firstName").getValue());
                  String lastname = String.valueOf(dataSnapshot.child("lastName").getValue());
                  String username = String.valueOf(dataSnapshot.child("userName").getValue());

                  String FullName = firstname+lastname;
                  et1.setText(FullName);
                  et2.setText(username);


              }
            }
        });

    }
}