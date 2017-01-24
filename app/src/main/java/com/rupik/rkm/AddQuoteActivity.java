package com.rupik.rkm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddQuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        ImageButton backBtn = (ImageButton)findViewById(R.id.customBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton changeLanguageBtn = (ImageButton)findViewById(R.id.changeLanguageBtn);
        changeLanguageBtn.setVisibility(View.GONE);
        ImageButton addCommentBtn = (ImageButton)findViewById(R.id.commentBtn);
        addCommentBtn.setVisibility(View.GONE);

        Button postquoteBtn = (Button)findViewById(R.id.postQuoteBtn);
        postquoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        String[] TO = {"sohambhowmik@gmail.com"};
        String[] CC = {"shreya.coolone@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RKM Add Quote");

        EditText authorET = (EditText)findViewById(R.id.authorET);
        EditText quoteET = (EditText)findViewById(R.id.quoteET);

        if(authorET.getText().toString().length() == 0 || quoteET.getText().toString().length() == 0)
        {
            Toast.makeText(this, "Please Enter Both the Author Name and The Quotation" , Toast.LENGTH_LONG).show();
            return;
        }

        String mailBody = "Author: " + authorET.getText().toString() + "    Quote: " + quoteET.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_TEXT, mailBody);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AddQuoteActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
