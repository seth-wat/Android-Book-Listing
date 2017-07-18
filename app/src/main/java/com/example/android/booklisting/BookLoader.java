package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by God on 7/18/2017.
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
