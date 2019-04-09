package com.blot.bastien.test4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ListHistory extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Smile> list = null;
    private SharedPreferences mPreferences;


    public ListHistory(Context context, int resource, ArrayList<Smile> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Smile lists = list.get(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);
        }




        /* Layout specifics */
        ImageView mood = convertView.findViewById(R.id.mt_list_mood);
        Button moodComment = convertView.findViewById(R.id.mt_list_button);
        TextView day = convertView.findViewById(R.id.mt_list_textview);
        RelativeLayout backgroundColor = convertView.findViewById(R.id.mt_view_relative_layout);
        LinearLayout mLinearLayout = convertView.findViewById(R.id.mt_history_linearlayout);


        day.setText(lists.getDay());
        backgroundColor.setBackgroundColor(ContextCompat.getColor(context, lists.getColors()));
        mood.setImageResource(lists.getImages());

        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Map<String, ?> allEntries = mPreferences.getAll();
        Map<String, String> hashString = new TreeMap<>(Collections.reverseOrder());


        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().contains("_comment")) {
                hashString.put(entry.getKey(), (String) entry.getValue());

                for (TreeMap.Entry<String, String> entry2 : hashString.entrySet()) {
                    String cle = entry2.getKey();
                    String value = entry2.getValue();

                    final String preferenceComment = value;

                    moodComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, preferenceComment, Toast.LENGTH_LONG).show();
                        }
                    });

                    if (preferenceComment == null) {
                        moodComment.setVisibility(View.GONE);
                    }

                }
            }

            final int mScreenWidth = Smile.loadInt(context, "SCREEN_WIDTH", 0);
            final int mScreenHeight = Smile.loadInt(context, "SCREEN_HEIGHT", 0);

            if (lists.getColors() == R.color.faded_red) {
                backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 58) / 100, mScreenHeight / 7));
            }

            if (lists.getColors() == R.color.warm_grey) {
                backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 68) / 100, mScreenHeight / 7));

            }

            if (lists.getColors() == R.color.cornflower_blue_65) {
                backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 78) / 100, mScreenHeight / 7));

            }

            if (lists.getColors() == R.color.light_sage) {
                backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 88) / 100, mScreenHeight / 7));

            }

            if (lists.getColors() == R.color.banana_yellow) {
                backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(mScreenWidth, mScreenHeight / 7));

            }

        }
        return convertView;
    }
}












