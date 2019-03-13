package com.blot.bastien.test4;

import com.google.gson.Gson;

import java.io.Reader;

public class Smile {
    private int images;
    private int colors;
    private int musics;

    public Smile (int images, int colors, int musics){
        this.images = images;
        this.colors = colors;
        this.musics = musics;
    }

    public int getImages() {
        return images;
    }

    public int getColors() {
        return colors;
    }

    public int getMusics() {
        return musics;
    }

    public String tojson(){
        return new Gson().toJson(this);
    }


    @Override
    public String toString() {
        return "Smile{" +
                "images=" + images +
                ", colors=" + colors +
                ", musics=" + musics +
                '}';
    }
}

