package com.rupik.rkm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // Quotes URL --- https://api.myjson.com/bins/1d9obn
    //https://quarkbackend.com/myfiles
    //https://quarkbackend.com/getfile/sohambhowmik/quotes

    static int image_index = 1;
    final  int MAX_SCENES = 9;


    String buttonType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        changeQuote();
        changeHomeWall();


        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      Handler handler = new Handler(Looper.getMainLooper());
                                      handler.postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              changeHomeWall();
                                          }
                                      },100);

                                  }

                              },
                60000,
                60000);

        Timer t_quotes = new Timer();
        t_quotes.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      Handler handler = new Handler(Looper.getMainLooper());
                                      handler.postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              changeQuote();
                                          }
                                      },100);

                                  }

                              },
                60000,
                60000);

        ImageButton ThakurButton = (ImageButton)findViewById(R.id.thakurButton);
        ThakurButton.setOnClickListener(buttonOnClickListener);
        ImageButton swamijiButton = (ImageButton)findViewById(R.id.swamijiButton);
        swamijiButton.setOnClickListener(buttonOnClickListener);
        ImageButton maaButton = (ImageButton)findViewById(R.id.maaButton);
        maaButton.setOnClickListener(buttonOnClickListener);
    }

    View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, DetailsActivity.class);
            switch(v.getId()) {
                case R.id.thakurButton:
                    i.putExtra("buttonType", "SriRamakrishna");
                    break;
                case R.id.swamijiButton:
                    i.putExtra("buttonType", "Swamiji");
                    break;
                case R.id.maaButton:
                    i.putExtra("buttonType", "MaaSaradaDevi");
                    break;
            }

            MainActivity.this.startActivity(i);
        }
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_main_drawer_test, menu);
//        return true;
//    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

            return super.onPrepareOptionsMenu(menu);
}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean shouldCloseMenu = true;

//        if(menuSectionSelected == 1 && id == R.id.menu_monastic_disciples)
//        {
//            shouldCloseMenu = false;
//            menuSectionSelected = -1;
//            invalidateOptionsMenu();
//        }
//        else if(id == R.id.menu_monastic_disciples)
//        {
//            shouldCloseMenu = false;
//            menuSectionSelected = 1;
//            invalidateOptionsMenu();
//        }

        if(id == R.id.monasticDisciplesBtn)
        {
            Intent i = new Intent(this, DisciplesListActivity.class);
            startActivity(i);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



    Quote fetchQuote()
    {
        ArrayList<Quote> quotesArr = new ArrayList<>();


        InputStream is = this.getResources().openRawResource(R.raw.quotes);

        try {
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;
            String jsontext = new String(buffer);
            JSONArray jArray = new JSONArray(jsontext);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);

                String quote = jObj.optString("quote");
                String author = jObj.optString("author");

                Quote quoteObj = new Quote(quote,author);

                quotesArr.add(quoteObj);
            }
        }
        catch (Exception e)
        {

        }

        SharedPreferences sp = this.getSharedPreferences("RKM_Prefs", MODE_PRIVATE);
        int qCount = sp.getInt("Quote_Number", -1);

        qCount++;
        Quote quote = quotesArr.get(qCount);

        if(qCount<quotesArr.size()-1)
        {
            qCount=0;
        }
        else {
            qCount++;
        }

        SharedPreferences.Editor spEditor = sp.edit();

        spEditor.putInt("Quote_Number",qCount);
        spEditor.commit();

        return quote;
    }

    //---------------===========================------------------

    void changeHomeWall()
    {
        SharedPreferences sp = this.getSharedPreferences("RKM_Prefs", MODE_PRIVATE);
        int homeWallIndex = sp.getInt("Home_Wall_Index",1);


        String imgName = "scene_"+homeWallIndex;
        homeWallIndex++;
        if(homeWallIndex > MAX_SCENES)
        {
            homeWallIndex = 1;
        }

        SharedPreferences.Editor spEditor = sp.edit();

        spEditor.putInt("Home_Wall_Index",homeWallIndex);
        spEditor.commit();

        String PACKAGE_NAME = getApplicationContext().getPackageName();
        final int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+imgName , null, null);

        final ImageView homeWallIV = (ImageView)findViewById(R.id.homeWallIV);
        homeWallIV.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));


//        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
//        homeWallIV.startAnimation(fadeIn);
//        fadeIn.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
////                homeWallIV.setImageResource(R.drawable.scene_2);
//                homeWallIV.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
//                homeWallIV.startAnimation(fadeOut);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }

    void changeQuote()
    {
        Quote quote = fetchQuote();
        TextView quoteTv = (TextView) findViewById(R.id.quoteTV);
        TextView authorTV = (TextView) findViewById(R.id.authorTV);

        quoteTv.setText(quote.getQuote());
        authorTV.setText(quote.getAuthor());
    }

}
