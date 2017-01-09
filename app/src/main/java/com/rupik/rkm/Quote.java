package com.rupik.rkm;

/**
 * Created by macmin5 on 25/11/16.
 */

public class Quote {
    String quote;
    String author;

    Quote(String quoteStr, String authorStr)
    {
        quote = quoteStr;
        author = authorStr;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
