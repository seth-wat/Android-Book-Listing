package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * AysncTaskLoader to handle the network request on the background thread
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    public BookLoader(Context ctxt) {
        super(ctxt);
    }

    @Override
    protected void onStartLoading() {
        //Force loadInBackground to begin.
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> bookList = null;

        Log.v("BookLoader" , "I am about to make the connection and run the query in loadInBackground");
        bookList = QueryHandler.fetchBookData(QueryHandler.getUrl());
        Log.v("BookLoader", "I've finshed fetching the book data");
        return bookList;
    }
}