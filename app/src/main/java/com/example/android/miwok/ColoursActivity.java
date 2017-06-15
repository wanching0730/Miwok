package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColoursActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colours);

        final ArrayList<Word> colours = new ArrayList<Word>();
        colours.add(new Word("red", "merah", R.drawable.color_red, R.raw.color_red));
        colours.add(new Word("black", "hitam", R.drawable.color_black, R.raw.color_black));
        colours.add(new Word("brown", "chocolate", R.drawable.color_brown, R.raw.color_brown));
        colours.add(new Word("yellow", "kuning", R.drawable.color_mustard_yellow, R.raw.color_dusty_yellow));
        colours.add(new Word("white", "putih", R.drawable.color_white, R.raw.color_white));
        colours.add(new Word("green", "hijau", R.drawable.color_green, R.raw.color_green));
        colours.add(new Word("grey", "gray", R.drawable.color_gray, R.raw.color_gray));


       WordAdapter itemsAdapter = new WordAdapter(this, colours, R.color.category_colors);
        ListView listItems = (ListView) findViewById(R.id.coloursView);
        listItems.setAdapter(itemsAdapter);

        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = colours.get(i);
                mediaPlayer = MediaPlayer.create(ColoursActivity.this, word.getAudioResourceId());
                mediaPlayer.start();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
