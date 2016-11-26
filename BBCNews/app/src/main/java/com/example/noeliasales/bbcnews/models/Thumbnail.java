package com.example.noeliasales.bbcnews.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(name = "media:thumbnail", strict = false)
public class Thumbnail {

    @Attribute(name = "url")
    public String url;
}
