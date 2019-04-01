package com.blot.bastien.test4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListHistory extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<List> list = null;
    SharedPreferences mPreferences;


    public ListHistory( Context context, int resource, ArrayList<List> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;


    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        final List lists = list.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);
        }

        /* Layout specifics */
        TextView dayNameText =  convertView.findViewById(R.id.mt_day_textview);
        Button moodComment =  convertView.findViewById(R.id.mt_day_button);
        RelativeLayout backgroundColor = convertView.findViewById(R.id.mt_view_relative_layout);
        LinearLayout mLinearLayout =  convertView.findViewById(R.id.mt_history_linearlayout);

        return convertView;

    }
}