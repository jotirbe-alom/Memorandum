package com.example.memorandum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewholder> {
    Context allInbox;
    ArrayList<Users> usersArrayList;
    public UserAdapter(AllInbox allInbox, ArrayList<Users> usersArrayList) {
        this.allInbox = allInbox;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(allInbox).inflate(R.layout.user_item,parent,false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewholder holder, int position) {

        Users users = usersArrayList.get(position);
        holder.userItemuserName.setText(users.uName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(allInbox, chatWin.class);
                intent.putExtra("nameeee",users.getuName());
                intent.putExtra("userId",users.getUserId());
                allInbox.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return usersArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView userItemuserName;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            userItemuserName = itemView.findViewById(R.id.userItemuserName);

        }
    }
}
