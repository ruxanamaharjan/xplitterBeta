package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddGroup extends AppCompatActivity {

    private static final String TAG = AddGroup.class.getSimpleName();
    ImageButton btn_back;
    ArrayList<Integer> groupFriend = new ArrayList<>();
    TextView save;
    EditText groupName;
    DatabaseReference dbReference;
    EditText friendName;
    ArrayList sb;
    StringBuffer sbb =null;
    String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupName = (EditText) findViewById(R.id.edit_text_group_name);
        friendName = (EditText) findViewById(R.id.edit_text_friend_name);
        dbReference = FirebaseDatabase.getInstance().getReference("Groups");
        id = dbReference.push().getKey();
        HashMap<String, Object> totalFriends = new HashMap<String, Object>();

        btn_back = findViewById(R.id.image_button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGroup.super.onBackPressed();
            }
        });

        save = (TextView)findViewById(R.id.textView_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group_name = groupName.getText().toString();
                if (TextUtils.isEmpty(group_name)) {
                    Toast.makeText(AddGroup.this, "Enter the group name", Toast.LENGTH_SHORT).show();
                }
                else {
                    //AddGroupInfo groupInfo = new AddGroupInfo(totalFriends);
                    dbReference.child(id).child(groupName.getText().toString()).child("Members").setValue(sb).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        GroupInfo groupInfo = new GroupInfo(id, groupName.getText().toString());
                                        FirebaseDatabase.getInstance().getReference("GroupName").child(id).setValue(groupInfo);
                                        FirebaseDatabase.getInstance().getReference("GroupEvent").child(id).child("Events").setValue("");
                                        Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent i = new Intent(AddGroup.this, Group.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }, 1500);
                                    } else {
                                        Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
         });

        //my part_2

        ArrayList<ModelAddGroup> friendLists= retrieve();
        RecyclerView recyclerView = findViewById(R.id.rv_add_friend);
        AdapterAddGroup adapterAddGroup = new AdapterAddGroup(this,friendLists);
        recyclerView.setAdapter(adapterAddGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab_add_friend = findViewById(R.id.fab_addfriend);
        fab_add_friend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sb = new ArrayList();
                sbb= new StringBuffer();

                for(ModelAddGroup m : adapterAddGroup.checkedFriends){
                    sb.add(m.getusername());
                    sbb.append(m.getusername());
                    sbb.append("\n");
                }

                if(adapterAddGroup.checkedFriends.size()>0){
                    Toast.makeText(AddGroup.this,sbb.toString(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddGroup.this,"Please select friends",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // end my part_2
    }


    public ArrayList<ModelAddGroup> retrieve(){
        ArrayList<ModelAddGroup> friendLists = new ArrayList<>();
        friendLists.clear();
//        friendLists.add(0, "Choose Friend");
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String name = (String) Objects.requireNonNull(data).get("username");
                    friendLists.add(new ModelAddGroup(name, false));
                }
                System.out.println(friendLists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return friendLists;
    }

}