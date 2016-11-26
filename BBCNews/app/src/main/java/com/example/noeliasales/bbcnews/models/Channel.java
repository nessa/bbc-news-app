package com.example.noeliasales.bbcnews.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(name = "channel", strict = false)
public class Channel {

    @ElementList(name = "item", inline = true)
    public List<NewsItem> newsItems;
}
