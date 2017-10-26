package com.vinay.wizdem.vnytimes.eventbus;

/**
 * Created by vinay_1 on 10/26/2017.
 */

public class BooksListToDetailsEvent {
    public String title;
    public String author;

    public BooksListToDetailsEvent(String title, String author){
        this.title = title;
        this.author = author;
    }
}
