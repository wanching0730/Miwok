package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father", "Vater", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "Mutter", R.drawable.family_father, R.raw.family_mother));
        words.add(new Word("brother", "Bruter", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("sister", "Schwester", R.drawable.family_older_sister, R.raw.family_older_sister));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.familyView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
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
