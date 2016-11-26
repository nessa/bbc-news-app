package com.example.noeliasales.bbcnews.models;


import android.util.Log;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Root(name = "item", strict = false)
public class NewsItem {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    @Element(name = "title")
    public String title;

    @Element(name = "description")
    public String description;

    @Element(name = "link")
    public String link;

    @Validate
    @Element(name = "pubDate")
    private String mPubDate;

    public Date date;

    @Element
    public Thumbnail thumbnail;

    @Commit
    private void parseDate() {
        if (mPubDate != null) {
            try {
                date = SDF.parse(mPubDate);
            } catch (ParseException e) {
                Log.d("ADAPTER", "ERROR "+e.getMessage());
            } finally {
                mPubDate = null;
            }
        }
    }
}