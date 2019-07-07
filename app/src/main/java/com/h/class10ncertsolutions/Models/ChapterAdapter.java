package com.h.class10ncertsolutions.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.h.class10ncertsolutions.*;

/* created by ashbatra in June 2019 */

public class ChapterAdapter extends ArrayAdapter<chapter> {
    private final Context context;
    private final ArrayList<chapter> values;

    public ChapterAdapter(Context context, ArrayList<chapter> list) {

        super(context, R.layout.list_item_layout, list);
        this.context = context;
        this.values = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.list_item_layout, parent, false);

        TextView chapter = (TextView) rowview.findViewById(R.id.chptext);
        ImageView icon = (ImageView) rowview.findViewById(R.id.chpnum);

        chapter.setText(values.get(position).getChp());
        icon.setImageResource(getImageId(getContext(),"a"+values.get(position).getNum()));

        return rowview;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
