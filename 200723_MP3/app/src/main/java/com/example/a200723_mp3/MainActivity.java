package com.example.a200723_mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView musicList;
    private ImageView imgAlbumArt;
    private TextView txtTitle;
    private TextView txtArtist;
    private ProgressBar progCircle;
    private ImageButton btnPrev;
    private ImageButton btnPlay;
    private ImageButton btnStop;
    private ImageButton btnNext;

//    private MusicData selectedMP3; //리스트뷰에서 선택된 파일
    private String sdcardPath;
    private int flag_PlayPause;
    private int currentPosition;
    private ArrayList<MusicData> musicDataArrayList = new ArrayList<>();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 아이디 등록
        findViewByIdFunc();

        // 외부 저장장치 권한 요청
        requestPermissionFunc();

        // sdcard 절대경로 저장
        sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

        // sdcard에 있는 파일중 mp3파일만 골라낸다
        loadMP3FileFromSDCard();
        MusicAdapter adapter = new MusicAdapter(getApplicationContext());
        adapter.setMusicList(musicDataArrayList);
        // 어댑터 설정
        musicList.setAdapter(adapter);

        //

        progCircle.setVisibility(View.INVISIBLE);

        //리스트뷰에서 보여줄 라디오버튼(그룹) 설정
        musicList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        musicList.setAdapter(adapter);
        musicList.setItemChecked(0, true);

        // 리스트뷰 이벤트 선택 아이템을 등록한다
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                currentPosition = position;
//                selectedMP3 = musicDataArrayList.get(position);
            }
        });

    }

    private void loadMP3FileFromSDCard() {
        // 파일의 메타데이터 구하기위한 MediaMetadataRetriever 인스턴스 생성
        MediaMetadataRetriever media = new MediaMetadataRetriever();

        // 확장자 구하기 위한 MimeTypeMap 인스턴스 생성
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        File[] fileList = new File(sdcardPath).listFiles();

        for (File file : fileList) {

            // 확장자 가져오기
            String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());

            // 파일 이름
            String fileName = file.getName();

            // 확장자가 .mp3 인 파일들만 걸러냄
            if (extension.equals("mp3")) {
                // 데이터 소스 지정
                media.setDataSource(sdcardPath + fileName);
                byte[] data = media.getEmbeddedPicture();
                Bitmap bitmap = null;
                if (data != null) {
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                } else {
                    ImageView defaultImg = new ImageView(this);
                    defaultImg.setImageResource(R.drawable.music);
                    BitmapDrawable drawable = (BitmapDrawable) defaultImg.getDrawable();
                    bitmap = drawable.getBitmap();
                }

                String title = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artist = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

                MusicData musicData = new MusicData(title, artist, bitmap, fileName);
                musicDataArrayList.add(musicData);
            }
        }
    }

    // 외부 저장장치 권한 요청
    private void requestPermissionFunc() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    private void findViewByIdFunc() {
        musicList = findViewById(R.id.musicList);
        imgAlbumArt = findViewById(R.id.imgAlbumArt);
        txtTitle = findViewById(R.id.txtTitle);
        txtArtist = findViewById(R.id.txtMusicArtist);
        progCircle = findViewById(R.id.progCircle);
        btnPrev = findViewById(R.id.btnPrev);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnNext = findViewById(R.id.btnNext);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                try {

                    if (flag_PlayPause == 0) {
                        mediaPlayer.setDataSource(sdcardPath + musicDataArrayList.get(currentPosition).getFileName());
                        //음악 준비하는 시간을 기다려줘야함
                        mediaPlayer.prepare();
                        startPlayer();


                    } else if (flag_PlayPause == 1) {
                        mediaPlayer.pause();
                        btnPlay.setImageResource(R.drawable.play);
                        flag_PlayPause = 2;

                    } else {
                        startPlayer();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnStop:

                stopPlayer();


                break;

            case R.id.btnPrev:

                stopPlayer();

                try {
                    currentPosition--;

                    if (currentPosition < 0) {
                        currentPosition = musicDataArrayList.size() - 1;
                    }

                    mediaPlayer.setDataSource(sdcardPath + musicDataArrayList.get(currentPosition).getFileName());
                    mediaPlayer.prepare();
                    startPlayer();

                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.btnNext:

                stopPlayer();

                try {
                    currentPosition++;

                    if (currentPosition == musicDataArrayList.size()) {
                        currentPosition = 0;
                    }

                    mediaPlayer.setDataSource(sdcardPath + musicDataArrayList.get(currentPosition).getFileName());
                    mediaPlayer.prepare();
                    startPlayer();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;


            default:
                break;
        }
    }

    // 정지 세팅
    private void stopPlayer() {
        mediaPlayer.stop();
        mediaPlayer.reset();

        txtTitle.setText("제 목");
        txtArtist.setText("아티스트");

        flag_PlayPause = 0;
        btnPlay.setImageResource(R.drawable.play);
        progCircle.setVisibility(View.INVISIBLE);
    }

    // 재생 세팅
    private void startPlayer() {
        MusicData selectedMusic = musicDataArrayList.get(currentPosition);
        mediaPlayer.start();

        txtTitle.setText(selectedMusic.getTitle());
        txtArtist.setText(selectedMusic.getArtist());
        imgAlbumArt.setImageBitmap(selectedMusic.getAlbumArt());

        progCircle.setVisibility(View.VISIBLE);
        btnPlay.setImageResource(R.drawable.pause);
        flag_PlayPause = 1;
    }
}
