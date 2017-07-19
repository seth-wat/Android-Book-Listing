package com.example.android.booklisting;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * This class handles establishing the connection, making the query to google.books.com
 * and parsing the JSON response.
 *
 * The class should only be accessed through fetchBookData
 */

public final class QueryHandler {
    public static final String LOG_TAG = QueryHandler.class.getSimpleName();
    public static final String TEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";
    private static final int PROPER_RESPONSE_CODE = 200;


    private QueryHandler() {
    }

    public static URL createURL(String url) {
        URL url2Return = null;
        try {
            url2Return = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url2Return;
    }

    public static HttpURLConnection makeConnection(URL url) {
        if (url == null) {
            return null;
        }

        HttpURLConnection myConnection = null;

        try {
            myConnection = (HttpURLConnection) url.openConnection();
            myConnection.setReadTimeout(10000);
            myConnection.setConnectTimeout(15000);
            myConnection.setRequestMethod("GET");
            myConnection.connect();
            Log.v(LOG_TAG, Integer.toString(myConnection.getResponseCode()));
            if (myConnection.getResponseCode() == PROPER_RESPONSE_CODE) {
                return myConnection;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myConnection;
    }

    private static String getRawJSONFromStream(HttpURLConnection httpUrlCon) {
        StringBuilder rawJSON = new StringBuilder();
        InputStream is = null;
        try {
            is = httpUrlCon.getInputStream();
            InputStreamReader isR = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isR);
            String line = br.readLine();
            while (line != null) {
                rawJSON.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpUrlCon != null) {
                httpUrlCon.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rawJSON.toString();
    }

    private static List<Book> parseJSONData(String rawJSON) {
        return null;
    }

    public static List<Book> fetchBookData(String url) {
        return null;
    }

}
