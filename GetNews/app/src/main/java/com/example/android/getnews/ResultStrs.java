package com.example.android.getnews;


public class ResultStrs {

    private String mTitle;
    private String mSection;
    private String mWebUrl;

    public ResultStrs(String title, String section, String webUrl) {
        mTitle = title;
        mSection = section;
        mWebUrl = webUrl;
    }
    // Getter Methods
    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

}

