package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

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
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(1, new Book("Test Title", (new String[]{"Actor 1", "Actor 2"}), "Fake Description", 12, 2.20));

        return bookList;
    }
}