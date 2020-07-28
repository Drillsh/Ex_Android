package com.example.mp3_project_lsh;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Player extends Fragment implements View.OnClickListener{

    private ImageView ivAlbum;
    private TextView tvPlayCount, tvArtist, tvTitle, tvCurrentTime, tvDuration;
    private SeekBar seekBar;
    private ImageButton ibPlay,ibPrevious, ibNext, ibLike;

    private MainActivity mainActivity;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private int index;
    private MusicData musicData = new MusicData();
    private ArrayList<MusicData> likeArrayList = new ArrayList<>();
    private MusicAdapter musicAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.player, container, false);

        ivAlbum = view.findViewById(R.id.ivAlbum);
        tvPlayCount = view.findViewById(R.id.tvPlayCount);
        tvArtist = view.findViewById(R.id.tvArtist);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        tvDuration = view.findViewById(R.id.tvDuration);
        seekBar = view.findViewById(R.id.seekBar);
        ibPlay = view.findViewById(R.id.ibPlay);
        ibPrevious = view.findViewById(R.id.ibPrevious);
        ibNext = view.findViewById(R.id.ibNext);
        ibLike = view.findViewById(R.id.ibLike);

        ibPlay.setOnClickListener(this);
        ibPrevious.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibLike.setOnClickListener(this);

        musicAdapter = mainActivity.getMusicAdapter_like();
        musicAdapter.setMusicList(likeArrayList);

        seekBarChangeMethod();

        return view;
    }

    //버튼 클릭 이벤트처리 함수
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ibPlay :
                if(ibPlay.isActivated()){
                    mediaPlayer.pause();
                    ibPlay.setActivated(false);
                }else{
                    mediaPlayer.start();
                    ibPlay.setActivated(true);
                    setSeekBarThread();
                }
                break;
            case R.id.ibPrevious :
                mediaPlayer.stop();
                mediaPlayer.reset();
                try {
                    if(index == 0){
                        index = mainActivity.getMusicDataArrayList().size();
                    }
                    index--;
                    setPlayerData(index);

                } catch (Exception e) {
                    Log.d("ubPrevious",e.getMessage());
                }
                break;
            case R.id.ibNext :
                try {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    if(index == mainActivity.getMusicDataArrayList().size()-1){
                        index= -1;
                    }
                    index++;
                    setPlayerData(index);

                } catch (Exception e) {
                    Log.d("ibNext",e.getMessage());
                }
                break;
            case R.id.ibLike :

                if(ibLike.isActivated()){
                    ibLike.setActivated(false);
                    musicData.setLiked(0);
                    likeArrayList.remove(musicData);
                    musicAdapter.notifyDataSetChanged();

                }else{
                    ibLike.setActivated(true);
                    musicData.setLiked(1);
                    likeArrayList.add(musicData);
                    musicAdapter.notifyDataSetChanged();
                }

                break;
            default:break;
        }
    }


    //시크바 변경에 관한 함수
    private void seekBarChangeMethod() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    //시크바 스레드 에 관한 함수
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setSeekBarThread(){
        Thread thread = new Thread(new Runnable() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

            @Override
            public void run() {
                while(mediaPlayer.isPlaying()){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvCurrentTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                        }
                    });
                    SystemClock.sleep(100);
                }
            }
        });
        thread.start();
    }


    // 플레이어 화면 처리
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPlayerData(int pos) {
        index = pos;

        mediaPlayer.stop();
        mediaPlayer.reset();

        MusicAdapter musicAdapter = new MusicAdapter(mainActivity);
        musicData = mainActivity.getMusicDataArrayList().get(pos);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        tvTitle.setText(musicData.getTitle());
        tvArtist.setText(musicData.getArtist());
        tvPlayCount.setText(String.valueOf(musicData.getPlayCount()));
        tvDuration.setText(simpleDateFormat.format(Integer.parseInt(musicData.getDuration())));

        if(musicData.getLiked() == 1){
            ibLike.setActivated(true);
        }else{
            ibLike.setActivated(false);
        }

        Bitmap albumImg = musicAdapter.getAlbumImg(mainActivity, Integer.parseInt(musicData.getAlbumArt()), 200);
        if(albumImg != null){
            ivAlbum.setImageBitmap(albumImg);
        }else{
            ivAlbum.setImageResource(R.drawable.album_default);
        }

        // 음악 재생
        Uri musicURI = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,musicData.getId());
        try {
            mediaPlayer.setDataSource(mainActivity, musicURI);
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(Integer.parseInt(musicData.getDuration()));
            ibPlay.setActivated(true);

            setSeekBarThread();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        if(index == mainActivity.getMusicDataArrayList().size()-1){
                            index= -1;
                        }
                        index++;
                        musicData.setPlayCount(1);
                        setPlayerData(index);

                    } catch (Exception e) {
                        Log.d("onCompletion",e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
