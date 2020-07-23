package com.example.a200723_mp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MusicData> musicList = new ArrayList<>();
    private ImageView imgAlbumArt;
    private TextView txtTitle;
    private TextView txtArtist;

    public MusicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // 뷰 객체화
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = layoutInflater.inflate(R.layout.music_layout, null);
        }

        // 뷰 id 찾기
        findViewByIdFunc(view);

        // 컴포넌트에 데이터 입력
        setDataAtComponent(position);


        return view;
    }

    private void setDataAtComponent(int position) {
        MusicData musicData = musicList.get(position);
        imgAlbumArt.setImageBitmap(musicData.getAlbumArt());
        txtTitle.setText(musicData.getTitle());
        txtArtist.setText(musicData.getArtist());
    }

    // 뷰 id 찾기
    private void findViewByIdFunc(View view) {
        imgAlbumArt = view.findViewById(R.id.imgAlbumArt);
        txtTitle = view.findViewById(R.id.txtMusicTitle);
        txtArtist = view.findViewById(R.id.txtMusicArtist);
    }

    public ArrayList<MusicData> getMusicList() {
        return musicList;
    }

    public void setMusicList(ArrayList<MusicData> musicList) {
        this.musicList = musicList;
    }
}
