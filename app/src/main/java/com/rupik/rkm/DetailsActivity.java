package com.rupik.rkm;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity{

    ArrayList<SaintDetails> detailsArrayList;
    int pageNumber = 0;
    int lang_prefs; //1=Eng ; -1=Beng

    String eng_src_file_name;
    String bengali_src_file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String buttonType = extras.getString("buttonType");
        switch (buttonType)
        {
            case "SriRamakrishna":
                eng_src_file_name = "ramakrishna";
                bengali_src_file_name = "ramakrishna_bengali";
                break;

            case "Swamiji":
                eng_src_file_name = "swami_vivekanenda";
                bengali_src_file_name = "swami_vivekanenda_bengali";
                break;

            case "MaaSaradaDevi":
                eng_src_file_name = "sarada_devi";
                bengali_src_file_name = "sarada_devi_bengali";
                break;
        }

        SharedPreferences sp = this.getSharedPreferences("prefs",MODE_PRIVATE);
        lang_prefs = sp.getInt("lang_prefs",1);
        displayData();

        Button prevButton = (Button)findViewById(R.id.prev_page_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber-1>=0)
                {
                    pageNumber-=1;
                    updateUI();
                }
            }
        });

        Button nextButton = (Button)findViewById(R.id.next_page_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber+1<detailsArrayList.size())
                {
                    pageNumber+=1;
                    updateUI();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.language_selection, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.lang_sel_eng:
                if(lang_prefs==-1) {
                    lang_prefs=1;
                    displayData();
                }
                break;
            case R.id.lang_sel_bengali:
                if(lang_prefs==1) {
                    lang_prefs=-1;
                    displayData();
                }
                break;
        }

        SharedPreferences sp = this.getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putInt("lang_prefs",lang_prefs);
        spEditor.commit();
        return true;
    }





    void displayData()
    {
        pageNumber = 0;

        detailsArrayList = new ArrayList<>();

        String PACKAGE_NAME = getApplicationContext().getPackageName();

        int fileName;
        if(lang_prefs==1)
        {
            fileName = getResources().getIdentifier(PACKAGE_NAME+":raw/"+eng_src_file_name , null, null);
        }
        else
        {
            fileName = getResources().getIdentifier(PACKAGE_NAME+":raw/"+bengali_src_file_name , null, null);
        }

        try {

            //https://lookaside.fbsbx.com/file/ramakrishna.json?token=AWwOE5j3_xzOAqUXcGp0I8fHut1nt-Ljh8LCi6Gi4LBvfeYLBcaYbkeyEDYxAybhpw8nx1X_tDY4MKxMSemIdFylZrFzsnblkLAX8HftzInGrM8dyiA2R09-3dJt7WPMjxgDkzlPbNj9IggysOi_A59C


//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        // Create a URL for the desired page
//                        URL url = new URL("https://drive.google.com/open?id=0B2H6wfbSDsXZYjh2ejBwcVNBTW8");
//
//                        // Read all the text returned by the server
//                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//                        String str;
//                        while ((str = in.readLine()) != null) {
//                            Log.d("Str",str);
//                        }
//                        in.close();
//                    } catch (MalformedURLException e) {
//                        Log.d("MalformedURLException", e.getLocalizedMessage());
//                    } catch (IOException e) {
//                        Log.d("IOERR", e.getLocalizedMessage());
//                    }
//                }
//            });


            InputStream is = this.getResources().openRawResource(fileName);

            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;
            String jsontext = new String(buffer);
            JSONArray jArray = new JSONArray(jsontext);
            for(int i=0;i<jArray.length();i++)
            {
                JSONObject jObj = jArray.getJSONObject(i);
                String personTitle = jObj.optString("person_title");
                String page_title = jObj.optString("page_title");
                String page_subtitle = jObj.optString("page_subtitle");
                String image = jObj.optString("image");
                String main_content = jObj.optString("main_content");
                String id = jObj.optString("id");

                SaintDetails details = new SaintDetails(id, personTitle, page_title, page_subtitle, image, false, main_content);
                detailsArrayList.add(details);
            }

            if(detailsArrayList.size()>0)
                updateUI();
        }
        catch (Exception e)
        {

        }
    }

    void updateUI()
    {
        validateButtons();

        TextView pageNumberTV = (TextView)findViewById(R.id.pageNumber);
        String pageNoText = "Page "+ Integer.toString(pageNumber+1) + " of " + Integer.toString(detailsArrayList.size());
        pageNumberTV.setText(pageNoText);

        SaintDetails saintDetails = detailsArrayList.get(pageNumber);

        //There should always be a Person Title
        TextView personTV = (TextView)findViewById(R.id.person_title);
        personTV.setText(saintDetails.getPersonTitle());

        TextView pageTitle = (TextView)findViewById(R.id.page_title);
        if(saintDetails.getPage_title().contains("none")||saintDetails.getPage_title().length()==0) {
            pageTitle.setVisibility(View.GONE);
        }
        else {
            pageTitle.setVisibility(View.VISIBLE);
            pageTitle.setText(saintDetails.getPage_title());
        }

        TextView pageSubTitle = (TextView)findViewById(R.id.page_subtitle);
        if(saintDetails.getPage_subtitle().contains("none")||saintDetails.getPage_subtitle().length()==0)
        {
            pageSubTitle.setVisibility(View.GONE);
        }
        else {
            pageSubTitle.setVisibility(View.VISIBLE);
            pageSubTitle.setText(saintDetails.getPage_subtitle());
        }

        //There always has to be a main content. Without it the Details Page has no relevance
        TextView mainContent = (TextView)findViewById(R.id.page_content);
        mainContent.setText(saintDetails.getMain_content());

        ImageView imageView = (ImageView) findViewById(R.id.page_imageview);
        String imageName = saintDetails.getImage();
        if(imageName.contains("none")||imageName.length()==0)
        {
            imageView.setVisibility(View.GONE);
        }
        else {
            imageView.setVisibility(View.VISIBLE);
            String PACKAGE_NAME = getApplicationContext().getPackageName();
            int imgId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imageName, null, null);

            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), imgId));
        }

        //scroll the page to top
        ScrollView scrollView = (ScrollView)findViewById(R.id.details_scrollview);
        scrollView.scrollTo(0,0);
    }

    void validateButtons()
    {
        Button prevButton = (Button)findViewById(R.id.prev_page_button);
        Button nextButton = (Button)findViewById(R.id.next_page_button);
        if(pageNumber==0)
        {
            prevButton.setEnabled(false);
        }
        else {
            prevButton.setEnabled(true);
        }
        if(pageNumber+1==detailsArrayList.size())
        {
            nextButton.setEnabled(false);
        }
        else {
            nextButton.setEnabled(true);
        }
    }


}
