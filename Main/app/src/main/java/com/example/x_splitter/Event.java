package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Event extends AppCompatActivity {
    FloatingActionButton fab_add;
    String GN;
    String GID;
    String EID;
    String GGID;
    EditText searchEvent;
    private static final int Activity_num = 3; // for recognizing menu item number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Event.this,FAB_Menu_page.class));
            }
        });

        searchEvent = findViewById(R.id.edit_text_search);
        ArrayList<ModelHomeEvent> event = getEventData();
        ArrayList<ModelHomeEvent> event2 = getEventData2();


        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        AdapterHomeEvent adapterHomeEvent = new AdapterHomeEvent(this,event, event2);
        recyclerView.setAdapter(adapterHomeEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchEvent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterHomeEvent.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        setBottomNavigationView();
    }

    public ArrayList<ModelHomeEvent> getEventData(){
        ArrayList<ModelHomeEvent> modelHomeEvents = new ArrayList<>();
        modelHomeEvents.clear();
        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> eventdata = (Map<String, Object>) snapshot.getValue();
                    String eventname = (String) Objects.requireNonNull(eventdata).get("eventName");
                    GID = snapshot.child("groupID").getValue().toString();
                    EID = snapshot.child("id").getValue().toString();

                    FirebaseDatabase.getInstance().getReference("GroupName").child(GID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Map<String, Object> groupdata = (Map<String, Object>) dataSnapshot.getValue();
                                GN = (String) Objects.requireNonNull(groupdata).get("groupName");
                                GGID = (String) Objects.requireNonNull(groupdata).get("id");
                            modelHomeEvents.add(new ModelHomeEvent(eventname, GN, "Not Settled", GGID , "123"));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return modelHomeEvents;
    }

    public ArrayList<ModelHomeEvent> getEventData2(){
        ArrayList<ModelHomeEvent> modelHomeEvents2 = new ArrayList<>();
        modelHomeEvents2.clear();
        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> eventdata = (Map<String, Object>) snapshot.getValue();
                    String eventname = (String) Objects.requireNonNull(eventdata).get("eventName");
                    GID = (String) Objects.requireNonNull(eventdata).get("groupID");
                    EID = (String) Objects.requireNonNull(eventdata).get("id");
                    System.out.println("Aunty:"+EID);
                    modelHomeEvents2.add(new ModelHomeEvent(EID));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return modelHomeEvents2;
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(Event.this, Home.class); // Activity_num = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(Event.this, Group.class); // Activity_num = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_event:
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(Event.this, Profile.class); // Activity_num = 4
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
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