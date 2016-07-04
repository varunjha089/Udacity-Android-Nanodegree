package com.example.android.booklist;

/**
 * Created by me on 30/6/16.
 */
public class ResultStrs {

    private String mTitle = "";
    private String mAuthor = "";
    private String mPublisher = "";

    public ResultStrs(String title, String author, String publisher) {
        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
    }
    // Getter Methods
    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublisher() {
        return mPublisher;
    }

    // Setter methods
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

}
