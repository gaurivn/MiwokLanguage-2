package com.example.miwoklanguage;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mediaplay;

    private AudioManager audiomanage;

    //private Handler handler = new Handler();
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        // Stop playback and release resources
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mediaplay.pause();
                        // Reset player to the start of the file
                        mediaplay.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        // Resume playback
                        mediaplay.start();
                    }
                };
            };

    private MediaPlayer.OnCompletionListener mcomplisten = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TextView textView = new TextView(getActivity());
       // textView.setText(R.string.hello_blank_fragment);
       // return textView;
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /**  Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */
        audiomanage = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView  = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                // Request audio focus for playback
                int result = audiomanage .requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now
                    mediaplay = MediaPlayer.create(getActivity(), word.getAudioresid());
                    mediaplay.start();
                    mediaplay.setOnCompletionListener(mcomplisten);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaplay != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaplay.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaplay = null;

            // Abandon audio focus when playback complete
            audiomanage.abandonAudioFocus(afChangeListener);
        }
    }

}
