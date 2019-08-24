package com.example.x_splitter;

import android.net.Uri;

public class ModelMedia {
    private Uri media;

    public ModelMedia(Uri media) {
        this.media = media;
    }

    public Uri getMedia() {
        return media;
    }

    public void setMedia(Uri media) {
        this.media = media;
    }
}
