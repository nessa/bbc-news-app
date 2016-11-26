package com.example.noeliasales.bbcnews.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "rss", strict = false)
public class News {

    @Element(name = "channel")
    public Channel channel;
}