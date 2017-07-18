package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

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
        return null;
    }
}