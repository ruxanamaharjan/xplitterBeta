package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Profile extends AppCompatActivity {

    private TextView mProfileName;
    private TextView mProfileEmail;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private TextView logout_button;
    Set<Map.Entry<String,String>> friendSet;

    FloatingActionButton fab_add;

    private static final int Activity_num = 4; // for recognizing menu item number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileName=(TextView)findViewById(R.id.profile_name);
        mProfileEmail=(TextView)findViewById(R.id.profile_email);
        logout_button =  findViewById(R.id.logout_button);

        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
      //  String user = firebaseAuth.getCurrentUser().getUid();

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, GetStartedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
                startActivity(intent);
            }
        });

        mDatabase.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String display_name = dataSnapshot.child("username").getValue(String.class);
                String display_email = dataSnapshot.child("email").getValue(String.class);

                mProfileName.setText(display_name);
                mProfileEmail.setText(display_email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,FAB_Menu_page.class));
            }
        });

        if((AddUser.mapAddedUser)!=null){
            friendSet = AddUser.mapAddedUser.entrySet();
//        Map<String,String> map = AddUser.mapAddedUser;
            ArrayList<Map.Entry<String,String>> listOfFren = new ArrayList<Map.Entry<String, String>>(friendSet);
            System.out.println("try it" + listOfFren);

            ArrayList<ModelProfile> friendlist = getFriendList(listOfFren);

            RecyclerView recyclerView = findViewById(R.id.friendlist_recycler_view);
            AdapterProfile adapterProfile = new AdapterProfile(this,friendlist);
            recyclerView.setAdapter(adapterProfile);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


        setBottomNavigationView();
    }

    public static ArrayList<ModelProfile> getFriendList( ArrayList<Map.Entry<String,String>> listOfFren){
        ArrayList<ModelProfile> modelProfiles = new ArrayList<>();
        for(Map.Entry<String,String> entry: listOfFren){
            String key = entry.getKey();
            String value = entry.getValue();
            modelProfiles.add(new ModelProfile(key,value));
        }
        System.out.println("rux" + modelProfiles);
        return modelProfiles;
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(Profile.this, Home.class); // Activity_num = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(Profile.this, Group.class); // Activity_num = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_event:
                        Intent intent3 = new Intent(Profile.this, Event.class); // Activity_num = 3
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_profile:
                        break;

                }
                return false;
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}