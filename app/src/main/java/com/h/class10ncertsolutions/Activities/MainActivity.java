package com.h.class10ncertsolutions.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.h.class10ncertsolutions.Models.*;
import com.h.class10ncertsolutions.*;

/* created by ashbatra in June 2019 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    LinearLayout sliderdots;
    private int dotcount;
    private ImageView[] dots;
    RatingBar ratingBar;
    Button submit, later;

    private List<String> bannerDataList;
    GridLayout gridLayout;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        count=0;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        bannerDataList = new ArrayList<String>();

        getjson();
        setViewPager();
        setgrid();


        }

    private void setgrid() {
        gridLayout = (GridLayout) findViewById(R.id.mainGrid);

        for(int i=0;i<gridLayout.getChildCount();i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);

            final String ii = Integer.toString(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,BooksActivity.class);
                    intent.putExtra("Sub", ii);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(count==1){
                finishAffinity();
            } else
            Toast.makeText(MainActivity.this,"Press back again to exit",Toast.LENGTH_SHORT).show();
            count++;
        }
    }

    private void getjson() {
        String json;
        try{
            InputStream inputStream = getAssets().open("app.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("bannersdata");
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject url = jsonArray.getJSONObject(i);
                String urlString = url.getString("url");
                bannerDataList.add(urlString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.v_pager1);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,bannerDataList);
        viewPager.setAdapter(viewPagerAdapter);

        sliderdots = (LinearLayout) findViewById(R.id.llayout);
        dotcount = viewPagerAdapter.getCount();

        dots = new ImageView[dotcount];

        for(int i=0;i<dotcount;i++)  {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0,8,0);
            sliderdots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for(int j=0;j<dotcount;j++) {
                    dots[j].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                }
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    protected void onResume() {
        count = 0;
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_rate) {

            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(getLayoutInflater().inflate(R.layout.rate, null));

            ratingBar= dialog.findViewById(R.id.ratingbar);
            submit= dialog.findViewById(R.id.submitrating);
            later = dialog.findViewById(R.id.later);

            later.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float rating = ratingBar.getRating();

                    if(rating <= 3.0) {
                        Toast.makeText(MainActivity.this, "Thank you for your Feedback", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(MainActivity.this, "Opening PlayStore...", Toast.LENGTH_SHORT).show();
                        final String appPackagename = getApplicationContext().getPackageName();
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appPackagename));
                        startActivity(intent1);
                        dialog.dismiss();
                    }
                }
            });
            dialog.show();

        }
        else if (id == R.id.nav_about) {
            Intent intent3 = new Intent(MainActivity.this,AboutusActivity.class);
            startActivity(intent3);
        }
        else if (id == R.id.nav_share) {

            Intent intent4 = new Intent(Intent.ACTION_SEND);
            final String appPackagename = getApplicationContext().getPackageName();
            String strAppLink = "";

            try {
                strAppLink = "https://play.google.com/store/apps/details?id=" + appPackagename;
            } catch (android.content.ActivityNotFoundException anfe) {
                strAppLink = "https://play.google.com/store/apps/details?id=" + appPackagename;
            }
            intent4.setType("text/link");
            String shareBody = "Hey! Check out this Offline NCERT Solutions app for Class X: " + strAppLink;

            String shareSub = "CBSE class 10 NCERT Solutions";
            intent4.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            intent4.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(intent4,"Share Using"));

        }
        else if (id == R.id.nav_more) {
            Toast.makeText(this, "Opening More Apps", Toast.LENGTH_SHORT).show();
        Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=PUT_DEVELOPER_ID_HERE&hl=en"));
            startActivity(intent5);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
