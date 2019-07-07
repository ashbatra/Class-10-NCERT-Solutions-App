package com.h.class10ncertsolutions.Models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import com.h.class10ncertsolutions.*;

/* created by ashbatra in June 2019 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> bannerPathList;

    public ViewPagerAdapter(Context context,List<String> bnr) {
        this.context = context;
        this.bannerPathList = bnr;
    }

    @Override
    public int getCount() {
        return bannerPathList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_layout, null);
        ImageView v_banner = (ImageView) view.findViewById(R.id.banner);

        Picasso.with(context.getApplicationContext()).load(bannerPathList.get(position))
                .into(v_banner, new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                    }
                });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=in.edu.extraclass.student"));
                context.startActivity(i);
            }
        });


        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
