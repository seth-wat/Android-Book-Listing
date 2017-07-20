package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    ListView mListView;
    BookArrayAdapater mAdapter;
    EditText mEditView;
    //this will server as the unique loader id.
    int queryClicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.display_list_view);
        mAdapter = new BookArrayAdapater(this, new ArrayList<Book>());
        mListView.setAdapter(mAdapter);
        mEditView = (EditText) findViewById(R.id.book_edit_text);

        mEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditView.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search_item) {
            String fullUrl = QueryHandler.BASE_URL + mEditView.getText().toString() + "&maxResults=10";
            if (!fullUrl.equals(QueryHandler.getUrl())) {
                queryClicks++; //Need to create a new loader if the url is new.
            }
            QueryHandler.setUrl(QueryHandler.BASE_URL + mEditView.getText().toString() + "&maxResults=10");
            getLoaderManager().initLoader(queryClicks, null, MainActivity.this);
        }
        return true;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(MainActivity.this);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        Log.v("MainActivity", "onLoadFinished is now executing");
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        
    }
}
