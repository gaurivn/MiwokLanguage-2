package com.example.miwoklanguage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    private int colorres;

    public WordAdapter(Context context, ArrayList<Word> pWords, int mcolorres) {
        super(context, 0, pWords);
        colorres = mcolorres;
    }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // return super.getView(position, convertView, parent);
                // Check if the existing view is being reused, otherwise inflate the view
                View listItemView = convertView;

                if (listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(
                            R.layout.list_item, parent, false);
                }
                Word my_word = (Word) getItem(position);
                TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
                miwokTextView.setText(my_word.getMiw());
                TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
                defaultTextView.setText(my_word.getDef());
                // Find the ImageView in the list_item.xml layout with the ID image.
                ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
       /* // Set the ImageView to the image resource specified in the current Word
        imageView.setImageResource(my_word.getImageres());*/

                //Check if image is provided for this word or not
                if (my_word.hasImage()) {
                    imageView.setImageResource(my_word.getImageres());
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }

                //Set the theme color for the list item
                View textContainer = listItemView.findViewById(R.id.text_container);
                //Find the color that the resource id maps to
                int color  = ContextCompat.getColor(getContext(), colorres);
                textContainer.setBackgroundColor(color);

                return listItemView;
            }


}
