package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.display_list_view);
        getLoaderManager().initLoader(1, null, this);
        SearchView sv = (SearchView) findViewById(R.id.book_search_view);
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(MainActivity.this);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        Log.v("MainActivity", "onLoadFinished is now executing");
        BookArrayAdapater mAdapter = new BookArrayAdapater(this, (ArrayList<Book>) data);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        
    }
}
