package com.example.kimvui.truyentranh;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static List<Comic> comicsList=new ArrayList<>();
    public static Comic comicSelected;
    public static List<Chapter> chapterList;
    public static Chapter chapterSelected;
    public static int chapterIndex=-1;

    public static String formatString(String name) {
        StringBuilder stringBuilder=new StringBuilder(name.length()> 15? name.substring(0,15)+"...":name);
        return stringBuilder.toString();
    }
    }
