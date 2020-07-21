package com.example.voteimage;


import java.io.Serializable;

public class ImageInfo implements Serializable {
    private int count;
    private Integer image;
    private String name;

    public ImageInfo() {
    }

    public ImageInfo(int count, Integer image, String name) {
        this.count = count;
        this.image = image;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
