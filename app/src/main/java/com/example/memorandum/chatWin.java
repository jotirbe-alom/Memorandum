package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class chatWin extends AppCompatActivity {
    String  reciverUid,receiverName,SenderUID;
    String senderRoom, receiverRoom;
    RecyclerView mmessangerAdpter;
    TextView receieverUserName;
    EditText messagebox;
    ImageView sendicon;

    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    ArrayList<msgModelClass> messagessArrayList;
    messagesAdapter messagesAdapterr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_win);

        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        receiverName = getIntent().getStringExtra("nameeee");
        reciverUid = getIntent().getStringExtra("userId");

        messagessArrayList = new ArrayList<>();

        mmessangerAdpter = findViewById(R.id.chatwinRecycleViewer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        mmessangerAdpter.setLayoutManager(linearLayoutManager);
        messagesAdapterr = new messagesAdapter(chatWin.this,messagessArrayList);
        mmessangerAdpter.setAdapter(messagesAdapterr);




        receieverUserName = findViewById(R.id.receiverusername);

        SenderUID = firebaseAuth.getUid();

        senderRoom = SenderUID+reciverUid;
        receiverRoom = reciverUid+SenderUID;

        messagebox = findViewById(R.id.chatwinmessage);
        sendicon = findViewById(R.id.chatwinsendimg);

        receieverUserName.setText(""+receiverName);

        //DatabaseReference reference = database.getReference().child("users").child(firebaseAuth.getUid());
        DatabaseReference chatreference = database.getReference().child("users").child("senderRoom").child("messages");

        chatreference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagessArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                   msgModelClass messages = dataSnapshot.getValue(msgModelClass.class);
                   messagessArrayList.add(messages);
               }
               messagesAdapterr.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        sendicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messagebox.getText().toString();
                if(message.isEmpty()){
                    Toast.makeText(chatWin.this,"enter the message first",Toast.LENGTH_SHORT).show();
                    return;
                }
                messagebox.setText("");
                Date date = new Date();
                msgModelClass messagess = new msgModelClass(message,SenderUID, date.getTime());
                database = FirebaseDatabase.getInstance();
                database.getReference().child("chats").child("senderRoom").child("messages").push().setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        database.getReference().child("chats").child("receiverRoom").child("messages").push().setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                });

            }
        });

    }
}