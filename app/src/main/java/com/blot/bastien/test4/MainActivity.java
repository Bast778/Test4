
package com.blot.bastien.test4;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static final String Tag = MainActivity.class.getSimpleName();
    private ImageButton mNoteButton;
    private ImageButton mHistoryButton;
    private VerticalViewPager mVerticalViewPager;
    private MyCustomPageAdapter myCustomPagerAdapter;
    private Table mTable;
    private SharedPreferences mPreferences;
    private String mCurrentMoodComment;
    private String mMainYesterdayComment;
    private int mScreenWidth;
    private String SCREEN_WIDTH = "SCREEN_WIDTH";
    private int mScreenHeight;
    private String SCREEN_HEIGHT = "SCREEN_HEIGHT";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creation des Smiley avec couleurs + images + musiques
        mTable = new Table();
        final Smile happy = new Smile(R.drawable.smiley_happy, R.color.light_sage, R.raw.happy);
        Smile superhappy = new Smile(R.drawable.smiley_super_happy, R.color.banana_yellow, R.raw.super_happy);
        Smile sad = new Smile(R.drawable.smiley_sad, R.color.faded_red, R.raw.sad);
        Smile normal = new Smile(R.drawable.smiley_normal, R.color.cornflower_blue_65, R.raw.normal);
        Smile disappointed = new Smile(R.drawable.smiley_disappointed, R.color.warm_grey, R.raw.disappointed);
        // Ajout des Smiley + couleurs + musiques dans la classe Smile
        mTable.addSmile(happy);
        mTable.addSmile(superhappy);
        mTable.addSmile(sad);
        mTable.addSmile(normal);
        mTable.addSmile(disappointed);
        // Mes preferences enregistrés
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Mon Vertical Viewpager
        mVerticalViewPager = findViewById(R.id.verticalViewPager);
        mVerticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
                String key = dateFormat.format(new Date(System.currentTimeMillis()));
                Log.d(Tag, key);
                Smile smile = mTable.smiles.get(position);
                MediaPlayer.create(getApplicationContext(), smile.getMusics()).start();
                mPreferences.edit().putString(key, smile.tojson()).apply();

            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // History Button
        mHistoryButton = findViewById(R.id.mt_history_button);
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Click the History button
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
        // Note Button
        mNoteButton = findViewById(R.id.mt_note_button);
        // Click the Note Button
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.Mood);

                /* Set up the input */
                final EditText input = new EditText(MainActivity.this);
                /* Specify the type of input expected; caps for new sentences */
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                builder.setView(input);
                // Affichage du commentaire de l'utilisateur pendant 24h
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
                String key4 = simpleDateFormat.format(new Date(System.currentTimeMillis())) + "_comment";
                String preferenceComment = mPreferences.getString(key4, null);
                if (preferenceComment != null) {
                    input.setText(preferenceComment);
                }


                /* "Ok"  button */
                builder.setPositiveButton(R.string.Positivebutton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.Positive, Toast.LENGTH_SHORT).show();
                        // Enregistrement du commentaire du jour
                        mCurrentMoodComment = input.getText().toString();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
                        String key2 = simpleDateFormat.format(new Date(System.currentTimeMillis())) + "_comment";
                        Log.d(Tag, key2);
                        mPreferences.edit().putString(key2, mCurrentMoodComment).apply();
                    }
                });

                /* "Cancel"  button */
                builder.setNegativeButton(R.string.NegativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.Negative, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });
        myCustomPagerAdapter = new MyCustomPageAdapter(MainActivity.this, mTable);
        mVerticalViewPager.setAdapter(myCustomPagerAdapter);


        // Enregistrement de la date du jour à l'ouverture de l'application
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
        String key3 = format.format(new Date(System.currentTimeMillis()));
        String preferenceMood = mPreferences.getString(key3, null);
        if (preferenceMood != null) {
            Gson gson = new Gson();
            Smile smile = gson.fromJson(preferenceMood, Smile.class);
            int index = mTable.getSmileIndex(smile);
            mVerticalViewPager.setCurrentItem(index);

        }

        /* ScreenWidth in pixel */
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;

        System.out.println(mScreenWidth + mScreenHeight);
        Smile.saveInt(MainActivity.this, SCREEN_WIDTH, mScreenWidth);
        Smile.saveInt(MainActivity.this, SCREEN_HEIGHT, mScreenHeight);
    }
}


