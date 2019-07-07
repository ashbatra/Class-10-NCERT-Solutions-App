package com.h.class10ncertsolutions.Activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

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

public class ChapterActivity extends AppCompatActivity {
    ListView chapters;

    ArrayList<chapter> list1;
    ArrayList<chapter> list2;
    ArrayList<chapter> list3;
    ArrayList<chapter> list4;
    ArrayList<chapter> list5;
    ArrayList<chapter> list6;
    ArrayList<chapter> list7;
    ArrayList<chapter> list8;
    ArrayList<chapter> list9;
    ArrayList<chapter> list10;
    ArrayList<chapter> list11;
    ArrayList<chapter> list12;
    ArrayList<chapter> list13;
    ArrayList<chapter> list14;
    ArrayList<chapter> list15;
    ViewPager viewPager;
    LinearLayout sliderdots;
    private int dotcount;
    private ImageView[] dots;

    private List<String> bannerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        chapters = (ListView) findViewById(R.id.chptrs);
        list1 = new ArrayList<chapter>();
        list2 = new ArrayList<chapter>();
        list3 = new ArrayList<chapter>();
        list4 = new ArrayList<chapter>();
        list5 = new ArrayList<chapter>();
        list6 = new ArrayList<chapter>();
        list7 = new ArrayList<chapter>();
        list8 = new ArrayList<chapter>();
        list9 = new ArrayList<chapter>();
        list10 = new ArrayList<chapter>();
        list11 = new ArrayList<chapter>();
        list12 = new ArrayList<chapter>();
        list13 = new ArrayList<chapter>();
        list14 = new ArrayList<chapter>();
        list15 = new ArrayList<chapter>();

        String bookname = getIntent().getStringExtra("Book");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(bookname);

        actionBar.setDisplayHomeAsUpEnabled(true);

        bannerDataList = new ArrayList<String>();

        getjson();
        setViewPager();

        String booknum = getIntent().getStringExtra("Icon");

