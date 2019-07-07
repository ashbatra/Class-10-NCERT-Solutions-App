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

public class BookAdapter extends ArrayAdapter<field> {
private final Context context;
private final ArrayList<field> values;

public BookAdapter(Context context, ArrayList<field> list) {

        super(context, R.layout.grid_item_layout, list);
        this.context = context;
        this.values = list;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.grid_item_layout, parent, false);

        TextView book = (TextView) rowview.findViewById(R.id.text);
        ImageView icon = (ImageView) rowview.findViewById(R.id.icon);

        book.setText(values.get(position).getBook());
        icon.setImageResource(getImageId(getContext(),"img"+values.get(position).getIcon()));

        return rowview;
        }

public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
        }
        }
