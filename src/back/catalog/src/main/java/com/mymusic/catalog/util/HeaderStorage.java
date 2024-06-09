package com.mymusic.catalog.util;

import org.springframework.stereotype.Component;

@Component
public class HeaderStorage {
    private static final ThreadLocal<String> myHeader = new ThreadLocal<>();

    public static void setMyHeader(String headerValue) {
        myHeader.set(headerValue);
    }

    public static String getMyHeader() {
        return myHeader.get();
    }

    public static void clear() {
        myHeader.remove();
    }
}
