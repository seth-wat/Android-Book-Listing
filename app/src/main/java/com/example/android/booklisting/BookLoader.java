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

        //ArrayList to be returned to begin testing networking code
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(0, new Book("Test Title", (new String[]{"Actor 1", "Actor 2"}), "Fake Description", 12, 2.20));

        Log.v("BookLoader" , "I am about to make the connection and run the query in loadInBackground");
        HttpURLConnection testHttpURL = QueryHandler.makeConnection(QueryHandler.createURL(QueryHandler.TEST_URL));
        QueryHandler.parseJSONData(QueryHandler.getRawJSONFromStream(testHttpURL));
        return bookList;
    }
}