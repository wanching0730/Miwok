package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.UserDictionary;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;

public class NumbersActivity extends AppCompatActivity {

    //int count = 0;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener(){
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        public void onAudioFocusChange(int focusChange){
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "eins", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "zwei", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "drei", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "vier", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "funf", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "sechs", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "seben", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "acht", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "neun", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "zehn", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.numbersView);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                   // audioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        audioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }
}
       /* while(count < words.size()) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(count));
            rootView.addView(wordView);
            count++;

        }*/




