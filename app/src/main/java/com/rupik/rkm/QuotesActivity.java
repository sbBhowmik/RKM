package com.rupik.rkm;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {

    ArrayList<Quote> quotesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        fetchQuoteJson(); //// TODO: 12/01/17
        displayQuote();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return true;
    }

    String quotesJsonString = "";
    void fetchQuoteJson()
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String urlStr = "https://quarkbackend.com/getfile/sohambhowmik/quotes";
                    quotesJsonString = "";

                    // Create a URL for the desired page
                    URL url = new URL(urlStr);

                    // Read all the text returned by the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String str;

                    while ((str = in.readLine()) != null) {
                        Log.d("Str",str);
                        quotesJsonString = quotesJsonString+str;
                    }
                    in.close();

                } catch (MalformedURLException e) {
                    Log.d("MalformedURLException", e.getLocalizedMessage());
                } catch (IOException e) {
                    Log.d("IOERR", e.getLocalizedMessage());
                }
            }
        });
    }

    void displayQuote()
    {
        quotesArr = new ArrayList<>();
        String jsontext = "";
        try {
            if(quotesJsonString.length()>0)
            {
                jsontext = quotesJsonString;
            }
            else {
                InputStream is = this.getResources().openRawResource(R.raw.quotes);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1) ;
                jsontext = new String(buffer);
            }

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

        if(qCount==quotesArr.size()-1)
        {
            qCount=0;
        }
        else {
            qCount++;
        }

        SharedPreferences.Editor spEditor = sp.edit();

        spEditor.putInt("Quote_Number",qCount);
        spEditor.commit();

        ListView qListView = (ListView) findViewById(R.id.quotesListView);
        QuotesListAdapter adapter = new QuotesListAdapter(this, quotesArr);
        qListView.setAdapter(adapter);
    }
}
