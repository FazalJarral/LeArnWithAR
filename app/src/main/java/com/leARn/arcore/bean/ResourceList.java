package com.leARn.arcore.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResourceList {
    @SerializedName("resources")
    ArrayList<Resource> resources;

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }
}
