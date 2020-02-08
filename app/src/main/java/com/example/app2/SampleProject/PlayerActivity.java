package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.app2.R;

public class PlayerActivity extends AppCompatActivity {

    VideoView videoView;
    AppCompatSeekBar seekBar;
    ImageView imgPlay;
    ImageView imgStop;
    boolean isPlaying = false;

    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        setupViews();

        autoPlay();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    imgPlay.setImageResource(R.drawable.ic_play_circle_filled_blue);
                    videoView.pause();
                    currentPosition = videoView.getCurrentPosition();
                    isPlaying = false;
                } else {
                    videoView.seekTo(currentPosition);
                    imgPlay.setImageResource(R.drawable.ic_pause_circle_filled_blue);
                    videoView.start();
                    isPlaying = true;
                }
            }
        });

        imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoView.stopPlayback();
                videoView.seekTo(0);
                seekBar.setProgress(0);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int duration = videoView.getDuration();
                float percent = progress / 100f;
                float time = duration * percent;
                videoView.seekTo((int) time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void autoPlay() {

        Uri uri = Uri.parse("android.resource://" + getPackageName() +
                "/" + R.raw.smart_select);

        videoView.setVideoURI(uri);

        videoView.start();

        imgPlay.setImageResource(R.drawable.ic_pause_circle_filled_blue);

        isPlaying = true;
    }

    private void setupViews() {

        videoView = (VideoView) findViewById(R.id.vv_player_videoPlayer);
        imgPlay = (ImageView) findViewById(R.id.img_player_play);
        imgStop = (ImageView) findViewById(R.id.img_player_stop);
        seekBar = (AppCompatSeekBar) findViewById(R.id.sb_player_seekbar);
    }
}
