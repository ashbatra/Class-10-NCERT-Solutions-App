package com.h.class10ncertsolutions.Activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.h.class10ncertsolutions.Models.*;
import com.h.class10ncertsolutions.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* created by ashbatra in June 2019 */

public class BooksActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<field> list1;
    ArrayList<field> list2;
    ArrayList<field> list3;
    ArrayList<field> list4;
    ArrayList<field> list5;
    ViewPager viewPager;
    LinearLayout sliderdots;
    private int dotcount;
    private ImageView[] dots;

    private List<String> bannerDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        gridView = (GridView) findViewById(R.id.grid);
        list1 = new ArrayList<field>();
        list2 = new ArrayList<field>();
        list3 = new ArrayList<field>();
        list4 = new ArrayList<field>();
        list5 = new ArrayList<field>();

        String Sub = getIntent().getStringExtra("Sub");
        ActionBar actionBar = getSupportActionBar();

        bannerDataList = new ArrayList<String>();

        getjson();
        setViewPager();

        if(Sub.equals("0")){
            actionBar.setTitle("Maths");
            setData1();
            setAdapter1();
        } else if(Sub.equals("1")) {
            actionBar.setTitle("Science");
            setData2();
            setAdapter2();
        } else if(Sub.equals("2")) {
            actionBar.setTitle("English");
            setData3();
            setAdapter3();
        } else if(Sub.equals("3")) {
            actionBar.setTitle("Social Studies");
            setData4();
            setAdapter4();
        } else if(Sub.equals("4")) {
            actionBar.setTitle("Hindi");
            setData5();
            setAdapter5();
        }

    }

    private void getjson() {
        String json;
        String jsonFileName = "file.json";
        try{
            InputStream inputStream = getAssets().open(jsonFileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("key");
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
        viewPager = (ViewPager) findViewById(R.id.v_pager2);
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



    private void setAdapter1() {

        BookAdapter adapter = new BookAdapter(this, list1);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = list1.get(position).getBook();
                String bookIcon = list1.get(position).getIcon();
                Intent intent = new Intent(BooksActivity.this,ChapterActivity.class);
                intent.putExtra("Book",bookName);
                intent.putExtra("Icon",bookIcon);
                startActivity(intent);
            }
        });
    }

    private void setData1() {

        field f1 = new field("Mathematics (X)", "1");
        field f2 = new field("Maths Exemplar (X)", "2");

        list1.add(f1);
        list1.add(f2);

    }

    private void setAdapter2() {

        BookAdapter adapter = new BookAdapter(this, list2);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = list2.get(position).getBook();
                String bookIcon = list2.get(position).getIcon();
                Intent intent = new Intent(BooksActivity.this,ChapterActivity.class);
                intent.putExtra("Book",bookName);
                intent.putExtra("Icon",bookIcon);
                startActivity(intent);
            }
        });
    }

    private void setData2() {

        field f1 = new field("Science (X)", "3");
        field f2 = new field("Science Exemplar (X)", "4");

        list2.add(f1);
        list2.add(f2);

    }

    private void setAdapter3() {

        BookAdapter adapter = new BookAdapter(this, list3);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = list3.get(position).getBook();
                String bookIcon = list3.get(position).getIcon();
                Intent intent = new Intent(BooksActivity.this,ChapterActivity.class);
                intent.putExtra("Book",bookName);
                intent.putExtra("Icon",bookIcon);
                startActivity(intent);
            }
        });
    }

    private void setData3() {

        field f1 = new field("First Flight (X)", "5");
        field f2 = new field("Foot Prints (X)", "6");
        field f3 = new field("Literature Reader (X)", "7");

        list3.add(f1);
        list3.add(f2);
        list3.add(f3);

    }

    private void setAdapter4() {

        BookAdapter adapter = new BookAdapter(this, list4);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = list4.get(position).getBook();
                String bookIcon = list4.get(position).getIcon();
                Intent intent = new Intent(BooksActivity.this,ChapterActivity.class);
                intent.putExtra("Book",bookName);
                intent.putExtra("Icon",bookIcon);
                startActivity(intent);
            }
        });
    }

    private void setData4() {

        field f1 = new field("Contemporary India (X)", "8");
        field f2 = new field("Understanding Economic Development (X)", "9");
        field f3 = new field("India and the Contemporary World-II (X)", "10");
        field f4 = new field("Democratic Politics", "11");

        list4.add(f1);
        list4.add(f2);
        list4.add(f3);
        list4.add(f4);

    }

    private void setAdapter5() {

        BookAdapter adapter = new BookAdapter(this, list5);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = list5.get(position).getBook();
                String bookIcon = list5.get(position).getIcon();
                Intent intent = new Intent(BooksActivity.this,ChapterActivity.class);
                intent.putExtra("Book",bookName);
                intent.putExtra("Icon",bookIcon);
                startActivity(intent);
            }
        });
    }

    private void setData5() {

        field f1 = new field("Kshitij-2 (X)", "12");
        field f2 = new field("Sparsh (X)", "13");
        field f3 = new field("Kritika (X)", "14");
        field f4 = new field("Sanchayan Bhag-2 (X)", "15");

        list5.add(f1);
        list5.add(f2);
        list5.add(f3);
        list5.add(f4);

    }
}
