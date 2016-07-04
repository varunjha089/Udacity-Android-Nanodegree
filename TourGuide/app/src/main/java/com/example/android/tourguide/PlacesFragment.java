package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * PlacesFragment Class.
 */
public class PlacesFragment extends Fragment {



    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of content
        final ArrayList<Content> content = new ArrayList<Content>();
        content.add(new Content(R.string.place_one, R.string.place_review_one,
                R.drawable.zermatt));
        content.add(new Content(R.string.place_two, R.string.place_review_two,
                R.drawable.basel));
        content.add(new Content(R.string.place_three, R.string.place_review_three,
                R.drawable.saanen));
        content.add(new Content(R.string.place_four, R.string.place_review_four,
                R.drawable.zurich));
        content.add(new Content(R.string.place_five, R.string.place_review_five,
                R.drawable.geneva));
        content.add(new Content(R.string.place_six, R.string.place_review_six,
                R.drawable.saasfee));
        content.add(new Content(R.string.place_seven, R.string.place_review_seven,
                R.drawable.wengen));
        content.add(new Content(R.string.place_eight, R.string.place_review_eight,
                R.drawable.arosa));

        ContentAdapter adapter = new ContentAdapter(getActivity(), content, R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }
}