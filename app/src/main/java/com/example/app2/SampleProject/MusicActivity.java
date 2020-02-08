package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    Thread thread;
    ImageView imgPlay;
    MediaPlayer mediaPlayer;
    int currentPosition;
    androidx.appcompat.widget.AppCompatSeekBar seekBar;
    TextView txtTime;
    Handler handler;
    Timer timer;

    String url = "http://dl.nicmusic.net/nicmusic/021/077/Mohsen%20Yeganeh%20-%20Moohat%20%28Remix%29.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        setupViews();

        //playAutoMusic();

        mediaPlayer = new MediaPlayer();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MusicActivity.this, "ready to play", Toast.LENGTH_SHORT).show();
                                    //imgPlay.setImageResource(R.drawable.ic_pause_circle_filled_blue);
                                    mediaPlayer.start();
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    imgPlay.setImageResource(R.drawable.ic_pause_circle_filled_blue);
                    mediaPlayer.pause();
                    currentPosition = mediaPlayer.getCurrentPosition();
                } else {
                    imgPlay.setImageResource(R.drawable.ic_play_circle_filled_blue);
                    mediaPlayer.seekTo(currentPosition);
                    mediaPlayer.start();
                    txtTime.setText(showMusicTime(mediaPlayer.getDuration()));
                    seekBar.setMax(mediaPlayer.getDuration());
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    }, 0, 1000);
                }
            }
        });

        timer = new Timer();



    }

    private void setupViews() {
        handler = new Handler();

        imgPlay = (ImageView) findViewById(R.id.img_musicPlayer_play);
        txtTime = (TextView) findViewById(R.id.txt_music_time);
        seekBar = (androidx.appcompat.widget.AppCompatSeekBar) findViewById(R.id.sb_music_seekbar);
    }

    private void playAutoMusic() {
//        mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
//            mediaPlayer.prepareAsync();
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    Toast.makeText(MusicActivity.this, "ready to play", Toast.LENGTH_SHORT).show();
//                    //imgPlay.setImageResource(R.drawable.ic_pause_circle_filled_blue);
//                    mediaPlayer.start();
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.purge();
        timer.cancel();
        mediaPlayer.release();
    }

    private String showMusicTime(int duration) {
        int sec = (int) duration / 1000;
        int min = sec / 60;
        sec %= 60;

        return (min + ":" + sec);
    }
}
