package com.example.administrator.miwok;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String englishWord;
    private String miwokWord;
    private int imageRes = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;


    public Word(String wordMiwok, String wordEnglish, int audioResourceId){

        englishWord = wordEnglish;
        miwokWord = wordMiwok;
        mAudioResourceId = audioResourceId;

    }

    public Word(String wordMiwok, String wordEnglish, int imageId, int audioResourceId){

        englishWord = wordEnglish;
        miwokWord = wordMiwok;
        imageRes = imageId;
        mAudioResourceId = audioResourceId;

    }


    public String getEnglishWord(){
        return englishWord;
    }

    public String getMiwokWord(){
        return miwokWord;
    }

    public int getImageRes(){
        return imageRes;
    }

    public boolean hasImage(){
        return imageRes != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
