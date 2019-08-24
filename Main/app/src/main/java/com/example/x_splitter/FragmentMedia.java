package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMedia extends DialogFragment {
    public FragmentMedia(){
    }

    Context context;
    View view;
    ArrayList<ModelMedia> mediaData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.media_popup, container, false);

        //mediaData = getMediaData();
        RecyclerView recyclerView = view.findViewById(R.id.rv_media_popup);
        AdapterMedia adapterMedia = new AdapterMedia(this.context,mediaData);


        return view;
    }

}
