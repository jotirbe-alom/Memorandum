package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllInbox extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    UserAdapter adapter;
    RecyclerView userIdRecyclerView;
    DatabaseReference reference;
    ArrayList<Users> usersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_inbox);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = database.getReference().child("users");

        usersArrayList = new ArrayList<>();

        userIdRecyclerView = findViewById(R.id.userIdRecyclerView);
        userIdRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(AllInbox.this,usersArrayList);
        userIdRecyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if(auth.getCurrentUser()==null){
            Intent intent = new Intent(AllInbox.this, Login.class);
            startActivity(intent);
        }

    }
}