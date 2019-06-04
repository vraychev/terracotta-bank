package com.joshcummings.codeplay.terracotta.defense.http.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Sanitizer {
    public static String sanitize(String unsafe) {
        return Jsoup.clean(unsafe, Whitelist.basic());
    }
}
