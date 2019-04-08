
package com.blot.bastien.test4;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HistoryActivity extends AppCompatActivity {
    private ListView mHistoryListView;
    private TextView mHistoryTextView;
    private SharedPreferences mPreferences;
    ArrayList<Smile> strings = new ArrayList<Smile>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mHistoryListView = findViewById(R.id.mt_listview_layout);


        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Liste des hummeurs utilisateurs sur 7 jours

        Map<String, ?> allEntries = mPreferences.getAll();
        Map<String, String> hashString = new TreeMap<>(Collections.reverseOrder());


        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (!entry.getKey().contains("_comment")) {
                hashString.put(entry.getKey(), (String) entry.getValue());
            }


        }

        System.out.println(hashString);




        for (TreeMap.Entry<String, String> entry2 : hashString.entrySet()) {
            String cle = entry2.getKey();
            String value = entry2.getValue();
            Gson gson = new Gson();
            Smile smile = gson.fromJson(value, Smile.class);
            smile.setDay(cle);
            strings.add(smile);


        }
        ListHistory listHistory = new ListHistory(getApplicationContext(), R.layout.list_history, strings);
        mHistoryListView.setAdapter(listHistory);


    }



    //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryActivity.this,
    //android.R.layout.simple_list_item_1, listmoods);
    //mHistoryListView.setAdapter(adapter);



}





