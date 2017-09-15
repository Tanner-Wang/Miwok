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

    ViewHolder viewHolder;

    public WordsAdapter(Activity context, ArrayList<Word> words, int colorResourceId, int playBackGround) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
        playImageBackGroundId = playBackGround;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.textContainer = convertView.findViewById(R.id.text_container);
            viewHolder.miwokTextView = (TextView) convertView.findViewById(R.id.miwokWord);
            viewHolder.englishTextView = (TextView) convertView.findViewById(R.id.englishNumber);
            viewHolder.imageBackGround = (ImageView) convertView.findViewById(R.id.play);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Word currentWord = getItem(position);

        if (currentWord.hasImage()) {

            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setImageResource(currentWord.getImageRes());
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }

        int color1 = ContextCompat.getColor(getContext(),mColorResourceId);
        viewHolder.textContainer.setBackgroundColor(color1);

        viewHolder.miwokTextView.setText(currentWord.getMiwokWord());

        viewHolder.englishTextView.setText(currentWord.getEnglishWord());

        int color2 = ContextCompat.getColor(getContext(),playImageBackGroundId);
        viewHolder.imageBackGround.setBackgroundColor(color2);

        return convertView;

    }

    static class ViewHolder{
        private ImageView imageView;
        private View textContainer;
        private TextView miwokTextView;
        private TextView englishTextView;
        private ImageView imageBackGround;
    }


}
