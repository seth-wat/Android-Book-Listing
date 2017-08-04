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

public class BookLoader extends AsyncTaskLoader<String> {
    private String bookData;

    public BookLoader(Context ctxt) {
        super(ctxt);
    }


    @Override
    protected void onStartLoading() {
        if (bookData != null) {
          deliverResult(bookData);
        } else {
            forceLoad();
        }
    }

    @Override
    public String loadInBackground() {
        String urlString = QueryHandler.getUrl();
        HttpURLConnection httpCon = null;
        if (urlString != null) {
            httpCon = QueryHandler.makeConnection(QueryHandler.createURL(urlString));
        }
        Log.v("BookLoader" , "I am about to make the connection and run the query in loadInBackground");
        Log.v("BookLoader", "I've finshed fetching the book data");
        if (httpCon != null) {
            return QueryHandler.getRawJSONFromStream(httpCon);
        } else {
            return null;
        }
    }
    //gets called before loadInBackground, this way you don't have to make the network request again
    @Override
    public void deliverResult(String data) {
        if ((!data.isEmpty()) || data == null)
        bookData = data;
        super.deliverResult(data);
    }
}