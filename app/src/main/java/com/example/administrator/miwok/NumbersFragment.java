package com.example.administrator.miwok;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private final String LOAD_BITMAP_URL = "http://android.developer.com/Tanner/picture/001";


    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };


    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    LruCache<String, Bitmap> mCache;


    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取内存容量大小
        int memoryCache = (int) Runtime.getRuntime().maxMemory()/1024;
        //设置缓存容量上限，为
        int bitmapCache = memoryCache/8;
        mCache = new LruCache<String, Bitmap>(bitmapCache){
            @Override
            protected int sizeOf(String BitmapName, Bitmap bitmap) {
                return super.sizeOf(BitmapName, bitmap);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti","one",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("otiiko","two",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("tollkosu","three",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("oyyisa","four",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("massokka","five",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("temmokka","six",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("kenekaku","seven",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("kawinta","eight",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("wo'e","nine",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("na'aacha","ten",R.drawable.number_ten,R.raw.number_ten));


        WordsAdapter adapter = new WordsAdapter(getActivity(), words, R.color.category_numbers,R.color.category_numbers);

        ListView itemList = (ListView) rootView.findViewById(R.id.list);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word word = words.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private void addBitmap(String BitmapName, Bitmap bitmap){
        if (mCache.get(BitmapName) != null){
            mCache.put(BitmapName, bitmap);
        }
    }

    private void loadBitmap(String bitmapName) {
        if (mCache.get(bitmapName) != null) {
            //TODO: 加载图片
        }
        //如果缓存中不存在该图片就触发后台线程进行下载
        else {
            new MyAsyncTask().execute(LOAD_BITMAP_URL, bitmapName);
        }
    }

        class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected void onPreExecute() {
                Toast.makeText(getContext(), "a asynctask is starting.", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected Bitmap doInBackground (String... params){
                Bitmap mBitmap = null;
                try {
                    URL mUrl = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
                    connection.setRequestMethod("get");
                    connection.setReadTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addBitmap(params[1], mBitmap);
                return mBitmap;

            }
        }
}
