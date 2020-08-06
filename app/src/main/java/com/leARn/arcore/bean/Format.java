package com.leARn.arcore.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

public class Format {
    @SerializedName("formatType")
    String formatType;
    @SerializedName("root")
    @Expose
    FormatRoot formatRoot;

    @SerializedName("resources")
            @Expose
    ArrayList<Resource> resourceList;
    public Format() {
    }

    public ArrayList<Resource> getResourceList() {
        return resourceList;
    }

    public String getFormatType() {
        return formatType;
    }

    public FormatRoot getFormatRoot() {
        return formatRoot;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @NonNull
    @Override
    public String toString() {
        return formatType + " " + formatRoot.getUrl();
    }


    public static class TypeComp implements Comparator<Format> {

        @Override
        public int compare(Format o1, Format o2) {
            return o1.getFormatType().compareTo(o2.getFormatType());
        }
    }
}
