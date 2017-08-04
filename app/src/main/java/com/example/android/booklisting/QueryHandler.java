package com.example.android.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles establishing the connection, making the query to google.books.com
 * and parsing the JSON response.
 * <p>
 * The class should only be accessed through fetchBookData
 */

public final class QueryHandler {
    public static final String LOG_TAG = QueryHandler.class.getSimpleName();
    public static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int PROPER_RESPONSE_CODE = 200;
    private static String queryUrl;


    private QueryHandler() {
    }

    public static void setUrl(String urlToSet) {
        queryUrl = urlToSet;
    }

    public static String getUrl() {
        return queryUrl;
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
            } else {
                myConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myConnection;
    }

    public static String getRawJSONFromStream(HttpURLConnection httpUrlCon) {
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
        Log.v(LOG_TAG, rawJSON.toString());
        return rawJSON.toString();
    }

    public static List<Book> parseJSONData(String rawJSON) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        JSONObject mainObject = null;
        JSONArray itemArray = null ;
        JSONObject jsonBookObject = null;
        JSONArray authorArray = null;

        String title = null;
        String[] authors = null;
        String description = null;
        int pageCount = 0;
        double rating = 0.00;

        try {
            mainObject = new JSONObject(rawJSON);
            itemArray = mainObject.getJSONArray("items");
            //Loop through each object in the itemArray, each object represents a book.
            Log.e(LOG_TAG, Integer.toString(itemArray.length()));
            for (int i = 0; i < itemArray.length(); i++) {
                jsonBookObject = itemArray.getJSONObject(i).getJSONObject("volumeInfo");
                try {
                    title = jsonBookObject.getString("title");
                } catch(JSONException e) {
                    title = "No Title Available";
                }
                try {
                    description = jsonBookObject.getString("description");
                }catch (JSONException e) {
                    description = "No description available";
                }
                try {
                    pageCount = jsonBookObject.getInt("pageCount");
                } catch(JSONException e) {
                    pageCount = 0;
                }
                try {
                    rating = jsonBookObject.getDouble("averageRating");
                } catch(JSONException e) {
                    rating = 0.00;
                }
                try {
                    authorArray = jsonBookObject.getJSONArray("authors");
                    authors = new String[authorArray.length()];
                    for (int j = 0; j < authorArray.length(); j++) {
                        authors[j] = authorArray.getString(j);
                    }
                } catch(JSONException e) {
                    authors = (new String[]{"No authors available"});
                }
                bookList.add(new Book(title, authors, description, pageCount, rating));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //If all the variables are set from parsing the JSON
        //... not sure if this if statement is needed, I think JSON will throw an exception in the case the JSON property cannot be stored in the local variable
        //Make the book object

        return bookList;
    }

    public static List<Book> fetchBookData(String url) {
        return parseJSONData(getRawJSONFromStream(makeConnection(createURL(url))));
    }

}
