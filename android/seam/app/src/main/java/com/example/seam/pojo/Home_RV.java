package com.example.seam.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.seam.BR;

/**
 * @e-mail:2036573698@qq.com
 */
public class Home_RV extends BaseObservable {
    private String title;
    private Integer image;

    public Home_RV() {
    }

    public Home_RV(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }
}
