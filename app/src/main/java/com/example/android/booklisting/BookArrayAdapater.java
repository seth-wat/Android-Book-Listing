package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Adapter class to populate the list in MainActivity with books.
 */

public class BookArrayAdapater extends ArrayAdapter<Book> {

    public BookArrayAdapater(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            //Inflate and attach the list_item view to its parent ViewGroup
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView mTitleView = (TextView) convertView.findViewById(R.id.title_text_view);
        TextView mPageCountView = (TextView) convertView.findViewById(R.id.page_count_text_view);
        TextView mRatingView = (TextView) convertView.findViewById(R.id.rating_text_view);
        TextView mDescView = (TextView) convertView.findViewById(R.id.description_text_view);
        TextView mAuthorsView = (TextView) convertView.findViewById(R.id.authors_text_view);


        Book book2Populate = getItem(position);
        mTitleView.setText(book2Populate.getTitle());
        mPageCountView.setText(book2Populate.getPage_count());
        mRatingView.setText(Double.toString(book2Populate.getRating()));
        mDescView.setText(book2Populate.getDescription());
        mAuthorsView.setText(book2Populate.getAuthors());



    }
}
