package com.example.wordlistsecond;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private int resourceId;

    public WordAdapter(Context context, int textViewResourceId, List<Word> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word word = getItem(position); //获取当前项的Word实例
        View view = LayoutInflater.from(getContext()).inflate(R.layout.word_item, parent, false);
        TextView wordName = (TextView) view.findViewById(R.id.word_name);
        wordName.setText(word.getQuery());
        return view;
    }

}
