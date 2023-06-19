package com.example.seam.pojo;

/**
 * @e-mail:2036573698@qq.com
 */
public class Banners {
    private String title;
    private Integer image;

    public Banners() {
    }

    public Banners(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
