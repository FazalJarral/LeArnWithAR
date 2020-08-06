package com.leARn.arcore.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Asset {
    @SerializedName("name")
    String id;
    String displayName;
    String url;
    String obj_url;
    Thumbnail thumbnail;
    @SerializedName("formats")
            @Expose
    ArrayList<Format> formatList;
    public Asset() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getObj_url() {
        return obj_url;
    }

    public void setObj_url(String obj_url) {
        this.obj_url = obj_url;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<Format> getFormatList() {
        return formatList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFormatList(ArrayList<Format> formatList) {
        this.formatList = formatList;
    }

    @NonNull
    @Override
    public String toString() {
        return displayName + "" + thumbnail.getUrl();
    }
}
