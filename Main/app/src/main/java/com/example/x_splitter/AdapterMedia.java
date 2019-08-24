package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMedia extends RecyclerView.Adapter<AdapterMedia.MediaViewHolder> {
    private Context context;
    public ArrayList<ModelMedia> data = new ArrayList<>();

    public AdapterMedia(Context context, ArrayList<ModelMedia> data) {
        this.context = context;
        this.data = data;
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder{
        ImageView media;

        public MediaViewHolder(View itemView){
            super(itemView);
            media = itemView.findViewById(R.id.media);
        }
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_see_media,parent,false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
            holder.media.setImageURI(data.get(position).getMedia());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
