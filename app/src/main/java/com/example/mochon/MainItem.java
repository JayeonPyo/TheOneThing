package com.example.mochon;


import android.widget.CheckBox;

import java.io.Serializable;

public class MainItem implements Serializable {
    private String title;
    private String des;
    private boolean isSelected;
    private float rating_import;
    private float rating_time;
    private float rating_success;

    public MainItem(){}

    public MainItem(String title, String des){
        this.title = title;
        this.des = des;
    }

    public MainItem(String title, String des, boolean isSelected){
        this.title = title;
        this.des = des;
        this.isSelected = isSelected;
    }

    public MainItem(String title, String des, float rating_import, float rating_time, float rating_success){
        this.title = title;
        this.des = des;
        this.rating_import = rating_import;
        this.rating_time = rating_time;
        this.rating_success = rating_success;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getRating_import() {
        return rating_import;
    }

    public void setRating_import(float rating_import) {
        this.rating_import = rating_import;
    }

    public float getRating_time() {
        return rating_time;
    }

    public void setRating_time(float rating_time) {
        this.rating_time = rating_time;
    }

    public float getRating_success() {
        return rating_success;
    }

    public void setRating_success(float rating_success) {
        this.rating_success = rating_success;
    }



}
