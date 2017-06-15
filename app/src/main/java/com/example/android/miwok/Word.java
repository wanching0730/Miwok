package com.example.android.miwok;

import android.media.Image;
import android.provider.ContactsContract;

/**
 * Created by USER on 24/5/2017.
 */

public class Word {
    private String miwokTranslation, defaultTranslation;
    private  int ImageResourceId = No_IMAGE_PROVIDED;
    private static final int No_IMAGE_PROVIDED = -1;
    private int audioResourceId;

    public Word (String miwokTranslation, String defaultTranslation, int audioResourceId){
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceId = audioResourceId;

    }

    public Word (String miwokTranslation, String defaultTranslation, int ImageResourceId, int audioResourceId){
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.ImageResourceId = ImageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }

    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public int getImageResourceId(){
        return ImageResourceId;
    }

    public boolean checkImage(){
        return ImageResourceId != No_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){ return audioResourceId;}
}