        if(booknum.equals("1")){
            setData1();
            setAdapter1();
        } else if(booknum.equals("2")) {
            setData2();
            setAdapter2();
        } else if(booknum.equals("3")) {
            setData3();
            setAdapter3();
        } else if(booknum.equals("4")) {
            setData4();
            setAdapter4();
        } else if(booknum.equals("5")) {
            setData5();
            setAdapter5();
        } else if(booknum.equals("6")) {
            setData6();
            setAdapter6();
        } else if(booknum.equals("7")) {
            setData7();
            setAdapter7();
        } else if(booknum.equals("8")) {
            setData8();
            setAdapter8();
        } else if(booknum.equals("9")) {
            setData9();
            setAdapter9();
        } else if(booknum.equals("10")) {
            setData10();
            setAdapter10();
        } else if(booknum.equals("11")) {
            setData11();
            setAdapter11();
        } else if(booknum.equals("12")) {
            setData12();
            setAdapter12();
        } else if(booknum.equals("13")) {
            setData13();
            setAdapter13();
        } else if(booknum.equals("14")) {
            setData14();
            setAdapter14();
        } else if(booknum.equals("15")) {
            setData15();
            setAdapter15();
        }

    }

    private void getjson() {
        String json;
        String jsonFileName = "file.json";       //your json file name
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
        viewPager = (ViewPager) findViewById(R.id.v_pager3);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter1() {

        ChapterAdapter adapter = new ChapterAdapter(this, list1);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list1.get(position).getChp();
                String chpnum = list1.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","1");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData1() {

        chapter c1 = new chapter("Real Numbers", "1");
        chapter c2 = new chapter("Polynomials", "2");
        chapter c3 = new chapter("Pair of Linear Equations in two Variables", "3");
        chapter c4 = new chapter("Quadratic Equations", "4");
        chapter c5 = new chapter("Arithmetic Progression", "5");
        chapter c6 = new chapter("Triangles", "6");
        chapter c7 = new chapter("Coordinate Geometry", "7");
        chapter c8 = new chapter("Introduction to Trigonometry", "8");
        chapter c9 = new chapter("Some Applications of Trigonometry", "9");
        chapter c10 = new chapter("Circles", "10");
        chapter c11 = new chapter("Constructions", "11");
        chapter c12 = new chapter("Areas Related to Circles", "12");
        chapter c13 = new chapter("Surface Areas and Volumes", "13");
        chapter c14 = new chapter("Statistics", "14");
        chapter c15 = new chapter("Probability", "15");

        list1.add(c1);
        list1.add(c2);
        list1.add(c3);
        list1.add(c4);
        list1.add(c5);
        list1.add(c6);
        list1.add(c7);
        list1.add(c8);
        list1.add(c9);
        list1.add(c10);
        list1.add(c11);
        list1.add(c12);
        list1.add(c13);
        list1.add(c14);
        list1.add(c15);
    }

    private void setAdapter2() {

        ChapterAdapter adapter = new ChapterAdapter(this, list2);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list2.get(position).getChp();
                String chpnum = list2.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","2");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData2() {

        chapter c1 = new chapter("Real Numbers", "1");
        chapter c2 = new chapter("Polynomials", "2");
        chapter c3 = new chapter("Pair of Linear Equations in two Variables", "3");
        chapter c4 = new chapter("Quadratic Equations", "4");
        chapter c5 = new chapter("Arithmetic Progression", "5");
        chapter c6 = new chapter("Triangles", "6");
        chapter c7 = new chapter("Coordinate Geometry", "7");
        chapter c8 = new chapter("Introduction to Trigonometry and its Applications", "8");
        chapter c9 = new chapter("Circles", "9");
        chapter c10 = new chapter("Constructions", "10");
        chapter c11 = new chapter("Areas Related to Circles", "11");
        chapter c12 = new chapter("Surface Areas and Volumes", "12");
        chapter c13 = new chapter("Statistics and Probability", "13");


        list2.add(c1);
        list2.add(c2);
        list2.add(c3);
        list2.add(c4);
        list2.add(c5);
        list2.add(c6);
        list2.add(c7);
        list2.add(c8);
        list2.add(c9);
        list2.add(c10);
        list2.add(c11);
        list2.add(c12);
        list2.add(c13);

    }

    private void setAdapter3() {

        ChapterAdapter adapter = new ChapterAdapter(this, list3);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list3.get(position).getChp();
                String chpnum = list3.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","3");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData3() {

        chapter c1 = new chapter("Chemical Reactions and Equations", "1");
        chapter c2 = new chapter("Acids, Bases and Salts", "2");
        chapter c3 = new chapter("Metals and Non-Metals", "3");
        chapter c4 = new chapter("Carbon and its Compounds", "4");
        chapter c5 = new chapter("Periodic Classification of Elements", "5");
        chapter c6 = new chapter("Life Processes", "6");
        chapter c7 = new chapter("Control and Coordination", "7");
        chapter c8 = new chapter("How do Organisms Reproduce?", "8");
        chapter c9 = new chapter("Heredity and Evolution", "9");
        chapter c10 = new chapter("Light- Reflection and Refraction", "10");
        chapter c11 = new chapter("Human Eye and Colourful World", "11");
        chapter c12 = new chapter("Electricity", "12");
        chapter c13 = new chapter("Magnetic Effects of Electric Current", "13");
        chapter c14 = new chapter("Sources of Energy", "14");
        chapter c15 = new chapter("Our Environment", "15");
        chapter c16 = new chapter("Management of Resourses", "16");

        list3.add(c1);
        list3.add(c2);
        list3.add(c3);
        list3.add(c4);
        list3.add(c5);
        list3.add(c6);
        list3.add(c7);
        list3.add(c8);
        list3.add(c9);
        list3.add(c10);
        list3.add(c11);
        list3.add(c12);
        list3.add(c13);
        list3.add(c14);
        list3.add(c15);
        list3.add(c16);
    }

    private void setAdapter4() {

        ChapterAdapter adapter = new ChapterAdapter(this, list4);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list4.get(position).getChp();
                String chpnum = list4.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","4");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData4() {

        chapter c1 = new chapter("Chemical Reactions and Equations", "1");
        chapter c2 = new chapter("Acids, Bases and Salts", "2");
        chapter c3 = new chapter("Metals and Non-Metals", "3");
        chapter c4 = new chapter("Carbon and its Compounds", "4");
        chapter c5 = new chapter("Periodic Classification of Elements", "5");
        chapter c6 = new chapter("Life Processes", "6");
        chapter c7 = new chapter("Control and Coordination", "7");
        chapter c8 = new chapter("How do Organisms Reproduce?", "8");
        chapter c9 = new chapter("Heredity and Evolution", "9");
        chapter c10 = new chapter("Light- Reflection and Refraction", "10");
        chapter c11 = new chapter("Human Eye and Colourful World", "11");
        chapter c12 = new chapter("Electricity", "12");
        chapter c13 = new chapter("Magnetic Effects of Electric Current", "13");
        chapter c14 = new chapter("Sources of Energy", "14");
        chapter c15 = new chapter("Our Environment", "15");
        chapter c16 = new chapter("Management of Resourses", "16");

        list4.add(c1);
        list4.add(c2);
        list4.add(c3);
        list4.add(c4);
        list4.add(c5);
        list4.add(c6);
        list4.add(c7);
        list4.add(c8);
        list4.add(c9);
        list4.add(c10);
        list4.add(c11);
        list4.add(c12);
        list4.add(c13);
        list4.add(c14);
        list4.add(c15);
        list4.add(c16);
    }

    private void setAdapter5() {

        ChapterAdapter adapter = new ChapterAdapter(this, list5);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list5.get(position).getChp();
                String chpnum = list5.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","5");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData5() {

        chapter c1 = new chapter("A Letter to God", "1");
        chapter c2 = new chapter("Nelson Mandela: Long Walk to Freedom", "2");
        chapter c3 = new chapter("Two Stories about Flying", "3");
        chapter c4 = new chapter("From the Diary of Anne Frank", "4");
        chapter c5 = new chapter("The Hundred Dresses-I", "5");
        chapter c6 = new chapter("The Hundred Dresses-II", "6");
        chapter c7 = new chapter("Glimpses of India", "7");
        chapter c8 = new chapter("Mijbil the Otter", "8");
        chapter c9 = new chapter("Madam Rides the Bus", "9");
        chapter c10 = new chapter("The Serman at Benaras", "10");
        chapter c11 = new chapter("The Proposal", "11");
        chapter c12 = new chapter("Dust of Snow", "12");
        chapter c13 = new chapter("Fire and Ice", "13");
        chapter c14 = new chapter("A Tiger in the Zoo", "14");
        chapter c15 = new chapter("How we tell Wild Animals", "15");
        chapter c16 = new chapter("The Ball Poem", "16");
        chapter c17 = new chapter("Amanda!", "17");
        chapter c18 = new chapter("Animals", "18");
        chapter c19 = new chapter("The Trees", "19");
        chapter c20 = new chapter("Fog", "20");
        chapter c21 = new chapter("The Tale of Custard the Dragon", "21");
        chapter c22 = new chapter("For Anne Gregory", "22");

        list5.add(c1);
        list5.add(c2);
        list5.add(c3);
        list5.add(c4);
        list5.add(c5);
        list5.add(c6);
        list5.add(c7);
        list5.add(c8);
        list5.add(c9);
        list5.add(c10);
        list5.add(c11);
        list5.add(c12);
        list5.add(c13);
        list5.add(c14);
        list5.add(c15);
        list5.add(c16);
        list5.add(c17);
        list5.add(c18);
        list5.add(c19);
        list5.add(c20);
        list5.add(c21);
        list5.add(c22);
    }

    private void setAdapter6() {

        ChapterAdapter adapter = new ChapterAdapter(this, list6);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list6.get(position).getChp();
                String chpnum = list6.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","6");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData6() {

        chapter c1 = new chapter("A Triumph of Surgery", "1");
        chapter c2 = new chapter("The Thief's Story", "2");
        chapter c3 = new chapter("The Midnight Visitor", "3");
        chapter c4 = new chapter("A Question of Trust", "4");
        chapter c5 = new chapter("Footprints Without Feet", "5");
        chapter c6 = new chapter("The Making of a Scientist", "6");
        chapter c7 = new chapter("The Necklace", "7");
        chapter c8 = new chapter("The Hack Driver", "8");
        chapter c9 = new chapter("Bhali", "9");
        chapter c10 = new chapter("The Book that Saved the Earth", "10");

        list6.add(c1);
        list6.add(c2);
        list6.add(c3);
        list6.add(c4);
        list6.add(c5);
        list6.add(c6);
        list6.add(c7);
        list6.add(c8);
        list6.add(c9);
        list6.add(c10);
    }

    private void setAdapter7() {

        ChapterAdapter adapter = new ChapterAdapter(this, list7);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list7.get(position).getChp();
                String chpnum = list7.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","7");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData7() {

        chapter c1 = new chapter("Two Gentlemen Of Verona", "1");
        chapter c2 = new chapter("Mrs. Packletide's Tiger", "2");
        chapter c3 = new chapter("The Letter", "3");
        chapter c4 = new chapter("The Shady Plot", "4");
        chapter c5 = new chapter("Patol Babu, Film Star", "5");
        chapter c6 = new chapter("Virtually True", "6");
        chapter c7 = new chapter("Frog and Nightingale", "7");
        chapter c8 = new chapter("Mirror", "8");
        chapter c9 = new chapter("Not the Marble Nor the Guilded Monuments", "9");
        chapter c10 = new chapter("Ozymandias", "10");
        chapter c11 = new chapter("The Rime of the Ancient Mariner", "11");
        chapter c12 = new chapter("Snake", "12");
        chapter c13 = new chapter("Dear Departed", "13");
        chapter c14 = new chapter("Julies Caeser", "14");

        list7.add(c1);
        list7.add(c2);
        list7.add(c3);
        list7.add(c4);
        list7.add(c5);
        list7.add(c6);
        list7.add(c7);
        list7.add(c8);
        list7.add(c9);
        list7.add(c10);
        list7.add(c11);
        list7.add(c12);
        list7.add(c13);
        list7.add(c14);
    }

    private void setAdapter8() {

        ChapterAdapter adapter = new ChapterAdapter(this, list8);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list8.get(position).getChp();
                String chpnum = list8.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","8");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData8() {

        chapter c1 = new chapter("Resourses and Development", "1");
        chapter c2 = new chapter("Forest and Wildlife Resources", "2");
        chapter c3 = new chapter("Water Resources", "3");
        chapter c4 = new chapter("Agriculture", "4");
        chapter c5 = new chapter("Minerals and Energy Resources", "5");
        chapter c6 = new chapter("Manufacturing Industries", "6");
        chapter c7 = new chapter("Life Lines of National Economy", "7");

        list8.add(c1);
        list8.add(c2);
        list8.add(c3);
        list8.add(c4);
        list8.add(c5);
        list8.add(c6);
        list8.add(c7);
    }

    private void setAdapter9() {

        ChapterAdapter adapter = new ChapterAdapter(this, list9);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list9.get(position).getChp();
                String chpnum = list9.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","9");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData9() {

        chapter c1 = new chapter("Development", "1");
        chapter c2 = new chapter("Sectors of the Economy", "2");
        chapter c3 = new chapter("Money and Credit", "3");
        chapter c4 = new chapter("Globalisation and the Indian Economy", "4");
        chapter c5 = new chapter("Consumer Rights", "5");

        list9.add(c1);
        list9.add(c2);
        list9.add(c3);
        list9.add(c4);
        list9.add(c5);

    }

    private void setAdapter10() {

        ChapterAdapter adapter = new ChapterAdapter(this, list10);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list10.get(position).getChp();
                String chpnum = list10.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","10");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData10() {

        chapter c1 = new chapter("The Rise of Nationalism in Europe", "1");
        chapter c2 = new chapter("The Nationalist Movement in Indo-China", "2");
        chapter c3 = new chapter("Nationalism in India?", "3");
        chapter c4 = new chapter("The Making of a Global World", "4");
        chapter c5 = new chapter("The Age of Industrialization", "5");
        chapter c6 = new chapter("Work, Life and Leisure", "6");
        chapter c7 = new chapter("Print Culture and the Modern World", "7");
        chapter c8 = new chapter("Novels, Society and History", "8");

        list10.add(c1);
        list10.add(c2);
        list10.add(c3);
        list10.add(c4);
        list10.add(c5);
        list10.add(c6);
        list10.add(c7);
        list10.add(c8);

    }

    private void setAdapter11() {

        ChapterAdapter adapter = new ChapterAdapter(this, list11);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list11.get(position).getChp();
                String chpnum = list11.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","11");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData11() {

        chapter c1 = new chapter("Power Sharing", "1");
        chapter c2 = new chapter("Federalism", "2");
        chapter c3 = new chapter("Democracy and Diversity", "3");
        chapter c4 = new chapter("Gender, Religion and Caste", "4");
        chapter c5 = new chapter("Popular Struggles and Movements", "5");
        chapter c6 = new chapter("Political Parties", "6");
        chapter c7 = new chapter("Outcomes of Democracy", "7");

        list11.add(c1);
        list11.add(c2);
        list11.add(c3);
        list11.add(c4);
        list11.add(c5);
        list11.add(c6);
        list11.add(c7);

    }

    private void setAdapter13() {

        ChapterAdapter adapter = new ChapterAdapter(this, list13);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list13.get(position).getChp();
                String chpnum = list13.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","13");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData13() {

        chapter c1 = new chapter("कबीर - साखी", "1");
        chapter c2 = new chapter("मीरा - पद", "2");
        chapter c3 = new chapter("बिहारी - दोहे", "3");
        chapter c4 = new chapter("मैथिलीशरण गुप्त-  मनुष्यता", "4");
        chapter c5 = new chapter("सुमित्रानंदन पंत - पर्वत प्रदेश में पावस", "5");
        chapter c6 = new chapter("महादेवी वर्मा - मधुर-मधुर मेरे दीपक जल", "6");
        chapter c7 = new chapter("वीरेन डंगवाल - तोप", "7");
        chapter c8 = new chapter("कैफ़ी आजमी - कर चले हम फ़िदा", "8");
        chapter c9 = new chapter("रविंद्रनाथ ठाकुर - आत्मत्रण", "9");
        chapter c10 = new chapter("प्रेमचंद - बड़े भाई साहब", "10");
        chapter c11 = new chapter("सीताराम सेकसरिया - डायरी का एक पन्ना", "11");
        chapter c12 = new chapter("लीलाधर मंडलोई - तताँरा-वामीरो कथा", "12");
        chapter c13 = new chapter("प्रहलाद अग्रवाल - तीसरी कसम के शिल्पकार शैलेन्द्र", "13");
        chapter c14 = new chapter("अंतोन चेखव - गिरगिट", "14");
        chapter c15 = new chapter("निदा फाजली - अब कहाँ दूसरे के दुःख से दुखी होने वाले", "15");
        chapter c16 = new chapter("रवींद्र केलेकर - पतझर में टूटी पत्तियां", "16");
        chapter c17 = new chapter("हबीब तनवीर - कारतूस (एकांकी)", "17");

        list13.add(c1);
        list13.add(c2);
        list13.add(c3);
        list13.add(c4);
        list13.add(c5);
        list13.add(c6);
        list13.add(c7);
        list13.add(c8);
        list13.add(c9);
        list13.add(c10);
        list13.add(c11);
        list13.add(c12);
        list13.add(c13);
        list13.add(c14);
        list13.add(c15);
        list13.add(c16);
        list13.add(c17);

    }

    private void setAdapter12() {

        ChapterAdapter adapter = new ChapterAdapter(this, list12);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list12.get(position).getChp();
                String chpnum = list12.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","12");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData12() {

        chapter c1 = new chapter("उधौ ", "1");
        chapter c2 = new chapter("राम-लक्ष्मण-परशुराम-संवाद", "2");
        chapter c3 = new chapter("पाँयनि नूपुर", "3");
        chapter c4 = new chapter("आत्मकथ्य", "4");
        chapter c5 = new chapter("उत्साह", "5");
        chapter c6 = new chapter("यह दंतुरित मुस्कान फसल", "6");
        chapter c7 = new chapter("छाया मत छूना", "7");
        chapter c8 = new chapter("कन्यादान", "8");
        chapter c9 = new chapter("संगतकार", "9");
        chapter c10 = new chapter("नेताजी का चश्मा", "10");
        chapter c11 = new chapter("बालगोबिन भगत", "11");
        chapter c12 = new chapter("लखनवी अंदाज़", "12");
        chapter c13 = new chapter("मानवीय करुणा की दिव्या चमक", "13");
        chapter c14 = new chapter("एक कहानी यह भी", "14");
        chapter c15 = new chapter("स्त्री-शिक्षा के विरोधीकुरातकों का खंडन", "15");
        chapter c16 = new chapter("नौबतखाने में इबादत", "16");
        chapter c17 = new chapter("संस्कृति", "17");

        list12.add(c1);
        list12.add(c2);
        list12.add(c3);
        list12.add(c4);
        list12.add(c5);
        list12.add(c6);
        list12.add(c7);
        list12.add(c8);
        list12.add(c9);
        list12.add(c10);
        list12.add(c11);
        list12.add(c12);
        list12.add(c13);
        list12.add(c14);
        list12.add(c15);
        list12.add(c16);
        list12.add(c17);

    }

    private void setAdapter14() {

        ChapterAdapter adapter = new ChapterAdapter(this, list14);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list14.get(position).getChp();
                String chpnum = list14.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","14");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData14() {

        chapter c1 = new chapter("माता का अँचल", "1");
        chapter c2 = new chapter("जॉर्ज पंचम की नाक", "2");
        chapter c3 = new chapter("साना-साना हाथ जोड़ि", "3");
        chapter c4 = new chapter("एही ठैया झुलनी हेरानी हो रामा!", "4");
        chapter c5 = new chapter("मैं क्या लिखता हूँ?", "5");

        list14.add(c1);
        list14.add(c2);
        list14.add(c3);
        list14.add(c4);
        list14.add(c5);

    }

    private void setAdapter15() {

        ChapterAdapter adapter = new ChapterAdapter(this, list15);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chpName = list15.get(position).getChp();
                String chpnum = list15.get(position).getNum();
                Intent intent = new Intent(ChapterActivity.this,PdfActivity.class);
                intent.putExtra("chapter",chpName);
                intent.putExtra("book","15");
                intent.putExtra("number",chpnum);
                startActivity(intent);
            }
        });
    }

    private void setData15() {

        chapter c1 = new chapter("हरिहर काका", "1");
        chapter c2 = new chapter("सपनों के-से दिन", "2");
        chapter c3 = new chapter("टोपी शुक्ला", "3");

        list15.add(c1);
        list15.add(c2);
        list15.add(c3);
    }


}