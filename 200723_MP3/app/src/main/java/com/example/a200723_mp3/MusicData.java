package com.example.a200723_mp3;

import android.graphics.Bitmap;

public class MusicData {

    private String title;
    private String artist;
    private Bitmap albumArt;
    private String fileName;

    public MusicData(String title, String artist, Bitmap albumArt, String fileName) {
        this.title = title;
        this.artist = artist;
        this.albumArt = albumArt;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Bitmap getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(Bitmap albumArt) {
        this.albumArt = albumArt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
