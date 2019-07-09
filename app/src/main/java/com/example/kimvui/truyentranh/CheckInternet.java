package com.example.kimvui.truyentranh;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class CheckInternet extends Application {
    static CheckInternet internetInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        internetInstance=this;

    }
    public static synchronized CheckInternet getInstance() {
        return internetInstance;
    }
}
