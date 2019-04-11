package com.blot.bastien.test4;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.Reader;

import static android.content.Context.MODE_PRIVATE;

public class Smile {
    private int images;
    private int colors;
    private int musics;
    private String day;
    private String comment;

    public Smile (int images, int colors, int musics, String comment){
        this.images = images;
        this.colors = colors;
        this.musics = musics;
        this.comment = comment;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Smile{" +
                "images=" + images +
                ", colors=" + colors +
                ", musics=" + musics +
                ", day='" + day + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    //Enregistrement de la taille de l'ecran du smartphone

    private  static final String PREFS_NAME = "PRES_NAME";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public static void saveInt(Context context, String key, int intValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, intValue);
        editor.apply();
    }

    public static   int loadInt( Context context, String key, int intDefaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, intDefaultValue);
    }
}



