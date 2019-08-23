package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class FragmentTransac extends Fragment {
    View view;
    Context context;
    static String currentGroupId;
    static String currentEventId;
    String groupId;
    String groupName;
    String eventId;
    String ID;

//    public FragmentTransac(String currentGroupID, String currentEventID) {
//    }

//    public FragmentTransac(String currentGroupId, String currentEventId)
//    {
//        this.currentGroupId =currentGroupId;
//        this.currentEventId = currentEventId;
//    }
//
//    public String getCurrentGroupId() {
//        return currentGroupId;
//    }
//
//    public String getCurrentEventId() {
//        return currentEventId;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_transac_fragment,container,false);

        groupId = AdapterHomeEvent.grpId;
        groupName = AdapterHomeEvent.grpName;
        eventId = AdapterHomeEvent.id;




getTransactionData();
        return view;
    }

    public void getTransactionData(){
        ArrayList<ModelTransaction> modelTransactions = new ArrayList<>();
modelTransactions.clear();
//        String currentEventID = intent.getStringExtra("currentEventID");
//        String currentGroupID = intent.getStringExtra("currentGroupID");

        FirebaseDatabase.getInstance().getReference("Transactions")
                .child(groupId)
                .child(eventId)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String amount = snapshot.child("amount").getValue().toString();
                    String category = snapshot.child("category").getValue().toString();
                    String date = snapshot.child("date").getValue().toString();
                modelTransactions.add(new ModelTransaction(date,amount,category));

            }
                RecyclerView recyclerView = view.findViewById(R.id.event_transac_recycler_view);
                AdapterTransaction adapter = new AdapterTransaction(getActivity(),modelTransactions);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        modelTransactions.add(new ModelTransaction("21-10-2013","Lunch","2300","Neha","Food"));
//        modelTransactions.add(new ModelTransaction("20-11-2014","Skirt","130","Sammy","Dress"));
//        modelTransactions.add(new ModelTransaction("01-01-2013","Bus ticket","230","Srijal","Fair"));
//        modelTransactions.add(new ModelTransaction("22-02-2014","Dinner","1000","Ruxana","Food"));
//        modelTransactions.add(new ModelTransaction("11-10-2014","Lunch","1300","Nam","Food"));
//        modelTransactions.add(new ModelTransaction("12-09-2017","Shirt","300","Sneha","Dress"));
//        modelTransactions.add(new ModelTransaction("13-03-2017","Pant","1200","Nena","Dress"));
//        modelTransactions.add(new ModelTransaction("24-02-2017","Milk","45","Nishan","Food"));


    }
}