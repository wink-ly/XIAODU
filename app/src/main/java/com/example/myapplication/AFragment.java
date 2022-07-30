package com.example.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import java.io.File;

public class AFragment extends Fragment {
    private MediaPlayer player;
    private File file;
    private ImageView imageView;
    private boolean isPause = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.zhou);
        final ImageButton playmusic = view.findViewById(R.id.play);
        file = new File("/sdcard/music.mp3");

        if(file.exists()) {
            player = MediaPlayer.create(getActivity(), Uri.parse(file.getAbsolutePath()));
        }else{
            Toast.makeText(getActivity(),"要播放的音乐找不到",Toast.LENGTH_SHORT).show();
            return;
        }
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play();
            }
        });
        playmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",0f,360.0f);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(10000);
                animator.setRepeatCount(-1);
                
                if (player.isPlaying()&&!isPause){
                    player.pause();
                    isPause = true;
                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.play,null));
                }else {
                    player.start();
                    animator.start();
                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.stop,null));
                    isPause = false;
                }
            }
        });
    }

    @Override
    public void onDestroy()
    {
        if (player.isPlaying()){
            player.stop();
        }
        player.release();
        super.onDestroy();
    }
    private void play()
    {
        try {
            player.reset();
            player.setDataSource(file.getAbsolutePath());
            player.prepare();
            player.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
