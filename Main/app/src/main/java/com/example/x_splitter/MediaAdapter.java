package com.example.x_splitter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {
    ArrayList<String> mediaList;
    Context context;

    public MediaAdapter(Context context, ArrayList<String> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_image_view,parent,false);
        return new MediaAdapter.MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Glide.with(context).load(Uri.parse(mediaList.get(position))).into(holder.mMedia);
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder{
        ImageView mMedia;

        public MediaViewHolder(View itemVIew){
            super(itemVIew);
            mMedia = itemVIew.findViewById(R.id.media);
        }
    }
}
