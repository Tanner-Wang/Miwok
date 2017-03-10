package com.example.administrator.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordsAdapter extends ArrayAdapter<Word> {

     private int mColorResourceId;

    private int playImageBackGroundId;



    public WordsAdapter(Activity context, ArrayList<Word> words, int colorResourceId, int playBackGround) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
        playImageBackGroundId = playBackGround;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {

            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageRes());
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color1 = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color1);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokWord);
        miwokTextView.setText(currentWord.getMiwokWord());

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishNumber);
        englishTextView.setText(currentWord.getEnglishWord());

        ImageView imageBackGround = (ImageView) listItemView.findViewById(R.id.play);
        int color2 = ContextCompat.getColor(getContext(),playImageBackGroundId);
        imageBackGround.setBackgroundColor(color2);

        return listItemView;


    }


}
