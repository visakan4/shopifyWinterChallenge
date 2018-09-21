package com.example.visak.shopifyandroidchallenge;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by visak on 2018-09-19.
 *
 * TagAdapter helps in setting the tagValues to the ListView
 *
 */

public class TagAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<String> tags = null;
    private ArrayList<String> filterTag;
    LayoutInflater layoutInflater;

    public TagAdapter(Context context, int textViewResourceId, ArrayList<String> tags) {
        super(context, textViewResourceId, tags);
        this.context = context;
        this.tags = tags;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.filterTag = new ArrayList<String>();
        this.filterTag.addAll(tags);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        view = layoutInflater.inflate(R.layout.list_view_items, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(tags.get(position));
        return view;
    }


    /**
     *
     * Filters the text based on a value and sets up the listView based on it
     *
     * @param filterText
     */

    public void filter(String filterText){
        filterText = filterText.toLowerCase(Locale.getDefault());
        tags.clear();
        if (filterText.length() == 0){
            tags.addAll(filterTag);
        }else {
            for (String tagValue:filterTag){
                if (tagValue.toLowerCase(Locale.getDefault()).contains(filterText)){
                    tags.add(tagValue);
                }
            }
        }
        notifyDataSetChanged();
    }
}
