package com.example.kimvui.truyentranh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChaptersActivity extends AppCompatActivity {
    RecyclerView recyclerView_chapter;
    TextView number_chapter;
    LinearLayoutManager layoutManager;
    List<Chapter> chapterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapters_activity);
        recyclerView_chapter = (RecyclerView) findViewById(R.id.recycler_chapter);
        number_chapter = (TextView) findViewById(R.id.number_chapter);


        layoutManager = new LinearLayoutManager(this);
        recyclerView_chapter.setHasFixedSize(true);
        recyclerView_chapter.setLayoutManager(layoutManager);
        recyclerView_chapter.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        getChapters(Common.comicSelected);

    }

    private void getChapters(Comic comicSelectes) {
        Common.chapterList = comicSelectes.Chapters;
        recyclerView_chapter.setAdapter(new MyChapterAdapter(this,comicSelectes.Chapters));
        number_chapter.setText(new StringBuilder("Số chương (")
                .append(comicSelectes.Chapters.size())
                .append(")"));
    }
}
