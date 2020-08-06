package com.leARn.arcore.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelList {
    @SerializedName("assets")
    ArrayList<Asset> assets;
    @SerializedName("nextPageToken")
    String nextPageToken;
    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }
}
