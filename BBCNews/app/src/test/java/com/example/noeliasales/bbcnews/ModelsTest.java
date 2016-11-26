package com.example.noeliasales.bbcnews;


import com.example.noeliasales.bbcnews.models.NewsItem;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class ModelsTest {

    @Test
    public void parseDateIsCorrect() throws Exception {
        NewsItem item = new NewsItem();

        Field pubDateField = item.getClass().getDeclaredField("mPubDate");
        pubDateField.setAccessible(true);
        pubDateField.set(item, "Fri, 25 Nov 2016 01:11:01 GMT");

        Method parseDateMethod =  item.getClass().getDeclaredMethod("parseDate");
        parseDateMethod.setAccessible(true);
        parseDateMethod.invoke(item);

        String dateString = "" + item.date.getTime();
        assertEquals(dateString, "1480036261000");
    }
}
