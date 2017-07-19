package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(1, null, this);

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(MainActivity.this);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        Log.v("MainActivity", "onLoadFinished is now executing");
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        
    }
}
